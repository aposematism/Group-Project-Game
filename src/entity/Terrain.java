package entity;

import entity.*;
import model.*;
import util.*;
import javafx.scene.image.Image;

/**
 * Created by Mark on 27/09/17.
 */
public class Terrain extends Entity {

    public Terrain(GameContext context, Tile tile, Image sprite){
        super(context, tile, sprite);
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
