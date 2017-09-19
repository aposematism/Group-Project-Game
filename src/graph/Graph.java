package graph;

import java.util.Collection;
import java.util.Optional;

import util.Direction;
import util.GridLocation;

/**
 * A grid-based graph that can be used to find shortest paths.
 * @author dylan
 */
public class Graph {
	/**
	 * Creates a new graph.
	 */
	// FIXME: make this public and pass a map into it
	//        we first need an in-memory class to represent the world.
	//        with this, we can use that data from here; no point in duplicating
	//        should be added once the class exists.
	protected Graph() {
		throw new IllegalStateException("unimplemented");
	}

	/**
	 * Places a node at a location.
	 */
    void set(GridLocation location, Node node) {
    	// FIXME: implement
    }

    /**
     * Gets the node at a given location.
     */
    Optional<Node> get(GridLocation location) {
    	// FIXME: implement
    	return null;
    }

    /**
     * Clears the node at a location.
     */
    // FIXME: we may not need this if we are given the grid in the constructor
    Optional<Node> unset(GridLocation l) {
    	// FIXME: implement
    	return null;
    }

    /**
     * Checks if two nodes are adjacent
     */
    boolean isAdjacent(Node a, Node b) {
    	// FIXME: implement
    	return false;
    }

    /**
     * Moves a node to another location
     */
    // FIXME: we may not need this if we are given the grid in the constructor
    void move(Node node, GridLocation location) {
    	// FIXME: implement
    }

    /**
     * Moves a node in a direction
     */
    // FIXME: we may not need this if we are given the grid in the constructor
    void move(Node node, Direction direction) {
    	// FIXME: implement
    }

    /**
     * Gets all neighbors adjacent to a node.
     */
    Collection<Node> getNeighbours(Node node) {
    	// FIXME: implement
    	return null;
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

    /**
     * Gets the width of the graph grid.
     */
    int getWidth() {
    	return -1; // FIXME: implement
    }

    /**
     * Gets the height of the graph grid.
     */
    int getHeight() {
    	return -1; // FIXME: implement
    }
}
