package tp1_2.control.commands;

import java.util.Arrays;

import tp1_2.logic.GameModel;
import tp1_2.logic.gameobjects.GameObjectFactory;
import tp1_2.logic.gameobjects.GameObject;
import tp1_2.view.GameView;
import tp1_2.view.Messages;

public class AddObjectCommand extends AbstractCommand {

	
	private static final String NAME = Messages.COMMAND_ADDOBJECT_NAME ;
	private static final String SHORTCUT = Messages.COMMAND_ADDOBJECT_SHORTCUT; 
	private static final String DETAILS = Messages.COMMAND_ADDOBJECT_DETAILS; 
	private static final String HELP = Messages.COMMAND_ADDOBJECT_HELP ; 
	private String[] objInfo;
	private GameObjectFactory mi_factory;

	public AddObjectCommand() {
		super(NAME,SHORTCUT,DETAILS,HELP);
	}
	
	@Override
	public void execute(GameModel game, GameView view) {
		GameObject new_object = mi_factory.parse(objInfo);
		try {
			game.add_obj(new_object);
			view.showGame();
		}catch (NullPointerException e){
			String line = String.join(" ",objInfo);
			view.showError(Messages.INVALID_GAME_OBJECT.formatted(line));
		}
	}

	@Override
	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			objInfo = Arrays.copyOfRange(commandWords, 1, commandWords.length);
			mi_factory = new GameObjectFactory();
			return this;
		}
		return null;
	}

}
