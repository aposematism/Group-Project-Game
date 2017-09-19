package graphics;

import java.awt.*;

import javafx.scene.image.Image;
import javafx.geometry.BoundingBox;
import Exceptions.NotImplementedYetException;
import util.Direction;
import util.GridLocation;

/**
 * This will store data and provide public methods to facilitate drawing objects on the screen.
 * Created by weirjosh on 19/09/17.
 */
public class Sprite {
    protected Point position;
    protected Image image;
    protected BoundingBox boundingBox;

    //TODO: will be final, initialized in one of the constructors, all
    //sprites must be part of a grid.
    Grid grid;

    /**
     * Creates sprite in centre of given cell of the given Grid object,
     * adjusting the size automatically to fit the cell.
     */
    public Sprite(String imgDir, int cellRow, int cellCol, Grid gr){
    }

    /**
     * Creates sprite in centre of the given cell of the given GRID object, with
     * the given dimensions in pixels.
     */
    public Sprite(String imgDir, int cellRow, int cellCol, Dimension size, int height, Grid gr){
    }

    /**
     * Creates sprite at the given point on the canvas, with the given dimensions
     */
    public Sprite(String imgDir, Point location, Dimension size, int height, Grid gr){
    }

    /**
     *Returns the bounding box of this sprite used to manage collision
     */
    public BoundingBox getBoundingBox(){
        throw new NotImplementedYetException();
    }

    /**
     * returns its location
     */
    public GridLocation getCell(){
        throw new NotImplementedYetException();
    }

    /**
     * Translate this sprite's location in pixel coordinates dx and dy
    */
    public void translate(int dx, int dy){
    }

    /**
     *Translate this cell by an amount proportional to the size of the cell
     * in the given direction. Eg. if cell width was 40 and position was 10, 10,
     * then moveCell(Direction.Right) will change its position to 50, 10.
     */
    public void moveOneCell(Direction d){
    }

    /**
     *Draw this sprite to the given canvas.
     */
    public void draw(Canvas c){
    }







}
