package tp1_2.logic;

import tp1_2.logic.gameobjects.GameObject;
import tp1_2.logic.gameobjects.Mario;

public interface GameWorld {

	public boolean isSolid(int col, int row);
	
	public void marioExited();
	
	public void doInteractionsFrom(GameObject from);
	
	public void addPoints(int pointsToAdd);
	
	public void add_obj(GameObject obj);
	
	public void receive_mario(Mario mario);
	
	public void insert_obj(GameObject obj);
	
}
