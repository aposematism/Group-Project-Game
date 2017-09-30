package com.swen.herebethetitle.model;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.util.Direction;
import javafx.scene.image.Image;

/**
 * Top-level class for dealing with game model information.
 * @author J Woods
 *
 */
public class GameContext {
	public Region currentRegion;
	public Player player;
	
	public GameContext() {
		this.currentRegion = CreateTestRegion();

		this.player = new Player(null, Direction.Up);
		this.currentRegion.get(0, 0).add(player);
	}
	
	public static Region CreateTestRegion() {
	    Region region = new Region(10, 10);
	    return region;
	}
	
	public Region getCurrentRegion() { return this.currentRegion; }

	public Player getPlayer() { return this.player; }
}
