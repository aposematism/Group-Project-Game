package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.items.Item;
import org.junit.Test;
import static org.junit.Assert.*;
import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;

import java.util.*;


/**
 * Created by Mark on 19/09/2017.
 */
public class PlayerTests {
	@Test
	public void test_addItem_1(){
		Player p = new Player(null, Direction.Down);
		Item i = new Item(null);
		p.addItem(i);
		assertEquals(1, p.getInventory().size());
	}

	@Test
	public void test_addWeapon_1(){
		Player p = new Player(null, Direction.Down);
		Item i = new Weapon(null, 3.5);
		p.addItem(i);
		assertEquals(0, p.getInventory().size());
		assertTrue(p.getWeapon().isPresent());
	}

	@Test
	public void test_addArmour_1(){
		Player p = new Player(null, Direction.Down);
		Item i = new Armour(null, Armour.TYPE.BOOTS, 3.5);
		p.addItem(i);
		assertEquals(0, p.getInventory().size());
		assertTrue(p.getArmour().has(Armour.TYPE.BOOTS));
	}

	@Test
	public void test_itemEquals_1(){
			Item i1 = new Item(null);
		Item i2 = new Item(null);

		assertFalse(i1.equals(i2));
	}

	@Test
	public void test_posseses_item_1(){
		Player p = new Player(null, Direction.Down);
		Item i = new Item(null);
		p.addItem(i);
		assertTrue(p.possesses(i));
	}

	@Test
	public void test_posseses_weapon_1(){
		Player p = new Player(null, Direction.Down);
		Item i = new Weapon(null, 3.5);
		p.addItem(i);
		assertTrue(p.possesses(i));
	}

	@Test
	public void test_posseses_armour_1(){
		Player p = new Player(null, Direction.Down);
		Item i = new Armour(null, Armour.TYPE.BOOTS, 3.5);
		p.addItem(i);
		assertTrue(p.possesses(i));
	}

	@Test
	public void test_damage_noArmour_1(){
		Player p = new Player(null, Direction.Down);
		p.damage(20);
		assertEquals(Mob.FULL_HEALTH-20, p.getHealth(), 0);
	}

	@Test
	public void test_damage_armour_1(){
		Player p = new Player(null, Direction.Down);
		Armour a = new Armour(null, Armour.TYPE.HELMET, 3.5);
		p.addItem(a);
		p.damage(20);
		assertTrue(p.getHealth()>Mob.FULL_HEALTH-20);
		assertTrue(p.getHealth()<Mob.FULL_HEALTH);
	}

	@Test
	public void test_damage_armour_2(){
		Player p = new Player(null, Direction.Down);
		Armour a = new Armour(null, Armour.TYPE.HELMET, 3.5);
		Armour b = new Armour(null, Armour.TYPE.TORSO, 6);
		Armour c = new Armour(null, Armour.TYPE.BOOTS, 1.75);
		p.addItem(a, b, c);
		p.damage(20);
		assertTrue(p.getHealth()>Mob.FULL_HEALTH-20);
		assertTrue(p.getHealth()<Mob.FULL_HEALTH);
	}

	@Test
	public void test_getInventory_1(){
		Player p = new Player(null, Direction.Down);

		Item a = new Item(null);
		Item b = new Item(null);
		Item c = new Item(null);

		p.addItem(a,b,c);

		List<Item> items = p.getInventory();

		assertEquals(a, items.get(0));
		assertEquals(b, items.get(1));
		assertEquals(c, items.get(2));
	}

	@Test
	public void test_getWeapon_1(){
		Player p = new Player(null, Direction.Down);

		Item a = new Weapon(null, 5);

		p.addItem(a);

		assertTrue(p.getWeapon().isPresent());
		assertEquals(a, p.getWeapon().get());
	}

	@Test
	public void test_clearInventory_1(){
		Player p = new Player(null, Direction.Down);

		Item a = new Item(null);
		Item b = new Item(null);
		Item c = new Item(null);

		assertEquals(0, p.getInventory().size());

		p.addItem(a,b,c);

		assertEquals(3, p.getInventory().size());

		p.clearInventory();

		assertEquals(0, p.getInventory().size());
	}

	@Test
	public void test_isPenetrable(){
		Player p = new Player(null, Direction.Down);
		assertFalse(p.isPenetrable());
	}

	@Test
	public void test_toString(){
		Player p = new Player(null, Direction.Down);
		assertNotNull(p.toString());
	}

	@Test
	public void test_ArmourSlots_has(){
		Player p = new Player(null, Direction.Down);

		Armour a = new Armour(null, Armour.TYPE.BOOTS, 0);
		Armour b = new Armour(null, Armour.TYPE.HELMET, 0);
		Armour c = new Armour(null, Armour.TYPE.TORSO, 0);

		p.addItem(a,b,c);

		assertTrue(p.getArmour().has(Armour.TYPE.BOOTS));
		assertTrue(p.getArmour().has(Armour.TYPE.HELMET));
		assertTrue(p.getArmour().has(Armour.TYPE.TORSO));
		assertFalse(p.getArmour().has(Armour.TYPE.LEGS));
	}

	@Test
	public void test_ArmourSlots_get(){
		Player p = new Player(null, Direction.Down);

		Armour a = new Armour(null, Armour.TYPE.BOOTS, 0);
		Armour b = new Armour(null, Armour.TYPE.HELMET, 0);
		Armour c = new Armour(null, Armour.TYPE.TORSO, 0);

		p.addItem(a,b,c);

		assertEquals(a, p.getArmour().get(Armour.TYPE.BOOTS));
		assertEquals(b, p.getArmour().get(Armour.TYPE.HELMET));
		assertEquals(c, p.getArmour().get(Armour.TYPE.TORSO));
		assertNull(p.getArmour().get(Armour.TYPE.LEGS));
	}

	@Test
	public void test_ArmourSlots_iterator_1(){
		Player p = new Player(null, Direction.Down);

		Armour a = new Armour(null, Armour.TYPE.BOOTS, 0);
		Armour b = new Armour(null, Armour.TYPE.HELMET, 0);
		Armour c = new Armour(null, Armour.TYPE.TORSO, 0);

		p.addItem(a,b,c);

		Player.ArmourSlots slots = p.getArmour();

		for(Armour armour: slots){
			assertNotNull(armour);
		}
	}
}
