package pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import util.GridLocation;

/**
 * A path through a 2d grid.
 * @author dylan
 */
public class Path implements Iterable<GridLocation> {
	/**
	 * The locations that make up the path.
	 */
	public final List<GridLocation> parts;
	
	/**
	 * Creates a new graph path.
	 */
	public Path(Collection<GridLocation> parts) {
		this.parts = new ArrayList<GridLocation>(parts);
	}
	
	/**
	 * Gets the number of nodes in the path.
	 */
	public int getLength() {
		return this.parts.size();
	}

	@Override
	public Iterator<GridLocation> iterator() {
		return this.parts.iterator();
	}
}
