package pathfinding.tests;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import entity.Obstacle;
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
        TestGrid grid = new TestGrid(5, 5);
        Tile source = grid.get(0, 0);
        Tile dest = grid.get(4, 4);
        Tile midpoint = grid.get(2, 2);
        midpoint.setMapEntity(new Obstacle(midpoint.getLocation(), null));
        
        verifyPath(grid, source, dest,
                new GridLocation(0, 0), new GridLocation(1, 1), new GridLocation(2, 1),
                new GridLocation(3, 2), new GridLocation(4, 3), new GridLocation(4, 4));
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
