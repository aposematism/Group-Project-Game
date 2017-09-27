package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Tile;

import javafx.scene.image.Image;

/**
 * Created by Mark on 27/09/17.
 */
public class Terrain extends Entity {

    public Terrain(GameContext context, Image sprite){
        super(context, sprite);
    }

    @Override
    public void interact(GameContext context){} //Not intractable

    @Override
    public boolean isPenetrable() {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
