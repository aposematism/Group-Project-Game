package com.swen.herebethetitle.entity;

import java.util.Optional;

import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

/**
 * An object on the map that obstructs other entities from moving.
 *
 * Created by Mark on 19/09/2017.
 *
 * @author Mark Metcalfe
 */
public class Static extends Entity {

	/**
	 * Optional as Static objects don't actually need to do anything.
	 */
	private Optional<Behavior> behavior;

	public Static(String name, String spritePath) {
		super(name, spritePath);
		behavior = Optional.empty();
	}

	/**
	 * Changes the behavior of the Static
	 * Could be used to change, for example, an unmovable rock to a moveable one
	 */
	public void setBehavior(Behavior behavior) { this.behavior = Optional.of(behavior); }

	/**
	 * Calls interact() in the behavior
	 */
	public void interact(GameContext context, Notifier notifier) {
	    behavior.ifPresent(b->b.interact(context,this, notifier));
	}

	/**
	 * Strategy Pattern of what it actually does when interacted with.
	 */
	public interface Behavior {
		void interact(GameContext context, Static aStatic, Notifier notifier);
		boolean isPenetrable();
		String toString();
	}

	/**
	 * Checks if the Static can be moved through based on its behavior
	 */
	@Override
	public boolean isPenetrable() { return behavior.isPresent() && behavior.get().isPenetrable(); }

	@Override
	public String toString() {
		return super.toString()+(behavior.isPresent() ? " "+behavior.get().toString() : "");
	}
}
