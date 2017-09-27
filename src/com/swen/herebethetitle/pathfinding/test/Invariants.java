package com.swen.herebethetitle.pathfinding.test;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Terrain;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;

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
