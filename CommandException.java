package tp1_2.exceptions;

public class CommandException extends Exception {

	public CommandException(String message) {
		super(message);
	}
	
	public CommandException(String message,Exception sub) {
		super(message,sub);
	}
	
}
