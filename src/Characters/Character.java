package Characters;

import java.util.HashMap;
import Global.Constants;
import Utility.Logging;

public class Character {
	
	// Required keys.
	public String name;
	
	public Character(HashMap<String, Object> characterData, String filePath) {
		name = (String) characterData.get(Constants.CHARACTER_NAME_KEY);
		// Activates if there is no name for the character.
		if(name == null) {
			Logging.warning(filePath + Constants.CHARACTER_NO_NAME_ERROR);
			if(CharacterLoader.filesWithErrors.containsKey(filePath))
				CharacterLoader.filesWithErrors.replace(filePath, CharacterLoader.filesWithErrors.get(filePath) + " It also " + Constants.CHARACTER_NO_NAME_ERROR);
			CharacterLoader.filesWithErrors.put(filePath, Constants.CHARACTER_NO_NAME_ERROR);
			return;
		}
		Logging.debug("Character " + name + " loaded successfully.");
	}
}
