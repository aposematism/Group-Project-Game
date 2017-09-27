package com.swen.herebethetitle.model;

import java.util.ArrayList;
import java.util.List;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.util.GridLocation;

/** 
 * Implementation of foundation level node for the parser.
 * @author - Jordan
 * */

public class Tile {
	//Node Values
	private GridLocation location;
	//Neighbours
	private ArrayList<Tile> neighbours;
	//Entities list
	private Terrain mapTerrain;
	private ArrayList<Entity> interactives;
	
	public Tile(int x, int y, String c){
	    this(new GridLocation(x,y), c);
	}

	public Tile(GridLocation location, String c){
		this.location = location;
		this.neighbours = new ArrayList<Tile>();
		this.interactives = new ArrayList<Entity>();
	}

	public void setMapTerrain(Terrain mapTerrain) {
		this.mapTerrain = mapTerrain;
	}
	
	public Terrain getMapTerrain() {
	    return this.mapTerrain;
	}

	public List<Entity> getInteractives(){return interactives;}
	
	public ArrayList<Tile> getNeighbours(){
		return neighbours;
	}

	public boolean contains(Entity entity) { return interactives.contains(entity); }

	public boolean remove(Entity entity) { return interactives.remove(entity); }

	public void add(Entity entity) { interactives.add(entity); }

	/**
	 * Checks if neighbouring tiles contain the specified entity
	 * @param entity
	 * @return
	 */
	public boolean neighboursContain(Entity entity){
		for(Tile t: neighbours)
			if(t.interactives.contains(entity))
				return true;
		return false;
	}

	@Override
	public int hashCode() {
	    return location.hashCode();
  }

	/**
	 * Checks if the tile can be walked through.
	 */
	public boolean isPenetrable() {
	    // Check if there is a map entity that can't be passed through.
	    if (mapTerrain != null && !mapTerrain.isPenetrable())
	        return false;

	    // If every interactive entity in the tile is penetrable, then the tile is too.
	    return interactives.stream().allMatch(Entity::isPenetrable);
	}

	public GridLocation getLocation() { return this.location; }
}
