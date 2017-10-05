package com.swen.herebethetitle.view;

import java.util.Optional;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.logic.GameListener;
import com.swen.herebethetitle.model.GameContext;

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
public class WorldGraphics extends Scene implements GameListener{

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
			canvas = new GameCanvas(game, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);

			//FIXME HUD is implemented in the GameCanvas class.

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

	}
	
	/**
	 * Updates the GameCanvas if necessary, then draws the world and HUD.
	 */
	public void update() {
		/*now draw the world*/
		if(isTesting) {
			/*testing rendering mode*/
			canvas.getGraphicsContext2D().setFill(Color.BLUE);
			canvas.getGraphicsContext2D().fillRect(0, 0, GUI.DEFAULT_WIDTH, GUI.DEFAULT_HEIGHT);
		}else {
			/*standard rendering mode*/
			((GameCanvas)canvas).update();
		}
		/*now draw the HUD*/
		//hud.drawToCanvas();
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

	@Override
	public void onPlayerMoved(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerAttacked(Player player, NPC attacker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerKilled(Player player, Optional<NPC> aggressor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerPickup(Player player, Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDrop(Player player, Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCAttacked(NPC victim) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCKilled(NPC npc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCDialogBegin(NPC npc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCDialogMessage(NPC npc, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCDialogEnd(NPC npc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoorUnlocked(Static door) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoorUnlockFailed(Static door, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoorOpened(Static door) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoorClosed(Static door) {
		// TODO Auto-generated method stub
		
	}
}
