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
	 * @param context
	 */
	public abstract void interact(GameContext context);

	/**
	 * Get the JavaFX Image path
	 */
	public String getSpritePath() { return spritePath; }

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