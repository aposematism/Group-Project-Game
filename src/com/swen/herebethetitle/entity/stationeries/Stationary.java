package com.swen.herebethetitle.entity.stationeries;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.GameContext;
import javafx.scene.image.Image;
import java.util.Optional;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 *
 * @author Mark Metcalfe
 */
public class Stationary extends Entity {

	/**
	 * Optional as Stationary objects don't actually need to do anything.
	 */
	private Optional<Behavior> behavior;

	public Stationary(String name, Image sprite) {
		super(name, sprite);
		behavior = Optional.empty();
	}

	/**
	 * Changes the behavior of the Stationary
	 * Could be used to change, for example, an unmovable rock to a moveable one
	 */
	public void setBehavior(Behavior behavior) { this.behavior = Optional.of(behavior); }

	/**
	 * Calls interact() in the behavior
	 */
	public void interact(GameContext context) { behavior.ifPresent(b->b.interact(context,this)); }

	/**
	 * Strategy Pattern of what it actually does when interacted with.
	 */
	interface Behavior {
		void interact(GameContext context, Stationary stationary);
		boolean isPenetrable();
		String toString();
	}

	/**
	 * Checks if the Stationary can be moved through based on its behavior
	 */
	@Override
	public boolean isPenetrable() { return behavior.isPresent() && behavior.get().isPenetrable(); }

	@Override
	public String toString() {
		return super.toString()+(behavior.isPresent() ? " "+behavior.get().toString() : "");
	}
}
