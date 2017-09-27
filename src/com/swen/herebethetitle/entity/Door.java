package com.swen.herebethetitle.entity;


import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

/**
 * Created by Mark on 19/09/2017.
 */
public class Door extends Obstacle {

	private enum STATE { LOCKED, UNLOCKED, OPEN }

	private STATE state;

	private final int KEY;

	public Door(Image sprite, int key, STATE state){
		super(sprite);
		KEY = key;
		this.state = state;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void interact(GameContext context) {
		//TODO
		if (state == STATE.LOCKED) {
			//player.hasItem();
		}
	}



	@Override
	public boolean isPenetrable() {
		return state==STATE.OPEN;
	}
}
