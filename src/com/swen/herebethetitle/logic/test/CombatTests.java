package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.util.GridLocation;

public class CombatTests extends BaseTest {
    @Test
    public void startsFightsWithCloseEnemies() {
        NPC enemy = placeEnemy(new GridLocation(3, 3));
        
        assertEquals(new GridLocation(3, 3), region.getLocation(enemy));
        logic.update(100.0f);
        assertEquals(new GridLocation(2, 2), region.getLocation(enemy));
        logic.update(100.0f);
        assertEquals(new GridLocation(1, 1), region.getLocation(enemy));
        logic.update(100.0f);
        assertEquals(new GridLocation(1, 1), region.getLocation(enemy));
        logic.update(100.0f);
        assertEquals(new GridLocation(1, 1), region.getLocation(enemy));
    }
    
    @Test
    public void ignoresFarAwayEnemies() {
        NPC enemy = placeEnemy(new GridLocation(9, 9));
        
        assertEquals(new GridLocation(9, 9), region.getLocation(enemy));
        logic.update(100.0f);
        assertEquals(new GridLocation(9, 9), region.getLocation(enemy));
    }
}
