package tp1_2.logic;

import tp1_2.logic.gameobjects.*;
import tp1_2.exceptions.GameModelException;

import java.util.ArrayList;
import java.util.List;

public class LevelGameConfiguration implements GameConfiguration {

    private int levelNumber;
    private int remainingTime;
    private int lives;
    private int points;
    private List<GameObject> objects;

    public LevelGameConfiguration(int levelNumber) {
        if (levelNumber < -1 || levelNumber > 2) {
            throw new IllegalArgumentException("Nivel inválido: " + levelNumber);
        }
        this.levelNumber = levelNumber;
        loadLevel();
    }

    public LevelGameConfiguration() {
        this.levelNumber = 1;
        loadLevel();
    }

    public LevelGameConfiguration(FileGameConfiguration loader) {
		remainingTime = loader.getRemainingTime();
		lives = loader.numLives();
		objects = loader.getObjects();
	}

    private void loadLevel() {
        switch (levelNumber) {
            case -1: initBlankLevel(); break;
            case 0:  initLevel0(); break;
            case 1:  initLevel1(); break;
            case 2:  initLevel2(); break;
        }
    }

    private void baseInit() {
        this.objects = new ArrayList<>();
        this.remainingTime = 100;
        this.lives = 3;
        this.points = 0;
    }

    private void initBlankLevel() {
        baseInit();
    }

    private void initLevel0() {
        baseInit();
        for(int col = 0; col < 15; col++) {
            objects.add(new Land(null,new Position(13,col)));
            objects.add(new Land(null,new Position(14,col)));		
        }

        objects.add(new Land(null,new Position(Game.DIM_Y-3,9)));
        objects.add(new Land(null,new Position(Game.DIM_Y-3,12)));

        for(int col = 17; col < Game.DIM_X; col++) {
            objects.add(new Land(null,new Position(Game.DIM_Y-2, col)));
            objects.add(new Land(null,new Position(Game.DIM_Y-1, col)));		
        }

        objects.add(new Land(null,new Position(9,2)));
        objects.add(new Land(null,new Position(9,5)));
        objects.add(new Land(null,new Position(9,6)));
        objects.add(new Land(null,new Position(9,7)));
        objects.add(new Land(null,new Position(5,6)));

        // Salto final
        int tamX = 8;
        int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;

        for(int col = 0; col < tamX; col++) {
            for (int fila = 0; fila < col+1; fila++) {
                objects.add(new Land(null,new Position(posIniY- fila, posIniX+ col)));
            }
        }

        objects.add(new ExitDoor(null,new Position(Game.DIM_Y-3, Game.DIM_X-1)));

        // Personajes
        Mario mario = new Mario(null, new Position(Game.DIM_Y-3, 0));
        mario.go_big();
        objects.add(mario);

        objects.add(new Goombas(null, new Position(0, 19)));
    }

    private void initLevel1() {
        baseInit();

        for(int col = 0; col < 15; col++) {
            objects.add(new Land(null,new Position(13,col)));
            objects.add(new Land(null,new Position(14,col)));
        }

        objects.add(new Land(null,new Position(Game.DIM_Y-3,9)));
        objects.add(new Land(null,new Position(Game.DIM_Y-3,12)));

        for(int col = 17; col < Game.DIM_X; col++) {
            objects.add(new Land(null,new Position(Game.DIM_Y-2, col)));
            objects.add(new Land(null,new Position(Game.DIM_Y-1, col)));
        }

        objects.add(new Land(null,new Position(9,2)));
        objects.add(new Land(null,new Position(9,5)));
        objects.add(new Land(null,new Position(9,6)));
        objects.add(new Land(null,new Position(9,7)));
        objects.add(new Land(null,new Position(5,6)));

        // Salto final
        int tamX = 8;
        int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;

        for(int col = 0; col < tamX; col++) {
            for (int fila = 0; fila < col+1; fila++) {
                objects.add(new Land(null,new Position(posIniY- fila, posIniX+ col)));
            }
        }

        objects.add(new ExitDoor(null,new Position(Game.DIM_Y-3, Game.DIM_X-1)));

        Mario mario = new Mario(null,new Position(Game.DIM_Y-3, 0));
        mario.go_big();
        objects.add(mario);

        objects.add(new Goombas(null, new Position(0, 19)));
        objects.add(new Goombas(null, new Position(12, 6)));
        objects.add(new Goombas(null, new Position(4, 6)));
        objects.add(new Goombas(null, new Position(12, 8)));
        objects.add(new Goombas(null, new Position(10, 10)));
        objects.add(new Goombas(null, new Position(12, 11)));
        objects.add(new Goombas(null, new Position(12, 14)));
    }
    private void initLevel2() {
        baseInit();

        for(int col = 0; col < 15; col++) {
            objects.add(new Land(null,new Position(13,col)));
            objects.add(new Land(null,new Position(14,col)));
        }

        objects.add(new Land(null,new Position(Game.DIM_Y-3,9)));
        objects.add(new Land(null,new Position(Game.DIM_Y-3,12)));

        for(int col = 17; col < Game.DIM_X; col++) {
            objects.add(new Land(null,new Position(Game.DIM_Y-2, col)));
            objects.add(new Land(null,new Position(Game.DIM_Y-1, col)));
        }

        objects.add(new Land(null,new Position(9,2)));
        objects.add(new Land(null,new Position(9,5)));
        objects.add(new Land(null,new Position(9,6)));
        objects.add(new Land(null,new Position(9,7)));
        objects.add(new Land(null,new Position(5,6)));

        int tamX = 8;
        int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;

        for(int col = 0; col < tamX; col++) {
            for (int fila = 0; fila < col+1; fila++) {
                objects.add(new Land(null,new Position(posIniY- fila, posIniX+ col)));
            }
        }

        objects.add(new ExitDoor(null,new Position(Game.DIM_Y-3, Game.DIM_X-1)));

        Mario mario = new Mario(null,new Position(Game.DIM_Y-3, 0));
        mario.go_big();
        objects.add(mario);

        objects.add(new Goombas(null, new Position(0, 19)));
        objects.add(new Goombas(null, new Position(12, 6)));
        objects.add(new Goombas(null, new Position(4, 6)));
        objects.add(new Goombas(null, new Position(12, 8)));
        objects.add(new Goombas(null, new Position(10, 10)));
        objects.add(new Goombas(null, new Position(12, 11)));
        objects.add(new Goombas(null, new Position(12, 14)));

        objects.add(new Box(null,new Position(9,4)));
        objects.add(new Mushroom(null, new Position(12, 8)));
        objects.add(new Mushroom(null, new Position(2, 20)));
    }
    @Override
    public int getRemainingTime() { return remainingTime; }

    @Override
    public int numLives() { return lives; }

    @Override
    public int points() { return points; }

    @Override
    public List<GameObject> getObjects() { return objects; }
}

