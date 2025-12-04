package tp1_2.exceptions;

public class CommandParseException extends CommandException {
	
	public CommandParseException(String mensaje) {
		super(mensaje);
	}
	
	public CommandParseException(String mensaje,Exception sub) {
		super(mensaje,sub);
	}
}
