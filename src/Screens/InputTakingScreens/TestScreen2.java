package Screens.InputTakingScreens;

/* =============================================
 * TestScreen is for testing! Obviously!
 * This one was made to test character loading.
 * =============================================
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import Characters.Character;
import Characters.CharacterLoader;
import Characters.Group;
import Screens.InputTakingScreen;
import Utility.Logging;
import _Main.Frame;
import _Main.Input;
import _Main.Panel;

public class TestScreen2 extends InputTakingScreen {

	// Method that says whether or not the current screen uses the AniThread.
	public boolean usesAniThread() {
		return false;
	}
	
	// Graphic variables.
	private int listHeight = 15;
	private Font listFont = new Font("Courier New", 0, 14);
	private FontMetrics fm;
	
	// Character variables.
	public boolean charactersCounted = false;
	public int characterCount = 0;
	public int groupCount = 0;
	
	
	// Input methods. Scrolls the list up and down.
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.isShiftDown())
			listHeight-= e.getWheelRotation() * 35;
		listHeight-= e.getWheelRotation() * 10;
		Panel.panel.repaint();
	}
	public void keyPressed(KeyEvent e) { 
		byte multiplier = 10;
		if(e.isShiftDown())
			multiplier = 35;
		byte direction = Input.getButtonCode(e.getKeyCode());
		Logging.debug(direction);
		if(direction == Input.DOWN)
			listHeight-= multiplier;
		else if(direction == Input.UP)
			listHeight+= multiplier;
		else if(direction == Input.RIGHT)
			listHeight-= multiplier * 4;
		else if(direction == Input.LEFT)
			listHeight+= multiplier * 4;
		else return;
		Panel.panel.repaint();
	}
	
	
	// Method that draws for each screen.
	public void draw(Graphics g) {
		// Waits until characters are done loading.
		if(!CharacterLoader.doneLoading) {
			while(!CharacterLoader.doneLoading)
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			draw(g);
			return;
		}
		// Draws each group.
		g.setColor(Color.white);
		g.setFont(listFont);
		// Loads FontMetrics.
		if(fm == null)
			fm = g.getFontMetrics();
		// Draws characters/groups.
		int currentHeight = listHeight;
		for(String groupName : Group.groups_noSubgroups.keySet()) {
			Group gr = Group.groups.get(groupName);
			currentHeight = drawRecursive(g, gr, 0, currentHeight+=15);
			if(!charactersCounted)
				characterCount+= gr.characterCount;
		}
		// Draws total overview on top.
		charactersCounted = true;
		g.setColor(Color.red);
		g.drawString("(" + groupCount + " groups) (" + characterCount + " characters)", 20, listHeight);
		// Draws memory usage in top right.
		String memoryString = "Memory Usage: " + (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024/1024 + "M / " + Runtime.getRuntime().totalMemory()/1024/1024 + "M";
		g.setColor(Color.green);
		g.drawString(memoryString, Frame.frameWidth - fm.stringWidth(memoryString) - 5, 15);
	}
	
	
	// Recursive method that dives into groups and writes character names.
	public int drawRecursive(Graphics g, Group currentGroup, int depth, int currentHeight) {
		if(!charactersCounted)
			groupCount++;
		g.drawString(currentGroup.name, 20 + 30 * depth, currentHeight);
		if(currentGroup.hasSubgroups) {
			g.setColor(Color.gray);
			String subgroupString = "(" + currentGroup.subgroupCount + " subgroups)";
			g.drawString(subgroupString, 20 + 30 * depth + fm.stringWidth(currentGroup.name + " "), currentHeight);
			if(currentGroup.hasCharacters) {
				g.drawString("(" + currentGroup.characterCount + " characters)", 20 + 30 * depth + fm.stringWidth(currentGroup.name + "  " + subgroupString), currentHeight);
				g.setColor(Color.white);
				g.drawString("All Characters", 20 + 30 * (depth + 1), currentHeight+=15);
				ArrayList<Character> groupCharacters = currentGroup.getSortedCharacters();
				for(int i = 0; i < groupCharacters.size(); i++) {
					g.drawString(groupCharacters.get(i).name, 20 + 30 * (depth + 2), currentHeight+=15);
				}
			}
			else
				g.setColor(Color.white);
			for(int i = 0; i < currentGroup.subgroups.size(); i++)
				currentHeight = drawRecursive(g, currentGroup.subgroups.get(i), depth + 1, currentHeight+=15);
		} else if(currentGroup.hasCharacters) {
			g.setColor(Color.gray);
			g.drawString("(0 subgroups) (" + currentGroup.characterCount + " characters)", 20 + 30 * depth + fm.stringWidth(currentGroup.name + " "), currentHeight);
			g.setColor(Color.white);
			ArrayList<Character> groupCharacters = currentGroup.getSortedCharacters();
			for(int i = 0; i < groupCharacters.size(); i++) {
				g.drawString(groupCharacters.get(i).name, 20 + 30 * (depth + 1), currentHeight+=15);
			}
		} else {
			g.setColor(Color.gray);
			g.drawString("(0 subgroups) (0 characters)", 20 + 30 * depth + fm.stringWidth(currentGroup.name + " "), currentHeight);
			g.setColor(Color.white);
		}
		return currentHeight;
	}
	
	
	// Method that notifies the screen that it is being switched away from/to.
	public void notifySwitchAway() {}
	public void notifySwitchTo() {}
	
}