package entity;

import javafx.scene.image.ImageView;
import util.GridLocation;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 */
public class Obstacle implements Entity {

	private Policy policy;

	private GridLocation location;

	private ImageView sprite;

	public Obstacle(GridLocation startingPos, ImageView sprite){
		location = startingPos;
		this.sprite = sprite;
	}

	public void setPolicy(Policy policy){
		this.policy=policy;
	}

	public GridLocation getLocation() {
		return location;
	}

	public ImageView getSprite(){
		return sprite;
	}

	public void interact(Player player) {
		policy.interact(player,this);
	}
	
	@Override
	public boolean isPenetrable() {
	    return false;
	}

	interface Policy {
		void interact(Player player, Obstacle obstacle);
	}
}
