package entity;

import javafx.scene.image.Image;
import model.GameContext;
import util.GridLocation;

/**
 * Created by Mark on 27/09/17.
 */
public class Terrain extends Entity {

    public Terrain(GameContext context, GridLocation location, Image sprite){
        super(context, location, sprite);
    }

    @Override
    public void interact(Player player){} //Not intractable

    @Override
    boolean isPenetrable() {
        return true;
    }

    @Override
    public String toString() {
        return null;
    }
}
