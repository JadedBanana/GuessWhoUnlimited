package Screens;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class InputTakingScreen implements Screen {

	// Method that says whether or not the current screen uses the AniThread.
	public abstract boolean usesAniThread();
	
	
	// These input methods are here so that I don't have to fill out empty ones every time I don't need crap.
	public void mouseClicked(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { }
	public void mouseMoved(MouseEvent e) { }
	public void mouseWheelMoved(MouseWheelEvent e) { }
	public void keyTyped(KeyEvent e) { }
	public void keyPressed(KeyEvent e) { }
	public void keyReleased(KeyEvent e) { }
}
