package pathfinding.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Entity;
import entity.Obstacle;
import util.GridLocation;

/**
 * Tests invariants that must be true for pathfinding to work correctly.
 * @author dylan
 */
public class Invariants {
    @Test
    public void obstacleEntityIsImpenetrable() {
        Entity obstacle = new Obstacle(new GridLocation(2, 3), null);
        assertFalse(obstacle.isPenetrable());
    }
}
