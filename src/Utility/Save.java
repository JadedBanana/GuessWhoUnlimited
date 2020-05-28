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
		Logging.info("Loading save file...");
		for(String directory : Constants.SAVE_DIRS) {
			File saveFile = new File(directory);
			Logging.debug("Attempting to load save file at " + saveFile.getPath());
			if(!saveFile.exists())
				continue;
			else if(!saveFile.canRead()) {
				load_fail_reason = 1;
				load_file = directory;
				Logging.error("Save file cannot be read.");
				continue;
			}
		}
		// Executes only if the save file doesn't exist.
		if(load_fail_reason == -1) {
			load_fail_reason = 0;
			Logging.info("No save files found. Game launching with default data.");
		}
	}
	
}
