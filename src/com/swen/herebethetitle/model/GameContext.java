package com.swen.herebethetitle.model;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.parser.RegionManager;
import com.swen.herebethetitle.util.Direction;

/**
 * Top-level class for dealing with game model information.
 * @author J Woods
 */
public class GameContext {
	public Region currentRegion;
	public Player player;
	public RegionManager rm;

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
	public GameContext(Region initialRegion) {
		this.currentRegion = initialRegion;
		if (currentRegion.getPlayerTile().getInteractives().get(0) instanceof Player) {
			this.player = (Player) currentRegion.getPlayerTile().getInteractives().get(0);
		} else {
			this.player = (Player) currentRegion.getPlayerTile().getInteractives().get(1);
		}
		this.rm = new RegionManager(this.currentRegion);
	}
	
	public void moveRegion(Region reg) {
		this.currentRegion = reg;
		this.rm = new RegionManager(reg);
	}

	public static Region CreateTestRegion() {
		Region region = new Region(10, 10);

		return region;
	}

	public Region getCurrentRegion() { return this.currentRegion; }

	public RegionManager getRegionManager() {return this.rm;}
	
	public Player getPlayer() { return this.player; }
}
