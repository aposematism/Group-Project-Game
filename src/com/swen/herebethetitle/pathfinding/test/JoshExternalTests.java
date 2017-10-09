package com.swen.herebethetitle.pathfinding.test;

import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.pathfinding.Path;
import com.swen.herebethetitle.util.GridLocation;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.fail;

public class JoshExternalTests extends Base {
    /**
     * Looks like this
     * -----------
     * |A W      |
     * |W        |
     * |         |
     * |      B  |
     * |         |
     * -----------
     * This is possible by how the path finder is designed to navigate
     * diagonal cells.
     */
    @Test
    public void MovingThroughDiagonalWalls() {
        Region region = new Region(5, 5);
        Tile source = region.get(0, 0);
        Tile dest = region.get(4, 4);

        region.get(1,0).add(new Static("wall", null));
        region.get(0, 1).add(new Static("wall", null));
        //region.get(1, 1).add(new Static("wall", null));

        try {
            verifyUnreachable(region, source, dest);
        }catch(AssertionError e){ return; }
        fail("Path certainly IS reachable");
    }


    /**
     * Looks like this
     * -----------
     * |         |
     * |A W      |
     * |  W      |
     * |  W      |
     * |  B      |
     * -----------
     * Shortest path is to move down 2 steps then down-right one.
     */
    @Test
    @Ignore
    public void shortestPath_simple() {
        Region region = new Region(5, 5);
        Tile source = region.get(0, 1);
        Tile dest = region.get(1, 4);
        region.get(1,1).add(new Static("wall", null));
        region.get(1, 2).add(new Static("wall", null));
        region.get(1, 3).add(new Static("wall", null));

        verifyPath(region, source, dest,
                new GridLocation(0, 1), new GridLocation(0, 2), new GridLocation(0, 3),
                new GridLocation(1, 4));
    }

    /**
     * Looks like this
     * -----------
     * |      W  |
     * |A W   W B|
     * |  W      |
     * |  W   W  |
     * |      W  |
     * -----------
     * The shortest path in this scenario is to go up-right, down-right,
     * down-right and up-right in four moves, as opposed to 6 moves if it
     * decides to go the other way.
     *
     * TODO: this seems to take an unnecessary move to the right from 1,0 to 2,0
     */
    @Test
    @Ignore
    public void shortestPath_hard() {
        Region region = new Region(5, 5);
        Tile source = region.get(0, 1);
        Tile dest = region.get(4, 1);
        region.get(1,1).add(new Static("wall", null));
        region.get(1, 2).add(new Static("wall", null));
        region.get(1, 3).add(new Static("wall", null));
        region.get(3, 4).add(new Static("wall", null));
        region.get(3, 3).add(new Static("wall", null));
        region.get(3, 1).add(new Static("wall", null));
        region.get(3, 0).add(new Static("wall", null));

        verifyPath(region, source, dest,
                new GridLocation(0, 1), new GridLocation(1, 0), new GridLocation(2, 1),
                new GridLocation(3, 2), new GridLocation(4, 1));
    }

}
