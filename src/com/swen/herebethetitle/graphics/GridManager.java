package com.swen.herebethetitle.graphics;

import java.awt.Canvas;
import java.awt.Point;

import com.swen.herebethetitle.util.GridLocation;

/**A grid object is a representation of an area of evenly spaced cells over a given area.
 It will have static methods for turning its own grid coordinates into real world pixel coordinates
 among other things.
 * Created by weirjosh on 19/09/17.
 */
public class GridManager extends Canvas{

    //The canvas location (top right corner) of the grid. TODO may not need this either
    //private Point location;

    //TODO May not need these
//    private int rows;
//    private int cols;

    private int cellSize;

    //The horizontal and vertical gaps of this grid
    private int vGap;
    private int hGap;

    private static final int defaultCellSize = 64;

    /**
     *
     * @return
     */
    public static GridManager createDefaultManager(){
        return new GridManager(defaultCellSize, 0,0);
    }

    /**
     * Create a new grid manager
     * @param cellSize size (width & height) of each cell in the grid
     * @param vGap vertical gap between each cell in the grid.
     * @param hGap horizontal gap between each cell in the grid.
     */
    public GridManager(int cellSize, int vGap, int hGap){
        this.cellSize = cellSize;
        this.vGap = vGap;
        this.hGap = hGap;
    }

    /**
     * Takes a GridLocation and returns real world coordinates
     * @param location The grid location
     * @return A pixel coordinate
     */
    public Point getRealCoordinates(GridLocation location){
        int x = location.x * cellSize;
        int y = location.y * cellSize;

        return new Point(x,y);
    }

    /**
     * Takes a grid location, and calculates a real world coordinate offset by another grid location
     * @param location The grid location.
     * @param offset The offset from the given location.
     * @return A pixel coordinate
     */
    public Point getRealCoordinates(GridLocation location, GridLocation offset){
        int x = location.x + offset.x * cellSize;
        int y = location.y + offset.y * cellSize;

        return new Point(x,y);
    }

    /**
     * Takes a grid location, and calculates a real world coordinate offset by an arbitrary amount of pixels
     * @param location The grid location.
     * @param offset The offset (pixel vector) from the given location.
     * @return A pixel coordinate
     */
    public Point getRealCoordinates(GridLocation location, Point offset){
        int x = (location.x * cellSize) + offset.x;
        int y = (location.y * cellSize) + offset.y;

        return new Point(x,y);
    }


    /**
     * @return Get the vertical gap of this GridManager.
     */
    public int getvGap(){ return vGap; }

    /**
     * @return Get the horizontal gap of this GridManager.
     */
    public int gethGap(){return hGap;}

    /**
     * set the vertical gap
     */
    public void setvGap(int newVGap){vGap = newVGap;}

    /**
     * set the horizontal gap
     */
    public void sethGap(int newHGap){hGap = newHGap;}

    /**
     * @return get the cell size of this GridManager
     */
    public int getCellSize(){return cellSize;}

    /**
     * @param size The new cell size of this grid manager.
     */
    public void setCellSize(int size){cellSize = size;}




}
