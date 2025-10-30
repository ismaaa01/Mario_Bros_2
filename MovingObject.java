package tp1_2.logic.gameobjects;

import tp1_2.logic.Position;
import tp1_2.logic.Game;

public abstract class MovingObject extends GameObject {

	public MovingObject(Position pos, Game game ) {
		super(game,pos);
	}
	
	@Override
	public abstract String getIcon();
}
