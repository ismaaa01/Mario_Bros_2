package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Position;
import tp1_2.logic.GameWorld;
import tp1_2.exceptions.GameParseException;
import tp1_2.exceptions.ObjectParseException;

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
		super(game,pos,Name,Shortcut);
		open = false;
	}
	
	
	
	@Override
	public void update() {}
	
	public boolean isSolid() {
		if(open) 
			return true;
		return false;
	}
	
	public GameObject parse(String[] info,Position pos) throws GameParseException{
		if(matchObjName(info[0]) && info.length > 2)
			throw new GameParseException(Messages.ARGS_PARSE_ERROR.formatted(info));
		if(matchObjName(info[0])) {
			this.pos = pos;
			if(info.length > 1) {
				try {
				open = give_state(info[1]);
				}catch (ObjectParseException e) {
					throw new GameParseException(Messages.INVALID_BOX_STATUS.formatted(info));
				}
			}
			return this;
		}
		return null;
	}
	
	public boolean interactWith(GameItem other) {
		boolean guarda_estado = open;
		boolean can_interact = other.isInPosition(this.pos);
		boolean has_interacted = false;
		if (can_interact) {
			if(!open) {open = true;}
			has_interacted = other.receiveInteraction(this);
			if(!has_interacted ) {open = guarda_estado;}
        }
		return can_interact && has_interacted;
	}
	
	private boolean give_state(String state) throws ObjectParseException{
		if(state.equalsIgnoreCase("Full") || state.equalsIgnoreCase("F")) {
			return false;
		}else if(state.equalsIgnoreCase("Empty") || state.equalsIgnoreCase("E")) {
			return true;
		}
		throw new ObjectParseException();
	}
	
	public boolean receiveInteraction(Mario obj) {
		if(!open) {
			GameObjectFactory.open_box(pos, game);
			open = true;
			game.addPoints(50);
		}
		return true;
	}
	
	public String stringify() {
		String str = super.stringify() + " ";
		str += open ? "Empty" : "Full";
		return str;
	}

	@Override
	public String getIcon() {
		if(open) 
			return icono_open;
		return icono_close;
	}

}
