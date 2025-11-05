package tp1_2.logic;


import tp1_2.logic.gameobjects.*;
import tp1_2.logic.ActionList;

public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private int nlives;
	private int nLevel;
	private int remainingTime;
	private Mario mario;
	private GameObjectContainer gameObjects;

	//TODO fill your code
	
	public Game(int nLevel) {
		this.nLevel = nLevel;
		switch (nLevel){
		case 0: initLevel0(); break;
		}
	}
	
	public String positionToString(int col, int row) {

		Position pos = new Position(row, col);
		
		return this.gameObjects.postitionToString(pos);
		
	}

	public void give_actions_to_mario(ActionList act_list ) {
		this.mario.receive_actions(act_list);
	}
	
	public void update() {
		gameObjects.update();
	}
	
	public boolean isSolid(int col, int row) {
		return this.gameObjects.isSolid(col,row);
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return false;
	}

	public int remainingTime() {
		// TODO Auto-generated method stub
		return 100;
	}

	public int points() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numLives() {
		// TODO Auto-generated method stub
		return 3;
	}
	public void reset(int num_level) {
		switch (num_level){
			case 1: initLevel1(); break;
			case 0: initLevel0(); break;
		}
	}
	
	public void reset() {
		switch (nLevel){
			case 1: initLevel1(); break;
			case 0: initLevel0(); break;
		}
	}
		
	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}

	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean playerLoses() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// Not mandatory but recommended
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	public void doInteractionsFrom(GameObject from) {
	    gameObjects.doInteractionsFrom(from);
	}


	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(this,new Position(13,col)));
			gameObjects.add(new Land(this,new Position(14,col)));		
		}

		gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(this,new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(this,new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(this,new Position(9,2)));
		gameObjects.add(new Land(this,new Position(9,5)));
		gameObjects.add(new Land(this,new Position(9,6)));
		gameObjects.add(new Land(this,new Position(9,7)));
		gameObjects.add(new Land(this,new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(this,new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(this,new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);
		mario.go_big();
		gameObjects.add(new Goombas(this, new Position(0, 19)));
	}
	public void initLevel1() {
		
		this.nLevel = 1;
		this.remainingTime = 100;

		// 1. Mapa
		this.gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(this,new Position(13,col)));
			gameObjects.add(new Land(this,new Position(14,col)));		
		}

		this.gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,9)));
		this.gameObjects.add(new Land(this,new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			this.gameObjects.add(new Land(this,new Position(Game.DIM_Y-2, col)));
			this.gameObjects.add(new Land(this,new Position(Game.DIM_Y-1, col)));		
		}

		this.gameObjects.add(new Land(this,new Position(9,2)));
		this.gameObjects.add(new Land(this,new Position(9,5)));
		this.gameObjects.add(new Land(this,new Position(9,6)));
		this.gameObjects.add(new Land(this,new Position(9,7)));
		this.gameObjects.add(new Land(this,new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				this.gameObjects.add(new Land(this,new Position(posIniY- fila, posIniX+ col)));
			}
		}

		this.gameObjects.add(new ExitDoor(this,new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		this.mario.go_big();
		this.gameObjects.add(this.mario);
		
		this.gameObjects.add(new Goombas(this, new Position(0, 19)));
		this.gameObjects.add(new Goombas(this, new Position(12, 6)));
		this.gameObjects.add(new Goombas(this, new Position(4, 6)));
		this.gameObjects.add(new Goombas(this, new Position(12, 8)));
		this.gameObjects.add(new Goombas(this, new Position(10, 10)));
		this.gameObjects.add(new Goombas(this, new Position(12, 11)));
		this.gameObjects.add(new Goombas(this, new Position(12, 14)));
		
	}

}