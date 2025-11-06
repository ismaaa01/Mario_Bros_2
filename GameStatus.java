package tp1_2.logic;

public interface GameStatus {

	public String positionToString(int col, int row);
	
	public int remainingTime();
	
	public int points();
	
	public int numLives();
	
	public boolean playerWins();
	
	public boolean playerLoses();
}
