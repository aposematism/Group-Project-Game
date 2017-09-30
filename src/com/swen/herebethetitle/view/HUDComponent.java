package com.swen.herebethetitle.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class representing a component to be drawn on the HUD, like an inventory slot.
 * @author J Woods
 *
 */
public class HUDComponent {
	
	public static int DEFAULT_WIDTH = 50;
	public static int DEFAULT_HEIGHT = 50;
	
	private GraphicsContext g2d;
	private Canvas canvas;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	
	/**
	 * Constructs a basic HUD component.
	 * @param c the canvas to draw to
	 * @param x the xcoordinate on the canvas to draw to
	 * @param y the ycoordinate
	 * @param w width
	 * @param h height
	 */
	public HUDComponent(Canvas c, int x, int y, int w, int h) {
		canvas = c;
		g2d = canvas.getGraphicsContext2D();
		xCoord = x;
		yCoord = y;
		width = w;
		height = h;
	}
	
	public void drawToCanvas() {
		/*If this gets called, then something in a subclass hasn't been implemented*/
		g2d.setFill(Color.BLACK);
		g2d.strokeRect(xCoord, yCoord, width, height);
	}
}
