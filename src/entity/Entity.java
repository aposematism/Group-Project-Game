package entity;

import graph.Node;

/**
 * Overarching interface that defines all entities found in the game world.
 *
 * Created by Mark on 19/09/17.
 */
public interface Entity {
	void interact(Player player);
	Node getPosition();
}