package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Tile;

import javafx.scene.image.Image;

/**
 * Created by Mark on 19/09/2017.
 */
public class Weapon extends Item {

	private final double STRENGTH;

	private Actions actions;

	public Weapon(GameContext context, Tile spawnPos, Image sprite, double strength){
		super(context, spawnPos, sprite);
		this.STRENGTH = strength;
	}

	public double getStrength(){
		return STRENGTH;
	}

	public void attack(){
		actions.attack(this);
	}

	interface Actions extends Item.Actions {
		void attack(Weapon weapon);
	}

	public void setActions(Actions newActions){
		this.actions=newActions;
	}
}
