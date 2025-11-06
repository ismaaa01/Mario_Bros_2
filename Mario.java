package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Action;
import tp1_2.logic.ActionList;
import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;

public class Mario extends MovingObject {
	
	private boolean isBig;
	private final String icono_right = Messages.MARIO_RIGHT;
	private final String icono_left = Messages.MARIO_LEFT;
	private final String icono_stop = Messages.MARIO_STOP;
	private ActionList actList;
	
	public Mario(GameWorld game, Position pos) {
		super (game,pos,Action.RIGHT); 
	}
	
	public void reset_actions() {
		actList.eliminate();
	}

	public void receive_actions(ActionList act_list) {
		actList = act_list;
	}
	
	public void reverse_dir() {
		if(dir == Action.RIGHT) {
			dir_h = Action.LEFT;
		}else if(dir == Action.LEFT) {
			dir_h = Action.RIGHT;
		}else if(dir == Action.DOWN) {
			dir_h = Action.STOP;
		}
	}
	
	public boolean verifica_act(Action act) {
		if(act == Action.LEFT || act == Action.RIGHT || act == Action.STOP) {dir_h = act;}
		if(isBig && super.verifica_act(act)) {
			int nextCol = pos.get_col() + act.getX();
        	int nextRow = pos.get_row() -1  + act.getY();
			if (act == Action.DOWN) {
			return !game.isSolid(nextCol, nextRow);
			}
        	if (!pos.in_game(nextCol, nextRow)) return false; 
        	return !game.isSolid(nextCol, nextRow);
		}
		return super.verifica_act(act);
	}
	
	private void movement_player() {
		if(dir == Action.DOWN) {
			if(!verifica_act(dir)) {
				dir_h = Action.STOP;
			}else {
				while(verifica_act(dir) && pos.in_game() && dir == Action.DOWN) {
					pos.do_action(dir);
					game.doInteractionsFrom(this);
				}
				if(!pos.in_game()) {
					super.dead();
				}
			}
		}else {
			super.mouvement_auto();
		}
	}
	
	private boolean actions_to_do() {
		return actList != null && actList.in_action();
	}
	
	@Override
	public void update() {
		if(actions_to_do()) {
			for(int i = 0; i < actList.size();i++) {
				dir = actList.get(i);
				movement_player();
				game.doInteractionsFrom(this);
			}
			reset_actions();
		}else {
			super.update();
		}
		update_dir();
		if(!super.isAlive()) {
			game.mario_dies();
		}
	}
	
	public boolean isInPosition(Position p) {
		if(isBig) {return super.isInPosition(p) || this.pos.equals(p.get_col(),p.get_row()+1);}
		return super.isInPosition(p);
	}
	public void go_big() {
		this.isBig = true;
	}
	public void go_small() {
		this.isBig = false;
	}
	@Override
	public String getIcon() {
		if(super.isAlive()) {
			if(this.dir_h == Action.LEFT) {
				return icono_left;	
			}else if(dir_h == Action.STOP) {
				return icono_stop;
			}
			return icono_right;
		}
		return Messages.EMPTY;
	}
	
	public boolean interactWith(GameItem other) {
		boolean can_interact = other.isInPosition(this.pos) ;
		boolean has_interacted = false;
		if (can_interact && this.isAlive()) {
			has_interacted = other.receiveInteraction(this);
        }
		return can_interact && has_interacted;
	}

	@Override
	public boolean receiveInteraction(ExitDoor obj) {
        return false;
	}

	@Override
	public boolean receiveInteraction(Goombas obj) {
		if(dir != Action.DOWN) {
			if(this.isBig) { 
				isBig = false;
				obj.receiveInteraction(this);
			}
			else if (obj.isAlive()) { 
				super.dead();
			}
		}
		return true;
	}


}