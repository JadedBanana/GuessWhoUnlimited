package Screens;

/* ===============================================
 * TestScreen is for testing! Obviously!
 * ===============================================
*/

import _Main.Input;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import Utility.Logging;

public class TestScreen implements Screen {
	
	String[] CONTROL_NAMES = {"up", "left", "down", "right", "select", "back"};

	public void mouseClicked(MouseEvent e) { Logging.debug("Mouse clicked at " + e.getX() + ", " + e.getY()); }
	public void mousePressed(MouseEvent e) { Logging.debug("Mouse pressed at " + e.getX() + ", " + e.getY()); }
	public void mouseReleased(MouseEvent e) { Logging.debug("Mouse released at " + e.getX() + ", " + e.getY()); }
	public void mouseEntered(MouseEvent e) { Logging.debug("Mouse entered at " + e.getX() + ", " + e.getY()); }
	public void mouseExited(MouseEvent e) { Logging.debug("Mouse exited at " + e.getX() + ", " + e.getY()); }
	public void keyTyped(KeyEvent e) { Logging.debug("Key code " + e.getKeyCode() + " typed, equivalent to " + getControlEquivalent(e.getKeyCode())); }
	public void keyPressed(KeyEvent e) { Logging.debug("Key code " + e.getKeyCode() + " pressed, equivalent to " + getControlEquivalent(e.getKeyCode())); }
	public void keyReleased(KeyEvent e) { Logging.debug("Key code " + e.getKeyCode() + " released, equivalent to " + getControlEquivalent(e.getKeyCode())); }
	
	private String getControlEquivalent(int keyCode) {
		for(int i = 0; i < Input.COMPLETE_INPUT_KEY_CODE_LIST.length; i++)
			for(int j = 0; j < Input.COMPLETE_INPUT_KEY_CODE_LIST[i].length; j++)
				if(Input.COMPLETE_INPUT_KEY_CODE_LIST[i][j] == keyCode)
					return CONTROL_NAMES[i];
		return "";
					
		
	}

}
