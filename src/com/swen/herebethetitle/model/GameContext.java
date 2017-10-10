package com.swen.herebethetitle.model;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.parser.RegionManager;
import com.swen.herebethetitle.util.Direction;

/**
 * Top-level class for dealing with game model information.
 * @author J Woods
 */
public class GameContext {
	public Region currentRegion;
	public RegionManager manager;
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
	
	/** 
	 * So this hands the GameContext a current region inside a region manager.
	 * The region manager contains the current region and an array of its neighbouring regions.
	 * This enables you to call for the region next to it from within the gameContext.
	 * */
	public GameContext(RegionManager rm) {
		this.currentRegion = rm.getCurrent();
	}
	
	public static Region CreateTestRegion() {
	    Region region = new Region(10, 10);
		for(int i=0;i<5;i++){
			Entity e = new Static("", "file:res/cobble master.png");
			region.get(5,2+i).add(e);
		}
	    return region;
	}
	
	public Region getCurrentRegion() { return this.currentRegion; }

	public Player getPlayer() { return this.player; }
	
	public Region getNorthRegion() {
		return manager.getNorth();
	}
	
	public Region getEastRegion() {
		return manager.getEast();
	}
	
	public Region getSouthRegion() {
		return manager.getSouth();
	}
	
	public Region getWesthRegion() {
		return manager.getWest();
	}
}
