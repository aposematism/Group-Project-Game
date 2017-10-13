package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.graphics.GridManager;
import com.swen.herebethetitle.util.GridLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class GridManagerTests {

    GridManager m;
    GridLocation gl1;
    GridLocation gl2;
    GridLocation gl3;
    GridLocation gl4;
    GridLocation gl5;
    GridLocation gl6;
    Point offset;
    int cellSize;
    int vGap;
    int hGap;

    @Before
    public void init() {
        gl1 = new GridLocation(0, 1);
        gl2 = new GridLocation(0, 2);
        gl3 = new GridLocation(0, 4);
        gl4 = new GridLocation(1, 1);
        gl5 = new GridLocation(2, 2);
        gl6 = new GridLocation(4, 4);

        offset = new Point(20, 20);
        cellSize = 30;

	    m = new GridManager(0, 0, cellSize);

    }

    @Test
    public void test01_initialize(){
	    GridManager m = new GridManager(0, 0, cellSize);
	    Assert.assertEquals(m.getCellSize(), 30);
    }
    @Test
    public void test02_getCoordinates(){
        GridManager m = this.m;
        Assert.assertEquals(m.getRealCoordinates(gl1), new Point(0, 30));
        Assert.assertEquals(m.getRealCoordinates(gl2), new Point(0, 60));
        Assert.assertEquals(m.getRealCoordinates(gl3), new Point(0, 120));
        Assert.assertEquals(m.getRealCoordinates(gl4), new Point(30, 30));
        Assert.assertEquals(m.getRealCoordinates(gl5), new Point(60, 60));
        Assert.assertEquals(m.getRealCoordinates(gl6), new Point(120, 120));

	    m = new GridManager(45, 30, cellSize);
	    Assert.assertEquals(m.getRealCoordinates(gl1), new Point(45, 60));
	    Assert.assertEquals(m.getRealCoordinates(gl2), new Point(45, 90));
    }
    @Test
    public void test03_getCoordinatesWithOffset(){

        Assert.assertEquals(m.getRealCoordinates(gl1, offset), new Point(20-cellSize/2, 50-cellSize/2));
        Assert.assertEquals(m.getRealCoordinates(gl2, offset), new Point(20-cellSize/2, 80-cellSize/2));
        Assert.assertEquals(m.getRealCoordinates(gl3, offset), new Point(20-cellSize/2, 140-cellSize/2));
        Assert.assertEquals(m.getRealCoordinates(gl4, offset), new Point(50-cellSize/2, 50-cellSize/2));
        Assert.assertEquals(m.getRealCoordinates(gl5, offset), new Point(80-cellSize/2, 80-cellSize/2));
        Assert.assertEquals(m.getRealCoordinates(gl6, offset), new Point(140-cellSize/2, 140-cellSize/2));
    }

    @Test
    public void test04_testGaps(){
        Assert.assertEquals(m.getRealCoordinates(gl2).y
		        - m.getRealCoordinates(gl1).y - 2, m.getCellSize());

	    GridManager.toggleBorder();
	    Assert.assertEquals(m.getRealCoordinates(gl5).x
			    - m.getRealCoordinates(gl4).x, m.getCellSize());
    }

    @Test
    public void test05_getGridLocation(){

        GridLocation l = new GridLocation(4,5);
        offset = new Point(0,0);
        Assert.assertEquals(l, m.getGridLocation(m.getRealCoordinates(l), offset));

        offset = new Point(30,56);

        //Since the position has been aligned to the center of the cell, change offset.
        Point adjustedOffset = new Point(offset.x+(cellSize/2), offset.y+(cellSize/2));
        Assert.assertEquals(l, m.getGridLocation(m.getRealCoordinates(l, adjustedOffset), offset));
    }

    @Test
    public void test06_getCoordsFromDifferentOrigin(){
	    GridManager m = new GridManager(45, 30, cellSize);
	    Assert.assertEquals(m.getRealCoordinates(gl1), new Point(45, 60));
	    Assert.assertEquals(m.getRealCoordinates(gl2), new Point(45, 90));
    }


}
