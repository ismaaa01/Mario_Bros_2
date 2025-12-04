package tp1_2.logic;


import tp1_2.logic.gameobjects.*;
import tp1_2.view.Messages;

import java.util.ArrayList;
import java.util.List;
import tp1_2.exceptions.GameModelException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Game implements GameModel, GameStatus,GameWorld{

	
	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private int nlives;
	private int nLevel;
	private int remainingTime;
	private int points;
	private Mario mario;
	private GameObjectContainer gameObjects;
	private boolean win;
	private boolean running;
	private List<GameObject> inserted_objects;
	
	
	public Game(int nLevel) {
		this.points = 0;
		this.nLevel = nLevel;
		this.nlives = 3;
		this.win = false;
		this.running = true;
		this.inserted_objects = new ArrayList<GameObject>();
		switch (nLevel){
		case 2: initLevel2(); break;
		case 1: initLevel1(); break;
		case 0: initLevel0(); break;
		case -1: initBlankLevel();break;
		}
	}
	
	public void add_obj(GameObject obj) {
		this.gameObjects.add(obj);
		obj.receive_world(this);
	}
	
	public void receive_mario(Mario mario) {
		this.mario = mario;
	}
	
	public void insert_obj(GameObject obj) {
		inserted_objects.add(obj);
	} 
	//GameModel:
	
	private String stringify() {
		String game_string = String.valueOf(this.remainingTime)+ " " + String.valueOf(this.points) + " " + String.valueOf(this.nlives)+ '\n';
		game_string += gameObjects.stringify();
		return game_string;
	}
	
	public void save(String FileName)throws GameModelException{
		FileOutputStream out ;
		try {
			out = new FileOutputStream(FileName);
			String game_string = stringify();
			for(int i = 0; i < game_string.length() ; i++) {
				out.write(game_string.charAt(i));
			}
			if (out != null) {
	                out.close();
	        }
		}catch(IOException e) {
			throw new GameModelException(Messages.SAVE_ERROR.formatted(FileName));
		}
		
	}
	
	public void give_actions_to_mario(ActionList act_list ) {
		this.mario.receive_actions(act_list);
	}
	
	public void update() {
		this.remainingTime--;
		gameObjects.update();
		for(GameObject obj:inserted_objects) {
			this.gameObjects.add(obj);
		}
		inserted_objects.clear();
		if(!mario.isAlive()) {
			mario_dies();
		}
	}
	
	public void reset(int num_level) {
		switch (num_level){
			case 2: initLevel2(); break;
			case 1: initLevel1(); break;
			case 0: initLevel0(); break;
			case -1:initBlankLevel();break;
		}
	}
	
	public void reset() {
		switch (nLevel){
			case 2: initLevel2(); break;
			case 1: initLevel1(); break;
			case 0: initLevel0(); break;
			case -1:initBlankLevel();break;
		}
	}
	
	
	public void exit() {
		running = false;
	}
	
	public boolean isFinished() {
		return win || nlives == 0 || remainingTime == 0 || !running;
	}
	
	//GameStatus:
	
	public String positionToString(int col, int row) {
		Position pos = new Position(row, col);
		return this.gameObjects.postitionToString(pos);
	}

	public boolean playerWins() {
		return win;
	}
	
	public boolean playerLoses() {
		return nlives == 0 || remainingTime == 0;
	}
	
	public int remainingTime() {
		return this.remainingTime;
	}

	public int points() {
		return this.points;
	}
	
	
	public int numLives() {
		return this.nlives;
	}
	
	//GameWorld:

	public boolean isSolid(int col, int row) {
		return this.gameObjects.isSolid(col,row);
	}

	public void marioExited() {
		int victoryPoints = this.remainingTime * 10;
		this.points += victoryPoints;
		this.remainingTime = 0;
		this.win = true;
	}

	private void mario_dies() {
		if(nlives == 1) {
			this.nlives--;
		}else {
			this.nlives--;
			reset();
		}
	}
	
	public void addPoints(int pointsToAdd) {
		this.points += pointsToAdd;
	}
	
	public void doInteractionsFrom(GameObject from) {
	    if(gameObjects.doInteractionsFrom(from));
	}
	
	
	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}

	
	
	
	// Generar mapas:
	
	
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
		int tamX = 8;
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
		int tamX = 8;
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
	private void initBlankLevel() {
		this.nLevel = -1;
		this.remainingTime = 100;
		this.points = 0;
		this.nlives = 3;

		// 1. Mapa
		this.gameObjects = new GameObjectContainer();
	}
	
	private void initLevel2() {
		this.nLevel = 2;
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
		int tamX = 8;
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
		
		this.gameObjects.add(new Box(this,new Position(9,4)));
		this.gameObjects.add(new Mushroom(this, new Position(12, 8)));
		this.gameObjects.add(new Mushroom(this, new Position(2, 20)));
	}

}