package Utility;

/* ==========================================================================================
 * A JSON manager that reads and writes JSON.
 * I felt like writing my own based on previous experience in Python.
 * Since dictionaries don't exist in Java, this uses HashMaps instead.
 * Booleans and Numbers have custom classes written by me. See Utility/Bool and Utility/Num.
 * ==========================================================================================
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import Global.Constants;

public class JSON {
	
	// Reads JSON dictionaries.
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> read(File file) throws FileNotFoundException, JSONFormattingError {
		Logging.debug("Whatever");
		// Reads in the raw JSON data.
		String fullJSON = "";
		Scanner reader = new Scanner(file);
		while(reader.hasNext())
			fullJSON+= reader.nextLine();
		reader.close();
		
		// Removes any pobbile spaces at the start.
		fullJSON = Utility.removeFrontmostSpaces(fullJSON);
		
		return (HashMap<String, Object>) makeHashFromDict(fullJSON)[0];
	}
	
	// Returns a HashMap with the results of the dict along with a string representing the rest of the file.
	private static Object[] makeHashFromDict(String remainingJSON) throws JSONFormattingError {
		HashMap<String, Object> currentDict = new HashMap<String, Object>();
		remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(1));
		
		// Collects all the stuff within the dictionary.
		while(remainingJSON.charAt(0) != '}') {
			// Collects the key for the following object.
			if(remainingJSON.charAt(0) != '"') {
				throw new JSONFormattingError();
			}
			else {
				try {
					// Gets the key.
					int keyEnd = getStringEndIndex(remainingJSON);
					String key = remainingJSON.substring(1, keyEnd);
					// Removes the key AND the colon.
					remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(keyEnd + 2));
					// Gets the objects in the dict.
					Object[] dictReturn = getObject(remainingJSON);
					currentDict.put(key, dictReturn[0]);
					remainingJSON = (String) dictReturn[1];
				} catch(IndexOutOfBoundsException e) {
					throw new JSONFormattingError();
				}
			}
		}
		return new Object[] {currentDict, remainingJSON};
	}
	
	// Gets the object following the key of a dict/in a list.
	private static Object[] getObject(String remainingJSON) throws JSONFormattingError {
		Object[] objReturn = new Object[2];
		// Determines what the object is and calls the accurate message to deal with it.
		// String:
		if(remainingJSON.charAt(0) == '"') {
			int objectEnd = getStringEndIndex(remainingJSON);
			objReturn[0] = remainingJSON.substring(1, objectEnd);
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(objectEnd + 1));
		}
		// Dict:
		else if(remainingJSON.charAt(0) == '{') {
			Object[] dictReturn = makeHashFromDict(remainingJSON);
			objReturn[0] = dictReturn[0];
			remainingJSON = (String) dictReturn[1];
		}
		// List:
		else if(remainingJSON.charAt(0) == '[') {
			Object[] listContents = {};
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(1));
			while(remainingJSON.charAt(0) != ']') {
				Object[] listContentsNew = new Object[listContents.length + 1];
				for(int i = 0; i < listContents.length; i++) listContentsNew[i] = listContents[i];
				Object[] listReturn = getObject(remainingJSON);
				listContentsNew[listContents.length] = listReturn[0];
				listContents = listContentsNew;
				remainingJSON = (String) listReturn[1];
			}
			objReturn[0] = listContents;
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(1));
		}
		// Number:
		else if(Constants.NUMBERS.indexOf(remainingJSON.charAt(0)) != -1) {
			int numIndex = 1;
			while(Constants.NUMBERS.indexOf(remainingJSON.charAt(numIndex)) != -1)
				numIndex++;
			try {
				objReturn[0] = new Num(Double.parseDouble(remainingJSON.substring(0, numIndex)));
			} catch(NumberFormatException e) {
				throw new JSONFormattingError();
			}
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(numIndex));
		}
		// Boolean/Null:
		else if(remainingJSON.substring(0, 4).toLowerCase().equals("true")) {
			objReturn[0] = new Bool(true);
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(4));
		} else if(remainingJSON.substring(0, 4).toLowerCase().equals("null")) {
			objReturn[0] = null;
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(4));
	    } else if(remainingJSON.substring(0, 5).toLowerCase().equals("false")) {
			objReturn[0] = new Bool(false);
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(5));
	    }
		// Removes the comma, if it is there.
		if(remainingJSON.charAt(0) == ',')
			remainingJSON = Utility.removeFrontmostSpaces(remainingJSON.substring(1));
		objReturn[1] = remainingJSON;
		return objReturn;
	}
	
	// Gets the end of the string.
	private static int getStringEndIndex(String remainingJSON) {
		int endIndex = 1;
		boolean backslashActive = false;
		while(endIndex < remainingJSON.length()) {
			if(remainingJSON.charAt(++endIndex) == '\\')
				backslashActive = !backslashActive;
			else if(remainingJSON.charAt(endIndex) == '"' && !backslashActive)
				return endIndex;
			else
				backslashActive = false;
		}
		return -1;
	}
	
	// Exception class.
	@SuppressWarnings("serial")
	public static class JSONFormattingError extends Exception { }
}
