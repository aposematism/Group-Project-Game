package view;

import javafx.scene.*;
import model.GameContext;


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
	public WorldGraphics(GameContext g, Parent p) {
		super(p);
		canvas = new graphics.GameCanvas(region);
	}
	
	@Override
	public void update() {
		throw new exceptions.NotImplementedYetException();
	}

	//TODO Need a method to observe the game model and update GameCanvas if the region has changed
	//TODO by calling canvas.switchRegion(new region)

}
