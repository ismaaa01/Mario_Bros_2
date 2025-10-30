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
		super (game,pos);
		this.actList = new ActionList(); 
		super.dir = Action.RIGHT;
	}
	
	public void reset_actions() {
		actList.eliminate();
	}
	
	private void movement_player(Action act) {
		if(!verifica_act(dir) && dir == Action.DOWN) {
			dir_h = Action.STOP;
		}
		while(verifica_act(dir) &&	pos.in_game() && dir == Action.DOWN) {
			pos.do_action(dir);
			game.doInteractionsFrom(this);
		}

		if(!in_game()) {
			game.mario_dies();
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
			super.mouvement_auto();
			game.doInteractionsFrom(this);
		}
		reset_actions();
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

}
