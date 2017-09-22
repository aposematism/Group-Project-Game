package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.image.ImageView;
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

	public Player(GridLocation spawnPos, ImageView sprite, int startingHealth, Direction direction){
		super(spawnPos, startingHealth, direction, sprite);
		inventory = new ArrayList<>();
	}

	public Player(GridLocation spawnPos, ImageView sprite, Direction direction) {
		this(spawnPos, sprite, FULL_HEALTH, direction);
	}


	public void addItem(Item item){
		//TODO
	}

	public void addWeapon(Weapon weapon){
		//TODO
	}

	public void addArmour(Armour armour){
		//TODO
	}

	public void clearInventory(){
		inventory.clear();
	}

	public Iterator<Item> getInventory(){
		return inventory.iterator();
	}

	/**
	 * Doesn't do anything, can't interact with itself
	 * @param player
	 */
	public void interact(Player player){}
}
