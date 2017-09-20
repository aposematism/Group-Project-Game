package graphics;

import Exceptions.NotImplementedYetException;
import javafx.scene.canvas.Canvas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.Region;


/**
 * The canvas for the game world, it will draw all entities in the given region,
 *NOTE: that the given region must have a player entity inside it in order for the
 * drawAll() function to work. This is so because it needs to translate everything
 * in the region according to the player location.
 */
public class GameCanvas extends Canvas {
    private GridManager currentGrid;
    private Region currentRegion;

    private Map<Region, GridManager> usedRegions = new HashMap<>();

    /**
     * Create a new Game Canvas, initialized with the initialRegion it gets passed.
     * @param initialRegion The Region the player supposedly starts the game in.
     * @param width width of the canvas (NOTE: in PIXELS!!)
     * @param height height of the canvas (NOTE: in PIXELS!!)
     */
    public GameCanvas(Region initialRegion, int width, int height){
        super(width, height);
    }

    /**
     * Create a new Game Canvas, initialized with the initialRegion it gets passed.
     * @param initialRegion The Region the player supposedly starts the game in.
     */
    public GameCanvas(Region initialRegion){
        super();
    }


    /**
     * Will update the region that gets drawn to this canvas
     * @param newRegion The new region to draw all the entities of
     */
    public void switchRegions(Region newRegion){
        throw new NotImplementedYetException();
    }

    /**
     * @return
     */
    public GridManager getGrid(){
        throw new NotImplementedYetException();
    }

    public void setGrid(){
        throw new NotImplementedYetException();
    }


    public void drawAll(){

    }
}
