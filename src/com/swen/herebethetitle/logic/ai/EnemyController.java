package com.swen.herebethetitle.logic.ai;

import java.util.ArrayList;
import java.util.List;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.ai.Monster;

/**
 * Controllers the enemies in the game.
 * @author dylan
 */
public class EnemyController {
	/**
	 * A single, self-contained fight between a player and a monster.
	 * @author dylan
	 */
	protected static class Fight {
		public final Player player;
		public final Monster monster;
		
		/**
		 * Creates a new fight between a player and a monster.
		 */
		public Fight(Player player, Monster monster) {
			this.player = player;
			this.monster = monster;
		}
	}
	
	/**
	 * A list of all fights that are currently in progress.
	 */
	protected List<Fight> activeFights;

	/**
	 * Creates a new enemy controller.
	 */
	public EnemyController() {
		this.activeFights = new ArrayList<Fight>();
	}
	
	/**
	 * Updates the game after a single tick.
	 */
	public void tick() {
		
	}
	
	/**
	 * Starts a fight.
	 */
	public void startFight(Player player, Monster monster) {
		// Only start a fight if there isn't one already.
		// Throwing punches is hard when you're fighting a race condition too.
		if (!isFighting(player, monster))
			this.activeFights.add(new Fight(player, monster));
	}
	
	/**
	 * Checks if a player and a monster are fighting.
	 */
	protected boolean isFighting(Player player, Monster monster) {
		return this.activeFights.stream()
				.anyMatch(fight -> fight.player == player && fight.monster == monster);
	}
}
