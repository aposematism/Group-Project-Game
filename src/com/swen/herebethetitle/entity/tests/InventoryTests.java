package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.Inventory;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Armour;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Weapon;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 */
public class InventoryTests {
	@Test
	public void test_armour(){
		Player p = new Player(null, Direction.Down);
		Inventory i = p.inventory();

		Armour a = new Armour(null, Armour.TYPE.BOOTS, 0);
		Armour b = new Armour(null, Armour.TYPE.HELMET, 0);
		Armour c = new Armour(null, Armour.TYPE.TORSO, 0);

		p.add(a,b,c);

		assertTrue(i.hasArmour(Armour.TYPE.BOOTS));
		assertTrue(i.hasArmour(Armour.TYPE.HELMET));
		assertTrue(i.hasArmour(Armour.TYPE.TORSO));
		assertFalse(i.hasArmour(Armour.TYPE.LEGS));

		assertEquals(a, i.getArmour(Armour.TYPE.BOOTS));
		assertEquals(b, i.getArmour(Armour.TYPE.HELMET));
		assertEquals(c, i.getArmour(Armour.TYPE.TORSO));
		assertNull(i.getArmour(Armour.TYPE.LEGS));
	}

	@Test
	public void test_weapon(){
		Player p = new Player(null, Direction.Down);
		Inventory i = p.inventory();

		Weapon w = new Weapon(null, 3);

		p.add(w);

		assertTrue(i.hasWeapon());

		assertEquals(w, i.getWeapon());

		assertTrue(i.contains(w));
	}

	@Test
	public void test_items(){
		Player p = new Player(null, Direction.Down);
		Inventory i = p.inventory();

		Item a = new Item(null);
		Item b = new Item(null);
		Item c = new Item(null);
		Item d = new Item(null);

		p.add(a,b,c,d);

		assertEquals(4, i.getItems().size());

		assertEquals(a, i.getItems().get(0));
		assertEquals(b, i.getItems().get(1));
		assertEquals(c, i.getItems().get(2));
		assertEquals(d, i.getItems().get(3));

		assertTrue(i.contains(a));
		assertTrue(i.contains(b));
		assertTrue(i.contains(c));
		assertTrue(i.contains(d));
	}

	@Test
	public void test_clearInventory(){
		Player p = new Player(null, Direction.Down);

		Item a = new Item(null);
		Item b = new Item(null);
		Item c = new Item(null);

		assertEquals(0, p.inventory().getItems().size());

		p.add(a,b,c);

		assertEquals(3, p.inventory().getItems().size());

		p.inventory().clear();

		assertEquals(0, p.inventory().getItems().size());
	}
}
