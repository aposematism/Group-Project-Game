package com.swen.herebethetitle.pathfinding.test;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;

/**
 * Tests invariants that must be true for pathfinding to work correctly.
 * @author dylan
 */
public class Invariants {
    @Test
    public void obstacleEntityIsImpenetrable() {
        Entity Floor = new Floor(null);
        assertFalse(Floor.isPenetrable());
    }
}
