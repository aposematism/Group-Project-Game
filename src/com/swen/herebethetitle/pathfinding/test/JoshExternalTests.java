package com.swen.herebethetitle.pathfinding.test;

import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;
import org.junit.Test;

public class JoshExternalTests extends Base {
    /**
     * Looks like this
     * -----------
     * |A w      |
     * |W        |
     * |         |
     * |      B  |
     * |         |
     * -----------
     *
     * On order to get from A to B, we must follow the entire left wall, follow the entire
     * bottom wall, follow the entire right wall, then traverse left until we get to B.
     */
    @Test
    public void canRouteAroundGridBoundaryWhenForced() {
        Region region = new Region(5, 5);
        Tile source = region.get(0, 0);
        Tile dest = region.get(4, 4);

        region.get(1,0).add(new Static("wall", null));
        region.get(0, 1).add(new Static("wall", null));

        verifyUnreachable(region, source, dest);

    }
}
