package model;

import java.util.ArrayList;

import entity.Entity;

/** 
 * Implementation of foundation level node for the parser.
 * @author - Jordan
 * */

public class Tile {
	//Node Values
	private int x;
	private int y;
	//Neighbours
	private ArrayList<Tile> neighbours;
	//Entities list
	private Entity mapEntity;
	private ArrayList<Entity> interactives;
	
	public Tile(int x1, int y1){
		x = x1;
		y = y1;
		neighbours = new ArrayList<Tile>();
		interactives = new ArrayList<Entity>();
	}

	public void setMapEntity(Entity mapEnt) {
		mapEntity = mapEnt;
	}
	
	public ArrayList<Tile> getNeighbours(){
		return neighbours;
	}
}
