package Screens;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import _Main.AniThread;

public abstract class Transition implements Screen {
	
	// Determines the length of this transition.
	protected short transitionLength;

	// Input methods.
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
	
	// Method that says whether or not the current screen uses the AniThread.
	public boolean usesAniThread() { return true; }
	
	// Draw is only here so that we can keep track of the fps.
	public void draw(Graphics g) {
		AniThread.yeetIGottaFrame();
	}
}
