package com.swen.herebethetitle.logic.ai.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.logic.ai.Interaction;
import com.swen.herebethetitle.logic.ai.PlayerMove;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

public class PlayerMoveTest {
    protected static final GridLocation PLAYER_LOCATION = new GridLocation(0,0);
    
    protected Player player;
    protected Region region;

    @Test
    public void testCanMoveZeroSpaces() {
        PlayerMove move = createMovement(PLAYER_LOCATION);

        assertEquals(new GridLocation(0,0), region.getLocation(player));
        
        try {
            move.tick(region, new Notifier());
            fail("interaction should be complete immediately");
        } catch (Interaction.InteractionOver e) {
        }
        
        assertEquals(PLAYER_LOCATION, region.getLocation(player));
    }

    @Test
    public void testCanMoveOneSpace() {
        PlayerMove move = createMovement(new GridLocation(1,1));

        assertEquals(new GridLocation(0,0), region.getLocation(player));
        
        try {
            move.tick(region, new Notifier());
        } catch (Interaction.InteractionOver e) {
            fail("interaction prematurely over: " + e.getMessage());
        }
        
        assertEquals(new GridLocation(1,1), region.getLocation(player));

        try {
            move.tick(region, new Notifier());
            fail("player movement should be complete");
        } catch (Interaction.InteractionOver e) {
            // happy case
        }
    }
    
    @Test
    public void testCanMoveMultipleSpaces() {
        PlayerMove move = createMovement(new GridLocation(3,3));
        
        for (int i=1; i<=3; i++) {
            try {
                move.tick(region, new Notifier());
            } catch (Interaction.InteractionOver e) {
                fail("movement prematurely over: " + e.getMessage());
            }
            
            assertEquals(new GridLocation(i,i), region.getLocation(player));
        }
        
        try {
            move.tick(region, new Notifier());
            fail("movement should now be over");
        } catch (Interaction.InteractionOver e) {
            // happy case
        }
    }
    
    /**
     * Creates a player movement interaction.
     */
    protected PlayerMove createMovement(GridLocation destLoc) {
        Tile dest = region.get(destLoc);
        return new PlayerMove(player, dest);
    }
    
    @Before
    public void setup() {
        this.player = new Player(null, Direction.Right);
        this.region = new Region(8, 8);
        this.region.get(PLAYER_LOCATION).add(player);
    }
}
