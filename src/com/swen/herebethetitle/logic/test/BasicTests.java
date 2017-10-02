package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.logic.GameLogic;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

public class BasicTests {
	/**
	 * The game state.
	 */
	private GameContext context;
	/**
	 * The game logic object.
	 */
	private GameLogic logic;
	
	@Test
	public void cannotMovePlayerOutOfBounds() {
		try {
			logic.movePlayer(Direction.Left);
			fail("should not be able to move out of bounds");
		} catch (GameLogic.InvalidMove e) {
			assertEquals("direction is out of bounds", e.getMessage());
		}
	}
	
	@Test
	public void cannotMovePlayerOntoImpenetrableObstacle() {
		placeImpenetrableObject(new GridLocation(1, 0));
		try {
			logic.movePlayer(Direction.Right);
			fail("should not be able to move onto impenetrable object");
		} catch (GameLogic.InvalidMove e) {
			assertEquals("an obstacle is in the way", e.getMessage());
		}
	}
	
	@Test
	public void canMovePlayerOntoAdjacentEmptyTiles() {
		placeImpenetrableObject(new GridLocation(1, 0));
		try {
			logic.movePlayer(Direction.Down);
			logic.movePlayer(Direction.Right);
			logic.movePlayer(Direction.Right);
			logic.movePlayer(Direction.Up);
		} catch (GameLogic.InvalidMove e) {
			fail("should have been able to move onto location");
		}
	}
	
	/**
	 * Sets up the game context and logic.
	 * Creates a player at (0,0).
	 */
	@Before
	public void setupGameLogic() {
		this.context = new GameContext();
		this.logic = new GameLogic(context);

		context.getCurrentRegion().move(context.getPlayer(), new GridLocation(0,0));
	}
	
	/**
	 * Places an impenetrable object on the map.
	 */
	protected void placeImpenetrableObject(GridLocation location) {
		context.getCurrentRegion().get(location).add(new Static("impenetrable", null));
	}
}
