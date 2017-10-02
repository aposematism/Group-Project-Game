package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.util.GridLocation;

public class CombatTests extends BaseTest {
    @Test
    public void startsFightsWithCloseEnemies() {
        NPC enemy = placeEnemy(new GridLocation(4, 4));
        
        assertEquals(new GridLocation(4, 4), region.getLocation(enemy));
        logic.update(100.0f);
        // FIXME: ensure that the enemy moves
        //assertEquals(new GridLocation(3, 3), region.getLocation(enemy));
    }
}
