package com.swen.herebethetitle.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.ai.Monster;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.logic.exceptions.EntityOutOfRange;
import com.swen.herebethetitle.logic.exceptions.ImpossibleAction;
import com.swen.herebethetitle.logic.exceptions.InvalidDestination;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

/**
 * Provides updating and logic support to a game model.
 * @author dylan
 */
public class GameLogic {
	/**
	 * The observers that listen to the game updates.
	 */
	private Collection<GameListener> listeners;
	
	/**
	 * The game that is being controlled.
	 */
	private GameContext game;
	
	/**
	 * Creates a new game logic class.
	 */
	public GameLogic(GameContext game) {
		this.game = game;
		this.listeners = new ArrayList<GameListener>();
	}
	
	/**
	 * Updates the game logic after some time has passed.
	 * 
	 * Moves enemies.
	 * @param delta The number of elapsed seconds.
	 */
	public void update(float delta) {
		// FIXME: Implement this
		//     * Enemy pathfinding/updating
	}
	
	/**
	 * Get the player to attack an entity.
	 * 
	 * The victim 
	 * @param victim The entity to be attacked.
	 * @throws ImpossibleAction if the victim is out of range.
	 */
	public void attack(Monster victim) throws EntityOutOfRange {
		// FIXME: unimplemented.
		notify(listener -> listener.onEnemyAttacked(victim));
	}
	
	/**
	 * Picks up an item to the inventory.
	 */
	public void pickup(Item item) {
		getPlayer().inventory().add(item);
		notify(listener -> listener.onPlayerPickup(getPlayer(), item));
	}
	
	/**
	 * Drops an item from the inventory.
	 */
	public void drop(Item item) {
		getGame().getPlayer().inventory().remove(item);
		notify(listener -> listener.onPlayerDrop(getPlayer(), item));
	}
	
	/**
	 * Moves the player in a specific direction.
	 * @param direction The direction to move in.
	 * @throws InvalidDestination If the move would leave the player out of bounds or
	 * there is an impenetrable obstacle in the way.
	 */
	public void movePlayer(Direction direction) throws InvalidDestination  {
		GridLocation currentLocation = getCurrentRegion().getLocation(game.getPlayer());
		GridLocation newLocation = currentLocation.adjacent(direction);
		
		if (!getCurrentRegion().isWithin(newLocation))
			throw new InvalidDestination(game.getPlayer(),
					"direction is out of bounds");
		
		if (!getCurrentRegion().isPenetrable(newLocation))
			throw new InvalidDestination(getPlayer(),
					"an obstacle is in the way");
		
		game.getCurrentRegion().move(game.getPlayer(), newLocation);
		notify(listener -> listener.onPlayerMoved(getPlayer()));
	}
	
	/**
	 * Adds a game listener.
	 */
	public void addGameListener(GameListener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Gets the game that is being controlled.
	 */
	public GameContext getGame() { return this.game; }
	
	/**
	 * Gets the current region of the player.
	 */
	private Region getCurrentRegion() {
		return game.getCurrentRegion();
	}
	
	/**
	 * Gets the player object.
	 */
	private Player getPlayer() {
		return game.getPlayer();
	}
	
	/**
	 * Calls a function for every game listener.
	 */
	protected void notify(Consumer<GameListener> notifier) {
		for (GameListener listener : this.listeners) {
			notifier.accept(listener);
		}
	}
}
