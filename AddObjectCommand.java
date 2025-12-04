package tp1_2.control.commands;

import java.util.Arrays;

import tp1_2.logic.GameModel;
import tp1_2.logic.gameobjects.GameObjectFactory;
import tp1_2.logic.gameobjects.GameObject;
import tp1_2.view.GameView;
import tp1_2.view.Messages;
import tp1_2.exceptions.*;

public class AddObjectCommand extends AbstractCommand {

	
	private static final String NAME = Messages.COMMAND_ADDOBJECT_NAME ;
	private static final String SHORTCUT = Messages.COMMAND_ADDOBJECT_SHORTCUT; 
	private static final String DETAILS = Messages.COMMAND_ADDOBJECT_DETAILS; 
	private static final String HELP = Messages.COMMAND_ADDOBJECT_HELP ; 
	private String[] objInfo;

	public AddObjectCommand() {
		super(NAME,SHORTCUT,DETAILS,HELP);
	}
	
	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException{
		GameObjectFactory mi_factory = new GameObjectFactory();
		try {
			GameObject new_object = mi_factory.parse(objInfo);
			game.add_obj(new_object);
			view.showGame();
		}catch (GameModelException exc){
			throw new CommandExecuteException(exc);
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords.length < 3 && matchCommandName(commandWords[0]))
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		
		if(matchCommandName(commandWords[0])) {
			objInfo = Arrays.copyOfRange(commandWords, 1, commandWords.length);
			return this;
		}
		return null;
	}

}
