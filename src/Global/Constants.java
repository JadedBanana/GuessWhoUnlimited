package Global;

/* =======================================
 * Constants used throughout the program.
 * =======================================
*/

import java.time.format.DateTimeFormatter;

public class Constants {
	
	// Save constants.
	public static final String[] SAVE_DIRS = {
			System.getProperty("user.home") + "/AppData/Local/GuessWhoUnlimited/save.json",
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
	
	
	// ScreenManager constants.
	public static final String GAMESCREEN_TITLE = "game";
	
	
	// Character constants.
	// Character directories.
	public static final String[] DEFAULT_CHARACTER_DIRS = {
			System.getProperty("user.home") + "/AppData/Local/GuessWhoUnlimited/Characters/",
			System.getProperty("user.home") + "/Documents/GuessWhoUnlimited/Characters/"
	};
	// Character keys.
	public static final String CHARACTER_NAME_KEY = "name";
	public static final String CHARACTER_GROUP_KEY = "group";
	public static final String CHARACTER_TYPE_KEY = "case";
	public static final String CHARACTER_CHARACTER_TYPE_KEY = "characters";
	public static final String CHARACTER_GROUP_TYPE_KEY = "groups";
	public static final String[] CHARACTER_OPTIONAL_CHARACTER_KEYS = {
			"aka",
			"species",
			"gender",
			"race",
			"type",
			"evolves from",
			"evolves to",
			"power",
			"height",
			"weight",
			"birthday",
			"age",
			"alignment",
			"status",
			"date of death",
			"hair color",
			"eye color",
			"bio",
			"occupation",
			"blood type",
			"measurements",
			"chest size",
			"shoe size",
			"likes",
			"favorite food",
			"favorite color",
			"dislikes",
			"allies",
			"affiliation",
			"family",
			"relatives",
			"enemies",
			"nationality",
			"first appearance",
			"final appearance",
			"appearances",
			"portrayed by"
	};
	// Group keys.
	public static final String CHARACTER_GROUP_PARENT_GROUP_KEY = "parent";
	public static final String CHARACTER_GROUP_SUBGROUP_KEY = "subgroups";
	public static final String CHARACTER_GROUP_SORT_KEY = "sort";
	public static final String CHARACTER_SORT_DEFAULT_KEY = "default";
	public static final String CHARACTER_SORT_AZ_KEY = "alphabetical";
	public static final String CHARACTER_SORT_LOAD_KEY = "load order";
	// Error/loading messages.
	public static final String CHARACTER_DIR_LOADING_MESSAGE = "Attempting to load characters within directory ";
	public static final String CHARACTER_INVALID_KEY_ERROR = " has an invalid value for the key \"";
	public static final String CHARACTER_FORMATTING_ERROR =  " has formatting errors.";
	public static final String CHARACTER_UNLOADABLE_ERROR = " could not be loaded.";
	public static final String CHARACTER_NO_NAME_ERROR = " has a character that does not have a name.";
	public static final String CHARACTER_INVALID_GROUP_ERROR = " has an invalid value for their group(s).";
	public static final String CHARACTER_NO_DEFAULT_SORT_ERROR = " has an invalid value in their dict for group(s). If it isn't a number, default sort won't work correctly.";
	public static final String CHARACTER_GROUP_NO_NAME_ERROR = " has a group that does not have a name.";
	
}
