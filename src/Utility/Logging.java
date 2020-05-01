package Utility;

/* =====================================
 * Logging keeps track of all the logs.
 * =====================================
*/

import java.time.LocalDateTime;
import Global.Constants;

public class Logging {

	// Logging levels.
	public static final byte DEBUG = 0;
	public static final byte INFO = 1;
	public static final byte WARNING = 2;
	public static final byte ERROR = 3;
	public static final byte CRITICAL = 4;
	private static final String[] levelHeaders = {"DEBUG", "INFO", "WARNING", "ERROR", "CRITICAL"};
	public static byte currentLogLevel = 0;
	
	// The log text.
	public static String logText = "";
	
	// Logs stuff! This is what all the other log methods call to get their jobs done.
	public static void log(byte level, String input) {
		if(level < currentLogLevel)
			return;
		String messagePrefix = "[" + Constants.DATE_TIME_FORMAT.format(LocalDateTime.now()) + "] " + levelHeaders[level] + ": ";
		// Makes sure that everything is tabbed over the correct amount if there are multiple lines in the input.
		if(input.contains("\n")) {
			String tabsOver = "\n";
			for(int i = 0; i < messagePrefix.length(); i++)
				tabsOver+= " ";
			int lastIndex = input.length();
			while(input.lastIndexOf('\n', --lastIndex) != -1) {
				lastIndex = input.lastIndexOf('\n', lastIndex);
				input = input.substring(0, lastIndex) + tabsOver + input.substring(lastIndex + 1);
			}
		}
		System.out.println(messagePrefix + input);
	}
	
	// Log methods, with the levels built-in.
	public static void debug(String input) { log(DEBUG, input); }
	public static void info(String input) { log(INFO, input); }
	public static void warning(String input) { log(WARNING, input); }
	public static void error(String input) { log(ERROR, input); }
	public static void critical(String input) { log(CRITICAL, input); }
	
}
