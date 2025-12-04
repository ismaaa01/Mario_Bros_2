package tp1_2.control.commands;

import tp1_2.exceptions.CommandParseException;
import tp1_2.logic.GameModel;
import tp1_2.view.GameView;
import tp1_2.view.Messages;

public class ResetCommand extends AbstractCommand {

	
	private static final String NAME = Messages.COMMAND_RESET_NAME;
	private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
	private static final String HELP = Messages.COMMAND_RESET_HELP;
	
	private boolean cambio_level;
	private int nLevel;
	
	public ResetCommand() {
		super(NAME,SHORTCUT,DETAILS,HELP);
	}
	
	@Override
	public void execute(GameModel game, GameView view) {
		if(cambio_level) {
			if(nLevel != 1 && nLevel != 0 && nLevel != -1 && nLevel != 2) {
				view.showError(Messages.INVALID_LEVEL_NUMBER);
			}else {
				game.reset(nLevel);
				view.showGame();
			}
		}else {
			game.reset();
			view.showGame();
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords.length > 2 && matchCommandName(commandWords[0])) 
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length > 1) {
				cambio_level = true;
				try {
				this.nLevel = Integer.parseInt(commandWords[1]);
				}catch(NumberFormatException nfe) {
					throw new CommandParseException(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(commandWords[1]),nfe);
				}
			}else {
				cambio_level = false; 
			}
			return this;
		}
		return null;
	}

}
