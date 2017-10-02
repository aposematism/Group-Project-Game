package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.statics.*;
import com.swen.herebethetitle.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mark on 1/10/2017.
 *
 * @author Mark Metcalfe
 */
public class MiscTests {

	/**
	 * Asserts Floor is penetrable and that it has a string representation
	 */
	@Test
	public void test_Floor(){
		Floor f = new Floor(null);
		assertTrue(f.isPenetrable());
		assertNotNull(f.toString());
		System.out.println(f.toString());
	}

	/**
	 * Asserts Door has a string representation
	 */
	@Test
	public void test_Door_toString(){
		Door d = new Door(123, Door.STATE.LOCKED);
		Static s = new Static("", null);
		s.setBehavior(d);
		assertNotNull(d.toString());
		assertNotNull(s.toString());
		System.out.println(d.toString());
		System.out.println(s.toString());
	}

	/**
	 * Asserts Inventory has a string representation
	 */
	@Test
	public void test_Inventory_toString(){
		Inventory i = new Inventory();
		i.add(new Weapon("Bronze Sword", null,true,6.59));
		i.add(new Armour("Steel Leggings", null, Armour.TYPE.LEGS, 14.27));
		i.add(new Key("Church Key", null, 391));
		i.add(new Potion("Major Health Potion", null, 44));
		i.add(new Potion("Minor Poison", null, -27));
		assertNotNull(i.toString());
		System.out.println(i.toString());
	}

	/**
	 * Asserts Armour, Weapon, Key and Potion have a string representation
	 */
	@Test
	public void test_Items_toString(){
		Armour a = new Armour("Chestplate", null, Armour.TYPE.TORSO, 10);
		assertNotNull(a.toString());
		System.out.println(a.toString());

		Weapon w = new Weapon("Sword", null,false,5);
		assertNotNull(w.toString());
		System.out.println(w.toString());

		Key k = new Key("House Key", null,0);
		assertNotNull(k.toString());
		System.out.println(k.toString());

		Potion p = new Potion("Poison", null,-5);
		assertNotNull(p.toString());
		System.out.println(p.toString());
	}

	/**
	 * Asserts Static has a string representation
	 */
	@Test
	public void test_Stationary_toString(){
		Static s = new Static("Rock", null);
		assertNotNull(s.toString());
		System.out.println(s.toString());
	}

	/**
	 * Asserts Static has a string representation and includes its behavior
	 */
	@Test
	public void test_StationaryAndBehavior_toString(){
		Static s = new Static("Front Door", null);
		Door d = new Door(209, Door.STATE.LOCKED);
		s.setBehavior(d);
		assertNotNull(s.toString());
		System.out.println(s.toString());
	}

	/**
	 * Asserts NPC has a string representation and includes its behavior
	 */
	@Test
	public void test_NPCs_toString(){
		Behavior a = new Monster(50);
		NPC n = new NPC("Zombie", null, a,80, Direction.Down);

		assertNotNull(n.toString());
		assertNotNull(a.toString());
		System.out.println(n.toString());
		System.out.println(a.toString());
	}

	/**
	 * Asserts Player has a string representation
	 */
	@Test
	public void test_Player_toString(){
		Player p = new Player(null, Direction.Down);
		assertNotNull(p.toString());
		System.out.println(p.toString());
	}

	/**
	 * Asserts that entities names are correctly set and get
	 */
	@Test
	public void test_Entity_name(){
		Entity e = new Potion("Health",null,1);
		assertEquals(e.getName(),"Health");
	}
}