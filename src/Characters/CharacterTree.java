package Characters;

/* =========================================================================================================
 * CharacterTrees are used to sort characters within groups.
 * They have 3 types: Default, Alphabetical, and Attribute.
 * Default sorts based on the numerical values provided in the JSON file for the characters.
 * Alphabetical sorts based on A to Z names.
 * Attribute sorts based on the string/number/boolean values of specific attributes.
 * (Note: for attributes where multiple types clash, it will go boolean -> number -> string in that order.)
 * TODO: Add attribute sorting.
 * =========================================================================================================
*/

import java.util.ArrayList;
import Utility.Utility;

public class CharacterTree {

	// TreeType. Categories of trees.
	public enum TreeType {
		DEFAULT, ALPHABETICAL, ATTRIBUTE
	}
		
	// Size. Shows how many characters are in this tree.
	public int size;
	// TreeType. holds the type for THIS tree.
	TreeType type;
	// Head node.
	CharacterNode headNode;
	
	// Adds characters to the tree.
	public void add(Character character, double pos) {
		if(headNode == null)
			headNode = new CharacterNode(character, pos);
		else
			addRecursive(headNode, character, pos);
	}
	
	// Recursive method that helps add characters to the tree.
	private void addRecursive(CharacterNode currentNode, Character character, double pos) {
		if(type == TreeType.DEFAULT)
			if(pos < currentNode.pos)
				if(currentNode.leftNode != null)
					addRecursive(currentNode.leftNode, character, pos);
				else
					currentNode.leftNode = new CharacterNode(character, pos);
			else
				if(currentNode.rightNode != null)
					addRecursive(currentNode.rightNode, character, pos);
				else
					currentNode.rightNode = new CharacterNode(character, pos);
		else if(type == TreeType.ALPHABETICAL)
			switch(Utility.getAlphabeticalSuperior(currentNode.character.name, character.name)) {
				case -1:
					if(currentNode.leftNode != null)
						addRecursive(currentNode.leftNode, character, pos);
					else
						currentNode.leftNode = new CharacterNode(character, pos);
					break;
				case 0:
				case 1:
					if(currentNode.rightNode != null)
						addRecursive(currentNode.rightNode, character, pos);
					else
						currentNode.rightNode = new CharacterNode(character, pos);
			}
	}
	
	// In the rare case where a node needs to be replaced due to a child group determining where in the tree this node should appear, this method will replace the existing node.
	// Replaced nodes will have null values for characters, which will not appear in the actual GUI.
	public void replaceNode(Character character, double pos) { 
		if(headNode == null)
			return;
		replaceNodeRecursive(character, headNode); 
		add(character, pos);
	}
	
	// Recursive method that helps find the node we're looking for.
	private boolean replaceNodeRecursive(Character character, CharacterNode currentNode) {
		if(currentNode.leftNode != null)
			if(replaceNodeRecursive(character, currentNode.leftNode))
				return true;
		if(currentNode.rightNode != null)
			if(replaceNodeRecursive(character, currentNode.rightNode))
				return true;
		return currentNode.character == character;
	}
	
	// Copies nodes from this CharacterTree into the given group.
	public void copyNodes(Group copyTo) {
		if(headNode == null)
			return;
		copyNodesRecursive(copyTo, headNode);
	}
	
	// Recursive method that helps copy nodes.
	public void copyNodesRecursive(Group copyTo, CharacterNode currentNode) {
		if(currentNode.leftNode != null)
			copyNodesRecursive(copyTo, currentNode.leftNode);
		if(currentNode.rightNode != null)
			copyNodesRecursive(copyTo, currentNode.rightNode);
		copyTo.addFromChild(currentNode.character, currentNode.pos);
	}
	
	// Gets an ordered list representing the tree. 
	// Forward goes in a left node -> current node -> right node format.
	// Backwards goes right node -> current node -> left node.
	public ArrayList<Character> getCharacterList(boolean backwards) { return getCharacterListRecursive(backwards, headNode); }
	
	// Recursive method that gets the character list.
	private ArrayList<Character> getCharacterListRecursive(boolean backwards, CharacterNode currentNode) {
		ArrayList<Character> characters = new ArrayList<Character>();
		if(!backwards) {
			if(currentNode.leftNode != null)
				characters.addAll(getCharacterListRecursive(backwards, currentNode.leftNode));
			characters.add(currentNode.character);
		}
		if(currentNode.rightNode != null)
			characters.addAll(getCharacterListRecursive(backwards, currentNode.rightNode));
		if(backwards) {
			characters.add(currentNode.character);
			if(currentNode.leftNode != null)
				characters.addAll(getCharacterListRecursive(backwards, currentNode.leftNode));
		}
		return characters;
	}
	
	// CharacterNode. The basic unit of character measurement.
	private class CharacterNode {
		
		// Local variables.
		public Character character;
		public double pos;
		
		// The 2 nodes below this one.
		public CharacterNode leftNode = null;
		public CharacterNode rightNode = null;
		
		// Constructor.
		public CharacterNode(Character character, double pos) {
			this.character = character;
			this.pos = pos;
		}
	}
}
