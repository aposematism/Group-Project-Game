package entity;

import graph.Node;

import java.util.*;

/**
 * Created by Mark on 19/09/2017.
 */
public class Player extends Mob {

	private List<Item> inventory;

	private Armour[] armour = new Armour[Armour.TYPE.values().length];

	private Weapon weapon;

	public Player(Node spawnPos, int startingHealth, Direction direction){
		super(spawnPos, startingHealth, direction);
		inventory = new ArrayList<>();
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

	public void move(){
		//TODO
	}

	public void die(){
		//TODO
	}

	/**
	 * Doesn't do anything, can't interact with itself
	 * @param player
	 */
	public void interact(Player player){}
}
