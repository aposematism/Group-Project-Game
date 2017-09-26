package util;

/**
 * Represents a point on a 2-dimensional grid.
 * @author dylan
 */
public class GridLocation {
	/**
	 * The x-coordinate of the location.
	 */
	public final int x;
	/**
	 * The y-coordinate of the location.
	 */
	public final int y;
	
	/**
	 * Creates a new location.
	 */
	public GridLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds one location to another.
	 */
	public GridLocation add(GridLocation rhs) {
		return new GridLocation(this.x + rhs.x, this.y + rhs.y);
	}
	
	/**
	 * Subtracts one location from another.
	 */
	public GridLocation sub(GridLocation rhs) {
		return new GridLocation(this.x - rhs.x, this.y - rhs.y);
	}
	
	/**
	 * Calculates the length of the location if it were a vector.
	 */
	public double length() {
		return Math.sqrt(x*x + y*y);
	}
	
	/**
	 * Gets the Euclidian distance between two locations.
	 */
	public double distanceBetween(GridLocation other) {
		return this.sub(other).length();
	}
	
	@Override
	public String toString() {
	    return String.format("(%d,%d)", x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof GridLocation)) return false;
	    GridLocation rhs = (GridLocation)obj;
	    
	    return this.x == rhs.x && this.y == rhs.y;
	}
}
