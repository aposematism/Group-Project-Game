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
 */
public class NPC extends Mob {

	private Optional<Behavior> behavior;

	public NPC(Image sprite, Behavior behavior, int startingHealth, Direction direction){
		super(sprite, startingHealth, direction);
		this.behavior = Optional.of(behavior);
	}

	public NPC(Image sprite, int startingHealth, Direction direction){
		super(sprite, startingHealth, direction);
		this.behavior = Optional.empty();
	}

	@Override
	public void interact(GameContext context){
		behavior.ifPresent(b -> b.interact(context,this));
	}

	public void ping(GameContext context){
		behavior.ifPresent(b -> b.ping(context,this));
	}

	public void setBehavior(Behavior behavior) { this.behavior = Optional.of(behavior); }

	public Optional<Behavior> getBehavior() { return this.behavior; }

	@Override
	public String toString() { return super.toString()+" "+behavior.toString(); }

	@Override
	public boolean isPenetrable() { return behavior.isPresent() && behavior.get().isPenetrable(); }
}
