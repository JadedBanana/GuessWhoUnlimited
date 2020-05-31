package Screens;

/* ==================
 * Screen interface.
 * ==================
*/

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface Screen {

	// Input methods.
	public void mouseClicked(MouseEvent e);
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	public void mouseEntered(MouseEvent e);
	public void mouseExited(MouseEvent e);
	public void mouseDragged(MouseEvent e);
	public void mouseMoved(MouseEvent e);
	public void mouseWheelMoved(MouseWheelEvent e);
	public void keyTyped(KeyEvent e);	
	public void keyPressed(KeyEvent e);	
	public void keyReleased(KeyEvent e);
	
	
	// Method that draws for each screen.
	public void draw(Graphics g);
	
	
	// Method that notifies the screen that it is being switched away from/to.
	public void notifySwitchAway();
	public void notifySwitchTo();
	
	
	// Method that says whether or not the current screen uses the AniThread.
	public boolean usesAniThread();
	
}
