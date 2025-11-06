package tp1_2.logic.gameobjects;

import tp1_2.logic.Position;


public  interface GameItem {
	public  boolean isSolid();
	public  boolean isAlive();
	public  boolean isInPosition(Position pos);

	public  boolean interactWith(GameItem item);

	public  boolean receiveInteraction(Land obj);
	public  boolean receiveInteraction(ExitDoor obj);
	public  boolean receiveInteraction(Mario obj);
	public  boolean receiveInteraction(Goombas obj);
}