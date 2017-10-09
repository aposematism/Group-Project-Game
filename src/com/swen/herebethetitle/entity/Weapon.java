package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;

/**
 * Item that increases the damage dealt by the player in attacks
 *
 * Created by Mark on 19/09/2017.
 *
 * @author Mark Metcalfe
 */
public final class Weapon extends Item {

	private final double STRENGTH;

	private final boolean IS_MELEE;

	/**
	 * @param name The name of the weapon
	 * @param spritePath What it looks like
	 * @param isMelee Whether or not its a melee weapon
	 * @param strength The amount of damage that it will deal
	 */
	public Weapon(String name, String spritePath, boolean isMelee, double strength){
		super(name, spritePath);
		this.IS_MELEE = isMelee;
		this.STRENGTH = strength;
	}

	/**
	 * The amount of damage that can be dealt by the weapon
	 */
	public double getStrength() { return STRENGTH; }

	/**
	 * Whether or not the weapon is a melee weapon
	 */
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
