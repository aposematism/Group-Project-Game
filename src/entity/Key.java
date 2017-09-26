package entity;

import javafx.scene.image.Image;
import util.*;

/**
 * Created by metcalmark on 27/09/17.
 */
public class Key extends Item {

    private final int KEY;

    public Key(Player player, GridLocation spawnLocation, Image sprite, int key){
        super(player, spawnLocation, sprite);
        KEY = key;
        setActions(new Actions() {
            @Override
            public void pickup(Item item) {
                player.addItem(item);
            }

            @Override
            public void use(Item item) {

            }
        });
    }
}
