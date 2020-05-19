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
	public static void log(byte level, Object input) {
		String inputStr = input.toString();
		if(level < currentLogLevel)
			return;
		String messagePrefix = "[" + Constants.DATE_TIME_FORMAT.format(LocalDateTime.now()) + "] " + levelHeaders[level] + ": ";
		// Makes sure that everything is tabbed over the correct amount if there are multiple lines in the input.
		if(inputStr.contains("\n")) {
			String tabsOver = "\n";
			for(int i = 0; i < messagePrefix.length(); i++)
				tabsOver+= " ";
			int lastIndex = inputStr.length();
			while(inputStr.lastIndexOf('\n', --lastIndex) != -1) {
				lastIndex = inputStr.lastIndexOf('\n', lastIndex);
				inputStr = inputStr.substring(0, lastIndex) + tabsOver + inputStr.substring(lastIndex + 1);
			}
		}
		System.out.println(messagePrefix + input);
	}
	
	// Log methods, with the levels built-in.
	public static void debug(Object groupArray) { log(DEBUG, groupArray); }
	public static void info(Object input) { log(INFO, input); }
	public static void warning(Object input) { log(WARNING, input); }
	public static void error(Object input) { log(ERROR, input); }
	public static void critical(Object input) { log(CRITICAL, input); }
	
}
