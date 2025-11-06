package tp1_2.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1_2.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			//TODO fill with your code
			new ActionCommand(),
			new UpdateCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand()
			
	);

	public static Command parse(String[] commandWords) {	
		for (Command c: availableCommands) {
			if(c.parse(commandWords) != null){
				return c;
			}
		}
		return null;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: availableCommands) {
			commands.append(c.helpText());
			//TODO fill with your code
		}
		
		return commands.toString();
	}

}