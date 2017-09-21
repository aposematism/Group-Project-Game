package entity;

import util.GridLocation;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 */
public class Obstacle implements Entity {

	private Policy policy;

	private GridLocation location;

	public Obstacle(GridLocation startingPos){
		location = startingPos;
	}

	public void setPolicy(Policy policy){
		this.policy=policy;
	}

	public GridLocation getLocation() {
		return location;
	}

	public void interact(Player player) {
		policy.interact(player,this);
	}

	interface Policy {
		void interact(Player player, Obstacle obstacle);
	}
}
