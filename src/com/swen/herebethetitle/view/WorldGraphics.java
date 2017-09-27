package com.swen.herebethetitle.view;

import com.swen.herebethetitle.exceptions.NotImplementedYetException;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;

import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * Wrapper for the game canvas that handles interactions by the user with it.
 * @author J Woods
 *
 */
public class WorldGraphics extends Scene implements GUIcomponent{
	
	Region region;
	GameCanvas canvas;
	
	/**
	 * This will have to be changed to take a size.
	 * 
	 */
	public WorldGraphics(GameContext g, Parent p) {
		super(p);
		canvas = new GameCanvas(region);
	}
	
	@Override
	public void update() {
		throw new NotImplementedYetException();
	}

	//TODO Need a method to observe the game model and update GameCanvas if the region has changed
	//TODO by calling canvas.switchRegion(new region)

}
