package Screens;

import java.awt.Color;
import java.awt.Font;

/* ===============================================
 * TestScreen is for testing! Obviously!
 * This one was made to test character loading.
 * ===============================================
*/

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import Characters.Character;
import Characters.CharacterLoader;
import Characters.Group;
import Utility.Logging;
import _Main.Panel;

public class TestScreen2 implements Screen {

	private int listHeight = 0;
	private Font listFont = new Font("Courier New", 0, 14);
	
	// Input methods.
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
		public void mouseWheelMoved(MouseWheelEvent e) {
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
			int currentHeight = listHeight;
			for(String groupName : Group.groups_noSubgroups.keySet()) {
				currentHeight = drawRecursive(g, Group.groups.get(groupName), 0, currentHeight+=15);
			}
		}
		
		public int drawRecursive(Graphics g, Group currentGroup, int depth, int currentHeight) {
			g.drawString(currentGroup.name, 20 + 30 * depth, currentHeight);
			if(currentGroup.hasSubgroups) {
				if(currentGroup.hasCharacters) {
					g.drawString("All Characters", 20 + 30 * (depth + 1), currentHeight+=15);
					ArrayList<Character> groupCharacters = currentGroup.getSortedCharacters();
					for(int i = 0; i < groupCharacters.size(); i++) {
						g.drawString(groupCharacters.get(i).name, 20 + 30 * (depth + 2), currentHeight+=15);
					}
				}
				for(int i = 0; i < currentGroup.subgroups.size(); i++)
					currentHeight = drawRecursive(g, currentGroup.subgroups.get(i), depth + 1, currentHeight + 15);
			} else if(currentGroup.hasCharacters) {
				ArrayList<Character> groupCharacters = currentGroup.getSortedCharacters();
				for(int i = 0; i < groupCharacters.size(); i++) {
					g.drawString(groupCharacters.get(i).name, 20 + 30 * (depth + 1), currentHeight+=15);
				}
			}
			return currentHeight;
		}
		
		// Method that notifies the screen that it is being switched away from/to.
		public void notifySwitchAway() {}
		public void notifySwitchTo() {}
}
