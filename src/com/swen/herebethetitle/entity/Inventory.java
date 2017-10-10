package com.swen.herebethetitle.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Stores the Player's inventory
 *
 * Created by Mark on 30/09/2017.
 *
 * @author Mark Metcalfe
 */
public class Inventory implements Iterable<Item> {

	/**
	 * Items that aren't Armour or Weapons
	 */
	private List<Item> items;

	/**
	 * Armour slots, of which there are 4, one slot for each armour slot type
	 */
	private Armour[] armour;

	/**
	 * The current weapon
	 */
	private Optional<Weapon> weapon;

	public Inventory() { clear(); }

	/**
	 * Adds the given item to the appropriate collection/field
	 */
	protected void add(Item item) {
		if(item instanceof Weapon)
			this.weapon = Optional.of((Weapon) item);
		else if(item instanceof Armour)
			this.armour[((Armour)item).getSlot().ordinal()] = (Armour) item;
		else
			this.items.add(item);
	}

	/**
	 * Removes the given item from the appropriate collection/field
	 */
	protected void remove(Item item) {
		if(item instanceof Weapon)
			this.weapon = Optional.empty();
		else if(item instanceof Armour)
			this.armour[((Armour)item).getSlot().ordinal()] = null;
		else
			this.items.remove(item);
	}

	/**
	 * Checks if the given item is in any of the collections/fields
	 */
	protected boolean contains(Item item){
		if(weapon.isPresent() && item.equals(weapon.get()))
			return true;
		for(Armour a: armour)
			if(item.equals(a))
				return true;
		return this.items.contains(item);
	}


	/**
	 * Adds up the amount of present weapons, armour and items
	 */
	public int size() {
		int total = weapon.isPresent() ? items.size() + 1 : items.size();
		for (Armour a : armour) total = a != null ? total + 1 : total;
		return total;
	}

	/**
	 * Returns a copy of the Items list
	 * Forces the use of Inventories add/remove/contains methods
	 */
	public List<Item> getItems() { return new ArrayList<>(this.items); }

	/**
	 * Checks if the inventory has armour of a specific slot type
	 */
	public boolean hasArmour(Armour.TYPE type) { return armour[type.ordinal()] != null; }

	/**
	 * Gets the armour from the specified slot type
	 */
	public Armour getArmour(Armour.TYPE type) { return this.armour[type.ordinal()]; }

	/**
	 * Returns the whole armour array, for iterating through
	 */
	public Armour[] getArmour() { return armour; }

	/**
	 * Returns the (optional) weapon
	 */
	public Optional<Weapon> getWeapon() { return this.weapon; }

	/**
	 * The default iterator for the items
	 */
	public Iterator<Item> iterator() { return this.items.iterator(); }

	/**
	 * Reset the inventory
	 */
	protected void clear(){
		this.weapon = Optional.empty();
		this.armour = new Armour[Armour.TYPE.values().length];
		this.items  = new ArrayList<>();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getClass().getSimpleName());

		weapon.ifPresent(weapon->{
			s.append(" { ");
			s.append(weapon.toString());
			s.append(" }");
		});

		for(Armour a: armour){
			if(a!=null){
				s.append(" { ");
				s.append(a.toString());
				s.append(" }");
			}
		}

		for(Item i: this.items){
			s.append(" { ");
			s.append(i.toString());
			s.append(" }");
		}

		return s.toString();
	}
}