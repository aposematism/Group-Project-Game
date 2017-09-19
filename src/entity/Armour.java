package entity;

import graph.Node;

/**
 * Created by Mark on 19/09/2017.
 */
public class Armour extends Item {

	enum TYPE {
		HELMET,
		PLATEBODY,
		PLATELEGS,
		BOOTS
	}

	private final TYPE SLOT;

	private final double RATING;

	public Armour(Player player, Node spawnPos, TYPE type, double rating){
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

	void pickup() {
		//TODO
	}

	void use() {
		//TODO
	}
}
