package Screens;

/* ===============================================
 * ScreenManager manages screens.
 * Big surprise!
 * ===============================================
*/

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import Global.Constants;
import Screens.InputTakingScreens.GameScreen;
import Screens.InputTakingScreens.TestScreen3;
import Utility.Logging;
import _Main.AniThread;
import _Main.Panel;

public class ScreenManager {
	
	// Variables.
	public static Screen currentScreen;
	private static HashMap<String, Screen> screenList;
	public static boolean initComplete = false;
	
	
	// Puts all the screens intho the HashMap.
	public static void initialize() {
		screenList = new HashMap<String, Screen>();
		screenList.put(Constants.GAMESCREEN_TITLE, new GameScreen());
		currentScreen = new TestScreen3();
		currentScreen.notifySwitchTo();
		initComplete = true;
		Panel.panel.repaint();
	}

	
	// Input methods. Passes the input along to the current screen.
	public static void mouseClicked(MouseEvent e) { if(initComplete) currentScreen.mouseClicked(e); }
	public static void mousePressed(MouseEvent e) { if(initComplete) currentScreen.mousePressed(e); }
	public static void mouseReleased(MouseEvent e) { if(initComplete) currentScreen.mouseReleased(e); }		
	public static void mouseEntered(MouseEvent e) { if(initComplete) currentScreen.mouseEntered(e); }		
	public static void mouseExited(MouseEvent e) { if(initComplete) currentScreen.mouseExited(e); }
	public static void mouseDragged(MouseEvent e) { if(initComplete) currentScreen.mouseDragged(e); }
	public static void mouseMoved(MouseEvent e) { if(initComplete) currentScreen.mouseMoved(e); }
	public static void mouseWheelMoved(MouseWheelEvent e) { if(initComplete) currentScreen.mouseWheelMoved(e); }
	public static void keyTyped(KeyEvent e) { if(initComplete) currentScreen.keyTyped(e); }		
	public static void keyPressed(KeyEvent e) { if(initComplete) currentScreen.keyPressed(e); }		
	public static void keyReleased(KeyEvent e) { if(initComplete) currentScreen.keyReleased(e); }
	
	
	// Drawing methods. Passes to current screen.
	public static void draw(Graphics g) { 
		if(initComplete) 
			currentScreen.draw(g); 
		else 
			return;
		if(currentScreen.usesAniThread())
			AniThread.yeetIGottaFrame();
	}

}
