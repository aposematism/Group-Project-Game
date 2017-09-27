package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.*;
import com.swen.herebethetitle.util.*;

import javafx.scene.image.Image;

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

	public Armour(GameContext context, Tile spawnPos, Image sprite, TYPE type, double rating){
		super(context, spawnPos, sprite);
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
