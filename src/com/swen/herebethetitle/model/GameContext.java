package com.swen.herebethetitle.model;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.util.Direction;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Top-level class for dealing with game model information.
 * @author J Woods
 */
public class GameContext {
	public Region currentRegion;
	public Player player;
	
	public GameContext() {
		this.currentRegion = CreateTestRegion();

		this.player = new Player("file:res/Wizard.png", Direction.Up);
		this.currentRegion.get(0, 0).add(player);
	}

	/**
	 * Create context with preloaded player character
	 * @param player
	 */
	public GameContext(Player player) {
		this.currentRegion = CreateTestRegion();

		this.player = player;
		this.currentRegion.get(0, 0).add(player);
	}

	//TODO This needs to be replaced by something better.
	public GameContext(Region initialRegion){
	    this.currentRegion = initialRegion;
        this.player = (Player)currentRegion.getPlayerTile().getInteractives().get(0);
    }
	
	public static Region CreateTestRegion() {
	    Region region = new Region(10, 10);

	    return region;
	}
	
	public Region getCurrentRegion() { return this.currentRegion; }

	public Player getPlayer() { return this.player; }
}
