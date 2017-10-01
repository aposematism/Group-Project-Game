package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import javafx.scene.image.Image;

/**
 * Created by Mark on 19/09/2017.
 */
public final class Weapon extends Item {

	private final double STRENGTH;

	private final boolean IS_MELEE;

	public Weapon(Image sprite, boolean isMelee, double strength){
		super(sprite);
		this.IS_MELEE = isMelee;
		this.STRENGTH = strength;
	}

	public double getStrength() { return STRENGTH; }

	public boolean isMelee() { return IS_MELEE; }

	@Override
	public String toString() { return super.toString()+" "+IS_MELEE+" "+STRENGTH; }

	/**
	 * Overrides superclass method which deletes the item from inventory,
	 * Deliberately designed to do nothing when selected in the player's inventory
	 */
	@Override
	public void use(GameContext context) {}
}
