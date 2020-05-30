package Screens;

/* ===============================================
 * TestScreen is for testing! Obviously!
 * This one was made to test character loading.
 * ===============================================
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import Characters.Character;
import Characters.CharacterLoader;
import Characters.Group;
import _Main.Panel;

public class TestScreen2 implements Screen {

	// Graphic variables.
	private int listHeight = 15;
	private Font listFont = new Font("Courier New", 0, 14);
	private FontMetrics fm;
	
	// Character variables.
	public boolean charactersCounted = false;
	public int characterCount = 0;
	public int groupCount = 0;
	
	
	// Input methods.
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.isShiftDown())
			listHeight-= e.getWheelRotation() * 35;
		listHeight-= e.getWheelRotation() * 10;
		Panel.panel.repaint();
	}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}	
	public void keyReleased(KeyEvent e) {}
	
	
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
		int currentHeight = listHeight;
		for(String groupName : Group.groups_noSubgroups.keySet()) {
			Group gr = Group.groups.get(groupName);
			currentHeight = drawRecursive(g, gr, 0, currentHeight+=15);
			if(!charactersCounted)
				characterCount+= gr.characterCount;
		}
		charactersCounted = true;
		g.setColor(Color.red);
		g.drawString("(" + groupCount + " groups) (" + characterCount + " characters)", 20, listHeight);
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