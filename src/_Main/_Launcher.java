package _Main;

/* =============================================================================
 * Launcher launches the program. 
 * It asks the save manager to load any data it can find. See src/Utility/Save.
 * Then it initializes the frame. See src/_Main/Frame.
 * It's very simple and quick.
 * =============================================================================
*/

import Utility.Save;

public class _Launcher {
	
	// Main.
	public static void main(String[] args) {
		Save.loadFile();
		new Frame(args);
	}
}
