package entity;

import java.util.*;

import entity.*;
import entity.items.*;
import model.*;
import util.*;
import javafx.scene.image.Image;

/**
 *
 *
 * Created by Mark on 19/09/2017.
 */
public class Player extends Mob {

	private List<Item> inventory;

	private Armour[] armour = new Armour[Armour.TYPE.values().length];

	private Weapon weapon;

	public Player(GameContext context, Tile spawnPos, Image sprite, Direction direction){
		super(context, spawnPos, sprite, FULL_HEALTH, direction);
		inventory = new ArrayList<>();
	}

	public void addItem(Item item){
		inventory.add(item);
	}

	public boolean hasItem(Item item){
		if(item.equals(weapon))
			return true;
		for(Armour a: armour)
			if(item.equals(a))
				return true;
		return inventory.contains(item);
	}

	public void clearInventory(){
		inventory.clear();
	}

	public Iterator<Item> getInventory(){
		return inventory.iterator();
	}
	
	@Override
	public boolean isPenetrable() {
	    return true;
	}

	public boolean possesses(Item item){
		return inventory.contains(item);
	}

	/**
	 * Doesn't do anything, can't interact with itself
	 * @param
	 */
	public void interact(GameContext context){}

	@Override
	public String toString() {
		return null;
	}
}
