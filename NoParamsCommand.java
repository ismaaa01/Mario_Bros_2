package tp1_2.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords.length > 1) {return null;}
		if(matchCommandName(commandWords[0])) 
			return this;
		return null;
	}

}
