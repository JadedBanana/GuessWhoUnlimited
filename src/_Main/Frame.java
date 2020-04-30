package _Main;

/* ==================================================
 * Frame is essentially the window the game runs in.
 * ==================================================
*/

import javax.swing.JFrame;

import Global.Constants;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	// Self-referencing variable, allows this to be pulled from anywhere with minimal issue.
	public static Frame frame;
	
	// Current frame size. Set to the constant value first.
	public static int frameWidth = Constants.DEFAULT_WIDTH;
	public static int frameHeight = Constants.DEFAULT_HEIGHT;
	
	// Constructor.
	public Frame(String[] args) {
		// Adds the panel to the frame.
		add(new Panel(this));
		frame = this;
		
		// Customizes the window to the game's specifications.
		setResizable(Constants.RESIZABLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800 + this.getInsets().right + this.getInsets().left, 600 + this.getInsets().top + this.getInsets().bottom);
	}
	
}
