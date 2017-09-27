package com.swen.herebethetitle.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.pathfinding.PathfindingGrid;
import com.swen.herebethetitle.util.GridLocation;

/**
 * Representation of a game region, which could be inside or outside.
 * For now, weather information is internal.
 * @author J Woods
 * @author Dylan McKay
 */
public class Region implements PathfindingGrid, Iterable<Tile> {
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
    public Region(int width, int height) {
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

    /**
     * Removes an item from the region.
     */
    public void remove(Entity entity) {
        Tile tile = getTile(entity);
        tile.remove(entity);
    }
    
    /**
     * Gets the location of a tile in the region.
     */
    public GridLocation getLocation(Tile tile) {
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
    
    /**
     * Gets the location of an entity in the region.
     */
    public GridLocation getLocation(Entity entity) {
        Optional<Tile> tile = stream()
                .filter(t -> t.contains(entity))
                .findAny();
        
        if (tile.isPresent())
            return getLocation(tile.get());
        else
            throw new IllegalArgumentException("entity is not present in the region");
    }
    
    /**
     * Gets the tile that an entity is on.
     */
    public Tile getTile(Entity entity) {
        GridLocation location = getLocation(entity);
        return get(location);
    }
    
    /**
     * Attempts to get the tile a player is located on.
     */
    public Optional<Tile> maybeGetPlayerTile() {
        return stream().filter(t -> t.getInteractives().stream().anyMatch(e -> e instanceof Player)).findAny();
    }
    
    /**
     * Gets the tile a player is located on.
     * 
     * Throws an error if the player is not in the region.
     */
    public Tile getPlayerTile() {
        return maybeGetPlayerTile().get();
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
    
    /**
     * Gets a stream over all tiles in the region.
     * There is a consistent, but unspecified ordering of the result.
     */
    public Stream<Tile> stream() {
        return (Stream<Tile>)Arrays.asList(cells).stream().flatMap(row -> Arrays.asList(row).stream());
    }

    @Override
    public Iterator<Tile> iterator() {
        List<Tile> tiles = stream().collect(Collectors.toList());
        return tiles.iterator();
    }
}
