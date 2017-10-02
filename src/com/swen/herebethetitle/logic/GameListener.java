package com.swen.herebethetitle.logic;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.ai.Monster;
import com.swen.herebethetitle.entity.items.Item;

/**
 * A listener class which can handle updates from the game.
 * @author dylan
 */
public abstract class GameListener {
	/**
	 * Called when the player is moved.
	 */
	public void onPlayerMoved(Player player) { }
	
	/**
	 * Called when a monster is attacked by the player.
	 */
	public void onEnemyAttacked(Monster victim) { }
	
	/**
	 * Called when the player is attacked by a monster.
	 */
	public void onPlayerAttacked(Player player, Monster attacker) { }
	
	/**
	 * Called when a player picks up an item.
	 */
	public void onPlayerPickup(Player player, Item item) { }
	
	/**
	 * Called when a player drops an item.
	 */
	public void onPlayerDrop(Player player, Item item) { }
}
