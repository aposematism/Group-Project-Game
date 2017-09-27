package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 */
public class Obstacle extends Entity {

	private Policy policy;

	public Obstacle(Image sprite){
		super(sprite);
	}

	public void setPolicy(Policy policy){
		this.policy=policy;
	}

	public void interact(GameContext context) {
		policy.interact(context,this);
	}

	interface Policy {
		void interact(GameContext context, Obstacle obstacle);
	}

	@Override
	public String toString() {
		return null;
	}
	
	@Override
	public boolean isPenetrable() {
	    return false;
	}
}
