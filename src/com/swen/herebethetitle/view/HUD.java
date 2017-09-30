package com.swen.herebethetitle.view;

import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.GameContext;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This is the class for the HUD.
 * It will draw relevant player statistics (like health) to the given game canvas.
 * @author J Woods
 *
 */
public class HUD{
	
	private Canvas canvas;
	private GameContext game;
	private GraphicsContext g2d;
	
	public HUD(Canvas c, GameContext g) {
		canvas = c;
		game = g;
		g2d = canvas.getGraphicsContext2D();
	}

	/**
	 * Draws the HUD to the given canvas.
	 */
	public void drawToCanvas() {
		/*draw the HUD to the game canvas TODO*/
		//FIXME below is debugging code
		g2d.setFill(Color.BLUE.darker());
		g2d.fillRect(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		g2d.setFill(Color.GREEN.brighter());
		/*derive coordinates to put the text in centre*/
		/*derive coordinates to put the text in centre*/
		int y = GUI.DEFAULT_HEIGHT/2-12/2;
		int x = GUI.DEFAULT_WIDTH/2-20*"HUD testing".length()/2;
		drawTextToCanvas("HUD testing",x,y);
	}
	
	/**
	 * Draws a given text to the canvas at centre,
	 * @param text the text to draw
	 */
	public void drawTextToCanvas(String text, int x, int y) {
		canvas.getGraphicsContext2D().setFont(new Font(20));
		canvas.getGraphicsContext2D().fillText(text, x, y);
	}
}
