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
}
