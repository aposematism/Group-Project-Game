package com.swen.herebethetitle.parser;

import com.swen.herebethetitle.model.Region;

import java.io.File;
import java.io.IOException;

/** 
 * This class is to handle the current Region and its neighbours.
 * @author - Jordan Milburn
 * */
public class RegionManager {
	Region currentRegion;
	Region[] neighbouringRegions;//Clockwise from north, east, south and west.
	
	public RegionManager(Region c) {
		this.currentRegion = c;
		String[] neighbourStrings = c.getNeighbouringRegions();
		neighbouringRegions = new Region[neighbourStrings.length];
		for(int i = 0; i < neighbourStrings.length; i++) {
			neighbouringRegions[i] = createNeighbours(neighbourStrings[i]);
		}
	}
	
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
}
