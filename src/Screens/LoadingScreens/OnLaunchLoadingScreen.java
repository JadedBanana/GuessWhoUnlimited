package Screens.LoadingScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Screens.LoadingScreen;
import Utility.Logging;
import _Main.Frame;

public class OnLaunchLoadingScreen extends LoadingScreen {
	
	// Logs whether the log has been logged to.
	boolean logged = false;
	
	
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
