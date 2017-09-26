package graphics;


import entity.Entity;
import entity.Player;
import exceptions.NotImplementedYetException;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
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
    private Player player;

    private Map<Region, GridManager> usedRegions = new HashMap<>();

    private GraphicsContext gc;

    /**
     * Create a new Game Canvas, initialized with the initialRegion it gets passed.
     * @param initialRegion The Region the player supposedly starts the game in.
     * @param width width of the canvas (NOTE: in PIXELS!!)
     * @param height height of the canvas (NOTE: in PIXELS!!)
     */
    public GameCanvas(Region initialRegion, int width, int height){
        super(width, height);
        construct(initialRegion);
    }

    /**
     * Create a new Game Canvas, initialized with the initialRegion it gets passed.
     * @param initialRegion The Region the player supposedly starts the game in.
     */
    public GameCanvas(Region initialRegion){
        super();
        construct(initialRegion);
    }

    /**
     * Will update the region that gets drawn to this canvas
     * @param newRegion The new region to draw all the entities of
     */
    public void switchRegions(Region newRegion){
        currentRegion = newRegion;
        resetCanvas();
    }

    /**
     * @return
     */
    public GridManager getGrid(){
        return currentGrid;
    }

    public void setGrid(GridManager newGrid){
        currentGrid = newGrid;
    }

    /**
     * Draw every entity held within the current region of this Canvas.
     */
    public void drawAll(){
        List<Entity> entities = currentRegion.getEntities();
        int size = currentGrid.getCellSize();

        resetCanvas();
        for(Entity e: entities){
            Point location = currentGrid.getRealCoordinates(
                    e.getLocation(), calcOffset(player));

            gc.drawImage(e.getSprite(),
                    location.x, location.y, size, size);
        }
    }




    private void resetCanvas(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,this.getWidth(), this.getHeight());
    }

    private Player getPlayer(List<Entity> entities){
        for(Entity e: entities){
            if(e.getClass().isInstance(entity.Player.class)){
                return (Player)e;
            }
        }
        throw new IllegalArgumentException("Player not found inside Region");
    }

    private Point calcOffset(Player p){
        Point center = new Point((int)this.getWidth()/2, (int)this.getHeight()/2);
        Point playerCoords = currentGrid.getRealCoordinates(p.getLocation());
        return new Point(playerCoords.x-center.x, playerCoords.y-center.y);
    }

    private void construct(Region initialRegion){
        currentGrid = GridManager.createDefaultManager();
        currentRegion = initialRegion;
        player = getPlayer(currentRegion.getEntities());

        gc = this.getGraphicsContext2D();
        gc.fillRect(0,0,this.getWidth(), this.getHeight());
    }
}
