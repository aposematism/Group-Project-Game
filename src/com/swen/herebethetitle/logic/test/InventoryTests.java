package com.swen.herebethetitle.logic.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import com.swen.herebethetitle.entity.Key;
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
                logic.interact(key);
            } catch (EntityOutOfRange e) {
                fail("all keys should be in range");
            }
        }
    }
}
