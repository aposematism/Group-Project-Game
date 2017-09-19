package view;

import java.awt.Dimension;
import java.awt.Point;

import javafx.scene.*;


/**
 * Wrapper for the game canvas that handles interactions by the user with it.
 * @author J Woods
 *
 */
public class WorldGraphics extends Scene implements GUIcomponent{
	
	graphics.Grid worldGrid;
	graphics.GameCanvas canvas;
	
	/**
	 * This will have to be changed to take a size.
	 * 
	 */
	public WorldGraphics() {
		super(null);	//TODO change this in a sensible way
		worldGrid = new graphics.Grid(new Point(0,0), new Dimension(480,640), 20, 20);
		canvas = new graphics.GameCanvas(worldGrid, 480, 640);
		throw new Exceptions.NotImplementedYetException();
	}
	
	@Override
	public void update() {
		throw new Exceptions.NotImplementedYetException();
	}

}
