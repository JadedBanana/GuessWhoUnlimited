package Utility;

import java.util.ArrayList;

public class Util {

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
	
	
	// Takes an ArrayList and makes it into a String containing all the elements in the array.
	@SuppressWarnings("rawtypes")
	public static String arrayToString(ArrayList arr, boolean spaced) {
		String returnString = "[";
		for(int i = 0; i < arr.size(); i++) {
			if(spaced)
				returnString+= "\n\t";
			returnString += arr.get(i).toString() + ", ";
		}
		if(returnString.contains(",")) {
			returnString = returnString.substring(0, returnString.length()-2);
			if(spaced)
				returnString+= "\n";
		}
		return returnString + "]";
	}
	
	
	// Removes all the spaces in the front of a String.
	public static String removeFrontmostSpaces(String input) {
		if(input == null)
			return input;
		while(input.length() > 0 && (input.charAt(0) == ' ' || input.charAt(0) == '\t'))
			input = input.substring(1);
		return input;
	}
	
	
	// Determines alphabetical order between 2 Strings.
	// Returns -1 if the first is larger, 1 if the second, otherwise returns 0.
	public static byte getAlphabeticalSuperior(String str1, String str2) {
		int currentChar = 0; 
		char str1char = str1.charAt(currentChar), str2char = str2.charAt(currentChar);
		try {
			while(str1char == str2char) { str1char = str1.charAt(++currentChar); str2char = str2.charAt(currentChar); }
		} catch(StringIndexOutOfBoundsException e) {
			int str1length, str2length;
			if((str1length = str1.length()) > (str2length = str2.length())) return -1;
			else if(str1length < str2length) return 1;
			else return 0;
		}
		if(str1char > str2char) return -1;
		else if(str1char < str2char) return 1;
		else return 0;
	}
	
}
