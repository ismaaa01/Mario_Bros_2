package tp1_2.logic.gameobjects;

import tp1_2.view.Messages;
import tp1_2.logic.Game;
import tp1_2.logic.Position;

public class ExitDoor extends GameObject {
	
	private final String icono = Messages.EXIT_DOOR;


	
	public  ExitDoor(Game game,Position pos){
		super(game,pos);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	@Override
	public String getIcon() {
		return icono;
	}

}
