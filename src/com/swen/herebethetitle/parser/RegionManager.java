package com.swen.herebethetitle.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.swen.herebethetitle.model.Region;

/** 
 * This class is to handle the current Region and its neighbours.
 * @author - Jordan Milburn
 * */
public class RegionManager {
	Region currentRegion;
	Region[] neighbouringRegions;//Clockwise from north, east, south and west.
	
	/** 
	 * Generates all the regions necessary around the current region.
	 * */
	public RegionManager(Region c) {
		this.currentRegion = c;
		String[] neighbourStrings = c.getNeighbouringRegions();
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
			TerrainParser tp = new TerrainParser(new File(neigh));
			Region r = new Region(tp.getRA());
			return r;
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
}
