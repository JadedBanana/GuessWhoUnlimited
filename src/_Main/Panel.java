package _Main;

/* ===============================================
 * Panel is basically what draws the entire game.
 * It exists within the frame, and only there.
 * ===============================================
*/

import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	// Frame count.
	public static long frameCount = 0;
	
	// Self-referencing variable, allows this to be pulled from anywhere with minimal issue.
	public static Panel panel;
	
	public Panel(Frame frame) {
		panel = this;
	}
	
	public void paintComponent(Graphics g)
	{
		frameCount++;
		super.paintComponent(g);
		g.drawString("peeb", 40, 40);
	}
}
