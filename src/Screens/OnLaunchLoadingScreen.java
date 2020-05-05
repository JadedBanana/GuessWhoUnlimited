package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import Utility.Logging;
import _Main.Frame;

public class OnLaunchLoadingScreen implements Screen {
	
	boolean logged = false;

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
	
	// Method that draws for each screen.
	public void draw(Graphics g) {
		if(!logged) {
			Logging.debug("Drawing main loading screen.");
			logged = true;
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, Frame.frameWidth, Frame.frameHeight);
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", 30, 30));
		g.drawString("Loading", Frame.frameWidth / 2, Frame.frameHeight / 2);
	}
	
	// Method that notifies the screen that it is being switched away from/to.
	public void notifySwitchAway() { }
	public void notifySwitchTo() { }
}
