package com.swen.herebethetitle.entity.ai;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.model.GameContext;

/**
 * Created by Mark on 1/10/2017.
 */
public class Monster extends Behavior {

	private double ATTACK_STRENGTH;

	public Monster(double attackStrength) { ATTACK_STRENGTH=attackStrength; }

	public void ping(GameContext context, NPC npc) {
		if(canInteract(context,npc)){
			context.getPlayer().damage(ATTACK_STRENGTH);
		}
	}

	public void interact(GameContext context, NPC npc) {
		if(canInteract(context,npc) || !hasMeleeWeapon(context)){
			context.player.inventory().getWeapon().ifPresent(w ->
				npc.damage(w.getStrength())
			);
			npc.damage(Player.DEFAULT_DAMAGE);
		}
	}

	public String toString() { return super.toString()+" "+ATTACK_STRENGTH; }
}
