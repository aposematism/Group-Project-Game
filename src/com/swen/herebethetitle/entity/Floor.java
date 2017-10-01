package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

/**
 * Created by Mark on 27/09/17.
 */
public class Floor extends Entity {

    public Floor(Image sprite){ super(sprite); }

	/**
	 * Deliberately Blank, Floor can't be interacted with by design
	 */
	@Override
    public void interact(GameContext context){}

    @Override
    public boolean isPenetrable() { return true; }

    @Override
    public String toString() { return super.toString(); }
}
