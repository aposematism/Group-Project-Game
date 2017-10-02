package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;
import javafx.scene.image.Image;

/**
 * Overarching interface that defines all entities found in the game world.
 *
 * Created by Mark on 19/09/17.
 *
 * @author Mark Metcalfe
 */
public abstract class Entity {

	private Image sprite;

	private String name;

	/**
	 * @param name The name of the entity instance
	 * @param sprite The JavaFX Image that represents the entity
	 */
	public Entity(String name, Image sprite) {
		this.name = name;
		this.sprite = sprite;
	}

	/**
	 * Interactions are supported by all entities, has varying implementations
	 * @param context
	 */
	public abstract void interact(GameContext context);

	/**
	 * Get the JavaFX Image sprite
	 */
	public Image getSprite() { return sprite; }

	/**
	 * Get the name of the entity instance
	 * Could be used for tooltips etc
	 */
	public String getName() { return name; }

	public String toString() { return getClass().getSimpleName()+" \""+name+"\""; }

	/**
	 * Checks if the entity can be walked through.
	 */
	abstract public boolean isPenetrable();
}