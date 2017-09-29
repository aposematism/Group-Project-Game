package com.swen.herebethetitle.graphics;


import java.awt.Point;
import java.util.*;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


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

//    private Map<Region, List<Entity>> backLayerSprites = new HashMap<>();
//    private Map<Entity, List<Entity>> frontLayerSprites = new HashMap<>();

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
     * @param newRegion The new region to draw all the entities of. This region MUST contain
     *                  a Player Entity.
     */
    public void switchRegions(Region newRegion){
        currentRegion = newRegion;
        resetCanvas();


    }

    /**
     * @return The Grid manager of this Canvas
     */
    public GridManager getGrid(){
        return currentGrid;
    }

    /**@param newGrid Assign a new Grid manager to this canvas
     */
    public void setGrid(GridManager newGrid){
        currentGrid = newGrid;
    }

    /**
     * Draw every entity held within the current region of this Canvas.
     */
    public void drawAll(){
        int size = currentGrid.getCellSize();

        Point offset = calcOffset(player);
        resetCanvas();

        //For each tile in the region...
        Iterator<Tile> tiles = currentRegion.iterator();
        while(tiles.hasNext()){
            Tile t = tiles.next();
            Point pos = currentGrid.getRealCoordinates(t.getLocation(), offset);

            //Draw the background terrain sprite first
            gc.drawImage(t.getMapTerrain().getSprite(), size, size);

            //Draw each interactive entity that inhabits the current tile
            for(Entity e: t.getInteractives()){
                gc.drawImage(e.getSprite(), size, size);
            }

        }
    }

    private void resetCanvas(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,this.getWidth(), this.getHeight());
    }

    private Player getPlayer(){
        Iterator<Tile> tiles = currentRegion.iterator();
    	while(tiles.hasNext()){
            Tile t = tiles.next();
            for(Entity e: t.getInteractives()) {
                if (e.getClass().isInstance(Player.class)) {
                    return (Player) e;
                }
            }
        }
    	throw new IllegalArgumentException("Player not found inside Region");
    }

    private Point calcOffset(Player p){
        Point center = new Point((int)this.getWidth()/2, (int)this.getHeight()/2);
        GridLocation gridLocation = currentRegion.getLocation(p);
        Point playerCoords = currentGrid.getRealCoordinates(gridLocation);
        return new Point(playerCoords.x-center.x, playerCoords.y-center.y);
    }

    private void construct(Region initialRegion){
        currentGrid = GridManager.createDefaultManager();
        switchRegions(initialRegion);
        gc = this.getGraphicsContext2D();

        resetCanvas();
        player = getPlayer();
    }
}
