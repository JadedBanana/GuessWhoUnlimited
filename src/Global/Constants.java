package Global;

import java.time.format.DateTimeFormatter;

/* =======================================
 * Constants used throughout the program.
 * =======================================
*/

public class Constants {
	
	// Save constants.
	public static final String[] SAVE_DIRS = {
			"%appdata%/GuessWhoUnlimited/save.json",
			System.getProperty("user.home") + "/Documents/GuessWhoUnlimited/save.json"
	};
	
	// JSON constants.
	public static final String NUMBERS = "0123456789.-";
	
	// Logging constants.
	public static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
	
	// Frame constants.
	public static final String WINDOW_TITLE = "Guess Who Unlimited";
	public static final boolean RESIZABLE = false;
	public static final short DEFAULT_WIDTH = 960;
	public static final short DEFAULT_HEIGHT = 540;
	
	// Input constants.
	public static final byte[] UP_KEY_CODES = {38, 87, 104};
	public static final byte[] LEFT_KEY_CODES = {37, 65, 100};
	public static final byte[] DOWN_KEY_CODES = {40, 83, 98};
	public static final byte[] RIGHT_KEY_CODES = {39, 68, 102};
	public static final byte[] SELECT_KEY_CODES = {8, 27, 67};
	public static final byte[] BACK_KEY_CODES = {10, 32, 88};
	
}
