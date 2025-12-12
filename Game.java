package tp1_2.logic;


import tp1_2.logic.gameobjects.*;
import tp1_2.view.Messages;

import java.util.ArrayList;
import java.util.List;

import tp1_2.exceptions.GameLoadException;
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
	private LevelGameConfiguration conf;
	
	
	public Game(int nLevel) {
        this.nLevel = nLevel;
        this.nlives = 3;
        this.points = 0;
        this.win = false;
        this.running = true;
        this.inserted_objects = new ArrayList<>();
        this.conf = new LevelGameConfiguration(nLevel);
        backup();
    }

    public Game() {
        this.nLevel = 1;
        this.nlives = 3;
        this.points = 0;
        this.win = false;
        this.running = true;
        this.inserted_objects = new ArrayList<>();
        this.conf = new LevelGameConfiguration();
        backup();
    }

    private void backup() {
        gameObjects = new GameObjectContainer();
        remainingTime = conf.getRemainingTime();
        List<GameObject> obj_conf = conf.getObjects();
        for (GameObject obj : obj_conf) {
            GameObject nuevo = obj.clonar();
            add_obj(nuevo);
        }
    }
    public void add_obj(GameObject obj) {
        this.gameObjects.add(obj);
        obj.receive_world(this);
        if (obj instanceof Mario) this.mario = (Mario) obj;
    }

    public void receive_mario(Mario mario) {
        if (this.mario != null) this.mario.dead();
        this.mario = mario;
    }

    public void insert_obj(GameObject obj) {
        inserted_objects.add(obj);
    }
	private String stringify() {
		String game_string = String.valueOf(this.remainingTime)+ " " + String.valueOf(this.points) + " " + String.valueOf(this.nlives)+ '\n';
		game_string += gameObjects.stringify();
		return game_string;
	}

    public void load(String FileName) throws GameLoadException {
        try {
            FileGameConfiguration loader = new FileGameConfiguration(FileName, this);
            nLevel = -9;
            gameObjects = new GameObjectContainer();
            remainingTime = loader.getRemainingTime();
            points = loader.points();
            nlives = loader.numLives();
            List<GameObject> obj_conf = loader.getObjects();
            for (GameObject obj : obj_conf) add_obj(obj.clonar());
            conf = loader;
        } catch (GameLoadException e) {
            throw e;
        }
    }

    public void save(String FileName) throws GameModelException {
        try (FileOutputStream out = new FileOutputStream(FileName)) {
            String game_string = stringify();
            for (int i = 0; i < game_string.length(); i++) out.write(game_string.charAt(i));
        } catch (IOException e) {
            throw new GameModelException(e);
        }
    }

    public void give_actions_to_mario(ActionList act_list) {
        this.mario.receive_actions(act_list);
    }

    public void update() {
        remainingTime--;
        gameObjects.update();
        for (GameObject obj : inserted_objects) add_obj(obj);
        inserted_objects.clear();
        doInteractionsFrom(mario);
        if (!mario.isAlive()) mario_dies();
    }

    public void reset(int num_level) {
    	
        this.nLevel = num_level;
        this.conf = new LevelGameConfiguration(num_level);
        backup();
        if(num_level == -1) {
        	points = 0;
        	nlives = 3;
        }
    }

    public void reset() {
        if (conf != null) backup();
        if(nLevel == -1) {
        	points = 0;
        	nlives = 3;
        }
    }

    public void exit() { running = false; }

    public boolean isFinished() { return win || nlives == 0 || remainingTime == 0 || !running; }

    // GameStatus:
    public String positionToString(int col, int row) {
        return gameObjects.postitionToString(new Position(row, col));
    }

    public boolean playerWins() { return win; }
    public boolean playerLoses() { return nlives == 0 || remainingTime == 0; }
    public int remainingTime() { return remainingTime; }
    public int points() { return points; }
    public int numLives() { return nlives; }

    // GameWorld:
    public boolean isSolid(int col, int row) { return gameObjects.isSolid(col, row); }

    public void marioExited() {
        points += remainingTime * 10;
        remainingTime = 0;
        win = true;
    }

    private void mario_dies() {
        if (nlives == 1) {
            nlives--;
        } else {
            int save_lives = nlives;
            reset();
            nlives = save_lives - 1;
            if (nLevel == -1) nlives = 3;
        }
    }

    public void addPoints(int pointsToAdd) { points += pointsToAdd; }
    public void doInteractionsFrom(GameObject from) { gameObjects.doInteractionsFrom(from); }

    @Override
    public String toString() { return "TODO: Hola soy el game"; }

}