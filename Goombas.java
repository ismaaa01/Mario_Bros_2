package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Action;
import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;

public class Goombas extends MovingObject {
	

	private final String icono = Messages.GOOMBA;
	
	public Goombas(GameWorld game,Position pos) {
		super(game,pos,Action.LEFT);
	}
	
	public String getIcon() {
		if(super.isAlive()) {
			return icono;
		}
		return Messages.EMPTY;
	}

	@Override
	public boolean interactWith(GameItem other) {
		boolean can_interact = other.isInPosition(this.pos);
		boolean has_interacted = false;
		if (can_interact && this.isAlive()) {
			has_interacted = other.receiveInteraction(this); 
        }
		return can_interact && has_interacted;
	}

	@Override
	public boolean receiveInteraction(ExitDoor obj) {
		reverse_dir();
		return true;
	}

	@Override
	public boolean receiveInteraction(Mario obj) {
	    super.dead();
	    obj.receiveInteraction(this);
        return true;
	}
}