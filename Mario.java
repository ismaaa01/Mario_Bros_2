package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Action;
import tp1_2.logic.ActionList;
import tp1_2.logic.Game;
import tp1_2.logic.Position;

public class Mario extends MovingObject {

	private boolean isBig;
	private final String icono_right = Messages.MARIO_RIGHT;
	private final String icono_left = Messages.MARIO_LEFT;
	private final String icono_stop = Messages.MARIO_STOP;
	private ActionList actList;
	
	public Mario(Game game, Position pos) {
		super (game,pos,Action.RIGHT); 
		super.dir = Action.RIGHT;
		
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
	
	private void movement_player(Action act) {
		if(!verifica_act(dir) && dir == Action.DOWN) {
			dir_h = Action.STOP;
		}
		while(verifica_act(dir) &&	pos.in_game() && dir == Action.DOWN) {
			pos.do_action(dir);
			game.doInteractionsFrom(this);
		}

		if(!pos.in_game()) {
			super.dead();
		}if(verifica_act(act)) {
			pos.do_action(act);

		}else {
				if(act == Action.RIGHT) {
					dir_h = Action.LEFT;
				}else if(act == Action.LEFT) {
					dir_h = Action.RIGHT;
				}
		}
	}
	
	@Override
	public void update() {
		actList.simplify();
		update_dir();
		if(actList.in_action()) {
			for(int i = 0; i < actList.size();i++) {
				dir = actList.get(i);
				movement_player(dir);
				game.doInteractionsFrom(this);
			}
		}else {
			super.update();
		}
		reset_actions();
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
		if (other.isInPosition(this.pos)) {
            return other.receiveInteraction(this);
        }
		return false;
	}
	@Override
	public boolean receiveInteraction(Land obj) {
		return false;
	}

	@Override
	public boolean receiveInteraction(ExitDoor obj) {
		game.marioArrived();
        return true;
	}

	@Override
	public boolean receiveInteraction(Mario obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveInteraction(Goombas obj) {
		// Si Goomba toca a Mario → Mario muere
		if(this.isBig) isBig = false;
		else super.dead();
		return true;
	}


}