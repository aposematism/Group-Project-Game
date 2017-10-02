package com.swen.herebethetitle.pathfinding.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.swen.herebethetitle.entity.stationeries.Stationary;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;

public class BasicTests extends Base {
    @Test
    public void canFindOptimalPathWhenSimpleDiagonalLine() {
        Region region = new Region(5, 5);
        Tile source = region.get(0, 0);
        Tile dest = region.get(4, 4);
        
        verifyPath(region, source, dest,
                new GridLocation(0, 0), new GridLocation(1, 1), new GridLocation(2, 2),
                new GridLocation(3, 3), new GridLocation(4, 4));
    }
    
    @Test
    public void canRouteAroundImpenetrableObstacles() {
        Region region = new Region(5, 5);
        Tile source = region.get(0, 0);
        Tile dest = region.get(4, 4);
        Tile midpoint = region.get(2, 2);
        midpoint.add(new Stationary("wall", null));
        
        verifyPath(region, source, dest,
                new GridLocation(0, 0), new GridLocation(1, 1), new GridLocation(2, 1),
                new GridLocation(3, 2), new GridLocation(4, 3), new GridLocation(4, 4));
    }

    /**
     * Looks like
     * 
     * |-----------|
     * | A   w     |
     * |     w     |
     * | w w w w w |
     * |     w   B |
     * |-----------|
     * 
     * No path is possible.
     */
    @Test
    public void returnsNoPathWhenUnreachable() {
        Region region = new Region(5, 5);
        Tile source = region.get(0, 0);
        Tile dest = region.get(4, 4);

        // Draw two lines right through the map to cut the dest off.
        {
            for (int y=0; y<region.height; y++) {
				region.get(2, y).add(new Stationary("wall", null));
            }
            for (int x=0; x<region.width; x++) {
				region.get(x, 2).add(new Stationary("wall", null));
            }
        }
        
        verifyUnreachable(region, source, dest);
    }
    
    // There was a point where routing was indeterministic because hashCode was not implemented
    // on Tile.
    @Test
    public void impenetrableRoutingIsDeterministic() {
        for (int i=0; i<4; i++) {
            // Quick pause in case we have some kind of time dependency.
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) { }

            canRouteAroundImpenetrableObstacles();
        }
    }
}
