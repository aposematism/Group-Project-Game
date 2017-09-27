package entity;

import javafx.scene.image.Image;
import model.GameContext;
import util.Direction;
import util.GridLocation;

/**
 * Provides the basic implementation of functions that both Player and NPC would use,
 * notably for changing their health values and changing where they are in the game world.
 *
 * Created by Mark on 19/09/17.
 */
public abstract class Mob extends Entity {
	/**
	 * A full health value.
	 */
	public static final int FULL_HEALTH = 100;
	/**
	 * An empty health value.
	 */
	public static final int NO_HEALTH = 0;

	private int health;

	private Direction direction;

	public Mob(GameContext context, GridLocation spawnLocation, Image sprite, int startingHealth, Direction direction){
		super(context, spawnLocation, sprite);
		this.health = startingHealth;
		this.direction = direction;
	}

	public Direction getDirection(){
		return direction;
	}

	public void setDirection(Direction direction){
		this.direction=direction;
	}

	public void move(Direction direction){
//		GridLocation loc = location.adjacent(direction)
//		if(loc.x>=0 && loc.y>=0 )
	}

	public int getHealth(){
		return health;
	}

	public void damage(int amount){
		health -= amount;
		if(health<NO_HEALTH) health = NO_HEALTH;
	}

	public void heal(int amount){
		health += amount;
		if(health>FULL_HEALTH) health = FULL_HEALTH;
	}
}
