package pathfinding.tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Tile;
import pathfinding.PathfindingGrid;
import util.GridLocation;

/**
 * An extremely basic tile grid used for testing.
 * @author dylan
 */
public class BasicGrid implements PathfindingGrid {
    /**
     * The width of the grid.
     */
    public final int width;
    
    /**
     * The height of the grid.
     */
    public final int height;
    
    /**
     * The cells in the grid.
     * 
     * Index in terms of [x][y].
     */
    private Tile[][] cells;
    
    /**
     * Creates a new basic grid.
     * @param width
     * @param height
     */
    public BasicGrid(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.cells = new Tile[width][height];
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                String character = String.format("%d:%d", x, y);
                set(new GridLocation(x,y), new Tile(x, y, character));
            }
        }
    }
    
    /**
     * Gets the tile at a location.
     */
    public Tile get(GridLocation location) {
        return cells[location.x][location.y];
    }
    
    /**
     * Gets the tile at a location.
     */
    public Tile get(int x, int y) {
        return get(new GridLocation(x, y));
    }
    
    /**
     * Gets a tile at a location if the location is in bounds.
     */
    public Optional<Tile> tryGet(GridLocation location) {
        if (location.x < 0 || location.x >= width ||
                location.y < 0 || location.y >= height)
            return Optional.empty();
        
        return Optional.of(get(location));
    }
    
    /**
     * Places a tile at a location.
     */
    public void set(GridLocation location, Tile tile) {
        set(location, Optional.of(tile));
    }
    
    /**
     * Places a tile at a location.
     */
    public void set(GridLocation location, Optional<Tile> tile) {
        cells[location.x][location.y] = tile.orElse(null);
    }

    @Override
    public Collection<Tile> getAdjacent(Tile tile) {
        GridLocation location = getLocation(tile);

        List<Optional<Tile>> tiles = Arrays.asList(
            tryGet(location.add(new GridLocation(-1, +0))), // left
            tryGet(location.add(new GridLocation(-1, -1))), // top-left
            tryGet(location.add(new GridLocation(+0, -1))), // top
            tryGet(location.add(new GridLocation(+1, -1))), // top-right
            tryGet(location.add(new GridLocation(+1, +0))), // right
            tryGet(location.add(new GridLocation(+1, +1))), // bottom-right
            tryGet(location.add(new GridLocation(+0, +1))), // bottom
            tryGet(location.add(new GridLocation(-1, +1)))  // bottom-left
        );

        return tiles.stream()
            .filter(t -> t.isPresent())
            .map(Optional::get)
            .collect(Collectors.toList());
    }
    
    private GridLocation getLocation(Tile tile) {
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                GridLocation location = new GridLocation(x,y);
                Tile currentTile = get(location);
                
                if (currentTile == tile)
                    return location;
            }
        }
        throw new IllegalArgumentException("tile is not present in the grid");
    }
}
