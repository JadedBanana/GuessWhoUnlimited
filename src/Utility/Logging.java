package Utility;

import java.time.LocalDateTime;

import Global.Constants;

/* =====================================
 * Logging keeps track of all the logs.
 * =====================================
*/

public class Logging {

	// Logging levels.
	public static byte DEBUG = 0;
	public static byte INFO = 1;
	public static byte WARNING = 2;
	public static byte ERROR = 3;
	public static byte CRITICAL = 4;
	private static String[] levelHeaders = {"DEBUG", "INFO", "WARNING", "ERROR", "CRITICAL"};
	public static byte currentLogLevel = 0;
	
	// The log text.
	public static String logText = "";
	
	// Logs stuff!
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
	
}
