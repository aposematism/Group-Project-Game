package com.swen.herebethetitle.entity.npcs;

import com.swen.herebethetitle.entity.Mob;
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

	public NPC(Image sprite, int startingHealth, Direction direction){
		super(sprite, startingHealth, direction);
	}

	public void ping() { actions.ping(); }

	@Override
	public void interact(GameContext context) { actions.interact(this); }

	interface Actions {
		void ping();
		void interact(NPC npc);
		boolean isPenetrable();
	}

	@Override
	public void damage(int amount) {

	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public boolean isPenetrable(){
		return actions.isPenetrable();
	}
}
