package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.util.Direction;
import javafx.scene.image.Image;

/**
 * Provides the basic implementation of functions that both Player and NPC would use,
 * notably for changing their health values and changing where they are in the game world.
 *
 * Created by Mark on 19/09/17.
 *
 * @author Mark Metcalfe
 */
public abstract class Mob extends Entity {

	/**
	 * A full health value.
	 */
	public static final double FULL_HEALTH = 100;

	/**
	 * An empty health value.
	 */
	public static final double NO_HEALTH = 0;

	/**
	 * The amount of damage the mob can take before it dies
	 */
	protected double health;

	/**
	 * The direction that the mob is currently facing.
	 */
	private Direction direction;

	/**
	 * Mob itself is abstract, so must be constructed in a subclass
	 */
	public Mob(String name, String spritePath, double startingHealth, Direction direction){
		super(name, spritePath);
		this.health = startingHealth;
		this.direction = direction;
	}

	/**
	 * The direction that the mob is currently facing
	 */
	public Direction getDirection() { return direction; }

	/**
	 * Update the direction that the mob is facing
	 */
	public void setDirection(Direction direction) { this.direction=direction; }

	/**
	 * How much health the the mob currently has
	 */
	public double getHealth() { return health; }

	/**
	 * Remove health
	 * @param amount A positive number
	 * @throws IllegalArgumentException if input amount isn't positive
	 */
	public void damage(double amount){
		if(amount<=0)
			throw new IllegalArgumentException("Must Be Positive!");
		health -= amount;
		if(health<NO_HEALTH)
			health = NO_HEALTH;
	}

	/**
	 * Add health
	 * @param amount A positive number
	 * @throws IllegalArgumentException if input amount isn't positive
	 */
	public void heal(double amount){
		if(amount<=0)
			throw new IllegalArgumentException("Must Be Positive!");
		health += amount;
		if(health>FULL_HEALTH)
			health = FULL_HEALTH;
	}
	
	/**
	 * Checks if the mob has any health left.
	 */
	public boolean isDead() {
	    return this.health <= 0.0;
	}

	@Override
	public String toString() {
		return super.toString()+" "+health+" "+direction.toString();
	}
}
