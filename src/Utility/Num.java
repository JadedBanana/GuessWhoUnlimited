package Utility;

/* ======================================================================
 * A stand-in class for numbers. 
 * It has methods that help shorten the code when dealing with HashMaps.
 * ======================================================================
*/

public class Num {
	
	public Num(double realVal) {
		this.realVal = realVal;
	}

	double realVal;
	
	// Returns casted values of this Num's value.
	public byte byteVal() { return (byte) realVal; }
	public short shortVal() { return (short) realVal; }
	public int intVal() { return (int) realVal; }
	public float floatVal() { return (float) realVal; }
	public long longVal() { return (long) realVal; }
	public double doubleVal() { return realVal; }
	
	// Returns casted values of a given Num's value.
	public static byte byteVal(Object num) { return ((Num)num).byteVal(); }
	public static short shortVal(Object num) { return ((Num)num).shortVal(); }
	public static int intVal(Object num) { return ((Num)num).intVal(); }
	public static float floatVal(Object num) { return ((Num)num).floatVal(); }
	public static long longVal(Object num) { return ((Num)num).longVal(); }
	public static double doubleVal(Object num) { return ((Num)num).doubleVal(); }
}
