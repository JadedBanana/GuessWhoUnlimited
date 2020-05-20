package Characters;

/* ========================================================
 * Characters are the core of the game.
 * They have names and images, and are sorted into groups.
 * ========================================================
*/

import java.util.HashMap;
import Global.Constants;
import Utility.Logging;
import Utility.Num;

public class Character {
	
	// Required keys.
	public String name;
	public String filePath;
	
	// Everything else.
	public HashMap<String, Object> characteristics;
	
	public Character(HashMap<String, Object> characterData, String filePath) {
		this.filePath = filePath;
		name = (String) characterData.get(Constants.CHARACTER_NAME_KEY);
		// Activates if there is no name for the character.
		if(name == null) {
			Logging.warning(filePath + Constants.CHARACTER_NO_NAME_ERROR);
			if(CharacterLoader.filesWithErrors.containsKey(filePath))
				CharacterLoader.filesWithErrors.replace(filePath, CharacterLoader.filesWithErrors.get(filePath) + " It also " + Constants.CHARACTER_NO_NAME_ERROR);
			CharacterLoader.filesWithErrors.put(filePath, Constants.CHARACTER_NO_NAME_ERROR);
			return;
		}
		// Sorts this character into group(s).
		if(characterData.containsKey(Constants.CHARACTER_GROUP_KEY)) {
			Object group = characterData.get(Constants.CHARACTER_GROUP_KEY);
			// Activates if there's a dict for the groups.
			if(group.getClass() == HashMap.class) {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> groupsHash = (HashMap<String, Object>) group;
				for (String groupName : groupsHash.keySet())
					try {
						Group.addToGroup(this, groupName, Num.doubleVal(groupsHash.get(groupName)));
					} catch(ClassCastException e) {
						// Activates if there isn't a number for the dict, which lets it sort by default.
						Group.addToGroup(this, groupName, Double.MAX_VALUE);
						Logging.warning(filePath + "'s " + name + Constants.CHARACTER_INVALID_GROUP_ERROR);
						if(CharacterLoader.filesWithErrors.containsKey(filePath))
							CharacterLoader.filesWithErrors.replace(filePath, CharacterLoader.filesWithErrors.get(filePath) + name + " also " + Constants.CHARACTER_INVALID_GROUP_ERROR);
						CharacterLoader.filesWithErrors.put(filePath, name + Constants.CHARACTER_INVALID_GROUP_ERROR);
						return;
					}
			}
			// Activates if there's a list for the groups.
			else if(group.getClass() == Object[].class) {
				String[] groupsString = (String[]) group;
				for (String groupName : groupsString)
					Group.addToGroup(this, groupName, Double.MAX_VALUE);
			}
			// Activates if there's a string for the group.
			else if(group.getClass() == String.class) {
				Group.addToGroup(this, group.toString(), Double.MAX_VALUE);
			}
			// Activates if there's an error.
			else {
				Logging.warning(filePath + "'s " + name + Constants.CHARACTER_INVALID_GROUP_ERROR);
				if(CharacterLoader.filesWithErrors.containsKey(filePath))
					CharacterLoader.filesWithErrors.replace(filePath, CharacterLoader.filesWithErrors.get(filePath) + name + " also " + Constants.CHARACTER_INVALID_GROUP_ERROR);
				CharacterLoader.filesWithErrors.put(filePath, ": " + name + Constants.CHARACTER_INVALID_GROUP_ERROR);
				return;
			}
		}
		characteristics = characterData;
		Logging.debug("Character " + name + " loaded successfully.");
	}
	
	public String toString() {
		return name;
	}
}
