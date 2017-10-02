package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.logic.exceptions.EntityOutOfRange;
import com.swen.herebethetitle.util.GridLocation;

public class InventoryTests extends BaseTest {
    @Test
    public void ensureCanOnlyPickUpNeighbouringItems() {
        GridLocation playerLocation = new GridLocation(3,3);
        teleportPlayer(playerLocation);

        List<Key> keys = playerLocation.neighbours().stream()
                .map(l -> placeKey(l))
                .collect(Collectors.toList());
        for (Key key : keys) {
            try {
                logic.pickup(key);
            } catch (EntityOutOfRange e) {
                fail("all keys should be in range");
            }
        }
    }
    
    @Test
    public void ensureCanNotPickUpNonNeighbouringItems() {
        GridLocation playerLocation = new GridLocation(3,3);
        teleportPlayer(playerLocation);
        
        Key closeKey = placeKey(new GridLocation(4,3));
        Key farKey = placeKey(new GridLocation(5, 3));

        try {
            logic.pickup(closeKey);
        } catch (EntityOutOfRange e) {
            fail("should be able to pick up close key");
        }
        
        try {
            logic.pickup(farKey);
            fail("should not be abe to pick up far away key");
        } catch (EntityOutOfRange e) {
            assertEquals(e.entity, farKey);
            assertEquals("entity out of range", e.getMessage());
        }
    }
}
