package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import javafx.scene.image.Image;

/**
 * Created by Mark on 19/09/2017.
 */
public final class Weapon extends Item {

	private final double STRENGTH;

	public Weapon(Image sprite, double strength){
		super(sprite);
		this.STRENGTH = strength;
	}

	public double getStrength() { return STRENGTH; }

	@Override
	public void use(GameContext context) {}

	@Override
	public String toString() {
		return null;
	}
}
