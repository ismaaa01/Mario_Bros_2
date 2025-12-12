package tp1_2.logic;


import java.util.ArrayList;
import java.util.List;
import tp1_2.logic.gameobjects.GameItem;
import tp1_2.logic.gameobjects.GameObject;
import tp1_2.view.Messages;
import java.lang.StringBuilder;

final class GameObjectContainer {
	
	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	public void add(GameObject object) {
		this.objects.add(object);
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
		for (int i = 0; i < objects.size();i++) {
			if(objects.get(i).isAlive()) {
				objects.get(i).update();
			}
		}
	    clean();
	}

	private boolean clean() {
		boolean removed = false;
		for(int i = 0; i < objects.size();i++) {
			if(!objects.get(i).isAlive()) {
				objects.remove(i);
				removed = true;
			}
		}
		return removed;
	}
	
	public boolean doInteractionsFrom(GameItem from) {
		boolean interact = false;
		for (int i = 0; i < objects.size();i++) {
			if(objects.get(i).isAlive() && from.isAlive()) {
				from.interactWith(objects.get(i));
				objects.get(i).interactWith(from);
			}
	    }
	    return interact;
	}
	
	public String toString() {
		StringBuilder container = new StringBuilder();
		for(GameObject object:objects) {
			container.append(object+"\n");
		}
		return container.toString();
	}
}