package Global;

import java.time.format.DateTimeFormatter;

/* =======================================
 * Constants used throughout the program.
 * =======================================
*/

public class Constants {
	
	// Logging constants.
	public static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
	
	// Frame constants.
	public static final String WINDOW_TITLE = "Guess Who Unlimited";
	public static final boolean RESIZABLE = false;
	public static final short DEFAULT_WIDTH = 960;
	public static final short DEFAULT_HEIGHT = 540;
	
	// Save constants.
	public static final String[] SAVE_DIRS = {
			"%appdata%/GuessWhoUnlimited/save.json",
			System.getProperty("user.home") + "/Documents/GuessWhoUnlimited/save.json"
	};
	
}
