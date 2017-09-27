package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;

/**
 * Created by metcalmark on 27/09/17.
 */
public class Key implements Item.Actions {

    private final int KEY;

    public Key(int key){
        this.KEY = key;
    }

    @Override
    public void pickup(Item item, GameContext context) {
        context.getCurrentRegion().remove(item);
        context.getPlayer().addItem(item);
    }

    @Override
    public void use(Item item, GameContext context) {}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Key && ((Key)obj).KEY==this.KEY;
	}
}
