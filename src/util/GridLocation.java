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
}
