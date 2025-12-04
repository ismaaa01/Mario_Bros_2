package tp1_2.logic.gameobjects;

import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;
import tp1_2.view.Messages;
import tp1_2.exceptions.GameParseException;
import tp1_2.exceptions.ObjectParseException;

public abstract class GameObject implements GameItem{ 

	protected Position pos; // If you can, make it private.
	private boolean isAlive;
	protected GameWorld game;
	private String Name;
	private String Shortcut;
	
	public GameObject(String name,String shortcut) {
		this.Name = name;
		this.Shortcut = shortcut;
		this.isAlive = true;
	}
	
	public GameObject(GameWorld game, Position pos,String name,String shortcut) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		this.Name = name;
		this.Shortcut = shortcut;
	}
	
	public void receive_world(GameWorld game){
		this.game = game;
	}
	
	public GameObject parse(String[] info,Position pos) throws GameParseException{
		if(matchObjName(info[0]) && info.length > 1) 
			throw new ObjectParseException(Messages.ARGS_PARSE_ERROR);

		if(matchObjName(info[0])) {
			this.pos = pos;
			return this;
		}
		return null;
	}
	
	public boolean matchObjName(String n) {
		return (n.equalsIgnoreCase(Name) || n.equalsIgnoreCase(Shortcut));
	}
	
	public String stringify() {
		String str = "(";
		str += String.valueOf(pos.get_row())+","+String.valueOf(pos.get_col())+")" + " "+ this.Name;
		return str;
	}
	
	public boolean isInPosition(Position p) {
		return pos.equals(p);
	}
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}

	public abstract void update();
	
	public boolean isSolid() {
		return false; 
	}
	
	public abstract String getIcon();
	
	public boolean interactWith(GameItem other) {
		return false;
	}
	
	public boolean receiveInteraction(Land obj) {
		return false;
	}
	public boolean receiveInteraction(ExitDoor obj) {
		return false;
	}
	public boolean receiveInteraction(Mario obj) {
		return false;
	}
	public boolean receiveInteraction(Goombas obj) {
		return false;
	}
	public boolean receiveInteraction(Box obj) {
		return false;
	}
	public boolean receiveInteraction(Mushroom obj) {
		return false;
	}
}