package Screens;

/* ===============================================
 * ScreenManager manages screens.
 * ===============================================
*/

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;

import Global.Constants;

public class ScreenManager {
	
	public static Screen currentScreen = new TestScreen2();
	private static HashMap<String, Screen> screenList;
	
	// Puts all the screens intho the HashMap.
	public static void initialize() {
		screenList = new HashMap<String, Screen>();
		screenList.put(Constants.GAMESCREEN_TITLE, new GameScreen());
	}

	// Input methods. Passes the input along to the current screen.
	public static void mouseClicked(MouseEvent e) { currentScreen.mouseClicked(e); }
	public static void mousePressed(MouseEvent e) { currentScreen.mousePressed(e); }
	public static void mouseReleased(MouseEvent e) { currentScreen.mouseReleased(e); }		
	public static void mouseEntered(MouseEvent e) { currentScreen.mouseEntered(e); }		
	public static void mouseExited(MouseEvent e) { currentScreen.mouseExited(e); }
	public static void mouseDragged(MouseEvent e) { currentScreen.mouseDragged(e); }
	public static void mouseMoved(MouseEvent e) { currentScreen.mouseMoved(e); }
	public static void mouseWheelMoved(MouseWheelEvent e) { currentScreen.mouseWheelMoved(e); }
	public static void keyTyped(KeyEvent e) { currentScreen.keyTyped(e); }		
	public static void keyPressed(KeyEvent e) { currentScreen.keyPressed(e); }		
	public static void keyReleased(KeyEvent e) { currentScreen.keyReleased(e); }
	
	// Drawing methods. Passes to current screen.
	public static void draw(Graphics g) { currentScreen.draw(g); }
}
