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

	private int health;

	private GridLocation location;
	private Direction direction;

	public Mob(GridLocation spawnLocation, int startingHealth, Direction direction){
		this.location = spawnLocation;
		this.health = startingHealth;
		this.direction = direction;
	}

	public void interact(Player player) {
		//TODO
	}

	public Direction getDirection(){
		return direction;
	}

	public void setDirection(Direction direction){
		this.direction=direction;
	}

	public GridLocation getLocation(){
		return location;
	}

	public void setLocation(GridLocation location){
		this.location = location;
	}

	public int getHealth(){
		return health;
	}

	public void damage(int amount){
		health = health-amount<NO_HEALTH ? NO_HEALTH : health-amount;
	}

	public void heal(int amount){
		health = health+amount>FULL_HEALTH ? FULL_HEALTH : health+amount;
	}
}
