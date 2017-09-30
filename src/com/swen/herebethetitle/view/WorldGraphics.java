package com.swen.herebethetitle.view;

import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


/**
 * Wrapper for the game canvas and HUD that handles interactions by the user.
 * @author J Woods
 *
 */
public class WorldGraphics extends Scene implements GUIcomponent{
	
	private Region region;
	private Canvas canvas;
	private HUD hud;
	private GameContext game;
	private Group root;
	private boolean isTesting;
	
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
		r.addEventHandler(MouseEvent.MOUSE_PRESSED, e->{
			handleMousePress(e);
		});		
		//TODO - do we care about mouse entering and exiting?
		r.addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			update();
			/*derive coordinates to put the text in centre*/
			int y = GUI.DEFAULT_HEIGHT/2-20/2+25;
			int x = GUI.DEFAULT_WIDTH/2-20*"Mouse entered".length()/2;
			hud.drawTextToCanvas("Mouse entered",x,y);
		});		
		r.addEventHandler(MouseEvent.MOUSE_EXITED, e->{
			update();
			/*derive coordinates to put the text in centre*/
			int y = GUI.DEFAULT_HEIGHT/2-20/2+25;
			int x = GUI.DEFAULT_WIDTH/2-20*"Mouse exited".length()/2;
			hud.drawTextToCanvas("Mouse exited",x,y);
		});
		r.addEventHandler(KeyEvent.KEY_PRESSED, e->{
			handleKeyPress(e);
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
		if(isTesting) {
			canvas.getGraphicsContext2D().setFill(Color.BLUE);
			canvas.getGraphicsContext2D().fillRect(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		}
		/*now draw the HUD*/
		hud.drawToCanvas();
	}
	
	/**
	 * Handles a mouse press event.
	 * @param e the MouseEvent.
	 */
	private void handleMousePress(MouseEvent e) {

		update();
		/*derive coordinates to put the text in centre*/
		int y = GUI.DEFAULT_HEIGHT/2-20/2+25;
		int x = GUI.DEFAULT_WIDTH/2-20*"you pressed the mouse at 0.0,1.1".length()/2;
		hud.drawTextToCanvas("You pressed the mouse at " + e.getSceneX() + "," + e.getSceneY(),x,y);
	}	
	/**
	 * Handles a mouse press event.
	 * @param e the MouseEvent.
	 */
	private void handleKeyPress(KeyEvent e) {
		update();
		/*derive coordinates to put the text in centre*/
		int y = GUI.DEFAULT_HEIGHT/2-20/2+25;
		int x = GUI.DEFAULT_WIDTH/2-20*"you pressed the with text:   ".length()/2;
		hud.drawTextToCanvas("You pressed the key with text: " + e.getText(),x,y);
		hud.drawTextToCanvas("You pressed the key with code: " + e.getCode(),x,y+20);
	}
}
