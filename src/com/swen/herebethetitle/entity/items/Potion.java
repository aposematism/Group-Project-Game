package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import javafx.scene.image.Image;

/**
 * Consumable that increases or decreases the player's health
 *
 * Created by Mark on 30/09/2017.
 *
 * @author Mark Metcalfe
 */
public final class Potion extends Item {

	private final double HEALTH_CHANGE;

	/**
	 * @param name The name of the potion
	 * @param spritePath What it looks like
	 * @param healthChange How much it will increase the Player's health by
	 */
	public Potion(String name, String spritePath, double healthChange){
		super(name, spritePath);
		this.HEALTH_CHANGE = healthChange;
	}

	/**
	 * Heal or Damage the player depending on whether it is a positive or negative potion
	 */
	@Override
	public void use(GameContext context) {
		if(HEALTH_CHANGE>0){
			context.player.heal(HEALTH_CHANGE);
		} else if(HEALTH_CHANGE<0){
			context.player.damage(Math.abs(HEALTH_CHANGE));
		}
		super.use(context);
	}

	@Override
	public String toString() { return super.toString()+" "+HEALTH_CHANGE; }
}
