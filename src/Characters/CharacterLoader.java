package Characters;

/* ========================================================================
 * CharacterLoader loads characters and groups (misleading title, I know).
 * TODO: Add tutorial/example JSON files.
 * ========================================================================
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import Global.Constants;
import Utility.JSON;
import Utility.Logging;
import Utility.JSON.JSONFormattingError;

public class CharacterLoader {

	// All the static loader stuff.
	public static String[] characterDirs = Constants.DEFAULT_CHARACTER_DIRS;
	public static HashMap<String, String> filesWithErrors = new HashMap<String, String>();
	
	// Loads in all the characters.
	public static void load() {
		for (String characterDir : characterDirs) {
			try {
				Logging.info(Constants.CHARACTER_DIR_LOADING_MESSAGE + characterDir);
				File currentFileFolder = new File(characterDir);
				currentFileFolder.mkdirs();
				for(final File fileEntry : currentFileFolder.listFiles())
					findValidJSON(fileEntry);
			} catch(NullPointerException e) { }
		}
	}
	
	// Looks for valid JSON in the given folder, or acts recursively for sub-folders.
	@SuppressWarnings("unchecked")
	private static void findValidJSON(File currentFileFolder) {
		if(currentFileFolder.isDirectory())
			for(final File fileEntry : currentFileFolder.listFiles())
				findValidJSON(fileEntry);
		else if(currentFileFolder.getAbsolutePath().toLowerCase().endsWith(".json")) {
			try {
				HashMap<String, Object> data = JSON.read(currentFileFolder);
				String type = (String) data.get(Constants.CHARACTER_TYPE_KEY);
				String path = currentFileFolder.getAbsolutePath();
				// Sees if there is a single 'type' key in there.
				if(type != null)
					// Character.
					if(type.equals(Constants.CHARACTER_CHARACTER_TYPE_KEY))
						new Character(data, path);
					// Group.
					else if(type.contentEquals(Constants.CHARACTER_GROUP_TYPE_KEY))
						Group.createGroupWithData(data, path);
					// Other.
					else {
						Logging.warning(path + Constants.CHARACTER_INVALID_KEY_ERROR + Constants.CHARACTER_TYPE_KEY + "\". Character/group" + Constants.CHARACTER_UNLOADABLE_ERROR);
						filesWithErrors.put(path, Constants.CHARACTER_INVALID_KEY_ERROR + Constants.CHARACTER_TYPE_KEY + "\".");
					}
				// If there isn't a single 'type' key in there.
				else {
					// Characters.
					if(data.get(Constants.CHARACTER_CHARACTER_TYPE_KEY) != null) {
						Object characterThing = data.get(Constants.CHARACTER_CHARACTER_TYPE_KEY);
						// Activates if there's multiple characters.
						if(characterThing.getClass().equals(Object[].class)) {
							Object[] characterArray = (Object[]) characterThing;
							for(int i = 0; i < characterArray.length; i++)
								if(characterArray[i].getClass().equals(HashMap.class))
									new Character((HashMap<String, Object>) characterArray[i], path);
						}
						// Activates if there's ONE character.
						else if(characterThing.getClass().equals(HashMap.class))
							new Character((HashMap<String, Object>) characterThing, path);
						// Other.
						else {
							Logging.warning(path + Constants.CHARACTER_INVALID_KEY_ERROR + Constants.CHARACTER_CHARACTER_TYPE_KEY + "\". Character(s)" + Constants.CHARACTER_UNLOADABLE_ERROR);
							filesWithErrors.put(path, Constants.CHARACTER_INVALID_KEY_ERROR + Constants.CHARACTER_CHARACTER_TYPE_KEY +"\".");
						}
					}
					// Groups.
					if(data.get(Constants.CHARACTER_GROUP_TYPE_KEY) != null) {
						Object groupThing = data.get(Constants.CHARACTER_GROUP_TYPE_KEY);
						// Activates if there's multiple groups.
						if(groupThing.getClass().equals(Object[].class)) {
							Object[] groupArray = (Object[]) groupThing;
							for(int i = 0; i < groupArray.length; i++) {
								if(groupArray[i].getClass().equals(HashMap.class))
									Group.createGroupWithData((HashMap<String, Object>) groupArray[i], path);
							}
						}
						// Activates if there's ONE group.
						else if(groupThing.getClass().equals(HashMap.class))
							Group.createGroupWithData((HashMap<String, Object>) groupThing, path);
						// Other.
						else {
							Logging.warning(path + "" + Constants.CHARACTER_GROUP_TYPE_KEY +"\". Group(s)" + Constants.CHARACTER_UNLOADABLE_ERROR);
							filesWithErrors.put(path, Constants.CHARACTER_INVALID_KEY_ERROR + Constants.CHARACTER_GROUP_TYPE_KEY +"\".");
						}
					}
				}
			} catch (FileNotFoundException | JSONFormattingError e) {
				Logging.warning(currentFileFolder.getAbsolutePath() + Constants.CHARACTER_FORMATTING_ERROR + " Character(s)/group(s)" + Constants.CHARACTER_UNLOADABLE_ERROR);
				filesWithErrors.put(currentFileFolder.getAbsolutePath(), Constants.CHARACTER_FORMATTING_ERROR);
			}
		}
	}
}