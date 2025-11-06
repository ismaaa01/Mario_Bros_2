package tp1_2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1_2.logic.gameobjects.GameObject;
import tp1_2.view.Messages;

public class GameObjectContainer {
	
	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {
		objects.add(object);
	//TODO fill your code
	}
	public boolean isSolid(int col, int row) {
		Position p = new Position(row,col);
		for (GameObject object:objects) {
			if(object.isInPosition(p)) {
				return (object.isSolid());
			}
		}
		return false;
	}
	
	public String postitionToString(Position pos) {
		String pos_string = Messages.EMPTY;
		for (GameObject object:objects) {
			if(object.isInPosition(pos)) {
				pos_string += object.getIcon();
			}
		}
		return pos_string;
	}
	
	public void update() {
		for (GameObject obj: objects) {
			obj.update();
		}
	}

	//TODO fill your code
	public boolean doInteractionsFrom(GameObject from) {
		boolean interact = false;
	    for (GameObject obj : objects) {
	    	if(from != obj) {
	    		if(interact == false) {
	    			interact = obj.interactWith(from);
	    		}
	    		if(interact == false) {
	    			interact = from.interactWith(obj);
	    		}
	    		else{
	    			obj.interactWith(from);
	    			from.interactWith(obj);
	    		}
	    	}
	    }
	    return interact;
	}
	
	

	// TODO you should write a toString method to return the string that represents the object status
	// @Override
	// public String toString()
}