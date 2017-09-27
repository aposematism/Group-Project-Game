package com.swen.herebethetitle.entity.items;

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
        item.getTile().remove(item);
        item.player().addItem(item);
    }

    @Override
    public void use(Item item) {}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Key && ((Key)obj).KEY==this.KEY;
	}
}
