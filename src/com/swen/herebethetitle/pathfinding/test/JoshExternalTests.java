package com.swen.herebethetitle.pathfinding.test;

import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.pathfinding.Path;
import com.swen.herebethetitle.util.GridLocation;
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
}
