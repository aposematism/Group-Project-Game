package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

/**
 * An NPC AI that's aim is to attack and be attacked by the player
 *
 * Created by Mark on 1/10/2017.
 *
 * @author Mark Metcalfe
 */
public final class MonsterStrategy extends NPCBehavior {

	private final double ATTACK_STRENGTH;

	/**
	 * @param attackStrength How much damage the monster will deal, must be positive
	 */
	public MonsterStrategy(double attackStrength) {
		if(attackStrength<0)
			throw new IllegalArgumentException("attackStrength must be positive!");
		ATTACK_STRENGTH=attackStrength;
	}

	/**
	 * Attack the player if adjacent
	 */
	public void ping(GameContext context, NPC npc) {
		if(canInteract(context,npc)){
			context.getPlayer().damage(ATTACK_STRENGTH);
		}
	}

	/**
	 * Receive an attack from the Player
	 */
	@Override
	public void interact(GameContext context, NPC npc, Notifier notifier) {
		if(canInteract(context,npc) || hasMeleeWeapon(context)){
			//calculate damage
			context.player.inventory().getWeapon().ifPresent(w ->
				npc.damage(w.getStrength())
			);
			npc.damage(Player.DEFAULT_DAMAGE);
            notifier.notify(l -> l.onNPCAttacked(npc));

			//death
			if(npc.isDead()) {
				context.getCurrentRegion().getTile(npc).remove(npc);
				notifier.notify(l -> l.onNPCKilled(npc));
			}
		}
	}

	public String toString() { return super.toString()+" "+ATTACK_STRENGTH; }

	@Override
	public boolean isAggressive() { return true; }

	@Override
	public MonsterStrategy clone() throws CloneNotSupportedException {
		return new MonsterStrategy(this.ATTACK_STRENGTH);
	}
}
