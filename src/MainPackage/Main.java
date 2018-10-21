package MainPackage;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Main {
	public static Robot robot;
	public static UI userInterface;
	private static Rectangle r;
	private static SimpleDateFormat fmt;
	private static String datePostfix;
	private static Color nominalColor;
	private static BufferedImage selectedImage;
	private static String osName;

	public static void main(String[] args) {
		new Main();
	}

	
	public Main() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		userInterface = new UI();
		userInterface.setVisible(true);
	    osName = System.getProperty("os.name").toString();
	}

	public static void command(String[] values) {
		try {
			if (values[0].equals("MoveTo")) {
				robot.mouseMove(Integer.parseInt(values[1]),
						Integer.parseInt(values[2]));
			} else if (values[0].equals("Delay")) {
				robot.delay(Integer.parseInt(values[1]));
			} else if (values[0].equals("LeftClick")) {
				for (int i = 0; i < Integer.parseInt(values[1]); i++) {
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
				}
			} else if (values[0].equals("LeftDown")) {
				for (int i = 0; i < Integer.parseInt(values[1]); i++) {
					robot.mousePress(InputEvent.BUTTON1_MASK);
				}
			} else if (values[0].equals("LeftUp")) {
				for (int i = 0; i < Integer.parseInt(values[1]); i++) {
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
				}
			} else if (values[0].equals("RightClick")) {
				for (int i = 0; i < Integer.parseInt(values[1]); i++) {
					robot.mousePress(InputEvent.BUTTON3_MASK);
					robot.mouseRelease(InputEvent.BUTTON3_MASK);
				}
			} else if (values[0].equals("RightDown")) {
				for (int i = 0; i < Integer.parseInt(values[1]); i++) {
					robot.mousePress(InputEvent.BUTTON3_MASK);
				}
			} else if (values[0].equals("RightUp")) {
				for (int i = 0; i < Integer.parseInt(values[1]); i++) {
					robot.mouseRelease(InputEvent.BUTTON3_MASK);
				}
			} else if (values[0].equals("ScreenShot")) {
				r = new Rectangle();
				r.setBounds(Integer.parseInt(values[1]),
						Integer.parseInt(values[2]),
						Integer.parseInt(values[3]),
						Integer.parseInt(values[4]));
				r.setBounds(
						Integer.parseInt(values[1]),
						Integer.parseInt(values[2]),
						Math.abs(Integer.parseInt(values[1])
								- Integer.parseInt(values[3])),
						Math.abs(Integer.parseInt(values[2])
								- Integer.parseInt(values[4])));

				BufferedImage screencapture = robot.createScreenCapture(r);

				fmt = new SimpleDateFormat("HHmmss-SSS");
				datePostfix = fmt.format(new Date());

				File file = new File(UI.file.getParent(), "ScreenCapture_"
						+ datePostfix + ".jpg");
				try {
					ImageIO.write(screencapture, "jpg", file);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (values[0].equals("ComparePixel")) {
				nominalColor = new Color(userInterface.r, userInterface.g,
						userInterface.b);
				if (checkSameColor(nominalColor, robot.getPixelColor(
						Integer.parseInt(values[1]),
						Integer.parseInt(values[2])))) {
					try {
						new SoundUtils(500, 50, 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (values[0].equals("CompareArea")) {
				Rectangle selected;
				Point pt1 = userInterface.pt1;
				Point pt2 = userInterface.pt2;
				if (userInterface.compSameArea.isSelected()) {
					selected = new Rectangle(pt1.x, pt1.y,
							(Integer) Math.abs(pt1.x - pt2.x),
							(Integer) Math.abs(pt1.y - pt2.y));
					imagineComparePrep(selected);
				} else {
					int x1 = Integer.parseInt(values[1]);
					int y1 = Integer.parseInt(values[2]);
					int x2 = Integer.parseInt(values[3]);
					int y2 = Integer.parseInt(values[4]);
					int w1 = (Integer) (Math.abs(x1 - x2));
					int h1 = (Integer) (Math.abs(y1 - y2));
					selected = new Rectangle(x1, y1, w1, h1);
					if (w1 == userInterface.nominalImage.getWidth()
							&& h1 == userInterface.nominalImage.getHeight()) {
						imagineComparePrep(selected);
					} else {
						JOptionPane
								.showMessageDialog(
										userInterface,
										"'CompareArea' Script Syntax Error\n(Press OK to Exit) ",
										"Script Syntax Error!",
										JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else if (values[0].equals("KeyType")) {
				for (int i = 0; i < Integer.parseInt(values[2]); i++) {
					StringSelection stsel = new StringSelection(values[1]);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel,stsel);
					boolean isWindows = osName.startsWith("windows");
					boolean isLinux   = osName.startsWith("linux"); 
					boolean isMac   = osName.startsWith("Mac"); 
					
					if (isMac){
						robot.keyPress(KeyEvent.VK_META);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_META);
						robot.keyRelease(KeyEvent.VK_V);
					}else{
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_V);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(userInterface,
					"Script Syntax Error\n(Script will continue) ",
					"Script Syntax Error!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static void imagineComparePrep(Rectangle selected) {
		selectedImage = robot.createScreenCapture(selected);
		ImageComparer cp = new ImageComparer(selectedImage,
				userInterface.nominalImage, userInterface.tolerance);
		if (cp.isSame()) {
			try {
				new SoundUtils(1500, 0, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				new SoundUtils(500, 50, 5);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Exist on Different Option
			if (userInterface.existOnNotSame.isSelected()) {
				JOptionPane
						.showMessageDialog(
								userInterface,
								"Compare Area Result: Not Same\n(Script will continue) ",
								"Compare Area Result!",
								JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	private static boolean checkSameColor(Color c1, Color c2) {
		if (c1.equals(c2))
			return true;
		return false;
	}

}
