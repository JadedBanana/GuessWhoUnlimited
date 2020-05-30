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
	
	// The current sort.
	public String currentSort = Constants.CHARACTER_SORT_DEFAULT_KEY;
	public boolean reversedSort = false;
	
	// Characters/Subgroups/Parent groups in the group.
	public ArrayList<String> sorts = new ArrayList<String>();
	public HashMap<String, Object> characters = new HashMap<String, Object>();
	public ArrayList<Group> subgroups = new ArrayList<Group>();
	public ArrayList<Group> parentGroups = new ArrayList<Group>();
	public int characterCount = 0;
	public int subgroupCount = 0;
	
	// Booleans.
	public boolean hasCharacters = false;
	public boolean hasSubgroups = false;
	public boolean hasParents = false;
	
	
	// A basic constructor that is used only when loading groups directly from a JSON file rather than a character sort.
	// It is used to initialize the sorts for the characters.
	private Group() {
		// Default sort.
		characters.put(Constants.CHARACTER_SORT_DEFAULT_KEY, new CharacterTree());
		((CharacterTree) characters.get(Constants.CHARACTER_SORT_DEFAULT_KEY)).type = CharacterTree.TreeType.DEFAULT;
		sorts.add(Constants.CHARACTER_SORT_DEFAULT_KEY);
		// Alphabetical sort.
		characters.put(Constants.CHARACTER_SORT_AZ_KEY, new CharacterTree());
		((CharacterTree) characters.get(Constants.CHARACTER_SORT_AZ_KEY)).type = CharacterTree.TreeType.ALPHABETICAL;
		sorts.add(Constants.CHARACTER_SORT_AZ_KEY);
		// Load order sort.
		characters.put(Constants.CHARACTER_SORT_LOAD_KEY, new ArrayList<Character>());
		sorts.add(Constants.CHARACTER_SORT_LOAD_KEY);
	}
	
	
	// Another constructor that is used only when loading directly from a character sort.
	private Group(String groupName) {
		this();
		this.name = groupName;
		Logging.debug("Group " + groupName + " created successfully.");
	}
	
	
	// The function that adds the given character to the group.
	@SuppressWarnings("unchecked")
	public void add(Character character, double pos) {
		// Checks to see if this character already exists within this group.
		// If so, it updates the position of it in the default list.
		ArrayList<Character> loadOrderList = ((ArrayList<Character>) characters.get(Constants.CHARACTER_SORT_LOAD_KEY));
		if(loadOrderList.contains(character))
			((CharacterTree) characters.get(Constants.CHARACTER_SORT_DEFAULT_KEY)).replaceNode(character, pos);
		// Activates if this character ISN'T already in this group.
		else {
			loadOrderList.add(character);
			characterCount = loadOrderList.size();
			// Adds character to parent as well.
			for(Group parent : parentGroups)
				parent.addFromChild(character, pos);
			// Adds character to each sort.
			for(String sort : characters.keySet())
				if(!sort.equals(Constants.CHARACTER_SORT_LOAD_KEY))
					((CharacterTree) characters.get(sort)).add(character, pos);
			hasCharacters = true;
		}
	}
	
	
	// Adds a character specifically from a child group.
	@SuppressWarnings("unchecked")
	public void addFromChild(Character character, double pos) {
		// Checks to see if this character already exists within this group.
		// If so, it cancels.
		ArrayList<Character> loadOrderList = ((ArrayList<Character>) characters.get(Constants.CHARACTER_SORT_LOAD_KEY));
		if(loadOrderList.contains(character))
			return;
		// Activates if this character ISN'T already in this group.
		loadOrderList.add(character);
		characterCount = loadOrderList.size();
		// Adds character to parent as well.
		for(Group parent : parentGroups)
			parent.addFromChild(character, pos);
		// Adds character to each sort.
		for(String sort : characters.keySet())
			if(!sort.equals(Constants.CHARACTER_SORT_LOAD_KEY))
				((CharacterTree) characters.get(sort)).add(character, pos);
		hasCharacters = true;
	}
	
	
	// Adds a parent to this group.
	public void addParent(Group parent) {
		parentGroups.add(parent);
		hasParents = true;
		((CharacterTree) characters.get(Constants.CHARACTER_SORT_DEFAULT_KEY)).copyNodes(parent);
	}
	
	
	// The function that returns the sorted characters.
	@SuppressWarnings("unchecked")
	public ArrayList<Character> getSortedCharacters() {
		ArrayList<Character> characterList;
		if(currentSort.equals(Constants.CHARACTER_SORT_LOAD_KEY)) {
			characterList = ((ArrayList<Character>) characters.get(Constants.CHARACTER_SORT_LOAD_KEY));
			if(reversedSort) {
				ArrayList<Character> temp = characterList;
				characterList = new ArrayList<Character>();
				for(int i = 0; i < temp.size(); i++)
					characterList.add(temp.remove(0));
			}
		} else
			characterList = ((CharacterTree) (characters.get(currentSort))).getCharacterList(reversedSort);
		return characterList;
	}
	
	
	// Everything from here on out is all static.
	// This all is used in creation and modification of groups.
	public static HashMap<String, Group> groups = new HashMap<String, Group>();
	public static HashMap<String, Group> groups_noSubgroups = new HashMap<String, Group>();
	
	
	// Adds a character to a specific group.
	public static void addToGroup(Character character, String groupName, double pos) {
		if(!groups.containsKey(groupName)) {
			groups.put(groupName, new Group(groupName));
			groups_noSubgroups.put(groupName, groups.get(groupName));
		}
		groups.get(groupName).add(character, pos);
	}
	
	
	// Creates a group with data. 
	// When a character loads a group, there is no data, and it will be filled in automatically when the game finds the group data.
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
			if(subgroups.getClass() == Object[].class) {
				Group currentGroup = groups.get(groupName);
				for(Object o : (Object[]) subgroups) {
					String subgroupName = o.toString();
					if(!groups.containsKey(subgroupName)) {
						groups.put(subgroupName, new Group(subgroupName));
					}
					currentGroup.subgroups.add(groups.get(subgroupName));
					currentGroup.hasSubgroups = true;
					currentGroup.subgroupCount++;
					if(groups_noSubgroups.containsKey(subgroupName))
						groups_noSubgroups.remove(subgroupName);
					groups.get(subgroupName).addParent(currentGroup);
				}
			}
		}
	}
	
}
