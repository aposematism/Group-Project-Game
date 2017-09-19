package entity;

import util.Direction;
import util.GridLocation;

/**
 * Provides the basic implementation of functions that both Player and NPC would use,
 * notably for changing their health values and changing where they are in the game world.
 *
 * Created by Mark on 19/09/17.
 */
public abstract class Mob implements Entity {
	/**
	 * A full health value.
	 */
	public static final int FULL_HEALTH = 100;
	/**
	 * An empty health value.
	 */
	public static final int NO_HEALTH = 0;

	private final int startingHealth;
	private int health;

	private GridLocation position;
	private Direction direction;

	public Mob(GridLocation spawnPos, int startingHealth, Direction direction){
		this.position = spawnPos;
		this.startingHealth = startingHealth;
		this.health = startingHealth;
		this.direction = direction;
	}

	public void interact(Player player) {
		//TODO
	}

	public GridLocation getPosition(){
		return position;
	}

	public Direction getDirection(){
		return direction;
	}

	public void setDirection(Direction direction){
		this.direction=direction;
	}

	public int getHealth(){
		return health;
	}

	public void damage(int amount){
		health = health-amount<0 ? 0 : health-amount;
	}

	public void heal(int amount){
		health = health+amount>startingHealth ? startingHealth : health+amount;
	}
}
