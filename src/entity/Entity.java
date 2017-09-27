package entity;

import javafx.scene.image.Image;
import model.GameContext;
import util.GridLocation;

import java.util.Optional;

/**
 * Overarching interface that defines all entities found in the game world.
 *
 * Created by Mark on 19/09/17.
 */
public abstract class Entity {

	private GameContext context;
	private GridLocation location;
	private Image sprite;

	public Entity(GameContext context, GridLocation location, Image sprite){
		this.context = context;
		this.location = location;
		this.sprite = sprite;
	}

	public abstract void interact(GameContext context);

	public GridLocation getLocation() { return location; }

	public void setLocation(GridLocation location) { this.location = location; }

	public Image getSprite() { return sprite; }

	public Player player() { return context.getPlayer(); }

	public abstract String toString();

	/**
	 * Checks if the entity can be walked through.
	 */
	abstract public boolean isPenetrable();
}