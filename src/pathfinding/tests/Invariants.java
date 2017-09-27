package pathfinding.tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import entity.Entity;
import entity.Terrain;
import model.GameContext;
import model.Tile;
import util.GridLocation;

/**
 * Tests invariants that must be true for pathfinding to work correctly.
 * @author dylan
 */
public class Invariants {
    @Test
    public void obstacleEntityIsImpenetrable() {
        GameContext dummyContext = new GameContext();
        Tile tile = new Tile(new GridLocation(1, 1), "foo");

        Entity terrain = new Terrain(dummyContext, tile, null);
        assertFalse(terrain.isPenetrable());
    }
}
