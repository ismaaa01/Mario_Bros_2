package tp1_2.exceptions;

import tp1_2.exceptions.*;

public class GameModelException extends Exception {

	public GameModelException(String message) {
		super(message);
	}
	
	public GameModelException(String message,Exception sub) {
		super(message,sub);
	}
	
	public GameModelException() {
		super();
	}
}
