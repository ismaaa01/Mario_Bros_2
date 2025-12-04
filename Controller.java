package tp1_2.control;

import tp1_2.control.commands.Command;
import tp1_2.control.commands.CommandGenerator;
import tp1_2.logic.GameModel;
import tp1_2.view.GameView;
import tp1_2.exceptions.*;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private GameModel game;
	private GameView view;

	public Controller(GameModel game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 */
	public void run() {

		view.showWelcome();

		view.showGame();
		
		while ( !game.isFinished()) {
			String[] words = view.getPrompt();
			try {
				Command command = null;
				command = CommandGenerator.parse(words);
				command.execute(game, view);
			}catch(Exception exc) {
				view.showError(exc.getMessage());
				Throwable cause = exc.getCause();
				while(cause != null) {
					view.showError(cause.getMessage());
					cause = cause.getCause();
				}
			}
		}
		view.showEndMessage();
	}
}
