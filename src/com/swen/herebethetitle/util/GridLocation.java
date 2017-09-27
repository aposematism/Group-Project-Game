package com.swen.herebethetitle.util;

import com.sun.istack.internal.NotNull;

import java.util.InputMismatchException;

/**
 * Represents a point on a 2-dimensional grid.
 * @author dylan
 */
public final class GridLocation {
	/**
	 * The x-coordinate of the location.
	 */
	public final int x;
	/**
	 * The y-coordinate of the location.
	 */
	public final int y;

	/**
	 * GridLocation value representing a location that is not supposed to be seen
	 */
	public static final GridLocation OFF_GRID = new GridLocation(-1,-1);

	/**
	 * Creates a new location.
	 */
	public GridLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the GridLocation of an adjacent element on the grid based on direction relative to player
	 * @param direction
	 * @return
	 */
	@NotNull
	public GridLocation adjacent(Direction direction){
		switch(direction){
			case Up:    return new GridLocation(x, y-1);
			case Right: return new GridLocation(x+1, y);
			case Down:  return new GridLocation(x, y+1);
			case Left:  return new GridLocation(x-1, y);
			default:    throw new InputMismatchException();
		}
	}

	/**
	 * Checks whether another GridLocation is directly adjacent to this one
	 * @param other
	 * @return
	 */
	public boolean adjacent(GridLocation other){
		return (other.x-this.x==1 || other.x-this.x==-1) && (other.y-this.y==1 || other.y-this.y==-1);
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
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + x;
	    result = prime * result + y;
	    return result;
     }

	@Override
	public boolean equals(Object obj) {
	    return obj instanceof GridLocation && ((GridLocation) obj).x == this.x && ((GridLocation) obj).y == this.y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
