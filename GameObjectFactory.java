package tp1_2.logic.gameobjects;

import java.util.*;
import tp1_2.logic.*;

public class GameObjectFactory {
	
	
	
	private final List<GameObject> availableObjects; 
	
	public GameObjectFactory() {
		availableObjects = Arrays.asList(
				new Land(),
				new ExitDoor(),
				new Goombas(),
				new Mario(),
				new Box(),
				new Mushroom()
				);
	}
	
	public GameObject parse(String[] ObjectInfo) {
		String string_pos = ObjectInfo[0];
		String _pos = string_pos.substring(1, string_pos.length()-1);
		String[] positions = _pos.split(",");
		Position pos = new Position(Integer.parseInt(positions[0]),Integer.parseInt(positions[1]));
		String[] name_obj = Arrays.copyOfRange(ObjectInfo, 1, ObjectInfo.length);
		if(!pos.in_game()) {return null;}
		for(GameObject obj:availableObjects) {
			if(obj.parse(name_obj,pos) != null) {
				return obj;
			}
		}
		return null;
	}
	
}
