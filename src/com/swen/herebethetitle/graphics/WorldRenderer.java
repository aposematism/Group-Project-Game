package com.swen.herebethetitle.graphics;


import java.awt.Point;
import java.util.*;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.pathfinding.Graph;
import com.swen.herebethetitle.util.GridLocation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


/**
 * The WorldRenderer is responsible for rendering the state of the Region of the GameContext (ie.
 * renders all the terrain, interactive entities, the player etc) onto the GameCanvas. It is parsed
 * a List of sprites and the canvas it renders to must be provided.
 * @author weirjosh
 *
 */
public class WorldRenderer {
    private GridManager currentGrid;

    private int width;
    private int height;
    private GridLocation playerPos;

    /**
     * Create a new Game Canvas, initialized with the initialRegion it gets passed.
     * @author weirjosh
     */
    public WorldRenderer(){
        currentGrid = GridManager.createDefaultManager();
    }

    /**
     * @return The Grid manager of this Canvas
     * @author weirjosh
     */
    public GridManager getGrid(){
        return currentGrid;
    }

    /**@param newGrid Assign a new Grid manager to this canvas
     * @author weirjosh
     */
    public void setGrid(GridManager newGrid){
        currentGrid = newGrid;
    }


    /**
     * Draws each Sprite provided onto the provided Canvas
     * @param sprites Map from background Sprites, to the list of sprites that are
     *                rendered on top of it
     * @param player The location of the player for calculating te offset of each
     *               sprite to be rendered.
     * @param c The canvas to render to.
     * @author weirjosh
     */
    public void drawAll(Map<Sprite, List<Sprite>> sprites, GridLocation player, Canvas c){
        width = (int)c.getWidth();
        height = (int)c.getHeight();
        playerPos = player;
        GraphicsContext gc = c.getGraphicsContext2D();
        resetCanvas(gc);
        Point offset = calcOffset(width, height, player);

        for(Sprite s: sprites.keySet()){
            s.draw(gc, currentGrid, offset);
            for(Sprite si: sprites.get(s)){
                si.draw(gc, currentGrid, offset);
            }
        }
    }


    /**
     * Gets the gridLocation of the current point
     * @param pos The x and y coordinate of the canvas point
     * @author weirjosh
     */
    public GridLocation getLocation(Point pos){
        return currentGrid.getGridLocation(pos, calcOffset(width, height, playerPos));
    }

    private void resetCanvas(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,gc.getCanvas().getWidth(), gc.getCanvas().getWidth());
    }

    private Point calcOffset(int width, int height, GridLocation player){
        Point center = new Point(width/2, height/2);
        Point playerCoords = currentGrid.getRealCoordinates(player);
        return new Point(center.x-playerCoords.x, center.y-playerCoords.y);
    }
}
