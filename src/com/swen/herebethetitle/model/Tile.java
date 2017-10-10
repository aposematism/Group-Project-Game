package com.swen.herebethetitle.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;
import com.swen.herebethetitle.util.GridLocation;

/** 
 * Implementation of foundation level node for the parser.
 * @author - Jordan
 * */

public class Tile implements Iterable<Entity> {
	//Parser specific checking string.
	private String k;
	//Node Values
	private GridLocation location;
	//Neighbours
	private ArrayList<Tile> neighbours;
	//Entities list
	private Floor mapFloor;
	private ArrayList<Entity> interactives;
	
	public Tile(int x, int y, String c){
	    this(new GridLocation(x,y), c);
	}

	public Tile(GridLocation location, String c){
		this.k = c;
		this.location = location;
		this.neighbours = new ArrayList<Tile>();
		this.interactives = new ArrayList<Entity>();
	}

	public void setMapFloor(Floor mapFloor) {
		this.mapFloor = mapFloor;
	}
	
	public Floor getMapFloor() {
	    return this.mapFloor;
	}

	public List<Entity> getInteractives(){return interactives;}
	
	public ArrayList<Tile> getNeighbours(){
		return neighbours;
	}

	public boolean contains(Entity entity) { return interactives.contains(entity); }

	public boolean remove(Entity entity) {
	    return interactives.remove(entity);
    }

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
	    if (mapFloor != null && !mapFloor.isPenetrable())
	        return false;

	    // If every interactive entity in the tile is penetrable, then the tile is too.
	    return interactives.stream().allMatch(Entity::isPenetrable);
	}

	public GridLocation getLocation() { return this.location; }
	
	public String getCharacter(){
		return k;
	}
	
	/**
	 * Gets a stream over all entities in the tile.
	 */
	public Stream<Entity> stream() {
		return this.interactives.stream();
	}

	@Override
	public Iterator<Entity> iterator() {
		return this.interactives.iterator();
	}
	
	@Override
	public String toString() {
	    return this.location.toString();
	}
	
	public int getInteractiveSize() {
		return interactives.size();
	}
}
