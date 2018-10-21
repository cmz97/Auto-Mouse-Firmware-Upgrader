package MainPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ScriptLoader {
	private Scanner scanner;
	
	public ScriptLoader(File filePath){
		try {
			scanner = new Scanner(filePath);
			scanner.useDelimiter("\n");
			while (scanner.hasNext()) 
			{
				String data = scanner.next();
				String[] values = data.split(" ");
				Main.command(values); 
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
