package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

/**
 * Overarching interface that defines all entities found in the game world.
 *
 * Created by Mark on 19/09/17.
 *
 * @author Mark Metcalfe
 */
public abstract class Entity {

	private String spritePath;

	private String name;

	/**
	 * @param name The name of the entity instance
	 * @param spritePath The JavaFX Image url that represents the entity
	 */
	public Entity(String name, String spritePath) {
		this.name = name;
		this.spritePath = spritePath;
	}

	/**
	 * Interactions are supported by all entities, has varying implementations
	 * 
	 * This will dispatch notifications to a notifier.
	 */
	public abstract void interact(GameContext context, Notifier notifier);
	
	/**
	 * Interacts with the entity.
	 */
	public void interact(GameContext context) {
	    interact(context, new Notifier());
	}

	/**
	 * Get the JavaFX Image path
	 */
	public String getSpritePath() { return spritePath; }

	/**
	 * Update the sprite used
	 *
	 * @param spritePath
	 */
	protected void setSprite(String spritePath) {
		this.spritePath = spritePath;
	}

	/**
	 * Get the name of the entity instance
	 * Could be used for tooltips etc
	 */
	public String getName() { return name; }

	public String toString() { return getClass().getSimpleName()+" \""+name+"\" \""+spritePath+"\""; }

	/**
	 * Checks if the entity can be walked through.
	 */
	abstract public boolean isPenetrable();
}