package tp1_2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1_2.logic.gameobjects.GameObject;

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
	
	public String postitionToString(Position pos) {
		//TODO fill your code
		return "";
	}

	//TODO fill your code

	// TODO you should write a toString method to return the string that represents the object status
	// @Override
	// public String toString()
}
