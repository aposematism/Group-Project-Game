package com.swen.herebethetitle.entity.stationeries;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.ai.Behavior;
import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

import java.util.Optional;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 */
public class Stationary extends Entity {

	private Optional<Behavior> behavior;

	public Stationary(Image sprite) {
		super(sprite);
		behavior = Optional.empty();
	}

	public void setBehavior(Behavior behavior) { this.behavior = Optional.of(behavior); }

	public void interact(GameContext context) { behavior.ifPresent(b->b.interact(context,this)); }

	interface Behavior {
		void interact(GameContext context, Stationary stationary);
		boolean isPenetrable();
		String toString();
	}

	@Override
	public String toString() { return super.toString()+" "+behavior.toString(); }
	
	@Override
	public boolean isPenetrable() { return behavior.isPresent() && behavior.get().isPenetrable(); }
}
