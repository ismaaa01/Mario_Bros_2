package tp1_2.control.commands;

import tp1_2.logic.Game;
import tp1_2.view.GameView;
import tp1_2.view.Messages;
import tp1_2.logic.ActionList;
import tp1_2.logic.Action;
import java.util.*;

public class ActionCommand extends AbstractCommand {

	
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
	private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
	private static final String HELP = Messages.COMMAND_ACTION_HELP;
	
	private final String[] AVAILABLE_ACTIONS = {"left","right","up","down","stop"}; // para reconocer las posibles acciones
	private final List<Action> actions ;
	
	private ActionList act_list;
	
	public ActionCommand() {
		super(NAME,SHORTCUT,DETAILS,HELP);
		act_list = new ActionList();
		actions = new ArrayList<>(Arrays.asList(Action.LEFT,Action.RIGHT,Action.UP,Action.DOWN,Action.STOP)); // tiene que quedar en order junto a los available actions
	}
	
	@Override
	public void execute(Game game, GameView view) {
		// TODO Auto-generated method stub
		act_list.simplify();
		game.give_actions_to_mario(act_list);
		game.update();
		view.showGame();
	}

	@Override
	public Command parse(String[] commandWords) {
		if(this.matchCommand(commandWords[0])) {
			int act_p = 1;
			while (act_p < commandWords.length) {
				recorre_actions(commandWords[act_p]);
				act_p++;
			}
			return this;
		}
		return null;
	}
	
	private void recorre_actions(String action) {
		for (int i = 0; i < AVAILABLE_ACTIONS.length;i++) {
			if(AVAILABLE_ACTIONS[i].equalsIgnoreCase(action) || (action.length() == 1 && AVAILABLE_ACTIONS[i].charAt(0) == action.toLowerCase().charAt(0))) {
				act_list.add(actions.get(i));
			}
		}
	}

}
