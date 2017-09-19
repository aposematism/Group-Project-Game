package entity;

import util.GridLocation;

/**
 * Created by Mark on 19/09/2017.
 */
public class Door extends Obstacle {

	enum STATE { LOCKED, UNLOCKED }

	private final int KEY;
	private STATE state;

	public Door(GridLocation startingPos, int key){
		super(startingPos);
		KEY = key;
		state = STATE.LOCKED;
	}
}
