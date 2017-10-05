package com.swen.herebethetitle.entity.ai;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A behavioral pattern for storing the text said in an NPC friendly
 *
 * Created by Mark Metcalfe on 5/10/2017.
 *
 * @author Mark Metcalfe
 */
public final class Friendly extends Behavior {

	private Queue<String> dialog;

	public Friendly() { this.dialog = new PriorityQueue<>(); }

	public void addDialog(String... text) { dialog.addAll(Arrays.asList(text)); }

	public String nextMessage() { return dialog.poll(); }

	public boolean canTalkTo() { return dialog.size() != 0; }

	/**
	 * Friendly NPCs don't attack the player, instead they just randomly
	 * change direction on the spot if they still have something to say
	 */
	public void ping(GameContext context, NPC npc) {
		if(canTalkTo()){
			int ordinal = ThreadLocalRandom.current().nextInt(0, Direction.values().length);
			npc.setDirection(Direction.values()[ordinal]);
		}
	}

	public void interact(GameContext context, NPC npc, Notifier notifier) { }

	@Override
	public boolean isAggressive() { return false; }
}
