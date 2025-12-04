package tp1_2.control.commands;

import java.util.Arrays;
import java.util.List;
import tp1_2.exceptions.CommandParseException;

import tp1_2.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			new SaveCommand(),
			new AddObjectCommand(),
			new ActionCommand(),
			new UpdateCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand()
			
	);

	public static Command parse(String[] commandWords)throws CommandParseException {	
		
		for(Command cmd:availableCommands) {
			try {
			if(cmd.parse(commandWords)!= null) {
				return cmd;
			}
			}catch(CommandParseException e) {
				throw e;
			}
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		
		return commands.toString();
	}

}