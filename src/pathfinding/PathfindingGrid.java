package pathfinding;

import java.util.Collection;

import model.Tile;

/**
 * An abstract grid of tiles.
 * 
 * An interface is used so that the pathfinding library can operate on any kind of grid.
 * @author dylan
 */
public interface PathfindingGrid {
    /**
     * Gets a list of tiles that are adjacent to a tile.
     */
	public Collection<Tile> getAdjacent(Tile tile);
}
