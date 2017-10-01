package com.swen.herebethetitle.view;

import com.swen.herebethetitle.graphics.GameCanvas;
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
	 * Default constructor for a WorldGraphics outside of testing mode.
	 * @param g GameContext for this WorldGraphics
	 * @param r root group to attach to
	 * @return a WorldGraphics in non-testing mode
	 */
	public WorldGraphics(GameContext g, Group r) {
		this(g,r,false);
	}
	
	/**
	 * Constructs a WorldGraphics, with defined testing mode.
	 * @param g GameContext for this WorldGraphics
	 * @param r root group to attach to
	 * @return a WorldGraphics in non-testing mode
	 */
	public WorldGraphics(GameContext g, Group r, boolean t) {
		super(r);
		isTesting = t;
		game = g;
		if(isTesting) {
			canvas = new Canvas(GUI.DEFAULT_WIDTH,GUI.DEFAULT_HEIGHT);
		}else {
			canvas = new GameCanvas(region, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		}
		hud = new HUD(canvas, game);
		root = r;
		r.getChildren().add(canvas);
		r.requestFocus();
		
		/*event handling*/
		r.addEventHandler(MouseEvent.MOUSE_PRESSED, e->{
			handleMousePress(e);
		});
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
		if(game.getCurrentRegion()!=region && canvas instanceof GameCanvas) {
			region = game.getCurrentRegion();
			((GameCanvas)canvas).switchRegions(region);
		}
		/*now draw the world*/
		if(isTesting) {
			/*testing rendering mode*/
			canvas.getGraphicsContext2D().setFill(Color.BLUE);
			canvas.getGraphicsContext2D().fillRect(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		}else {
			/*standard rendering mode*/
			((GameCanvas)canvas).drawAll();
		}
		/*now draw the HUD*/
		hud.drawToCanvas();
	}
	
	/**
	 * Handles a mouse press event.
	 * @param e the MouseEvent.
	 */
	private void handleMousePress(MouseEvent e) {
		if(isTesting) {
			/*testing mode*/
			update();
			int y = GUI.DEFAULT_HEIGHT/2-20/2+25;
			int x = GUI.DEFAULT_WIDTH/2-20*"you pressed the mouse at 0.0,1.1".length()/2;
			hud.drawTextToCanvas("You pressed the mouse at " + e.getSceneX() + "," + e.getSceneY(),x,y);
		}else {
			/*standard mode*/
			//TODO
		}
	}
	/**
	 * Handles a mouse press event.
	 * @param e the MouseEvent.
	 */
	private void handleKeyPress(KeyEvent e) {
		if(isTesting) {
			/*testing mode*/
			update();
			/*derive coordinates to put the text in centre*/
			int y = GUI.DEFAULT_HEIGHT/2-20/2+25;
			int x = GUI.DEFAULT_WIDTH/2-20*"you pressed the with text:   ".length()/2;
			hud.drawTextToCanvas("You pressed the key with text: " + e.getText(),x,y);
			hud.drawTextToCanvas("You pressed the key with code: " + e.getCode(),x,y+20);
		}else {
			/*standard mode*/
			//TODO
		}
	}
}
