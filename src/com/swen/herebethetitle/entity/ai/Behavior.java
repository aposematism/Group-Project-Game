package com.swen.herebethetitle.entity.ai;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Mob;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.model.GameContext;

/**
 * Created by Mark on 30/09/2017.
 */
public abstract class Behavior {

	/**
	 * Checks that the NPC can be interacted with by the Player.
	 * Does this by checking if the
	 */
	public boolean canInteract(GameContext context, NPC npc){
		return context.currentRegion.getPlayerTile().neighboursContain(npc) ||
				context.currentRegion.getPlayerTile().contains(npc);
	}

	/**
	 * Checks if the Player possesses a melee weapon.
	 */
	public boolean hasMeleeWeapon(GameContext context){
		return context.player.inventory().getWeapon().isPresent() && context.player.inventory().getWeapon().get().isMelee();
	}

	abstract public void ping(GameContext context, NPC npc);

	abstract public void interact(GameContext context, NPC npc);

	/**
	 * Isn't penetrable by default, but can be overridden for special monsters
	 */
	public boolean isPenetrable() { return false; }

	public String toString() { return getClass().getName(); }
}
