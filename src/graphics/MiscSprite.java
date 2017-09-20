package graphics;

import java.awt.*;

import javafx.scene.image.Image;
import javafx.geometry.BoundingBox;
import Exceptions.NotImplementedYetException;
import util.Direction;
import util.GridLocation;

/**
 * This class will manage sprites of "non-entity" based origins, such as snowflakes, rainfall etc.
 * that will animate over top of the already rendered game world.
 * Created by weirjosh on 19/09/17.
 */
public class MiscSprite {
    protected Point position;
    protected Image image;
}
