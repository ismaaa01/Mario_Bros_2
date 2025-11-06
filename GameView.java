package tp1_2.view;

import tp1_2.logic.GameStatus;

public abstract class GameView implements ViewInterface{

	protected GameStatus game;
	
	public GameView(GameStatus game) {
		this.game = game;
	}
	
}
