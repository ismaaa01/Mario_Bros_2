package tp1_2.logic.gameobjects;

import tp1_2.logic.Action;
import tp1_2.logic.Game;
import tp1_2.logic.Position;

public abstract class GameObject implements GameItem{ // TODO 

	protected Position pos; // If you can, make it private.
	private boolean isAlive;
	protected Game game; 
	
	public GameObject(Game game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {
		return this.pos.equals(p);
	}
 	public Position getPosition() {
 		return pos;
 	}
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	
	
	// TODO implement and decide, Which one is abstract?
	public boolean isSolid() {
		return false; // eso es por defecto luego sino cada clase dira
	}
	// public void update()
	
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {
		// TODO Auto-generated method stub
	}
}
