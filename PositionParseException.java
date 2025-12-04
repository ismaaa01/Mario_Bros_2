package tp1_2.exceptions;

public class PositionParseException extends GameParseException {

	public PositionParseException(String message,Exception sub) {
		super(message,sub);
	}
	
}
