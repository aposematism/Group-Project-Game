package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.entity.items.Armour;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Weapon;

import java.util.*;

/**
 * Created by Mark on 30/09/2017.
 */
public class Inventory implements Iterable<Item> {

	private List<Item> items;

	private Armour[] armour;

	private Optional<Weapon> weapon;

	public Inventory(){
		items = new ArrayList<>();
		armour = new Armour[Armour.TYPE.values().length];
		weapon = Optional.empty();
	}

	public void add(Item item) {
		if(item instanceof Weapon)
			this.weapon = Optional.of((Weapon) item);
		else if(item instanceof Armour)
			this.armour[((Armour)item).getSlot().ordinal()] = (Armour) item;
		else
			this.items.add(item);
	}

	public void remove(Item item) {
		if(item instanceof Weapon)
			this.weapon = Optional.empty();
		else if(item instanceof Armour)
			this.armour[((Armour)item).getSlot().ordinal()] = null;
		else
			this.items.remove(item);
	}

	public boolean contains(Item item){
		if(weapon.isPresent() && item.equals(weapon.get()))
			return true;
		for(Armour a: armour)
			if(item.equals(a))
				return true;
		return this.items.contains(item);
	}

	public List<Item> getItems() { return new ArrayList<>(this.items); }


	public boolean hasArmour(Armour.TYPE type) { return armour[type.ordinal()] != null; }

	public Armour getArmour(Armour.TYPE type) { return this.armour[type.ordinal()]; }

	public Armour[] getArmour() { return armour; }


	public Optional<Weapon> getWeapon() { return this.weapon; }


	public Iterator<Item> iterator() { return this.items.iterator(); }

	public void clear(){
		this.weapon = Optional.empty();
		this.armour = new Armour[Armour.TYPE.values().length];
		this.items.clear();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getClass().getName());
		s.append(" ");
		if(weapon.isPresent()){
			s.append("{ ");
			s.append(weapon.get().toString());
			s.append(" } ");
		}
		for(Armour a: armour){
			if(a!=null){
				s.append("{ ");
				s.append(a.toString());
				s.append(" }");
			}
		}
		for(Item i: this){
			s.append("{ ");
			s.append(i.toString());
			s.append(" }");
		}
		return s.toString();
	}
}
