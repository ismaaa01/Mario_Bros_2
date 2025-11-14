package tp1_2.logic;

/**
 * 
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public boolean equals(Position other) {
		return this.row == other.row && this.col == other.col;
	}

	public boolean equals(int col, int row) {
		return this.row == row && this.col == col;
	}
	public boolean in_game(int col, int row) {
		return col >= 0 && col < Game.DIM_X && row >= 0 && row < Game.DIM_Y;
	}
	
	public boolean in_game() {
		return col >= 0 && col < Game.DIM_X && row >= 0 && row < Game.DIM_Y;
	}
	public void do_action(Action act) {
		this.row += act.getY();
		this.col += act.getX();
	}
	public int get_row(){
		return this.row;
	}
	public int get_col() {
		return this.col;
	}
	
}


