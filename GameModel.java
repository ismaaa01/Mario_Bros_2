package tp1_2.logic;

public interface GameModel {
	
	
	public boolean isFinished();
	
	public void update();
	
	public void reset();
	
	public void give_actions_to_mario(ActionList act_list );
	
	public void reset(int num_level);
	
	public void exit();
	
}
