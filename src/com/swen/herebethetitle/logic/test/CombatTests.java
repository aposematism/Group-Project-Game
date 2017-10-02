package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.logic.exceptions.EntityOutOfRange;
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
    
    @Test
    public void cannotAttackFarAwayEnemies() {
        NPC enemy = placeEnemy(new GridLocation(3, 3));
        
        try {
            logic.attack(enemy);
            fail("should not be able to attack enemy");
        } catch (EntityOutOfRange e) {
            assertEquals(e.entity, enemy);
            assertEquals("entity out of range", e.getMessage());
        }
    }
    
    @Test
    public void canAttackNeighbouringEnemies() {
        teleportPlayer(new GridLocation(3, 2));
        NPC enemy = placeEnemy(new GridLocation(3, 3));
        
        try {
            logic.attack(enemy);
        } catch (EntityOutOfRange e) {
            fail("should be able to attack enemy");
        }
        
        // FIXME: test attack side effects
    }
}
