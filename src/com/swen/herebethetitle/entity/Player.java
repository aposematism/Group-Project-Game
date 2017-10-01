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

	private int wallet;

	public static final double DEFAULT_DAMAGE = 5;

	public Player(Image sprite, double health, int wallet, Direction direction){
		super(sprite, health, direction);
		this.wallet = wallet;
		inventory = new Inventory();
	}

	public Player(Image sprite, Direction direction){
		this(sprite, FULL_HEALTH, 0, direction);
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

	public int getWallet() { return this.wallet; }

	public void addFunds(int amount) { this.wallet += amount; }

	public void removeFunds(int amount) { this.wallet -= amount; }

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
	public String toString() {
		return super.toString()+" "+wallet+" "+inventory.toString();
	}

	/**
	 * Doesn't do anything, Player can't interact with itself
	 * @param
	 */
	public void interact(GameContext context){}
}
