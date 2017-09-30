package com.swen.herebethetitle.pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.swen.herebethetitle.util.GridLocation;

/**
 * A path through a 2d grid.
 * 
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
    
    @Override
    public String toString() {
        List<String> parts = new ArrayList<String>();
        for (GridLocation location : this)
            parts.add(location.toString());

        return String.join(" -> ", parts);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Path)) return false;
        Path rhs = (Path)obj;

        if (this.parts.size() != rhs.parts.size()) return false;
        
        for (int i=0; i<this.parts.size(); i++) {
            if (!this.parts.get(i).equals(rhs.parts.get(i)))
                return false;
        }
        
        return true;
    }
}
