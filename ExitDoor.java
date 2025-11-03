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

	@Override
	public boolean interactWith(GameItem other) {
		 if (other.isInPosition(this.pos)) {
	            return other.receiveInteraction(this);
	        }
	        return false;
	}

	@Override
	public boolean receiveInteraction(Land obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveInteraction(ExitDoor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveInteraction(Mario obj) {
		  if (this.pos.equals(obj.pos)) {
	            game.playerWins();//no se como es mario salio por la puerta
	            return true;
	        }
	        return false;
	}

	@Override
	public boolean receiveInteraction(Goombas obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
