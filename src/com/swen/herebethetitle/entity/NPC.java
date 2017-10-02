package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.entity.ai.Behavior;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import javafx.scene.image.Image;
import java.util.Optional;

/**
 * NPC - Non Player Character - will be used for representing monsters and potentially other types of NPC
 * Uses strategy pattern for deciding its behavior.
 *
 * Created by Mark on 19/09/17.
 *
 * @author Mark Metcalfe
 */
public class NPC extends Mob {

	/**
	 * Strategy Pattern of what the NPC actually does when pinged and interacted with.
	 * Optional as the NPC doesn't necessarily have to do anything.
	 */
	private Optional<Behavior> behavior;

	/**
	 * Construct an NPC with a behavior
	 */
	public NPC(String name, Image sprite, Behavior behavior, int startingHealth, Direction direction){
		super(name, sprite, startingHealth, direction);
		this.behavior = Optional.of(behavior);
	}

	/**
	 * Construct an NPC without a behavior
	 */
	public NPC(String name, Image sprite, int startingHealth, Direction direction){
		super(name, sprite, startingHealth, direction);
		this.behavior = Optional.empty();
	}

	/**
	 * Calls interact() in the behavior
	 */
	@Override
	public void interact(GameContext context) { behavior.ifPresent(b -> b.interact(context,this)); }

	/**
	 * Calls ping() in the behavior
	 */
	public void ping(GameContext context) { behavior.ifPresent(b -> b.ping(context,this)); }

	/**
	 * Checks if the NPC can be moved through based on its behavior
	 */
	@Override
	public boolean isPenetrable() { return behavior.isPresent() && behavior.get().isPenetrable(); }

	/**
	 * Changes the behavior of the NPC
	 * Could be used to change, for example, a villager NPC into a zombie
	 */
	public void setBehavior(Behavior behavior) { this.behavior = Optional.of(behavior); }

	public Optional<Behavior> getBehavior() { return this.behavior; }

	@Override
	public String toString() {
		return super.toString()+(behavior.isPresent() ? " "+behavior.get().toString() : "");
	}
}
