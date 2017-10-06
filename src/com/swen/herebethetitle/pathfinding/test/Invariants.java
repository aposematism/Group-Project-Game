package com.swen.herebethetitle.pathfinding.test;

import static org.junit.Assert.assertFalse;

import com.swen.herebethetitle.entity.statics.Static;
import org.junit.Test;

import com.swen.herebethetitle.entity.Entity;

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
