package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.image.Image;
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

	public Player(GridLocation spawnPos, Image sprite, int startingHealth, Direction direction){
		super(spawnPos, startingHealth, direction, sprite);
		inventory = new ArrayList<>();
	}

	public Player(GridLocation spawnPos, Image sprite, Direction direction) {
		this(spawnPos, sprite, FULL_HEALTH, direction);
	}

	public void addItem(Item item){
		inventory.add(item);
	}

	private void addWeapon(Weapon weapon){
		weapon.setLocation(GridLocation.OFF_GRID);
		if(this.weapon==null || weapon.getStrength()>this.weapon.getStrength())
			this.weapon = weapon;
	}

	private void addArmour(Armour armour){
		armour.setLocation(GridLocation.OFF_GRID);
		if(this.armour[armour.getSlot().ordinal()]==null)
			this.armour[armour.getSlot().ordinal()] = armour;
		else if(armour.getRating() > this.armour[armour.getSlot().ordinal()].getRating())
			this.armour[armour.getSlot().ordinal()] = armour;
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
}
