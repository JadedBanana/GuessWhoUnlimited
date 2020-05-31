package _Main;

/* ===============================================
 * Panel is basically what draws the entire game.
 * It exists within the frame, and only there.
 * ===============================================
*/

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

import Global.Constants;
import Global.GlobalVars;
import Screens.ScreenManager;
import Utility.Logging;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	// Frame count.
	public static long frameCount = 0;
	
	// Self-referencing variable, allows this to be pulled from anywhere with minimal issue.
	public static Panel panel;
	
	// Debug fontMetrics.
	private FontMetrics fm;
	
	
	// Constructor.
	public Panel(Frame frame) {
		Logging.debug("Adding panel to frame.");
		panel = this;
		setFocusable(true);
		requestFocusInWindow();
		new Input(this);
		setBackground(Color.black);
	}
	
	
	// Method that draws every time something calls for new frames to be drawn.
	public void paintComponent(Graphics g)
	{
		// Has the screen draw what they need to.
		frameCount++;
		super.paintComponent(g);
		ScreenManager.draw(g);
		// Overlays debug info on top, if any.
		if(GlobalVars.DEBUG_MODE && GlobalVars.DEBUG_GUI_ELEMENTS) {
			g.setFont(Constants.DEBUG_FONT);
			if(fm == null)
				fm = g.getFontMetrics();
			String memoryString = "Memory Usage: " + (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024/1024 + "M / " + Runtime.getRuntime().totalMemory()/1024/1024 + "M";
			g.setColor(Color.green);
			g.drawString(memoryString, Frame.frameWidth - fm.stringWidth(memoryString) - 5, 15);
			g.setColor(Color.yellow);
			g.drawString("Target FPS: " + AniThread.currentFramerate, 5, 15);
			g.drawString("Actual FPS: " + AniThread.drawsLastSecond, 5, 30);
			g.setColor(Color.cyan);
			g.drawString("Frame #: " + frameCount, 5, 45);
		}
	}
	
}
