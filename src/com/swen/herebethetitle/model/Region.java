package com.swen.herebethetitle.model;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.pathfinding.PathfindingGrid;
import com.swen.herebethetitle.util.GridLocation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * 
     * Region Name. "Church
     * 
     * */
    private String regionName;
    
    /** 
     * List of neighbouring regions in string form.
     * */
    private String[] neighbouringRegions;
    /**
     * Creates a new basic grid.
     * @param width
     * @param height
     */
    public Region(int width, int height) {
        this.width = width;
        this.height = height;

	    this.cells = new Tile[height][width];
	    for (int y = 0; y < height; y++) {
		    for (int x = 0; x < width; x++) {
			    String character = String.format("%d:%d", x, y);
                set(new GridLocation(x,y), new Tile(x, y, character));
			    get(x, y).setMapFloor(new Floor("", "file:res/blank.png"));
		    }
        }
    }
    
    /**
     * Creates a custom grid from tile 2d array. Used for Terrain Parser.
     */
    public Region(Tile[][] tiles) {
	    this.height = tiles.length;
	    this.width = tiles[0].length;
	    this.cells = tiles;
    }
    
    public Region(String name, Tile[][] tiles) {
	    this.height = tiles.length;
	    this.width = tiles[0].length;
	    this.cells = tiles;
    	this.regionName = name;
    }
    
    /**
     * Gets the tile at a location.
     */
    public Tile get(GridLocation location) {
	    return cells[location.y][location.x];
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
        if (!isWithin(location))
            return Optional.empty();
        
        return Optional.of(get(location));
    }
    
    /**
     * Checks if the region contains an entity.
     */
    public boolean contains(Entity entity) {
        for (int y=0; y<this.height; y++)
            for (int x=0; x<this.width; x++)
                if (get(x, y).contains(entity))
                    return true;
        return false;
    }
    
    /**
     * Checks if a location is within the region.
     */
    public boolean isWithin(GridLocation location) {
        return location.x >= 0 && location.x < width &&
                location.y >= 0 && location.y < height;
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
	    cells[location.y][location.x] = tile.orElse(null);
    }
    
    /**
     * Checks if a location is penetrable.
     */
    public boolean isPenetrable(GridLocation location) {
    	    return get(location).isPenetrable();
    }

    /**
     * Removes an item from the region.
     */
    public void remove(Entity entity) {
        Tile tile = getTile(entity);
        if (!tile.remove(entity))
            throw new IllegalArgumentException("could not delete entity");
    }
    
    /**
     * Moves an entity to a location.
     */
    public void move(Entity entity, GridLocation location) {
    	    remove(entity);
    	    get(location).add(entity);
    }
    
    /**
     * Gets the location of a tile in the region.
     */
    public GridLocation getLocation(Tile tile) {
	    for (int y = 0; y < height; y++) {
		    for (int x = 0; x < width; x++) {
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
     * Gets an iterator of tiles within a vicinity.
     */
    public Iterator<Tile> vicinity(GridLocation location, double radius) {
		return vicinityList(location, radius).iterator();
    }
    
    /**
     * Gets a stream of tiles within a vicinity.
     */
    public Stream<Tile> vicinityStream(GridLocation location, double radius) {
    	    return vicinityList(location, radius).stream();
    }
    
    /**
     * Gets a list of tiles within a vicinity.
     */
    public List<Tile> vicinityList(GridLocation location, double radius) {
	    List<Tile> vicinity = new ArrayList<Tile>();

	    int maxRadius = (int) Math.round(radius);
	    // Go through every tile in the general area.
	    for (int y = location.y - maxRadius; y < location.y + radius; y++) {
			for (int x = location.x - maxRadius; x < location.x + maxRadius; x++) {
				Optional<Tile> tile = tryGet(new GridLocation(x,y));
				GridLocation tileLocation = new GridLocation(x,y);
				
				// Add any tiles within the radius.
				if (tile.isPresent() &&
						tileLocation.distanceBetween(location) <= radius) {
					vicinity.add(tile.get());
				}
			}
	    }
	    return vicinity;
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
    public Collection<Tile> getNeighbours(Tile tile) {
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
     * Checks if two entities are neighbouring.
     */
    public boolean isNeighbouring(Entity a, Entity b) {
	    return getTile(a).getLocation().isNeighbouring(getLocation(b));
    }
    
    /**
     * Gets a stream over all tiles in the region.
     * There is a consistent, but unspecified ordering of the result.
     */
    public Stream<Tile> stream() {
	    return Arrays.asList(cells).stream().flatMap(row -> Arrays.asList(row).stream());
    }

    @Override
    public Iterator<Tile> iterator() {
        List<Tile> tiles = stream().collect(Collectors.toList());
        return tiles.iterator();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
	    for (int y = 0; y < height; y++) {
		    for (int x = 0; x < width; x++) {
			    Tile tile = get(x,y);
                
                String letter = tile.getInteractives().isEmpty() ? "-" : "w";
                //String letter = tile.getInteractives().stream().findFirst().map(a -> a.toString()).orElse(" ");
                builder.append(letter);
            }
            builder.append('\n');
        }
        
        return builder.toString();
    }

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getXSize() {
		return cells[0].length;
	}

	public int getYSize() {
		return cells.length;
	}

	public String[] getNeighbouringRegions() {
		return neighbouringRegions;
	}

	public void setNeighbouringRegions(String[] nr) {
		this.neighbouringRegions = nr;
	}

	public int getInteractiveTotal() {
    	int total = 0;
    	for(Entity e : this.getPlayerTile().getInteractives()) {
    		if(e instanceof Player) {
    			total = ((Player) e).inventory().size();
    		}
    	}
	    for (int y = 0; y < height; y++) {
		    for (int x = 0; x < height; x++) {
			    total = total + cells[y][x].getInteractiveSize();
		    }
    	}
    	return total;
    }
}
