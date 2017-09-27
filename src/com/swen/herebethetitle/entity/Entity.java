package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.*;
import com.swen.herebethetitle.util.*;

import javafx.scene.image.Image;

/**
 * Overarching interface that defines all entities found in the game world.
 *
 * Created by Mark on 19/09/17.
 */
public abstract class Entity {

	private GameContext context;
	private Tile tile;
	private Image sprite;

	public Entity(GameContext context, Tile tile, Image sprite){
		this.context  = context;
		this.tile = tile;
		this.sprite   = sprite;
	}

	public abstract void interact(GameContext context);

	public Tile getTile() { return tile; }

	public void setLocation(Tile tile) { this.tile = tile; }

	public Image getSprite() { return sprite; }

	public Player player() { return context.getPlayer(); }

	public abstract String toString();

	/**
	 * Checks if the entity can be walked through.
	 */
	abstract public boolean isPenetrable();
}