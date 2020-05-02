package Screens;

/* ===============================================
 * TestScreen is for testing! Obviously!
 * ===============================================
*/

import _Main.Input;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Utility.Logging;

public class TestScreen implements Screen {
	
	// Variables used for testing.
	String[] CONTROL_NAMES = {"up", "left", "down", "right", "select", "back"};
	BufferedImage testImage;
	
	public TestScreen() {
		try {
			testImage = ImageIO.read(TestScreen.class.getResource("/diddyKongInVietnam.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Input methods.
	public void mouseClicked(MouseEvent e) { Logging.debug("Mouse clicked at " + e.getX() + ", " + e.getY()); }
	public void mousePressed(MouseEvent e) { Logging.debug("Mouse pressed at " + e.getX() + ", " + e.getY()); }
	public void mouseReleased(MouseEvent e) { Logging.debug("Mouse released at " + e.getX() + ", " + e.getY()); }
	public void mouseEntered(MouseEvent e) { Logging.debug("Mouse entered at " + e.getX() + ", " + e.getY()); }
	public void mouseExited(MouseEvent e) { Logging.debug("Mouse exited at " + e.getX() + ", " + e.getY()); }
	public void mouseDragged(MouseEvent e) { Logging.debug("Mouse dragged to " + e.getX() + ", " + e.getY()); }
	public void mouseMoved(MouseEvent e) { Logging.debug("Mouse moved to " + e.getX() + ", " + e.getY()); }
	public void mouseWheelMoved(MouseWheelEvent e) { 
		if(e.getWheelRotation() < 0)
			Logging.debug("Mouse scrolled up at " + e.getX() + ", " + e.getY());
		else
			Logging.debug("Mouse scrolled down at " + e.getX() + ", " + e.getY());
	}
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
	
	// Draw method.
	public void draw(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		g.fillRect(200, 100, 300, 100);
		Graphics2D g2 = (Graphics2D) g;
		
		AffineTransform xform = new AffineTransform();
		xform.setTransform(2, 0, -1, 1, 300, 300);
		g2.drawImage(testImage, xform, null);
	}

}
