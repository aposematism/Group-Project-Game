package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;

import com.swen.herebethetitle.model.Tile;
import javafx.scene.image.Image;

/**
 * Overarching interface that defines all entities found in the game world.
 *
 * Created by Mark on 19/09/17.
 */
public abstract class Entity {
	private Image sprite;

	public Entity(Image sprite) { this.sprite = sprite; }

	public abstract void interact(GameContext context);

	public Image getSprite() { return sprite; }

	public String toString(){
		return getClass().getName();
	}

	/**
	 * Checks if the entity can be walked through.
	 */
	abstract public boolean isPenetrable();
}