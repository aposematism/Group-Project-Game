package entity;

import util.GridLocation;

/**
 * Special Item that is designed to fit in the armour slots in player
 * Has a rating which dictates the quality of the armour and will be
 * used to calculate how much damage it will absorb.
 *
 * Created by Mark on 19/09/2017.
 */
public class Armour extends Item {

	enum TYPE {
		HELMET,
		TORSO,
		LEGS,
		BOOTS
	}

	private final TYPE SLOT;

	private final double RATING;

	public Armour(Player player, GridLocation spawnPos, TYPE type, double rating){
		super(player, spawnPos);
		SLOT = type;
		RATING = rating;
	}

	public TYPE getSlot() {
		return SLOT;
	}

	public double getRating() {
		return RATING;
	}
}
