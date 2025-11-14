package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Position;
import tp1_2.logic.Action;
import tp1_2.logic.GameWorld;

public class Box extends GameObject {

	
	private final String icono_open = Messages.EMPTY_BOX;
	private final String icono_close = Messages.BOX;
	
	private static final String Name = Messages.BOX_NAME;
	private static final String Shortcut = Messages.BOX_SHORTCUT;
	
	private boolean open;
	
	public Box() {
		super(Name,Shortcut);
		open = false;
	}
	
	public Box(GameWorld game,Position pos) {
		super(game,pos);
		open = false;
	}
	
	
	
	@Override
	public void update() {}
	
	public boolean isSolid() {
		if(open) 
			return true;
		return false;
	}
	
	public GameObject parse(String[] info,Position pos) {
		if(matchObjName(info[0])) {
			this.pos = pos;
			if(info.length > 1) {
				open = give_state(info[1]);
			}
			return this;
		}
		return null;
	}
	
	public boolean interactWith(GameItem other) {
		boolean can_interact = other.isInPosition(this.pos);
		boolean has_interacted = false;
		if (can_interact) {
			has_interacted = other.receiveInteraction(this); 
        }
		return can_interact && has_interacted;
	}
	
	private boolean give_state(String state) {
		if(state.equalsIgnoreCase("Full") || state.equalsIgnoreCase("F")) {
			return false;
		}else if(state.equalsIgnoreCase("Empty") || state.equalsIgnoreCase("E")) {
			return true;
		}
		return false;
	}
	
	public boolean receiveInteraction(Mario obj) {
		if(!open) {
			Position pos_mushroom = new Position(pos.get_row()-1,pos.get_col());
			Mushroom nuevo = new Mushroom(game,pos_mushroom,Action.STOP);
			game.insert_obj(nuevo);
			open = true;
			game.addPoints(50);
		}
		return true;
	}

	@Override
	public String getIcon() {
		if(open) 
			return icono_open;
		return icono_close;
	}

}
