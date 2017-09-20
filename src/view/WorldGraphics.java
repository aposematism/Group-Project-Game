package view;

import java.awt.Dimension;
import java.awt.Point;

import graphics.GridManager;
import javafx.scene.*;


/**
 * Wrapper for the game canvas that handles interactions by the user with it.
 * @author J Woods
 *
 */
public class WorldGraphics extends Scene implements GUIcomponent{
	
	model.Region region;
	graphics.GameCanvas canvas;
	
	/**
	 * This will have to be changed to take a size.
	 * 
	 */
	public WorldGraphics() {
		super(null);	//TODO change this in a sensible way
		canvas = new graphics.GameCanvas(region, 480, 640);
		throw new Exceptions.NotImplementedYetException();
	}
	
	@Override
	public void update() {
		throw new Exceptions.NotImplementedYetException();
	}

	//TODO Need a method to observe the game model and update GameCanvas if the region has changed
	//TODO by calling canvas.switchRegion(new region)

}
