package tp1_2.logic.gameobjects;

import java.util.*;
import tp1_2.logic.*;
import tp1_2.view.Messages;
import tp1_2.exceptions.*;

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
	
	public GameObject parse(String[] ObjectInfo) throws OffBoardException, ObjectParseException,PositionParseException,ActionParseException,GameParseException{
		String string_pos = ObjectInfo[0];
		String _pos = string_pos.substring(1, string_pos.length()-1);
		String[] positions = _pos.split(",");
		try {
			Position pos = new Position(Integer.parseInt(positions[0]),Integer.parseInt(positions[1]));
			String[] name_obj = Arrays.copyOfRange(ObjectInfo, 1, ObjectInfo.length);
			if(!pos.in_game()) {throw new OffBoardException(Messages.OFFBOARD_ERROR.formatted(ObjectInfo));}
			for(GameObject obj:availableObjects) {
				try{
					if(obj.parse(name_obj,pos) != null) {
						return obj;
					}
				}catch (GameParseException e) {
					String error = "";
					for (String er: ObjectInfo)
						error += er + " ";
					if(error != "") error = error.substring(0, error.length()-1);
					throw new GameParseException(e.getMessage().formatted(error));
				}
			}
		}catch (NumberFormatException nfe) {
			throw new PositionParseException(Messages.INVALID_POSITION,nfe);
		}
		
		throw new ObjectParseException(Messages.UNKNOWN_OBJECT.formatted(ObjectInfo));
	}
	
	public static void open_box(Position pos, GameWorld game) {
		Position pos_mushroom = new Position(pos.get_row()-1,pos.get_col());
		Mushroom nuevo = new Mushroom(game,pos_mushroom);
		game.insert_obj(nuevo);
	}
	
}
