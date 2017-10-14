package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A behavioral pattern for storing the conversational dialog in an friendly NPC
 *
 * Created by Mark Metcalfe on 5/10/2017.
 *
 * @author Mark Metcalfe
 */
public final class FriendlyStrategy extends NPCBehavior {

	/**
	 * Uses a queue to manage the next piece of dialog to be spoken
	 */
	private Queue<String> dialog;

	public FriendlyStrategy() { this.dialog = new ArrayDeque<>(); }

	/**
	 * Add dialog to be spoken
	 */
	public void addDialog(String... text) { dialog.addAll(Arrays.asList(text)); }

	/**
	 * Returns the next message in the dialog queue
	 */
	public String nextMessage() { return dialog.poll(); }

	/**
	 * Checks if there is anything left to say
	 */
	public boolean canTalkTo() { return dialog.size() != 0; }

	/**
	 * FriendlyStrategy NPCs don't attack the player, instead they just turn
	 * from side to side until they have nothing left to say
	 */
	public void ping(GameContext context, NPC npc) {
		//FIXME - ordinal will return -1 some (all?) the time, causes outofbounds erro
		if(canTalkTo()){
			int ordinal = npc.getDirection().ordinal();

			// 50/50 Chance of turning clockwise/anti-clockwise
			if(ThreadLocalRandom.current().nextBoolean())
				ordinal = ordinal + 1 >= Direction.values().length ? ordinal + 1 : 0;
			else
				ordinal = ordinal - 1 > 0 ? ordinal - 1 : 0;

			npc.setDirection(Direction.values()[ordinal]);
		}
	}

	/**
	 * Interactions are handled in the game logic, so it is blank here
	 */
	public void interact(GameContext context, NPC npc, Notifier notifier) { }

	/**
	 * FriendlyStrategy NPCs, so isn't aggressive
	 */
	@Override
	public boolean isAggressive() { return false; }

	/**
	 * Returns what hasn't been spoken
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(super.toString());
		s.append(" { ");
		for(String text: dialog){
			s.append("\"");
			s.append(text);
			s.append("\" ");
		}
		s.append("}");
		return s.toString();
	}
}
