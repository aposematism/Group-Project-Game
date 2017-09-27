package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

/**
 * Created by Mark on 27/09/17.
 */
public class Terrain extends Entity {

    public Terrain(Image sprite){ super(sprite); }

    @Override
    public void interact(GameContext context){} //Not intractable

    @Override
    public boolean isPenetrable() { return false; }

    @Override
    public String toString() { return null; }
}
