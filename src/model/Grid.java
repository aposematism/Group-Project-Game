package model;

import java.util.Optional;

import entity.Entity;
import util.GridLocation;

/**
 * A 2d rectangular grid that entities can be placed on.
 * @author dylan
 */
public class Grid implements Iterable<Entity> {
	private int width;
	private int height;
	
	/**
	 * An iterator over the entities in a grid.
	 * @author dylan
	 */
	public static class Iterator implements java.util.Iterator<Entity> {
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Entity next() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * Creates a new grid.
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Places an entity at a location.
	 */
	public void place(Entity entity, GridLocation location) {
		// FIXME: implement
	}
	
	/**
	 * Removes an entity from a location.
	 * @return The removed entity.
	 */
	public Optional<Entity> unplace(GridLocation location) {
		// FIXME: implement
		return null;
	}
	
	/**
	 * Moves the entity in one location to another location.
	 */
	public void move(GridLocation from, GridLocation to) {
		// FIXME: implement
	}

	/**
	 * Moves an entity to another location.
	 */
	public void move(Entity entity, GridLocation to) {
		Optional<GridLocation> location = locationOf(entity);
		if (!location.isPresent()) throw new IllegalArgumentException("entity is not in grid");

		move(location.get(), to);
	}
	
	/**
	 * Gets the location of an entity.
	 */
	public Optional<GridLocation> locationOf(Entity entity) {
		// FIXME: implement
		return null;
	}

	@Override
	public Iterator iterator() {
		// FIXME: implement
		return null;
	}
}
