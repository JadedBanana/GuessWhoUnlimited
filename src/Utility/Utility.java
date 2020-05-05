package Utility;

public class Utility {

	// Takes an array and makes it into a String containing all the elements in the array.
	public static String arrayToString(Object[] arr, boolean spaced) {
		String returnString = "[";
		for(int i = 0; i < arr.length; i++) {
			if(spaced)
				returnString+= "\n\t";
			returnString += arr[i].toString() + ", ";
		}
		if(returnString.contains(",")) {
			returnString = returnString.substring(0, returnString.length()-2);
			if(spaced)
				returnString+= "\n";
		}
		return returnString + "]";
	}
	
	public static String removeFrontmostSpaces(String input) {
		if(input == null)
			return input;
		while(input.length() > 0 && (input.charAt(0) == ' ' || input.charAt(0) == '\t'))
			input = input.substring(1);
		return input;
	}
	
	// Unfortunately, HashMaps convert any and all numbers to their class equivalents. 
	// These are here to convert them back when we load them in.
	public static int getIntFromHash(Object num) { return ((Integer)num).intValue(); }
	public static long getLongFromHash(Object num) { return ((Integer)num).longValue();	}
	public static double getDoubleFromHash(Object num) { return ((Double)num).doubleValue(); }
}
