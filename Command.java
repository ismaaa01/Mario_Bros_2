package tp1_2.control.commands;

import tp1_2.logic.Game;
import tp1_2.view.GameView;

public interface Command {

	public void execute(Game game, GameView view);	  
	public Command parse(String[] commandWords);
	public String helpText();
}
