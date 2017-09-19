package entity;

import graph.Node;

/**
 * Created by Mark on 19/09/2017.
 */
public class Obstacle implements Entity {

	private Actions actions;

	private Node position;

	public Obstacle(Node startingPos){
		position = startingPos;
	}

	public void setActions(Actions actions){
		this.actions=actions;
	}

	public void interact(Player player) {
		actions.move(player,this);
	}

	interface Actions {
		void move(Player player, Obstacle obstacle);
	}
}
