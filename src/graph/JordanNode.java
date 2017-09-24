package graph;

import java.util.ArrayList;

public abstract class JordanNode {
	private int x;
	private int y;
	private ArrayList<JordanNode> neighbours;
	private int ectg = 0;//estimated cost to goal.
	public double csffs = 0; //Cost So Far From Start.
	
	public void JordanNode(int x, int y){
		x = x;
		y = y;
		neighbours = new ArrayList<JordanNode>();
	}
}
