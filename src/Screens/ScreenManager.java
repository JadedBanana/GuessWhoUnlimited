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
import Screens.*;
import Screens.InputTakingScreens.*;
import Screens.LoadingScreens.*;
import Screens.Transitions.*;
import _Main.AniThread;
import _Main.Panel;

public class ScreenManager {
	
	// Variables.
	private static Screen currentScreen;
	private static HashMap<String, Transition> transitionList = new HashMap<String, Transition>();
	public static boolean initComplete = false;
	
	
	// Initializes the ScreenManager.
	public static void initialize() {
		currentScreen = new TestScreen3();
		currentScreen.notifySwitchTo();
		initComplete = true;
		Panel.panel.repaint();
	}
	
	
	// Switch methods. Takes an order from a screen to switch to a different screen.
	public static void switchScreen(Screen from, Screen to) {
		
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
	
	
	// Returns whether the current screen uses AniThread.
	public static boolean currentScreenUsesAniThread() { return currentScreen.usesAniThread(); }

}
