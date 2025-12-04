package tp1_2.exceptions;

public class GameParseException extends GameModelException {

	public GameParseException(String message){
		super(message);
	}
	
	public GameParseException(String message,Exception sub){
		super(message,sub);
	}
	
	public GameParseException(){
		super();
	}
}
