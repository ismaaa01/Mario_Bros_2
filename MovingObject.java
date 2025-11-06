package tp1_2.logic.gameobjects;

import tp1_2.logic.Position;
import tp1_2.logic.Action;
import tp1_2.logic.GameWorld;

public abstract class MovingObject extends GameObject {
	
	protected Action dir;
	protected Action dir_h;
	public MovingObject(GameWorld game, Position pos,Action initial ) {
		super(game,pos);
		this.dir = initial;
		this.dir_h = initial;
	}
	
	public boolean isSolid() {
		return false;
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

	protected void reverse_dir() {
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
}