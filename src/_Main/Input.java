package _Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/* ==================================================
 * Input is the input manager.
 * Mouse movements and keyboard strokes all head through here.
 * ==================================================
*/

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Global.Constants;
import Screens.ScreenManager;

public class Input {
	
	// Simple setup.
	public Input(Panel panel) {
		panel.addMouseListener(new MouseInput());
		panel.addKeyListener(new KeyInput());
	}
	
	// Values of different key inputs.
	public static final byte UP = 0;
	public static final byte LEFT = 1;
	public static final byte DOWN = 2;
	public static final byte RIGHT = 3;
	public static final byte SELECT = 4;
	public static final byte BACK = 5;
	public static final byte[][] COMPLETE_INPUT_KEY_CODE_LIST = {
			Constants.UP_KEY_CODES,
			Constants.LEFT_KEY_CODES,
			Constants.DOWN_KEY_CODES,
			Constants.RIGHT_KEY_CODES,
			Constants.SELECT_KEY_CODES,
			Constants.BACK_KEY_CODES
	};

	// Mouse input, just passes along stuff to the screens.
	public class MouseInput implements MouseListener {

		public void mouseClicked(MouseEvent e) { ScreenManager.mouseClicked(e); }
		public void mousePressed(MouseEvent e) { ScreenManager.mousePressed(e); }
		public void mouseReleased(MouseEvent e) { ScreenManager.mouseReleased(e); }		
		public void mouseEntered(MouseEvent e) { ScreenManager.mouseEntered(e); }		
		public void mouseExited(MouseEvent e) { ScreenManager.mouseExited(e); }
		
	}
	
	// Keyboard input, just passes along stuff to the screens.
	public class KeyInput implements KeyListener {
		
		public void keyTyped(KeyEvent e) { ScreenManager.keyTyped(e); }		
		public void keyPressed(KeyEvent e) { ScreenManager.keyPressed(e); }		
		public void keyReleased(KeyEvent e) { ScreenManager.keyReleased(e); }
		
	}
}
