package pathfinding.tests;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import entity.Terrain;
import model.GameContext;
import model.Tile;
import util.GridLocation;

public class BasicTests extends Base {
    @Test
    public void canFindOptimalPathWhenSimpleDiagonalLine() {
        TestGrid grid = new TestGrid(5, 5);
        Tile source = grid.get(0, 0);
        Tile dest = grid.get(4, 4);
        
        verifyPath(grid, source, dest,
                new GridLocation(0, 0), new GridLocation(1, 1), new GridLocation(2, 2),
                new GridLocation(3, 3), new GridLocation(4, 4));
    }
    
    @Test
    public void canRouteAroundImpenetrableObstacles() {
        GameContext dummyContext = new GameContext();
        TestGrid grid = new TestGrid(5, 5);
        Tile source = grid.get(0, 0);
        Tile dest = grid.get(4, 4);
        Tile midpoint = grid.get(2, 2);
        midpoint.setMapTerrain(new Terrain(dummyContext, midpoint, null));
        
        verifyPath(grid, source, dest,
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
        TestGrid grid = new TestGrid(5, 5);
        Tile source = grid.get(0, 0);
        Tile dest = grid.get(4, 4);
        GameContext dummyContext = new GameContext();

        // Draw two lines right through the map to cut the dest off.
        {
            for (int y=0; y<grid.height; y++) {
                Tile tile = grid.get(2, y);
                tile.setMapTerrain(new Terrain(dummyContext, tile, null));
            }
            for (int x=0; x<grid.width; x++) {
                Tile tile = grid.get(x, 2);
                tile.setMapTerrain(new Terrain(dummyContext, tile, null));
            }
        }
        
        verifyUnreachable(grid, source, dest);
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
