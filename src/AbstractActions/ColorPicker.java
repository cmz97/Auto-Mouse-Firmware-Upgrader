package AbstractActions;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import MainPackage.Main;


public class ColorPicker extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	public void actionPerformed(ActionEvent e) {
		Color c = Main.robot.getPixelColor(MouseInfo.getPointerInfo().getLocation().x
				, MouseInfo.getPointerInfo().getLocation().y);
		Main.userInterface.rVal.setText(c.getRed()+"");
		Main.userInterface.gVal.setText(c.getGreen()+"");
		Main.userInterface.bVal.setText(c.getBlue()+"");
	}
}
