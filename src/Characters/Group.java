package Characters;

import java.util.ArrayList;
import java.util.HashMap;

public class Group {
	
	public ArrayList<Character> characters;
	
	// Static stuff.
	public static HashMap<String, Group> groups = new HashMap<String, Group>();
	
	public static void addToGroup(Character character, String groupName) {
		if(!groups.containsKey(groupName)) {
			
		}
	}
	
	public static void createGroupWithData(HashMap<String, Object> groupData) {
		
	}
}
