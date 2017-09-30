package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.entity.ai.Actions;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;

import javafx.scene.image.Image;

/**
 * NPC - Non Player Character - will be used for representing monsters and potentially other types of NPC
 * Uses strategy pattern for deciding its actions.
 *
 * Created by Mark on 19/09/17.
 */
public class NPC extends Mob {

	private Actions actions;

	public NPC(Image sprite, Actions actions, int startingHealth, Direction direction){
		super(sprite, startingHealth, direction);
		this.actions = actions;
	}

	@Override
	public void interact(GameContext context) { actions.interact(context, this); }

	public void setActions(Actions actions) { this.actions = actions; }

	public Actions getActions() { return this.actions; }

	@Override
	public String toString() { return null; }

	@Override
	public boolean isPenetrable() { return actions.isPenetrable(); }
}
