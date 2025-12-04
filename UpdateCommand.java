package tp1_2.control.commands;

import tp1_2.logic.GameModel;
import tp1_2.view.GameView;
import tp1_2.view.Messages;

public class UpdateCommand extends NoParamsCommand {

	private static final String NAME = Messages.COMMAND_UPDATE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_UPDATE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_UPDATE_DETAILS;
	private static final String HELP = Messages.COMMAND_UPDATE_HELP;
	
	public UpdateCommand() {
		super(NAME,SHORTCUT,DETAILS,HELP);
	}
	
	@Override
	public void execute(GameModel game, GameView view) {
		game.update();
		view.showGame();
	}

	public boolean matchCommand(String c) {
		return super.matchCommandName(c) || c.length() == 0;
	}
	
}
