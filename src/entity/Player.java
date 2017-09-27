package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.items.Armour;
import entity.items.Item;
import entity.items.Weapon;
import javafx.scene.image.Image;
import model.GameContext;
import util.Direction;
import util.GridLocation;

/**
 *
 *
 * Created by Mark on 19/09/2017.
 */
public class Player extends Mob {

	private List<Item> inventory;

	private Armour[] armour = new Armour[Armour.TYPE.values().length];

	private Weapon weapon;

	public Player(GameContext context, GridLocation spawnPos, Image sprite, Direction direction){
		super(context, spawnPos, sprite, FULL_HEALTH, direction);
		inventory = new ArrayList<>();
	}

	public void addItem(Item item){
		inventory.add(item);
	}

	public boolean hasItem(Item item){
		if(item instanceof Weapon){
			if(item.equals(weapon))
				return true;
		}
		if(item instanceof Armour){
			for(Armour a: armour){
				if(item.equals(a))
					return true;
			}
		}
		return inventory.contains(item);
	}

	public void clearInventory(){
		inventory.clear();
	}

	public Iterator<Item> getInventory(){
		return inventory.iterator();
	}

	public boolean possesses(Item item){
		return inventory.contains(item);
	}

	/**
	 * Doesn't do anything, can't interact with itself
	 * @param player
	 */
	public void interact(Player player){}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public boolean isPenetrable(){
		return false;
	}
}
