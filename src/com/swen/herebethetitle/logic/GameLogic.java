package com.swen.herebethetitle.logic;

import com.swen.herebethetitle.entity.Player;
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
	 * Thrown when a move is attempted that is not possible.
	 * @author Dylan McKay
	 */
	public static class InvalidMove extends Exception {
		private static final long serialVersionUID = -2751815198575618672L;

		/**
		 * The player that triggered the invalid move.
		 */
		public final Player player;

		/**
		 * Creates a new invalid move exception.
		 */
		public InvalidMove(Player player, String message) {
			super(message);
			this.player = player;
		}
	}

	/**
	 * The game that is being controlled.
	 */
	private GameContext game;
	
	/**
	 * Creates a new game logic class.
	 */
	public GameLogic(GameContext game) {
		this.game = game;
	}
	
	public void update(float delta) {
		// FIXME: implement
	}
	
	/**
	 * Moves the player in a specific direction.
	 * @param direction The direction to move in.
	 * @throws InvalidMove If the move would leave the player out of bounds or
	 * there is an impenetrable obstacle in the way.
	 */
	public void movePlayer(Direction direction) throws InvalidMove  {
		GridLocation currentLocation = getCurrentRegion().getLocation(game.getPlayer());
		GridLocation newLocation = currentLocation.adjacent(direction);
		
		if (!getCurrentRegion().isWithin(newLocation))
			throw new InvalidMove(game.getPlayer(),
					"direction is out of bounds");
		
		if (!getCurrentRegion().isPenetrable(newLocation))
			throw new InvalidMove(game.getPlayer(),
					"an obstacle is in the way");
		
		game.getCurrentRegion().move(game.getPlayer(), newLocation);
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
}
