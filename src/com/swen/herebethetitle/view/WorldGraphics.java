package com.swen.herebethetitle.view;

import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;

import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * Wrapper for the game canvas and HUD that handles interactions by the user.
 * @author J Woods
 *
 */
public class WorldGraphics extends Scene implements GUIcomponent{
	
	Region region;
	GameCanvas canvas;
	HUD hud;
	GameContext game;
	
	/**
	 * This will have to be changed to take a size.
	 * 
	 */
	public WorldGraphics(GameContext g, Parent p) {
		super(p);
		game = g;
		canvas = new GameCanvas(region, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		hud = new HUD(canvas, game);
	}
	
	@Override
	public void update() {
		/*first check the region has not changed, and update it in the canvas if it has*/
		if(game.getCurrentRegion()!=region) {
			region = game.getCurrentRegion();
			canvas.switchRegions(region);
		}
		/*now draw the world*/
		canvas.drawAll();
		/*now draw the HUD*/
		hud.drawToCanvas();
	}
}
