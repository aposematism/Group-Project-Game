package pathfinding.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Optional;

import model.Tile;
import pathfinding.Graph;
import pathfinding.Path;
import pathfinding.PathfindingGrid;
import util.GridLocation;

public class Base {
    /**
     * Verifies that the pathfinder returns a path between two tiles.
     */
    protected void verifyPath(PathfindingGrid grid, Tile source, Tile dest, GridLocation ...expectedPathParts) {
        Path expectedPath = new Path(Arrays.asList(expectedPathParts));
        Optional<Path> actualPath = getPath(grid, source, dest);
        
        if (!actualPath.isPresent())
            fail(String.format("expected '%s' but no path was possible", expectedPath));
        
        assertEquals(expectedPath, actualPath.get());
    }
    
    protected void verifyUnreachable(PathfindingGrid grid, Tile source, Tile dest) {
        Optional<Path> actualPath = getPath(grid, source, dest);
        if (actualPath.isPresent())
            fail("there should be no path between the source and destination");
    }
    
    private Optional<Path> getPath(PathfindingGrid grid, Tile source, Tile dest) {
        Graph graph = new Graph(grid, source, dest);

        Optional<Path> actualPath = graph.findPath();
        return actualPath;
    }
}
