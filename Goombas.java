package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Action;
import tp1_2.logic.Game;
import tp1_2.logic.Position;

public class Goombas extends MovingObject {
	
	private final String icono =Messages.GOOMBA;
	
	public Goombas(Game game,Position pos) {
		super(game,pos);
		super.dir = Action.LEFT;
	}
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIcon() {
		if(super.isAlive()) {
			return icono;
		}
		else {
			return Messages.EMPTY;
		}
	}

}
