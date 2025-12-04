package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Action;
import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;

public class Goombas extends MovingObject {
	

	private final String icono = Messages.GOOMBA;
	
	private static final String Name = Messages.GOOMBA_NAME;
	private static final String Shortcut = Messages.GOOMBA_SHORTCUT;
	
	public static Action initial = Action.LEFT;
	
	public Goombas() {
		super(Name,Shortcut,initial);
	}
	
	public Goombas(GameWorld game,Position pos) {
		super(game,pos,initial,Name,Shortcut);
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
		if (can_interact) {
			has_interacted = other.receiveInteraction(this); 
        }
		return can_interact && has_interacted;
	}

	@Override
	public boolean receiveInteraction(ExitDoor obj) {
		reverse_dir();
		return false;
	}

	@Override
	public boolean receiveInteraction(Mario obj) {
		if(isAlive() ) {
			super.dead();
			game.addPoints(100);
		}
		return true;
	}
	
	public  boolean receiveInteraction(Box obj) {
		if(prev != null) {
			pos.do_action(prev);
		}
		update_dir();
		mouvement_auto();
		return false;
	}
}