package com.swen.herebethetitle.entity;

import java.util.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;

import javafx.scene.image.Image;

/**
 *
 *
 * Created by Mark on 19/09/2017.
 */
public class Player extends Mob {

	private Inventory inventory;

	public Player(Image sprite, Direction direction){
		super(sprite, FULL_HEALTH, direction);
		inventory = new Inventory();
	}

	public void add(Item... items) {
		for(Item item: items)
			inventory.add(item);
	}

	public void remove(Item... items) {
		for(Item item: items)
			inventory.remove(item);
	}

	public boolean possesses(Item... items){
		for(Item item: items){
			if(inventory.contains(item))
				return true;
		}
		return false;
	}

	public Inventory inventory() { return inventory; }

	@Override
	public void damage(double amount) {
		double armourTotal = 0;
		for(Armour a: inventory.getArmour())
			if(a!=null)
				armourTotal += a.getRating();
		super.damage(amount * ((100 - armourTotal)/100));
	}

	@Override
	public boolean isPenetrable() { return false; }

	@Override
	public String toString() { return null; }

	/**
	 * Doesn't do anything, Player can't interact with itself
	 * @param
	 */
	public void interact(GameContext context){}
}
