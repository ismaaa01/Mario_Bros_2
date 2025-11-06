package tp1_2.control.commands;

import tp1_2.logic.GameModel;
import tp1_2.view.GameView;

public interface Command {

	public void execute(GameModel game, GameView view);	  
	public Command parse(String[] commandWords);
	public String helpText();
}
