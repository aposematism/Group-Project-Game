package entity;

import javafx.scene.image.ImageView;
import util.GridLocation;

/**
 * Special Item that is designed to fit in the armour slots in player
 * Has a rating which dictates the quality of the armour and will be
 * used to calculate how much damage it will absorb.
 *
 * Created by Mark on 19/09/2017.
 */
public class Armour extends Item {

	public enum TYPE {
		HELMET,
		TORSO,
		LEGS,
		BOOTS
	}

	private final TYPE SLOT;

	private final double RATING;

	public Armour(Player player, GridLocation spawnPos, ImageView sprite, TYPE type, double rating){
		super(player, spawnPos, sprite);
		SLOT = type;
		RATING = rating;
	}

	public TYPE getSlot() {
		return SLOT;
	}

	public double getRating() {
		return RATING;
	}

	@Override
	public String toString() {
		return super.toString()+" "+SLOT+" "+RATING;
	}
}
