package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

/**
 * A Sprite is an javafx.scene.image.Image, along with a GridLocation.
 * Provides support for drawing to a Canvas using a GridLocation.
 * @author weirjosh
 */
public class Sprite {

    protected GridLocation loc;
    protected Image img;

    /**
     * Default constructor
     */
    public Sprite(){}

    /**
     * Create a new Sprite.
     * @param i The image this sprite will represent
     * @param l The location of this sprite on the canvas.
     * @author weirjosh
     */
    public Sprite(Image i, GridLocation l){
        loc = l;
        img = i;
    }

    /**
     * Draw this sprite using the given GraphicsContext. Offset by a given point.
     * @param gc the GraphicsContext used to render the image
     * @param g The gridManager for positioning
     * @param offset The offset from the origin.
     * @author weirjosh
     */
    public void draw(GraphicsContext gc, GridManager g, Point offset){
        Point pos = g.getRealCoordinates(loc, offset);
        gc.drawImage(img, pos.x, pos.y, g.getCellSize(), g.getCellSize());
    }

    /**
     * Draw this sprite using the given GraphicsContext.
     * @param gc the GraphicsContext used to render the image
     * @param g The gridManager for positioning
     * @author weirjosh
     */
    public void draw(GraphicsContext gc, GridManager g){
        Point pos = g.getRealCoordinates(loc);
        gc.drawImage(img, pos.x, pos.y, g.getCellSize(), g.getCellSize());
    }

    /**
     * Draws this sprite only if its position is within the given bounds
     * @param gc the GraphicsContext used to render the image
     * @param g The gridManager for positioning
     * @param offset The offset from the origin.
     * @param width The width of the bounds
     * @param height The height of the bounds
     * @return Whether or not drawing was successful.
     */
    public boolean drawInBounds(GraphicsContext gc, GridManager g, Point offset, int width, int height){

        Point pos = g.getRealCoordinates(loc, offset);

        if(checkInBounds(pos, g, width, height)) {
            gc.drawImage(img, pos.x, pos.y, g.getCellSize(), g.getCellSize());
            return true;
        }
        return false;
    }


    /**
     * @return The image of this sprite
     * @author weirjosh
     */
    public Image getImage(){
        return img;
    }

    /**
     * @return The location of this sprite
     * @author weirjosh
     */
    public GridLocation getLocation() {
        return loc;
    }

    public void setLocation(GridLocation newLoc){
        loc = newLoc;
    }

    public void setImage(Image im){img = im;}




    private boolean checkInBounds(Point p, GridManager g, int width, int height){
        int size = g.getCellSize();
        if(p.x+size < 0 ||
                p.x > width ||
                p.y+size < 0 ||
                p.y > height){
            return false;
        }
        return true;
    }
}
