package Characters;

/* ===============================================================================================================================
 * Groups sort characters.
 * They can be created either by appearing in a character's chosen group(s) or being mentioned in a group listing in a JSON file.
 * Already existing groups can be given attributes using group listings.
 * ===============================================================================================================================
*/

import java.util.ArrayList;
import java.util.HashMap;

import Global.Constants;
import Utility.Logging;

public class Group {
	
	// The name for the group.
	public String name;
	
	// The list of characters/subgroups in the group.
	public ArrayList<Character> characters = new ArrayList<Character>();
	public ArrayList<Group> subgroups = new ArrayList<Group>();
	
	// Booleans.
	public boolean hasCharacters = false;
	public boolean hasSubgroups = false;
	
	// A basic constructor that is used only when loading groups directly from a JSON file rather than a character sort.
	public Group() { }
	
	// Another constructor that is used only when loading directly from a character sort.
	public Group(String groupName) {
		this.name = groupName;
		Logging.debug("Group " + groupName + " created successfully.");
	}
	
	// Static stuff.
	public static HashMap<String, Group> groups = new HashMap<String, Group>();
	public static HashMap<String, Group> groups_noSubgroups = new HashMap<String, Group>();
	
	public static void addToGroup(Character character, String groupName) {
		if(!groups.containsKey(groupName)) {
			groups.put(groupName, new Group(groupName));
			groups_noSubgroups.put(groupName, groups.get(groupName));
		}
	}
	
	public static void createGroupWithData(HashMap<String, Object> groupData, String filePath) {
		String groupName = (String) groupData.get(Constants.CHARACTER_NAME_KEY);
		// Activates if there isn't a group name in this group.
		if(groupName == null) {
			Logging.warning(filePath + Constants.CHARACTER_GROUP_NO_NAME_ERROR);
			if(CharacterLoader.filesWithErrors.containsKey(filePath))
				CharacterLoader.filesWithErrors.replace(filePath, CharacterLoader.filesWithErrors.get(filePath) + " It also " + Constants.CHARACTER_GROUP_NO_NAME_ERROR);
			CharacterLoader.filesWithErrors.put(filePath, Constants.CHARACTER_GROUP_NO_NAME_ERROR);
			return;
		}
		// Adds group to list of groups if it isn't already there.
		if(!groups.containsKey(groupName)) {
			groups.put(groupName, new Group(groupName));
			groups_noSubgroups.put(groupName, groups.get(groupName));
		}
		// Checks for subgroups and acts accordingly.
		Object subgroups;
		if((subgroups = groupData.get(Constants.CHARACTER_GROUP_SUBGROUP_KEY)) != null) {
			if(subgroups.getClass() == Object[].class)
				for(Object o : (Object[]) subgroups) {
					groups.get(groupName).hasSubgroups = true;
					String subgroupName = o.toString();
					if(!groups.containsKey(subgroupName)) {
						groups.put(subgroupName, new Group(subgroupName));
					}
					groups.get(groupName).subgroups.add(groups.get(subgroupName));
					if(groups_noSubgroups.containsKey(subgroupName))
						groups_noSubgroups.remove(subgroupName);
				}
		}
	}
}
