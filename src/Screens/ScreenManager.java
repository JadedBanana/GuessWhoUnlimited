package Screens;

/* ===============================================
 * ScreenManager manages screens.
 * ===============================================
*/

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ScreenManager {
	
	public static Screen currentScreen = new TestScreen();

	// Input methods. Passes the input along to the current screen.
	public static void mouseClicked(MouseEvent e) { currentScreen.mouseClicked(e); }
	public static void mousePressed(MouseEvent e) { currentScreen.mousePressed(e); }
	public static void mouseReleased(MouseEvent e) { currentScreen.mouseReleased(e); }		
	public static void mouseEntered(MouseEvent e) { currentScreen.mouseEntered(e); }		
	public static void mouseExited(MouseEvent e) { currentScreen.mouseExited(e); }
	public static void keyTyped(KeyEvent e) { currentScreen.keyTyped(e); }		
	public static void keyPressed(KeyEvent e) { currentScreen.keyPressed(e); }		
	public static void keyReleased(KeyEvent e) { currentScreen.keyReleased(e); }
}
