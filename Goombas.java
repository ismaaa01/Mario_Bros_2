package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Action;
import tp1_2.logic.Game;
import tp1_2.logic.Position;

public class Goombas extends MovingObject {
	
	private final String icono =Messages.GOOMBA;
	
	public Goombas(Game game,Position pos) {
		super(game,pos,Action.LEFT);
		super.dir = Action.LEFT;
	}
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIcon() {
		if(super.isAlive()) {
			return icono;
		}
		return Messages.EMPTY;
	}



	@Override
	public boolean interactWith(GameItem other) {
		 if (other.isInPosition(this.pos)) {
	            return other.receiveInteraction(this);
	        }
	        return false;
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
	    super.dead();
        return true;
	}



	@Override
	public boolean receiveInteraction(Goombas obj) {
        return true;
	}


}