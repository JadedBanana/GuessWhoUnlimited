package Utility;

/* ======================================================
 * Save manages the save file. It both loads and writes.
 * ======================================================
*/

import java.io.File;
import java.util.HashMap;
import Global.Constants;

public class Save {
	
	// Current save data, if any.
	public static HashMap<String, Object> saveData;

	// Records if the load was successful.
	// Load fail reasons:
	//		0: Save file does not exist.
	// 		1: Save file cannot be read due to permissions.
	public static boolean load_successful = false;
	public static byte load_fail_reason = -1;
	public static String load_file;
	
	// Attempts to load from the save file.
	public static void loadFile() {
		for(String directory : Constants.SAVE_DIRS) {
			File saveFile = new File(directory);
			if(!saveFile.exists())
				continue;
			else if(!saveFile.canRead()) {
				load_fail_reason = 1;
				load_file = directory;
				Logging.log(Logging.ERROR, "Save file at " + directory + " cannot be read!");
				return;
			}
		}
		// Executes only if the save file doesn't exist.
		load_fail_reason = 0;
		Logging.log(Logging.INFO, "No save files found. Game launching with default data.");
	}
}
