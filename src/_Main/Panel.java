package _Main;

/* ===============================================
 * Panel is basically what draws the entire game.
 * It exists within the frame, and only there.
 * ===============================================
*/

import java.awt.Graphics;

import javax.swing.JPanel;

import Utility.Logging;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	// Frame count.
	public static long frameCount = 0;
	
	// Self-referencing variable, allows this to be pulled from anywhere with minimal issue.
	public static Panel panel;
	
	// Constructor.
	public Panel(Frame frame) {
		Logging.debug("Adding panel to frame.");
		panel = this;
		setFocusable(true);
		requestFocusInWindow();
		new Input(this);
	}
	
	// Method that draws every time something calls for new frames to be drawn.
	public void paintComponent(Graphics g)
	{
		frameCount++;
		super.paintComponent(g);
		g.drawString("peeb", 40, 40);
	}
	
}
