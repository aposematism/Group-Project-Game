package com.swen.herebethetitle.entity;

import java.util.Optional;

import com.swen.herebethetitle.entity.Static.Behavior;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

public class RegionDoor extends Entity{
	String regionDirection;
	
	
	public RegionDoor(String name, String spritePath, String direction) {
		super(name, spritePath);
		regionDirection = direction;
	}

	public void interact(GameContext context, Notifier notifier) {
	    //TODO: Behaviour implementation.
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
	public boolean isPenetrable() { return true; }

	@Override
	public String toString() {
		return super.toString()+(behavior.isPresent() ? " "+behavior.get().toString() : "");
	}
}
