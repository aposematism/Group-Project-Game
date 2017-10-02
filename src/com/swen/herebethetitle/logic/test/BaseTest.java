package com.swen.herebethetitle.logic.test;

import org.junit.Before;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.ai.Monster;
import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.logic.GameLogic;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

public class BaseTest {
	/**
	 * The game state.
	 */
	protected GameContext context;
	/**
	 * The current region.
	 */
	protected Region region;
	/**
	 * The game logic object.
	 */
	protected GameLogic logic;
	
	/**
	 * Sets up the game context and logic.
	 * Creates a player at (0,0).
	 */
	@Before
	public void setupGameLogic() {
		this.context = new GameContext();
		this.region = this.context.getCurrentRegion();
		this.logic = new GameLogic(context);

		context.getCurrentRegion().move(context.getPlayer(), new GridLocation(0,0));
	}
	
	/**
	 * Places an impenetrable object on the map.
	 */
	protected void placeImpenetrableObject(GridLocation location) {
		context.getCurrentRegion().get(location).add(new Static("impenetrable", null));
	}
	
	/**
	 * Places an enemy at a location.
	 */
	protected NPC placeEnemy(GridLocation location) {
		NPC npc = new NPC("baddy", null, 100.0, Direction.Right);
		npc.setBehavior(new Monster(1.5));
		context.getCurrentRegion().get(location).add(npc);
		return npc;
	}
}
