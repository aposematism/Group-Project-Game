package entity;

import graph.Node;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 */
public class Obstacle implements Entity {

	private Policy policy;

	private Node position;

	public Obstacle(Node startingPos){
		position = startingPos;
	}

	public void setActions(Policy actions){
		this.policy=actions;
	}

	public Node getPosition() {
		return position;
	}

	public void interact(Player player) {
		policy.interact(player,this);
	}

	interface Policy {
		void interact(Player player, Obstacle obstacle);
	}
}
