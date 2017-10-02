package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.swen.herebethetitle.entity.Mob;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
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

        assertEquals(Mob.FULL_HEALTH, player.getHealth(), 0.1);
        assertEquals(Mob.FULL_HEALTH, enemy.getHealth(), 0.1);
        try {
            logic.attack(enemy);
        } catch (EntityOutOfRange e) {
            fail("should be able to attack enemy");
        }

        assertEquals(Mob.FULL_HEALTH, player.getHealth(), 0.1);
        assertEquals(Mob.FULL_HEALTH - Player.DEFAULT_DAMAGE,
                enemy.getHealth(), 0.1);
    }
    
    @Test
    public void canKillEnemyWithBareHands() {
        NPC enemy = placeEnemy(new GridLocation(1, 1));

        try {
            for (int i=0; i<30 && !enemy.isDead(); i++) {
                logic.attack(enemy);
            }
        } catch (EntityOutOfRange e) {
            fail("should be able to attack enemy");
        } catch (IllegalArgumentException e) {
            assertEquals("cannot attack a dead entity", e.getMessage());
        }
        
        assertTrue(enemy.isDead());
    }
}
