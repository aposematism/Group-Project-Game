package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;

/**
 * Special Item that is designed to fit in the armour slots in Inventory
 * Has a rating which dictates the quality of the armour and will be
 * used to calculate how much damage it will absorb.
 *
 * Created by Mark on 19/09/2017.
 *
 * @author Mark Metcalfe
 */
public final class Armour extends Item {

	private final TYPE SLOT;
	private final double RATING;

	/**
	 * @param name The name of the armour
	 * @param spritePath What it looks like
	 * @param type What part of the body the armour covers
	 * @param rating How good it is at protecting the player
	 */
	public Armour(String name, String spritePath, TYPE type, double rating){
		super(name, spritePath);
		SLOT = type;
		RATING = rating;
	}

	/**
	 * The slot type the armour is
	 */
	public TYPE getSlot() { return SLOT; }

	/**
	 * The protection rating of the armour
	 */
	public double getRating() { return RATING; }

	/**
	 * Checks if this armour is better than the Player's current armour, if it is it replaces it
	 * @param context
	 */
	@Override
	protected void pickup(GameContext context) {
		if (context.player.inventory().getArmour(this.SLOT) == null || //doesn't have armour
				this.RATING > context.player.inventory().getArmour(this.SLOT).getRating() //or this armour is better
				) {
			super.pickup(context);
		}
	}

	/**
	 * Overrides superclass method which deletes the item from inventory,
	 * Deliberately designed to do nothing when selected in the player's inventory
	 */
	@Override
	public void use(GameContext context) {}

	@Override
	public String toString() { return super.toString()+" "+SLOT+" "+RATING; }

	@Override
	public Armour clone() throws CloneNotSupportedException {
		return new Armour(getName(), getSpritePath(), SLOT, RATING);
	}

	/**
	 * The four different types of armour there are that covers a body part
	 */
	public enum TYPE {
		BOOTS,
		LEGS,
		TORSO,
		HELMET
	}
}
