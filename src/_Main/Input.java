package _Main;

/* ==================================================
 * Input is the input manager.
 * Mouse movements and keyboard strokes all head through here.
 * ==================================================
*/

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Global.Constants;
import Global.GlobalVars;
import Screens.ScreenManager;

public class Input {	
	
	// Simple setup.
	public Input(Panel panel) {
		PanelInput input = new PanelInput();
		panel.addMouseListener(input);
		panel.addMouseMotionListener(input);
		panel.addMouseWheelListener(input);
		panel.addKeyListener(input);
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

	
	// All input. Just passes along stuff to the screens, except for debug inputs.
	public class PanelInput implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

		public void mouseClicked(MouseEvent e) { ScreenManager.mouseClicked(e); }
		public void mousePressed(MouseEvent e) { ScreenManager.mousePressed(e); }
		public void mouseReleased(MouseEvent e) { ScreenManager.mouseReleased(e); }		
		public void mouseEntered(MouseEvent e) { ScreenManager.mouseEntered(e); }		
		public void mouseExited(MouseEvent e) { ScreenManager.mouseExited(e); }
		public void mouseDragged(MouseEvent e) { ScreenManager.mouseDragged(e); }
		public void mouseMoved(MouseEvent e) { ScreenManager.mouseMoved(e); }
		public void mouseWheelMoved(MouseWheelEvent e) { ScreenManager.mouseWheelMoved(e); }
		public void keyTyped(KeyEvent e) { ScreenManager.keyTyped(e); }		
		public void keyPressed(KeyEvent e) { ScreenManager.keyPressed(e); }		
		public void keyReleased(KeyEvent e) { 
			ScreenManager.keyReleased(e); 
			if(GlobalVars.DEBUG_MODE) {
				int keyCode = e.getKeyCode();
				switch(keyCode) {
					case Constants.DEBUG_KEY_CODE: if(GlobalVars.DEBUG_GUI_ELEMENTS)
												       GlobalVars.DEBUG_GUI_ELEMENTS = false;
												   else
													   GlobalVars.DEBUG_GUI_ELEMENTS = true;
												   break;
				}
			}
		}
	}
	
	
	// Method that returns the effective button for the provided keyCode.
	public static byte getButtonCode(int keyCode) {
		for(byte i = 0; i < Input.COMPLETE_INPUT_KEY_CODE_LIST.length; i++)
			for(byte j = 0; j < Input.COMPLETE_INPUT_KEY_CODE_LIST[i].length; j++)
				if(Input.COMPLETE_INPUT_KEY_CODE_LIST[i][j] == keyCode)
					return i;
		return -1;
	}
	
}
