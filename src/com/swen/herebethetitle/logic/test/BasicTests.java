package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.swen.herebethetitle.logic.exceptions.InvalidDestination;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

public class BasicTests extends BaseTest {
    @Test
    public void cannotMovePlayerOutOfBounds() {
        try {
            logic.movePlayer(Direction.Left);
            fail("should not be able to move out of bounds");
        } catch (InvalidDestination e) {
            assertEquals("direction is out of bounds", e.getMessage());
        }
    }
    
    @Test
    public void cannotMovePlayerOntoImpenetrableObstacle() {
        placeImpenetrableObject(new GridLocation(1, 0));
        try {
            logic.movePlayer(Direction.Right);
            fail("should not be able to move onto impenetrable object");
        } catch (InvalidDestination e) {
            assertEquals("an obstacle is in the way", e.getMessage());
        }
    }
    
    @Test
    public void canMovePlayerOntoAdjacentEmptyTiles() {
        placeImpenetrableObject(new GridLocation(1, 0));
        try {
            logic.movePlayer(Direction.Down);
            logic.movePlayer(Direction.Right);
            logic.movePlayer(Direction.Right);
            logic.movePlayer(Direction.Up);
        } catch (InvalidDestination e) {
            fail("should have been able to move onto location");
        }
    }
}
