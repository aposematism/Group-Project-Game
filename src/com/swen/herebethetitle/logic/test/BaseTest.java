package com.swen.herebethetitle.logic.test;

import org.junit.Before;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.MonsterStrategy;
import com.swen.herebethetitle.entity.Key;
import com.swen.herebethetitle.entity.Static;
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
     * The active player.
     */
    protected Player player;
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
        this.player = this.context.getPlayer();
        this.logic = new GameLogic(context);

        teleportPlayer(new GridLocation(0, 0));
    }
    
    /**
     * Teleports the player to a location.
     */
    protected void teleportPlayer(GridLocation location) {
        context.getCurrentRegion().move(context.getPlayer(), location);
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
        npc.setBehavior(new MonsterStrategy(1.5));
        context.getCurrentRegion().get(location).add(npc);
        return npc;
    }
    
    /**
     * Places a key at a location.
     */
    protected Key placeKey(GridLocation location) {
        return placeKey(location, 123);
    }
    
    /**
     * Places a key at a location.
     */
    protected Key placeKey(GridLocation location, int password) {
        Key key = new Key("back doork key", null, password);
        context.getCurrentRegion().get(location).add(key);
        return key;
    }
}
