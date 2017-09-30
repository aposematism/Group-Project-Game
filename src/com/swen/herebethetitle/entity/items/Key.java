package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;

import javafx.scene.image.Image;

/**
 * Created by metcalmark on 27/09/17.
 */
public final class Key extends Item {

    private final int KEY;

    public Key(Image sprite, int key){
    	super(sprite);
        this.KEY = key;
    }

	public boolean equals(int key) { return this.KEY == key; }

	@Override
	public String toString() { return getClass().getName()+" "+KEY; }
}
