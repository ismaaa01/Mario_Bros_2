package tp1_2.logic.gameobjects;
import tp1_2.view.Messages;
import tp1_2.logic.Game;
import tp1_2.logic.Position;

public class Land extends GameObject {
	
	
	private final String icono =Messages.LAND;
	
	public Land(Game game,Position pos) {
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

	public void update() {
		
	}
	
	@Override
	public boolean receiveInteraction(Land obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveInteraction(ExitDoor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveInteraction(Mario obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveInteraction(Goombas obj) {
		// TODO Auto-generated method stub
		return false;
	}

}