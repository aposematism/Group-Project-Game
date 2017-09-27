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

	private List<Item> inventory;

	private ArmourSlots armour = new ArmourSlots();

	private Optional<Weapon> weapon;

	public Player(Image sprite, Direction direction){
		super(sprite, FULL_HEALTH, direction);
		inventory = new ArrayList<>();
	}

	public boolean possesses(Item item){
		if(weapon.isPresent() && item.equals(weapon))
			return true;
		for(Armour a: armour)
			if(item.equals(a))
				return true;
		return inventory.contains(item);
	}

	public void addItem(Item item) {
		if(item instanceof Weapon)
			weapon = Optional.of((Weapon)item);
		else if(item instanceof Armour)
			armour.set((Armour)item);
		else
			inventory.add(item);
	}

	@Override
	public void damage(int amount) {
		double armourTotal = 0;
		for(Armour a: armour)
			armourTotal += a.getRating();
		health -= amount * ((100 - armourTotal)/100);
	}

	public Iterator<Item> getInventory() { return inventory.iterator(); }

	public Optional<Weapon> getWeapon() { return this.weapon; }

	public ArmourSlots getArmour() { return this.armour; }

	public void clearInventory() { inventory.clear(); }

	@Override
	public boolean isPenetrable() { return true; }

	@Override
	public String toString() { return null; }

	public class ArmourSlots implements Iterable<Armour> {
		Armour[] slots = new Armour[Armour.TYPE.values().length];
		public boolean has(Armour.TYPE type) { return slots[type.ordinal()] != null; }
		public void set(Armour armour) { this.slots[armour.getSlot().ordinal()] = armour; }
		public Armour get(Armour.TYPE type) { return this.slots[type.ordinal()]; }

		@Override
		public Iterator<Armour> iterator() {
			return new Iterator<Armour>() {

				private int currentIndex = 0;

				@Override
				public boolean hasNext() {
					for(int i=currentIndex; i<slots.length; i++)
						if(slots[i]!=null)
							return true;
					return false;
				}

				@Override
				public Armour next() {
					for(int i=currentIndex; i<slots.length; i++)
						if(slots[i]!=null){
							currentIndex++;
							return slots[i];
						}
					return null;
				}
			};
		}
	}

	/**
	 * Doesn't do anything, can't interact with itself
	 * @param
	 */
	public void interact(GameContext context){}
}
