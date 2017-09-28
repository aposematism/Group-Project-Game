package com.swen.herebethetitle.view;

import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.GameContext;

/**
 * This is the class for the HUD.
 * It will draw relevant player statistics (like health) to the given game canvas.
 * @author J Woods
 *
 */
public class HUD{
	
	private GameCanvas canvas;
	private GameContext game;
	
	public HUD(GameCanvas c, GameContext g) {
		canvas = c;
		game = g;
	}

	/**
	 * Draws the HUD to the given canvas.
	 */
	public void drawToCanvas() {
		/*draw the HUD to the game canvas TODO*/
		canvas.getGraphicsContext2D().fillText("GUI test drawing", 100, 50);
	}
}
