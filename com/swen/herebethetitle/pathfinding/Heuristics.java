package com.swen.herebethetitle.pathfinding;

import com.swen.herebethetitle.model.Tile;

/**
 * Contains heuristics that can be used by the A* solver.
 * 
 * @author dylan
 */
public final class Heuristics {
    /// Private constructor to prevent construction.
    private Heuristics() {
    }

    /**
     * Gets the Euclidian distance between two tiles.
     * 
     * @param from
     * @param to
     * @param region
     * @return
     */
    public static double EucledianDistance(Tile from, Tile to, PathfindingGrid region) {
        return from.getLocation().distanceBetween(to.getLocation());
    }
}
