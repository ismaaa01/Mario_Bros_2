package tp1_2.control.commands;

import tp1_2.exceptions.CommandParseException;
import tp1_2.view.Messages;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords)throws CommandParseException  {
		if (commandWords.length > 1 && matchCommandName(commandWords[0])) 
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		
		Command cmd = null;
		if(commandWords.length == 1 && matchCommandName(commandWords[0])) 
			cmd = this;
		
		return cmd;
	}

}
