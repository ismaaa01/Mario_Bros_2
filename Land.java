package tp1_2.logic.gameobjects;
import tp1_2.view.Messages;
import tp1_2.logic.GameWorld;
import tp1_2.logic.Position;


public class Land extends GameObject {
	
	
	private final String icono =Messages.LAND;
	private static final String Name = Messages.LAND_NAME;
	private static final String Shortcut = Messages.LAND_SHORTCUT;
	
	
	public Land() {
		super(Name,Shortcut);
	}
	
	public Land(GameWorld game,Position pos) {
		super(game,pos);
	}
	
	public boolean isSolid() {
		return true;
	}
	
	@Override
	public String getIcon() {
		return icono;
	}

	public void update() {}
}