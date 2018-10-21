package AbstractActions;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import MainPackage.Main;


public class ScreenCapturePointOne extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	public void actionPerformed(ActionEvent e) {
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		Main.userInterface.xVal.setText(x+"");
		Main.userInterface.yVal.setText(y+"");
		Main.userInterface.repaint();
	}
}
