package tp1_2.logic.gameobjects;
import tp1_2.view.Messages;
import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;


public class Land extends GameObject {
	
	
	private final String icono =Messages.LAND;
	
	public Land(GameWorld game,Position pos) {
		super(game,pos);
	}
	
	public boolean isSolid() {
		return true;
	}
	
	@Override
	public String getIcon() {
		return icono;
	}

	@Override
	public boolean interactWith(GameItem other) {
		 if (other.isInPosition(this.pos)) {
	            return other.receiveInteraction(this);
	        }
	     return false;
	}
	public void update() {}
}