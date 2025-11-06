package tp1_2.logic;

import tp1_2.logic.gameobjects.GameObject;

public interface GameWorld {

	public boolean isSolid(int col, int row);
	
	public void marioExited();
	
	public void mario_dies();
	
	public void doInteractionsFrom(GameObject from);
	
	public void addPoints(int pointsToAdd);
	
}
