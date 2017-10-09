package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 *
 * @author Mark Metcalfe
 */
public class InventoryTests {

	/**
	 * Asserts that armour is correctly added and removed from the inventory
	 */
	@Test
	public void test_armour(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();
		Inventory i = p.inventory();

		Armour a = new Armour("", null, Armour.TYPE.BOOTS, 0);
		Armour b = new Armour("", null, Armour.TYPE.HELMET, 0);
		Armour c = new Armour("", null, Armour.TYPE.TORSO, 0);

		a.interact(context);
		b.interact(context);
		c.interact(context);

		assertTrue(i.hasArmour(Armour.TYPE.BOOTS));
		assertTrue(i.hasArmour(Armour.TYPE.HELMET));
		assertTrue(i.hasArmour(Armour.TYPE.TORSO));
		assertFalse(i.hasArmour(Armour.TYPE.LEGS));

		assertTrue(p.possesses(a));
		assertTrue(p.possesses(b));
		assertTrue(p.possesses(c));

		assertEquals(a, i.getArmour(Armour.TYPE.BOOTS));
		assertEquals(b, i.getArmour(Armour.TYPE.HELMET));
		assertEquals(c, i.getArmour(Armour.TYPE.TORSO));
		assertNull(i.getArmour(Armour.TYPE.LEGS));
	}

	/**
	 * Asserts that weapons are correctly added and removed from the inventory
	 */
	@Test
	public void test_weapon(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();
		Inventory i = p.inventory();

		Weapon w = new Weapon("", null, true, 3);

		w.interact(context);

		assertTrue(i.getWeapon().isPresent());

		assertEquals(w, i.getWeapon().get());

		assertTrue(p.possesses(w));
	}

	/**
	 * Asserts that items are correctly added and removed from the inventory
	 */
	@Test
	public void test_items(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();
		Inventory i = p.inventory();

		Item a = new Key("", null,0);
		Item b = new Key("", null,0);
		Item c = new Key("", null,0);
		Item d = new Key("", null,0);

		a.interact(context);
		b.interact(context);
		c.interact(context);
		d.interact(context);

		assertEquals(4, i.size());

		assertEquals(a, i.getItems().get(0));
		assertEquals(b, i.getItems().get(1));
		assertEquals(c, i.getItems().get(2));
		assertEquals(d, i.getItems().get(3));

		assertTrue(p.possesses(a));
		assertTrue(p.possesses(b));
		assertTrue(p.possesses(c));
		assertTrue(p.possesses(d));
	}

	/**
	 * Asserts that the inventory is cleared correctly
	 */
	@Test
	public void test_clearInventory(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();
		Inventory i = p.inventory();

		Item a = new Key("", null,0);
		Item b = new Key("", null,0);
		Item c = new Key("", null,0);

		assertEquals(0, p.inventory().getItems().size());

		a.interact(context);
		b.interact(context);
		c.interact(context);

		assertEquals(3, p.inventory().getItems().size());
	}

	/**
	 * Asserts that the inventory is cleared correctly
	 */
	@Test
	public void test_size(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();
		Inventory i = p.inventory();

		Item a = new Key("", null,0);
		Item b = new Armour("", null, Armour.TYPE.BOOTS, 0);
		Item c = new Weapon("", null, true, 0);

		assertEquals(0, i.size());

		a.interact(context);
		b.interact(context);
		c.interact(context);

		assertEquals(3, i.size());
	}
}
