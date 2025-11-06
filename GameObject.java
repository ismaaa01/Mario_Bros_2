package tp1_2.logic.gameobjects;

import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;

public abstract class GameObject implements GameItem{ // TODO 

	protected Position pos; // If you can, make it private.
	private boolean isAlive;
	protected GameWorld game; 
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {
		return isAlive && this.pos.equals(p);
	}
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	

	public abstract void update();
	
	// TODO implement and decide, Which one is abstract?
	public boolean isSolid() {
		return false; // eso es por defecto luego sino cada clase dira
	}
	
	public abstract String getIcon();
	
	public boolean interactWith(GameItem other) {
		return false;
	}
	
	public  boolean receiveInteraction(Land obj) {
		return false;
	}
	public  boolean receiveInteraction(ExitDoor obj) {
		return false;
	}
	public  boolean receiveInteraction(Mario obj) {
		return false;
	}
	public  boolean receiveInteraction(Goombas obj) {
		return false;
	}
}