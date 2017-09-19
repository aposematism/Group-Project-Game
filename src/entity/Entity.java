package entity;

import graph.Node;

/**
 * Skeleton Interface for the various Entity subclasses
 *
 * Created by Mark on 19/09/17.
 */
public interface Entity {
	void interact(Player player);
	Node getPosition();
}