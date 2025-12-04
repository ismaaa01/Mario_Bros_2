package tp1_2.logic.gameobjects;

import tp1_2.logic.Position;
import tp1_2.view.Messages;
import tp1_2.logic.Action;
import tp1_2.logic.GameWorld;
import tp1_2.exceptions.*;

public abstract class MovingObject extends GameObject {
	
	protected Action dir;
	protected Action dir_h;
	protected Action prev;
	
	public MovingObject(String Name,String Shortcut,Action initial) {
		super(Name,Shortcut);
		dir = initial;
		dir_h = initial;
	}
	
	public MovingObject(GameWorld game, Position pos,Action initial,String name, String shortcut) {
		super(game,pos,name,shortcut);
		this.dir = initial;
		this.dir_h = initial;
	}
	
	public boolean isSolid() {
		return false;
	}

	public GameObject parse(String[] info,Position pos)throws GameParseException {
		if(matchObjName(info[0]) && info.length > 2)
			throw new ObjectParseException(Messages.ARGS_PARSE_ERROR.formatted(info));
		if(matchObjName(info[0])) {
			this.pos = pos;
			if(info.length > 1) {
				try {
					Action initial = give_act(info[1]);
					if(initial == Action.UP || initial == Action.DOWN) throw new ActionParseException(Messages.INVALID_MOVING_DIR.formatted(info));
					dir = initial;
					dir_h = initial;
				}catch(ActionParseException e) {
					throw new GameParseException(Messages.UNKNOWN_MOVING_DIRECTION,e);
				}
			}
			return this;
		}
		return null;
	}
	
	public void update_dir() {
		if(!game.isSolid(pos.get_col(),pos.get_row()+Action.DOWN.getY())) {
			dir = Action.DOWN;
		}else {
			dir = dir_h;
		}
	}
	
	protected boolean verifica_act(Action act) {
		int nextCol = pos.get_col() + act.getX();
        int nextRow = pos.get_row() + act.getY();
			if (act == Action.DOWN) {
				return !game.isSolid(nextCol, nextRow);
			}
        if (!pos.in_game(nextCol, nextRow)) return false; 
        return !game.isSolid(nextCol, nextRow);
	}

	public void reverse_dir() {
		if(dir == Action.RIGHT) {
			dir_h = Action.LEFT;
		}else if(dir == Action.LEFT) {
			dir_h = Action.RIGHT;
		}
	}
	
	protected void mouvement_auto() {
		if(super.isAlive() && pos.in_game()) {
			if(verifica_act(dir)) {
				pos.do_action(dir);
				save_prev(dir);
			}else {
				reverse_dir();
			}
		}
	}
	
	public void update() {
		update_dir();
		mouvement_auto();
		game.doInteractionsFrom(this);
		if(!pos.in_game(pos.get_col(),pos.get_row())) {
			super.dead();
		}
	}
	@Override
	public abstract String getIcon();
	
	protected Action give_act(String dir_initial) throws ActionParseException{
		if(dir_initial.equalsIgnoreCase("LEFT") || dir_initial.equalsIgnoreCase("L")) {
			return Action.LEFT;
		}
		if(dir_initial.equalsIgnoreCase("STOP") || dir_initial.equalsIgnoreCase("S")) {
			return Action.STOP;
		}
		if(dir_initial.equalsIgnoreCase("RIGHT") || dir_initial.equalsIgnoreCase("R")) {
			return Action.RIGHT;
		}
		throw new ActionParseException(Messages.UNKNOWN_ACTION.formatted(dir_initial));
	}
	
	private void save_prev(Action act) {
		if(act == Action.RIGHT) {
			prev = Action.LEFT;
		}else if(act == Action.LEFT) {
			prev = Action.RIGHT;
		}else if(act == Action.UP) {
			prev = Action.DOWN;
		}else if(act == Action.DOWN) {
			prev = Action.UP;
		}
	}
	
	public String stringify() {
		String str = super.stringify() + " ";
		str += dir.toString();
		return str;
	}
}