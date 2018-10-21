package MainPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import AbstractActions.ColorPicker;
import AbstractActions.MousePositionAction;
import AbstractActions.ScreenCapturePointOne;
import AbstractActions.ScreenCapturePointTwo;
import AbstractActions.ScriptLoadingActions;

public class UI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final int UI_WIDTH = 295; // 510
	private final int UI_HEIGHT = 175;
	
	private boolean isRGBReady;
	private boolean isAreaReady;
	
	public static File file;
	private JFileChooser fc;
	private JButton loadScript;
	private JButton loadNomialRBGVal;
	
	private JButton mode;
	private JButton LoadNominalArea;

	public JTextField rVal;
	public JTextField gVal;
	public JTextField bVal;
	private JTextField tVal;
	
	public JTextField xVal;
	public JTextField yVal;
	public JTextField x1Val;
	public JTextField y1Val;
	
	public JCheckBox existOnNotSame;
	public JCheckBox compSameArea;
	
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;
	private JLabel l7;
	private JLabel l8;
	
	private JMenuBar menuBar;
	private JMenu menu;

	public static int programMode;
	public int r;
	public int g;
	public int b;
	public int tolerance;
	
	public BufferedImage nominalImage;

	public Point pt1;
	public Point pt2;
	private JMenuItem aboutAuthor;
	private JMenuItem aboutProgram;
	
	public UI() {

		isRGBReady = false;
        isAreaReady = false;
		programMode = 1;

		this.setTitle("Auto Command By Chengming");
		this.setLayout(null);
		this.setBounds(100, 100, UI_WIDTH, UI_HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		// this.addKeyListener(this);
		this.repaint();

		JPanel content = (JPanel) this.getContentPane();

		InputMap inputMap = content
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "loadScript");
		content.getActionMap().put("loadScript", new ScriptLoadingActions());

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "ColorPicker");
		content.getActionMap().put("ColorPicker", new ColorPicker());

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "position");
		content.getActionMap().put("position", new MousePositionAction());
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "Point1");
		content.getActionMap().put("Point1", new ScreenCapturePointOne());
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "Point2");
		content.getActionMap().put("Point2", new ScreenCapturePointTwo());

		fc = new JFileChooser();
		fc.setDialogTitle("Select a script to open");
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(new FileNameExtensionFilter("KevinScript",
				"kScript"));

		
		initJButton();
		initJTextField(90,68);
		initJLabel(90,50);
		
		existOnNotSame = new JCheckBox("EOD Mode");
		existOnNotSame.setBounds(0, 50, 100, 50);
		existOnNotSame.setSelected(true);
		this.add(existOnNotSame);
		
		compSameArea = new JCheckBox("CSA Mode");
		compSameArea.setBounds(0, 80, 100, 50);
		compSameArea.setSelected(true);
		this.add(compSameArea);
		
		menuBar = new JMenuBar();
		
		menu =  new JMenu("About");
		
		
		aboutAuthor = new JMenuItem("About Author");
		aboutAuthor.addActionListener(this);
		aboutProgram = new JMenuItem("About Program");
		aboutProgram.addActionListener(this);
		menu.add(aboutAuthor);
		menu.add(aboutProgram);
		
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadScript) {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				this.repaint();
				JOptionPane.showMessageDialog(this,
						"File Read Success, Press F1 to begain", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
				rVal.setEnabled(true);
				gVal.setEnabled(true);
				bVal.setEnabled(true);
				tVal.setEnabled(true);
				xVal.setEnabled(true);
				yVal.setEnabled(true);
				x1Val.setEnabled(true);
				y1Val.setEnabled(true);
				mode.setEnabled(true);
				l1.setEnabled(true);
				l2.setEnabled(true);
				l3.setEnabled(true);
				l4.setEnabled(true);
				l5.setEnabled(true);
				l6.setEnabled(true);
				l7.setEnabled(true);
				l8.setEnabled(true);
				loadNomialRBGVal.setEnabled(true);
				LoadNominalArea.setEnabled(true);
				this.repaint();
			} else {
				JOptionPane.showMessageDialog(this, "File Read Fail",
						"Failed!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		if (e.getSource() == mode) {
			ToggleMode();
		}
		
		if (e.getSource() == LoadNominalArea) {
			loadNomialArea();
		}
		
		if (e.getSource() == loadNomialRBGVal) {
			loadNomialRBG();		
		}
		
		if (e.getSource() == aboutAuthor) {
			JOptionPane.showMessageDialog(this, "Copyright (c) 2017\nChengming Zhang, All rights reserved.",
					"About the Author", JOptionPane.INFORMATION_MESSAGE);		
		}
		
		if (e.getSource() == aboutProgram) {
			JOptionPane.showMessageDialog(this, "This is a program that can help people\nautomate their computer experience by\n"
					+ "excute a pre-constructed .kScript file\nin a particular format. This program\nwill read the .kScript file"
					+ " and excute\nthe command line by line",
					"About the Program", JOptionPane.INFORMATION_MESSAGE);	
		}


	}

	private void loadNomialRBG() {
		if (isRGBValid(rVal.getText()) && isRGBValid(gVal.getText())
				&& isRGBValid(bVal.getText()) && isRGBValid(tVal.getText())) {
			r = Integer.parseInt(rVal.getText());
			g = Integer.parseInt(gVal.getText());
			b = Integer.parseInt(bVal.getText());
			tolerance = Integer.parseInt(tVal.getText());
			isRGBReady = true;			
		}else{
			showRGBError();
			isRGBReady = false;
		}		
	}

	private void loadNomialArea() {
		if (isAreaValid(xVal.getText()) && isAreaValid(yVal.getText())
				&& isAreaValid(x1Val.getText()) && isAreaValid(y1Val.getText())) {
			pt1 = new Point(Integer.parseInt(xVal.getText()),Integer.parseInt(yVal.getText()));
			pt2 = new Point(Integer.parseInt(x1Val.getText()),Integer.parseInt(y1Val.getText()));
			try{
				Rectangle r = new Rectangle(pt1.x,pt1.y,(Integer)Math.abs(pt1.x-pt2.x),(Integer)Math.abs(pt1.y-pt2.y));
				nominalImage = Main.robot.createScreenCapture(r);
				isAreaReady =  true;
			}catch (IllegalArgumentException x){
				JOptionPane.showMessageDialog(this,
						"Rectangle width and height must be > 0", "Failed!",
						JOptionPane.ERROR_MESSAGE);
				isAreaReady =  false;
			}				
		}else{
			 showAreaError();
			 isAreaReady = false;
		}
	}

	public static boolean isRGBValid(String str) {
		int d;
		try {
			d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		if (d <= 255 && d >= 0)
			return true;
		else
			return false;
	}
	
	public static boolean isAreaValid(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void showRGBError() {
		JOptionPane.showMessageDialog(this,
				"RGB Value Must Be Numeric and between 0-255\nError Must between 0 - 100 % \n(You might forgot to load RGB Value)", "Failed!",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void showAreaError() {
		isAreaReady = false;
		JOptionPane.showMessageDialog(this,
				"Nominal Area Value Must Be Numeric \n(You might forgot to load Nominal Area)", "Failed!",
				JOptionPane.ERROR_MESSAGE);

	}
	
	public boolean isAreaReady(){
		return isAreaReady;
	}
	
	public boolean isRGBReady(){
		return isRGBReady;
	}
	
	public void ToggleMode(){
		switch (programMode){
			case 1:
				programMode = 2;
				rVal.setEnabled(true);
				gVal.setEnabled(true);
				bVal.setEnabled(true);
				xVal.setEnabled(false);
				yVal.setEnabled(false);
				x1Val.setEnabled(false);
				y1Val.setEnabled(false);
				
				l1.setEnabled(true);
				l2.setEnabled(true);
				l3.setEnabled(true);
				l4.setEnabled(true);
				l5.setEnabled(false);
				l6.setEnabled(false);
				l7.setEnabled(false);
				l8.setEnabled(false);
				
				xVal.setText("0");
				yVal.setText("0");
				x1Val.setText("1");
				y1Val.setText("1");
				this.repaint();

				break;
			case 2:
				rVal.setEnabled(false);
				gVal.setEnabled(false);
				bVal.setEnabled(false);
				rVal.setText("0");
				gVal.setText("0");
				bVal.setText("0");
				xVal.setEnabled(true);
				yVal.setEnabled(true);
				x1Val.setEnabled(true);
				y1Val.setEnabled(true);
				
				l1.setEnabled(false);
				l2.setEnabled(false);
				l3.setEnabled(false);
				l5.setEnabled(true);
				l6.setEnabled(true);
				l7.setEnabled(true);
				l8.setEnabled(true);
				
				tVal.setEnabled(true);

				programMode = 3;
				this.repaint();

				break;
			case 3:
				rVal.setEnabled(false);
				gVal.setEnabled(false);
				bVal.setEnabled(false);
				xVal.setEnabled(false);
				yVal.setEnabled(false);
				x1Val.setEnabled(false);
				y1Val.setEnabled(false);
				tVal.setEnabled(false);
				
				l1.setEnabled(false);
				l2.setEnabled(false);
				l3.setEnabled(false);
				l4.setEnabled(false);
				l5.setEnabled(false);
				l6.setEnabled(false);
				l7.setEnabled(false);
				l8.setEnabled(false);

				programMode = 0;
				this.repaint();

				break;
			default:
				rVal.setEnabled(true);
				gVal.setEnabled(true);
				bVal.setEnabled(true);
				xVal.setEnabled(true);
				yVal.setEnabled(true);
				x1Val.setEnabled(true);
				y1Val.setEnabled(true);
				
				l1.setEnabled(true);
				l2.setEnabled(true);
				l3.setEnabled(true);
				l4.setEnabled(true);
				l5.setEnabled(true);
				l6.setEnabled(true);
				l7.setEnabled(true);
				l8.setEnabled(true);
				
				tVal.setEnabled(true);
				programMode = 1;
				this.repaint();

		}
	}
	
	private void initJButton(){
		
		loadScript = new JButton("Load Script");
		loadScript.setBounds(0, 0, 125, 30);
		loadScript.addActionListener(this);
		loadScript.setFocusable(false);
		this.add(loadScript);

		loadNomialRBGVal = new JButton("Load Nominal RGB Val");
		loadNomialRBGVal.setBounds(120, 0, 175, 30);
		loadNomialRBGVal.addActionListener(this);
		loadNomialRBGVal.setFocusable(false);
		loadNomialRBGVal.setEnabled(false);
		this.add(loadNomialRBGVal);
		
		mode = new JButton("Toggle Mode");
		mode.setBounds(0, 25, 125, 30);
		mode.addActionListener(this);
		mode.setFocusable(false);
		mode.setEnabled(false);
		this.add(mode);

		LoadNominalArea = new JButton("Load Nominal Area");
		LoadNominalArea.setBounds(120, 25, 175, 30);
		LoadNominalArea.addActionListener(this);
		LoadNominalArea.setFocusable(false);
		LoadNominalArea.setEnabled(false);
		this.add(LoadNominalArea);
		
	}
	
	private void initJTextField(int jTextFieldX, int jTextFieldY){
	
		rVal = new JTextField("0");
		rVal.setHorizontalAlignment(JTextField.CENTER);
		rVal.setBounds(jTextFieldX, jTextFieldY, 50, 20);
		rVal.addActionListener(this);
		rVal.setEnabled(false);
		rVal.setToolTipText("Input Red Value");
		this.add(rVal);

		gVal = new JTextField("0");
		gVal.setHorizontalAlignment(JTextField.CENTER);
		gVal.setBounds(jTextFieldX + 50, jTextFieldY, 50, 20);
		gVal.addActionListener(this);
		gVal.setEnabled(false);
		rVal.setToolTipText("Input Green Value");
		this.add(gVal);

		bVal = new JTextField("0");
		bVal.setHorizontalAlignment(JTextField.CENTER);
		bVal.setBounds(jTextFieldX + 100, jTextFieldY, 50, 20);
		bVal.addActionListener(this);
		bVal.setEnabled(false);
		rVal.setToolTipText("Input Blue Value");
		this.add(bVal);

		tVal = new JTextField("0");
		tVal.setHorizontalAlignment(JTextField.CENTER);
		tVal.setBounds(jTextFieldX + 150, jTextFieldY, 50, 20);
		tVal.addActionListener(this);
		tVal.setEnabled(false);
		rVal.setToolTipText("Input Tolerance");
		this.add(tVal);

		xVal = new JTextField("0");
		xVal.setHorizontalAlignment(JTextField.CENTER);
		xVal.setBounds(jTextFieldX, jTextFieldY + 35, 50, 20);
		xVal.addActionListener(this);
		xVal.setEnabled(false);
		xVal.setToolTipText("Input nominal x1 Coodinate");
		this.add(xVal);

		yVal = new JTextField("0");
		yVal.setHorizontalAlignment(JTextField.CENTER);
		yVal.setBounds(jTextFieldX + 50, jTextFieldY + 35, 50, 20);
		yVal.addActionListener(this);
		yVal.setEnabled(false);
		yVal.setToolTipText("Input nominal y1 Coodinate");
		this.add(yVal);

		x1Val = new JTextField("1");
		x1Val.setHorizontalAlignment(JTextField.CENTER);
		x1Val.setBounds(jTextFieldX + 100, jTextFieldY + 35, 50, 20);
		x1Val.addActionListener(this);
		x1Val.setEnabled(false);
		x1Val.setToolTipText("Input nominal x2 Coodinate");
		this.add(x1Val);

		y1Val = new JTextField("1");
		y1Val.setHorizontalAlignment(JTextField.CENTER);
		y1Val.setBounds(jTextFieldX + 150, jTextFieldY + 35, 50, 20);
		y1Val.addActionListener(this);
		y1Val.setEnabled(false);
		y1Val.setToolTipText("Input nominal y2 Coodinate");
		this.add(y1Val);
	}
	
	private void initJLabel(int x,int y) {
		
		l1 = new JLabel("Red");
		l2 = new JLabel("Green");
		l3 = new JLabel("Blue");
		l4 = new JLabel("Error%");
		l5 = new JLabel("x");
		l6 = new JLabel("y");
		l7 = new JLabel("x'");
		l8 = new JLabel("y'");
		
		l1.setHorizontalAlignment(JLabel.CENTER);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l3.setHorizontalAlignment(JLabel.CENTER);
		l4.setHorizontalAlignment(JLabel.CENTER);
		l5.setHorizontalAlignment(JLabel.CENTER);
		l6.setHorizontalAlignment(JLabel.CENTER);
		l7.setHorizontalAlignment(JLabel.CENTER);
		l8.setHorizontalAlignment(JLabel.CENTER);
		
		l1.setBounds(x, y, 50, 20);
		l2.setBounds(x + 50, y, 50, 20);
		l3.setBounds(x + 100, y, 50, 20);
		l4.setBounds(x + 150, y, 50, 20);
		
		l5.setBounds(x, y + 35, 50, 20);
		l6.setBounds(x + 50, y + 35, 50, 20);
		l7.setBounds(x + 100, y + 35, 50, 20);
		l8.setBounds(x + 150, y + 35, 50, 20);
		
		l1.setEnabled(false);
		l2.setEnabled(false);
		l3.setEnabled(false);
		l4.setEnabled(false);
		l5.setEnabled(false);
		l6.setEnabled(false);
		l7.setEnabled(false);
		l8.setEnabled(false);

		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		this.add(l5);
		this.add(l6);
		this.add(l7);
		this.add(l8);
	}

}
