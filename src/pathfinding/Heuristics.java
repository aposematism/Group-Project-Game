package pathfinding;

import model.Tile;

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
     * @param node The node for which the heuristic is being calculated for.
     * @param goal The ending node.
     * @param grid The grid both tiles exist in.
     */
    public static double EucledianDistance(Tile node, Tile goal, PathfindingGrid grid) {
        return node.getLocation().distanceBetween(goal.getLocation());
    }
}
