package tp1_2.control.commands;

import tp1_2.exceptions.CommandParseException;
import tp1_2.exceptions.CommandExecuteException;
import tp1_2.logic.GameModel;
import tp1_2.view.GameView;

public interface Command {

	public void execute(GameModel game, GameView view)throws CommandExecuteException;	  
	public Command parse(String[] commandWords) throws CommandParseException;
	public String helpText();
}
