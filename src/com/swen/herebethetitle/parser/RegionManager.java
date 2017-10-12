package com.swen.herebethetitle.parser;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.model.Region;

import java.io.File;
import java.io.IOException;

/** 
 * This class is to handle the current Region and its neighbours.
 * @author - Jordan Milburn
 * */
public class RegionManager {
	private Region currentRegion;
	private Region[] neighbouringRegions;//Clockwise from north, east, south and west.
	private Player player;
	
	/**
	 * This is the RegionManager that can take directly from the file. Proceeds in the same way as the RegionManager Constructor with input Region.
	 * @throws IOException  
	 * 	 * */
	public RegionManager(File terrainFile) throws IOException {
		Region region = new MapParser(terrainFile).getRegion();
		this.currentRegion = region;
		String[] neighbourStrings = region.getNeighbouringRegions();
		neighbouringRegions = new Region[neighbourStrings.length];
		for(int i = 0; i < neighbourStrings.length; i++) {
			neighbouringRegions[i] = createNeighbours(neighbourStrings[i]);
		}
	}
	
	/** 
	 * Generates all the regions necessary around the current region.
	 * */
	public RegionManager(Region region) {
		this.currentRegion = region;
		String[] neighbourStrings = region.getNeighbouringRegions();
		neighbouringRegions = new Region[neighbourStrings.length];
		for(int i = 0; i < neighbourStrings.length; i++) {
			neighbouringRegions[i] = createNeighbours(neighbourStrings[i]);
		}
	}
	
	/** 
	 * Enables feeding of regions by tests.
	 * */
	public RegionManager(Region c, Region[] ne) {
		this.currentRegion = c;
		this.neighbouringRegions = ne;
	}
	
	/** 
	 * This method turns the strings provided as neighbours into regions for use by the game.
	 * */
	private Region createNeighbours(String neigh) {
		try {
			return new MapParser(new File(neigh)).getRegion();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/** 
	 * These return the neighbouring region for this current Region.
	 * */
	public Region getNorth() {
		return neighbouringRegions[0];
	}
	
	public Region getEast() {
		return neighbouringRegions[1];
	}
	
	public Region getSouth() {
		return neighbouringRegions[2];
	}
	
	public Region getWest() {
		return neighbouringRegions[3];
	}
	
	public Region getCurrent() {
		return currentRegion;
	}
	
	/** 
	 * My attempt at Java 8 lambda magic.
	 * */
	public Player getPlayer() {
		return (Player) currentRegion.getPlayerTile().getInteractives().stream().filter(e -> e instanceof Player);
	}
}
