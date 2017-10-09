package com.swen.herebethetitle.pathfinding.test;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Static;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Tests invariants that must be true for pathfinding to work correctly.
 * @author dylan
 */
public class Invariants {
    @Test
    public void stationaryEntityIsImpenetrable() {
		Entity stationary = new Static("wall", null);
        assertFalse(stationary.isPenetrable());
    }
}
