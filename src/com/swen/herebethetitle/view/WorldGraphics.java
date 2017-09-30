package com.swen.herebethetitle.view;

import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


/**
 * Wrapper for the game canvas and HUD that handles interactions by the user.
 * @author J Woods
 *
 */
public class WorldGraphics extends Scene implements GUIcomponent{
	
	Region region;
	Canvas canvas;
	HUD hud;
	GameContext game;
	Group root;
	
	/**
	 * This will have to be changed to take a size.
	 * 
	 */
	public WorldGraphics(GameContext g, Group r) {
		super(r);
		game = g;
		//FIXME - after gamecanvas is implemented enough, switch to using a gamecanvas instead
		//canvas = new GameCanvas(region, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		canvas = new Canvas(GUI.DEFAULT_WIDTH,GUI.DEFAULT_HEIGHT);
		hud = new HUD(canvas, game);
		root = r;
		r.getChildren().add(canvas);
		r.requestFocus();
		
		/*event handling*/
		//TODO - delegate these to actual handling methods for easier modification
		r.addEventHandler(MouseEvent.MOUSE_PRESSED, e->{
			update();
			/*derive coordinates to put the text in centre*/
			int y = GUI.DEFAULT_HEIGHT/2-12/2+25;
			int x = GUI.DEFAULT_WIDTH/2-12*"you pressed the mouse at 0.0,1.1".length()/2;
			hud.drawTextToCanvas("You pressed the mouse at " + e.getSceneX() + "," + e.getSceneY(),x,y);
		});		
		r.addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			update();
			/*derive coordinates to put the text in centre*/
			int y = GUI.DEFAULT_HEIGHT/2-12/2+25;
			int x = GUI.DEFAULT_WIDTH/2-12*"Mouse entered".length()/2;
			hud.drawTextToCanvas("Mouse entered",x,y);
		});		
		r.addEventHandler(MouseEvent.MOUSE_EXITED, e->{
			update();
			/*derive coordinates to put the text in centre*/
			int y = GUI.DEFAULT_HEIGHT/2-12/2+25;
			int x = GUI.DEFAULT_WIDTH/2-12*"Mouse exited".length()/2;
			hud.drawTextToCanvas("Mouse exited",x,y);
		});
	}
	
	@Override
	public void update() {
		/*first check the region has not changed, and update it in the canvas if it has*/
		if(game.getCurrentRegion()!=region) {
			region = game.getCurrentRegion();
			//FIXME once using GameCanvas, uncomment this
			//canvas.switchRegions(region);
		}
		/*now draw the world*/
		//canvas.drawAll();
		//FIXME as above, change this once the canvas is ready
		canvas.getGraphicsContext2D().setFill(Color.BLUE);
		canvas.getGraphicsContext2D().fillRect(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		/*now draw the HUD*/
		hud.drawToCanvas();
	}	
}
