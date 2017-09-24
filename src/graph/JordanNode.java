package graph;

import java.util.ArrayList;

import entity.Entity;

/** 
 * My implementation of foundation level node for the parser.
 * */

public class JordanNode {
	//Node Values
	private int x;
	private int y;
	//Neighbours
	private ArrayList<JordanNode> neighbours;
	//A* relevant 
	private int ectg = 0;//estimated cost to goal.
	private double csffs = 0; //Cost So Far From Start.
	private boolean visited;
	//Entities list
	private Entity regionEntity;
	private ArrayList<Entity> interactives;
	
	public JordanNode(int x1, int y1){
		x = x1;
		y = y1;
		visited = false;
		neighbours = new ArrayList<JordanNode>();
		interactives = new ArrayList<Entity>();
	}
}
