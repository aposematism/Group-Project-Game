package entity;

import javafx.scene.image.ImageView;
import util.GridLocation;

/**
 * Created by Mark on 19/09/2017.
 */
public class Door extends Obstacle {

	enum STATE { LOCKED, UNLOCKED }

	private final int KEY;
	private STATE state;

	public Door(GridLocation startingPos, ImageView sprite, int key){
		super(startingPos, sprite);
		KEY = key;
		state = STATE.LOCKED;
	}
}
