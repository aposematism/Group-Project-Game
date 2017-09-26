package pathfinding.tests;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import org.junit.Test;

import entity.Obstacle;
import model.Tile;
import pathfinding.Graph;
import pathfinding.Path;
import pathfinding.PathfindingGrid;
import util.GridLocation;

public class BasicTests {
    @Test
    public void canFindOptimalPathWhenSimpleDiagonalLine() {
        BasicGrid grid = new BasicGrid(5, 5);
        Tile source = grid.get(0, 0);
        Tile dest = grid.get(4, 4);
        
        verifyPath(grid, source, dest,
                new GridLocation(0, 0), new GridLocation(1, 1), new GridLocation(2, 2),
                new GridLocation(3, 3), new GridLocation(4, 4));
    }
    
    @Test
    public void canRouteAroundImpenetrableObstacles() {
        BasicGrid grid = new BasicGrid(5, 5);
        Tile source = grid.get(0, 0);
        Tile dest = grid.get(4, 4);
        Tile midpoint = grid.get(2, 2);
        midpoint.setMapEntity(new Obstacle(midpoint.getLocation(), null));
        
        verifyPath(grid, source, dest,
                new GridLocation(0, 0), new GridLocation(1, 1), new GridLocation(2, 1),
                new GridLocation(3, 2), new GridLocation(4, 3), new GridLocation(4, 4));
    }
    
    /**
     * Verifies that the pathfinder returns a path between two tiles.
     */
    protected void verifyPath(PathfindingGrid grid, Tile source, Tile dest, GridLocation ...expectedPathParts) {
        Graph graph = new Graph(grid, source, dest);

        Path expectedPath = new Path(Arrays.asList(expectedPathParts));
        Optional<Path> actualPath = graph.findPath();
        
        if (!actualPath.isPresent())
            fail(String.format("expected '%s' but no path was possible", expectedPath));
        
        assertEquals(expectedPath, actualPath.get());
    }
}
