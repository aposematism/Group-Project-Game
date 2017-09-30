package com.swen.herebethetitle.entity.stationeries;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 */
public class Stationary extends Entity {

	private Policy policy;

	public Stationary(Image sprite) { super(sprite); }

	public void setPolicy(Policy policy) { this.policy=policy; }

	public void interact(GameContext context) { if(policy!=null) policy.interact(context,this); }

	interface Policy {
		void interact(GameContext context, Stationary stationary);
		boolean isPenetrable();
	}

	@Override
	public String toString() { return null; }
	
	@Override
	public boolean isPenetrable() { return policy!=null && policy.isPenetrable(); }
}
