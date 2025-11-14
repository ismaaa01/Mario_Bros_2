package tp1_2.logic.gameobjects;

import tp1_2.logic.*;
import tp1_2.view.Messages;

public class Mushroom extends MovingObject {

	private final String icono = Messages.MUSHROOM;
	
	private static Action initial_dir = Action.RIGHT;
	
	private static final String Name = Messages.MUSHROOM_NAME;
	private static final String Shortcut = Messages.MUSHROOM_SHORTCUT;
	
	public Mushroom() {
		super(Name,Shortcut,initial_dir);
	}
	
	public Mushroom(GameWorld game,Position pos) {
		super(game,pos,initial_dir);
	}
	
	public Mushroom(GameWorld game,Position pos,Action initial) {
		super(game,pos,initial_dir);
		dir = initial;
	}

	@Override
	public String getIcon() {
		if(isAlive())
			return icono;
		return Messages.EMPTY;
	}

	public  boolean interactWith(GameItem other) {
		boolean can_interact = other.isInPosition(this.pos);
		boolean has_interacted = false;
		if(can_interact) {
			has_interacted = other.receiveInteraction(this);
		}
		return can_interact && has_interacted;
	}
	
	public  boolean receiveInteraction(Mario obj) {
		if(isAlive()) {
			super.dead();
		}
		return true;
	}
}
