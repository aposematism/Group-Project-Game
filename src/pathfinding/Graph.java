package pathfinding;

import java.util.Collection;
import java.util.Optional;

import model.Region;
import model.Tile;

/**
 * A grid-based graph that can be used to find shortest paths.
 * @author dylan
 */
public class Graph {
	private final Region region;
	
	/**
	 * Creates a new graph.
	 */
	public Graph(Region region) {
		this.region = region;
	}

    /**
     * Finds the most optimal path between two nodes
     * 
     * The ordering of the parameters does not matter, as the path will always be the same.
     * @param a The first node.
     * @param b The second node.
     */
    Optional<Path> findPath(Node a, Node b) {
    	// FIXME: implement
    	return null;
    }
}
