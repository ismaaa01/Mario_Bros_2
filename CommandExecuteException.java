package tp1_2.exceptions;

import tp1_2.view.Messages;

public class CommandExecuteException extends CommandException{
	
	private static final String Execute_error = Messages.EXCECUTE_PROBLEM;
	
	public CommandExecuteException() {
		super(Execute_error);
	}
	
	public CommandExecuteException(Exception sub) {
		super(Execute_error,sub);
	}
}
