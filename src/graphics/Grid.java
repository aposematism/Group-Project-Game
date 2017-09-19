package graphics;

import Exceptions.NotImplementedYetException;
import javafx.geometry.BoundingBox;
import javafx.geometry.Dimension2D;
import util.GridLocation;

import java.awt.*;

/**A grid object is a representation of an area of evenly spaced cells over a given area.
 It will have static methods for turning its own grid coordinates into real world pixel coordinates
 among other things.
 * Created by weirjosh on 19/09/17.
 */
public class Grid extends Canvas{

    //The canvas location (top right corner) of the grid.
    private Point location;

    //The pixel bounds of this grid
    private Dimension2D boundingBox;

    //The size (rows/cols) of this grid
    private Dimension2D dimensions;

    /**
     *Create a new grid object at the given position on the canvas, with
     * the given size and number of rows and cols.
     */
    public Grid(Point pos, Dimension size, int cols, int rows){

    }
    /**
     *Create a new grid object at the given position on the canvas, with
     * the given size and number of rows and cols, and vetical gap/horizontal gap
     * between cells.
     */
    public Grid(Point pos, Dimension size, int cols, int rows, int vGap, int hGap){

    }

    /**
     *Turns a grid location into real pixel coordinates in the context of this current grid.
     */
    public Point getRealCoordinates(GridLocation location){
        throw new NotImplementedYetException();
    }


}
