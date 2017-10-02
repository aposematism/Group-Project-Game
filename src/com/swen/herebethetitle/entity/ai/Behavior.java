package com.swen.herebethetitle.entity.ai;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.model.GameContext;

/**
 * Strategy Pattern of what it an NPC actually does when interacted with.
 *
 * Created by Mark on 30/09/2017.
 *
 * @author Mark Metcalfe
 */
public abstract class Behavior {

	/**
	 * Checks that the NPC can be interacted with by the Player
	 * Can be interacted with if the player is on or adjacent to the NPCs tile
	 */
	public boolean canInteract(GameContext context, NPC npc){
		return context.currentRegion.getPlayerTile().neighboursContain(npc) ||
				context.currentRegion.getPlayerTile().contains(npc);
	}

	/**
	 * Checks if the Player possesses a melee weapon
	 */
	public boolean hasMeleeWeapon(GameContext context){
		return context.player.inventory().getWeapon().isPresent() && context.player.inventory().getWeapon().get().isMelee();
	}

	/**
	 * Called every game tick
	 */
	abstract public void ping(GameContext context, NPC npc);

	/**
	 * Called when the player interacts with the NPCs
	 */
	abstract public void interact(GameContext context, NPC npc);

	/**
	 * Checks if the behaviour can be aggressive.
	 * @return true if the entity can start a fight, false otherwise.
	 */
	abstract public boolean isAggressive();

	/**
	 * Isn't penetrable by default, but can be overridden for special monsters
	 */
	public boolean isPenetrable() { return false; }
	
	public String toString() { return getClass().getSimpleName(); }
}
