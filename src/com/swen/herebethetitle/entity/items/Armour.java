package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Tile;

import javafx.scene.image.Image;

/**
 * Special Item that is designed to fit in the armour slots in Inventory
 * Has a rating which dictates the quality of the armour and will be
 * used to calculate how much damage it will absorb.
 *
 * Created by Mark on 19/09/2017.
 */
public final class Armour extends Item {

	/**
	 * The four different types of armour there are that covers a body part
	 */
	public enum TYPE {
		HELMET,
		TORSO,
		LEGS,
		BOOTS
	}

	/**
	 * What type of armour this one is
	 */
	private final TYPE SLOT;

	/**
	 * How good the armour is at protecting the player
	 */
	private final double RATING;

	public Armour(Image sprite, TYPE type, double rating){
		super(sprite);
		SLOT = type;
		RATING = rating;
	}

	public TYPE getSlot() { return SLOT; }

	public double getRating() { return RATING; }

	/**
	 * Checks if this armour is better than the Player's current armour, if it is it replaces it
	 * @param context
	 */
	@Override
	public void pickup(GameContext context) {
		if(context.player.inventory().getArmour(this.SLOT)==null)
			context.player.add(this);
		else if(this.RATING>context.player.inventory().getArmour(this.SLOT).getRating())
			context.player.add(this);
	}

	@Override
	public String toString() { return super.toString()+" "+SLOT+" "+RATING; }

	/**
	 * Overrides superclass method which deletes the item from inventory,
	 * Deliberatley designed to do nothing when selected in the player's inventory
	 * @param context
	 */
	@Override
	public void use(GameContext context) {}
}
