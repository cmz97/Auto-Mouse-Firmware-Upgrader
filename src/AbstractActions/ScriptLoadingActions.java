package AbstractActions;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import MainPackage.Main;
import MainPackage.ScriptLoader;
import MainPackage.UI;


public class ScriptLoadingActions extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	public void actionPerformed(ActionEvent e) {
		boolean temp;
		temp = Main.userInterface.isAreaReady() && Main.userInterface.isRGBReady();

		switch (UI.programMode){
		    case 0:
			case 1:
				
				if(temp){
					new ScriptLoader(UI.file);
				}else{
					if(!Main.userInterface.isAreaReady())Main.userInterface.showAreaError();
					if(!Main.userInterface.isRGBReady())Main.userInterface.showRGBError();
				}
				break;
			case 2:
				if(temp){
					new ScriptLoader(UI.file);
				}else{
					Main.userInterface.showRGBError();
				}
				break;
			case 3:
				
				if(temp){
					new ScriptLoader(UI.file);
				}else{
					Main.userInterface.showAreaError();
				}
				break;
				
		}
		
	}
}
