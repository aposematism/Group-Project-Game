package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;

import java.util.Collection;

/**
 * The Player
 *
 * Created by Mark on 19/09/2017.
 *
 * @author Mark Metcalfe
 */
public class Player extends Mob {

	/**
	 * The Items, Weapon and Armours the player has
	 */
	private Inventory inventory;

	/**
	 * Represents how much currency the player possess.
	 * Used for buying items
	 */
	private int wallet;

	/**
	 * The amount of damage the player can deal by default, without a weapon.
	 */
	public static final double DEFAULT_DAMAGE = 5;

	/**
	 * Create a player with existing health and wallet values.
	 * Used for loading from a save file.
	 */
	public Player(String name, String spritePath, double health, int wallet, Direction direction, Item... items){
		super(name, spritePath, health, direction);
		this.wallet = wallet;
		inventory = new Inventory();
		for(Item i: items)
			inventory.add(i);
	}

	/**
	 * Create a starting, default instance of Player.
	 */
	public Player(String spritePath, Direction direction){ this("Player", spritePath, FULL_HEALTH, 0, direction); }

	/**
	 * Checks if the inventory contains any of the given items
	 */
	public boolean possesses(Item... items){
		for(Item item: items){
			if(inventory.contains(item))
				return true;
		}
		return false;
	}

	/**
	 * Returns the inventory object
	 */
	public Inventory inventory() { return inventory; }

	/**
	 * Returns how much currency the player currently has
	 */
	public int getWallet() { return this.wallet; }

	/**
	 * Increases the player's currency
	 */
	public void addFunds(int amount) { this.wallet += amount; }

	/**
	 * Decreases the player's currency
	 */
	public void removeFunds(int amount) { this.wallet -= amount; }

	/**
	 * Decreases the player's health points by a specified value.
	 * It takes into account the armour the player is wearing,
	 * the better the armour, the less damage taken.
	 */
	@Override
	protected void damage(double amount) {
		double armourTotal = 0;
		for(Armour a: inventory.getArmour())
			if(a!=null)
				armourTotal += a.getRating();
		super.damage(amount * ((100 - armourTotal)/100));
	}

	/**
	 * The Player is a solid entity, and cannot be moved through
	 */
	@Override
	public boolean isPenetrable() { return false; }

	@Override
	public String toString() {
		return super.toString()+" "+wallet+" "+inventory.toString();
	}

	/**
	 * Doesn't do anything, Player can't interact with itself
	 */
	public void interact(GameContext context, Notifier notifier) {}
}
