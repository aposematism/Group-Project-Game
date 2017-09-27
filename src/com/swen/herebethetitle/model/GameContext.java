package com.swen.herebethetitle.model;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.util.Direction;

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

		// every time you make two objects own eachother a kitten dies
		// somebody please, please make Entity store a GridLocation instead of
		// a Tile.
		// FIXME: fix this, add a player
		Tile tile = this.currentRegion.get(0, 0);
		this.player = new Player(this, null, Direction.Up);
		tile.add(player);
	}
	
	public static Region CreateTestRegion() {
	    Region region = new Region(10, 10);
	    return region;
	}
	
	public Region getCurrentRegion() { return this.currentRegion; }

	public Player getPlayer() { return this.player; }
}
