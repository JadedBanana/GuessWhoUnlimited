package Utility;

/* ======================================================================
 * A stand-in class for booleans. 
 * It has methods that help shorten the code when dealing with HashMaps.
 * ======================================================================
*/

public class Bool {

	boolean realVal;
	
	
	// Constructor.
	public Bool(boolean realVal) {
		this.realVal = realVal;
	}
	
	
	// Returns this Bool's value.
	public boolean getBool() { return realVal; }
	
	
	// Returns the value of a given Bool.
	public static boolean getBool(Object bool) { return ((Bool)bool).getBool(); }
}
