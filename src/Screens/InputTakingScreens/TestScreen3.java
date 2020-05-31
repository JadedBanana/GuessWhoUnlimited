package Screens.InputTakingScreens;

/* ==================================================
 * TestScreen is for testing! Obviously!
 * This one was made to test AniThread.
 * ==================================================
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Global.Constants;
import Screens.InputTakingScreen;
import Utility.Logging;
import _Main.AniThread;
import _Main.Frame;
import _Main.Input;
import _Main.Panel;

public class TestScreen3 extends InputTakingScreen {
	
	// Variables used for testing.
	BufferedImage testImage;
	short imageAngle = 0;
	int imgWidth, imgHeight;
	private Font framerateFont = new Font("Courier New", 0, 26);
	
	
	// Method that says whether or not the current screen uses the AniThread.
	public boolean usesAniThread() {
		return true;
	}
	
	
	// Constructor.
	public TestScreen3() {
		try {
			testImage = ImageIO.read(TestScreen.class.getResource(Constants.TEST_IMAGE_PATH));
			imgWidth = testImage.getWidth()/2;
			imgHeight = testImage.getHeight()/2;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		
	// Input methods. Increases/decreases the framerate.
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.isShiftDown())
				AniThread.currentFramerate-= e.getWheelRotation() * 4;
			AniThread.currentFramerate-= e.getWheelRotation();
			Panel.panel.repaint();
		}
		public void keyPressed(KeyEvent e) { 
			byte multiplier = 1;
			if(e.isShiftDown())
				multiplier = 5;
			byte direction = Input.getButtonCode(e.getKeyCode());
			Logging.debug(direction);
			if(direction == Input.DOWN)
				AniThread.currentFramerate-= multiplier;
			else if(direction == Input.UP)
				AniThread.currentFramerate+= multiplier;
			else if(direction == Input.RIGHT)
				AniThread.currentFramerate+= multiplier * 4;
			else if(direction == Input.LEFT)
				AniThread.currentFramerate-= multiplier * 4;
			AniThread.timeToWait = (short) (1000 / AniThread.currentFramerate);
		}
	
	
	// Draw method. Also spins Diddy Kong!
	public void draw(Graphics g) {
		super.draw(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform xform = new AffineTransform();
		xform.setToRotation(imageAngle++ * 0.01745329252, Frame.frameWidth / 2, Frame.frameHeight / 2);
		xform.translate(Frame.frameWidth / 2 - imgWidth, Frame.frameHeight / 2 - imgHeight);
		imageAngle%= 360;
		g2.drawImage(testImage, xform, null);
		g2.setFont(framerateFont);
		g2.setColor(Color.cyan);
		g2.drawString("Target Framerate: " + AniThread.currentFramerate, 10, Frame.frameHeight - 40);
		g2.drawString("Actual Framerate: " + AniThread.drawsLastSecond, 10, Frame.frameHeight - 8);
	}
		
	// Unused methods.
	public void notifySwitchAway() { }
	public void notifySwitchTo() { }
	
}