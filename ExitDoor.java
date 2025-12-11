package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;

public class ExitDoor extends GameObject {
	
	private final String icono = Messages.EXIT_DOOR;
	private static final String Name = Messages.EXITDOOR_NAME;
	private static final String Shortcut = Messages.EXITDOOR_SHORTCUT;
	
	public ExitDoor() {
		super(Name,Shortcut);
	}
	
	public  ExitDoor(GameWorld game,Position pos){
		super(game,pos,Name,Shortcut);
	}
	
	public ExitDoor(ExitDoor save) {
		super(save);
	}
	
	@Override
	public String getIcon() {
		return icono;
	}

	public void update() {}

	public boolean interactWith(GameItem other) {
		boolean can_interact = other.isInPosition(this.pos);
		boolean has_interacted = false;
		if (can_interact && this.isAlive()) {
			has_interacted = other.receiveInteraction(this); 
        }
		return can_interact && has_interacted;
	}
	
	@Override
	public boolean receiveInteraction(Mario obj) {
	     game.marioExited();
	     return false;
	}

	@Override
	public GameObject clonar() {
		return new ExitDoor(this);
	}

}