package tp1_2.logic;

import tp1_2.logic.gameobjects.GameObject;

public interface GameModel {
	
	
	public boolean isFinished();
	
	public void update();
	
	public void reset();
	
	public void give_actions_to_mario(ActionList act_list );
	
	public void reset(int num_level);
	
	public void exit();
	
	public void add_obj(GameObject obj);
	
}
