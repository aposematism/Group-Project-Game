package entity.items;

import util.*;

/**
 * Created by metcalmark on 27/09/17.
 */
public class Key implements Item.Actions {

    private final int KEY;

    public Key(int key){
        this.KEY = key;
    }

    @Override
    public void pickup(Item item) {
        item.setLocation(GridLocation.OFF_GRID);
        item.player().addItem(item);
    }

    @Override
    public void use(Item item) {

    }
}
