package tp1_2.logic.gameobjects;

import tp1_2.logic.Position;
import tp1_2.logic.Action;
import tp1_2.logic.Game;

public abstract class MovingObject extends GameObject {
	
	protected Action dir;
	protected Action dir_h;
	protected boolean isFalling;
	public MovingObject(Game game, Position pos ) {
		super(game,pos);
		this.dir = Action.RIGHT; //lo puse yo no se si es necesario
		this.dir_h = this.dir;
		this.isFalling=false;
	}
	
	public boolean isSolid() {
		return false;
	}
	public void stopFalling() {
		if(isFalling)isFalling = false;
	}
	public void update_dir() {
		if(!game.isSolid(pos.get_col(),pos.get_row()+Action.DOWN.getY())) {
			dir = Action.DOWN;
			isFalling = true;
		}else {
			dir = dir_h;
			isFalling = false;
		}

	}
	
	protected boolean verifica_act(Action act) {
		int nextCol = pos.get_col() + act.getX();
        int nextRow = pos.get_row() + act.getY();
        
        if (!pos.in_game(nextCol, nextRow)) return false; 
        return !game.isSolid(nextCol, nextRow);
	}

	protected void mouvement_auto() {
		if(super.isAlive() && pos.in_game(pos.get_col(),pos.get_row())) {
			update_dir();
			if(verifica_act(dir)) {
				pos.do_action(dir);
			}else {
				if(dir.getX()!=0) {
					if(dir == Action.RIGHT) {
						dir_h = Action.LEFT;
					}else if(dir == Action.LEFT) {
						dir_h = Action.RIGHT;
					}
				}
			}
		}
		if(!pos.in_game(pos.get_col(),pos.get_row())) {
			super.dead();
		}
	}
	
	public abstract void update();
	@Override
	public abstract String getIcon();
}
