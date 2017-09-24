package model;

import java.util.ArrayList;

import entity.Entity;
import util.GridLocation;

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
	private Entity mapEntity;
	private ArrayList<Entity> interactives;
	
	public Tile(int x1, int y1, String c){
		this.location = new GridLocation(x1, y1);
		neighbours = new ArrayList<Tile>();
		interactives = new ArrayList<Entity>();
	}

	public void setMapEntity(Entity mapEnt) {
		mapEntity = mapEnt;
	}
	
	public ArrayList<Tile> getNeighbours(){
		return neighbours;
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
	    if (mapEntity != null && !mapEntity.isPenetrable())
	        return false;

	    // If every interactive entity in the tile is penetrable, then the tile is too.
	    return interactives.stream().allMatch(Entity::isPenetrable);
	}

	public GridLocation getLocation() { return this.location; }
}
