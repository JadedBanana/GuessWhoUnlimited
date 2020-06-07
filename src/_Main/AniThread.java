package _Main;

import Global.Constants;
import Screens.ScreenManager;

/* ==================================================
 * AniThread is a thread specifically designed to do animation.
 * It sleeps on a timer which is the inverse of the max frame rate.
 * If there isn't one, it goes 250 times a second.
 * It can be toggled by the current screen so that 
 * ==================================================
*/

public class AniThread extends Thread {

	// The current max frame rate.
	public static short currentFramerate = Constants.DEFAULT_FRAMERATE;
	public static float timeToWait = 1000f/currentFramerate;
	// The last time at which the AniThread collected a frame.
	public static long lastFrameCollect = System.currentTimeMillis();
	// The amount of draws done in the last second.
	// One public -- finished count only.
	// One private -- incremental.
	public static short drawsLastSecond = 0;
	private static short drawsThisSecond = 0;
	// Self-referential variable because we like to keep things static around here.
	private static AniThread self;
	
	
	// Starts AniThread.
	public static void initialize() {
		self = new AniThread();
		self.start();
	}
	
	
	// The repeating thread.
	public void run() {
		while(true) {
			if(ScreenManager.currentScreen.usesAniThread()) {
				try {
					// Adds in remainders of stuff.
					short pauseLength = (short) timeToWait;
					if(timeToWait % 1 != 0) {
						float timeRemainder = timeToWait - pauseLength;
						if(Math.random() >= timeRemainder)
							pauseLength++;
					}
					Thread.sleep(pauseLength);
				} catch (InterruptedException e) { }
				Panel.panel.repaint();
			} else {
				drawsThisSecond = 0;
				drawsLastSecond = 0;
				try {
					Thread.sleep(4);
				} catch (InterruptedException e) { }
			}
		}
	}
	
	
	// Method that keeps track of the fps.
	public static void yeetIGottaFrame() {
		drawsThisSecond++;
		long currentTime = System.currentTimeMillis();
		if(currentTime % 1000 < lastFrameCollect % 1000) {
			drawsLastSecond = drawsThisSecond;
			drawsThisSecond = 0;
		}
		lastFrameCollect = currentTime;
	}
	
}
