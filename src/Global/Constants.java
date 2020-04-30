package Global;

/* =======================================
 * Constants used throughout the program.
 * =======================================
*/

public class Constants {
	
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
