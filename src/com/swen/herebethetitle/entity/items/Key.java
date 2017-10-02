package com.swen.herebethetitle.entity.items;

import javafx.scene.image.Image;

/**
 * Used for interacting with doors
 *
 * Created by Mark on 27/09/17.
 *
 * @author Mark Metcalfe
 */
public final class Key extends Item {

    private final int KEY;

	/**
	 * @param name The name of the key
	 * @param sprite What it looks like
	 * @param key The ID of the key that matches a door
	 */
	public Key(String name, Image sprite, int key){
    	super(name, sprite);
        this.KEY = key;
    }

	/**
	 * Check whether a given key ID is the same as this one
	 */
	public boolean equals(int key) { return this.KEY == key; }

	@Override
	public String toString() { return super.toString()+" "+KEY; }
}
