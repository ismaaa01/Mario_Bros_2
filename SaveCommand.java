package tp1_2.control.commands;

import tp1_2.exceptions.CommandExecuteException;
import tp1_2.exceptions.CommandParseException;
import tp1_2.exceptions.GameModelException;
import tp1_2.logic.GameModel;
import tp1_2.view.GameView;
import tp1_2.view.Messages;

public class SaveCommand extends AbstractCommand {

	private static final String name = Messages.COMMAND_SAVE_NAME;
	private static final String shortcut = Messages.COMMAND_SAVE_SHORTCUT;
	private static final String details = Messages.COMMAND_SAVE_DETAILS;
	private static final String help = Messages.COMMAND_SAVE_HELP;
	
	public String nombre_fich;
	
	public SaveCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		try {
			game.save(nombre_fich);
			view.showMessage(Messages.SAVE_SUCCESS.formatted(nombre_fich));
		}catch(GameModelException e) {
			throw new CommandExecuteException(e);
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0]) && (commandWords.length > 2 || commandWords.length < 2)) 
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		
		if(matchCommandName(commandWords[0])) {
			nombre_fich = commandWords[1];
			return this;
		}
		return null;
	}

	
	
}
