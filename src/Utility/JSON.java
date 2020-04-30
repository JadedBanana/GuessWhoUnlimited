package Utility;

/* ====================================================================
 * A JSON manager that reads and writes JSON.
 * Since dictionaries don't exist in Java, this uses HashMaps instead.
 * ====================================================================
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class JSON {

	// Reads JSON dictionaries.
	public static HashMap<String, Object> read(File file) throws FileNotFoundException {
		String fullJSON = "";
		Scanner reader = new Scanner(file);
		while(reader.hasNext())
			fullJSON+= reader.nextLine();
		reader.close();
		
		System.out.println(fullJSON);
		return null;
	}
}
