package com.swen.herebethetitle.entity.ai;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

import java.util.*;

/**
 * Created by Mark on 5/10/2017.
 */
public final class Conversational extends Behavior implements Iterable<String> {

	private Queue<String> dialog;

	public Conversational() { this.dialog = new PriorityQueue<>(); }

	public void addDialog(String... text) { dialog.addAll(Arrays.asList(text)); }

	public String getNext(){ return dialog.poll(); }

	public void ping(GameContext context, NPC npc) { }

	public void interact(GameContext context, NPC npc, Notifier notifier) { }

	@Override
	public boolean isAggressive() { return false; }

	@Override
	public Iterator<String> iterator() { return this.dialog.iterator(); }
}
