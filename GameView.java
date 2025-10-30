package tp1_2.view;

import tp1_2.logic.Game;

public abstract class GameView implements ViewInterface{

	protected Game game;
	
	public GameView(Game game) {
		this.game = game;
	}
	
}
