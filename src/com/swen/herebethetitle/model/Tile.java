package com.swen.herebethetitle.model;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.util.GridLocation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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

	public Floor getMapFloor() {
	    return this.mapFloor;
	}

	public void setMapFloor(Floor mapFloor) {
		this.mapFloor = mapFloor;
	}

	public List<Entity> getInteractives(){return interactives;}
	
	public ArrayList<Tile> getNeighbours(){
		return neighbours;
	}

	public boolean contains(Entity entity) { return interactives.contains(entity); }

	public boolean remove(Entity entity) {
	    return interactives.remove(entity);
    }

	public void add(Entity... entities) {
		for (Entity entity : entities) {
			if (entity instanceof Floor)
				setMapFloor((Floor) entity);
			else
				interactives.add(entity);
		}
	}

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
	
	/**
	 * Gets the top level entity on the tile.
	 * 
	 * Assumes that the entities are rendered from i=0 to i=size.
	 * i.e. The last tile in the array is the top.
	 */
	public Entity getTopEntity() {
		Entity entity = mapFloor;
		for (Entity e : interactives)
			if (!(e instanceof Player))
				entity = e;
		return entity;
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
