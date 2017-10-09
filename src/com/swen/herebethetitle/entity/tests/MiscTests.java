package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.GameContext;
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
	 * Asserts DoorStrategy has a string representation
	 */
	@Test
	public void test_Door_toString(){
		DoorStrategy d = new DoorStrategy(123, DoorStrategy.STATE.LOCKED);
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
		GameContext context = new GameContext();
		Player p = context.getPlayer();
		Inventory i = p.inventory();

		new Weapon("Bronze Sword", null,true,6.59).interact(context);
		new Armour("Steel Leggings", null, Armour.TYPE.LEGS, 14.27).interact(context);
		new Key("Church Key", null, 391).interact(context);
		new Potion("Major Health Potion", null, 44).interact(context);
		new Potion("Minor Poison", null, -27).interact(context);

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
	public void test_Static_toString(){
		Static s = new Static("Rock", null);
		assertNotNull(s.toString());
		System.out.println(s.toString());
	}

	/**
	 * Asserts Static has a string representation and includes its behavior
	 */
	@Test
	public void test_StaticAndBehavior_toString(){
		Static s = new Static("Front DoorStrategy", null);
		DoorStrategy d = new DoorStrategy(209, DoorStrategy.STATE.LOCKED);
		s.setBehavior(d);
		assertNotNull(s.toString());
		System.out.println(s.toString());
	}

	/**
	 * Asserts NPC has a string representation and includes its behavior
	 */
	@Test
	public void test_NPCs_toString(){
		NPCBehavior a = new MonsterStrategy(50);
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

	/**
	 * Asserts FriendlyStrategy has a string representation
	 */
	@Test
	public void test_Friendly_toString(){
		FriendlyStrategy friendly = new FriendlyStrategy();
		friendly.addDialog("Hello!","Good weather today!","Bye!");
		assertNotNull(friendly.toString());
		System.out.println(friendly.toString());
	}

	/**
	 * Asserts FriendlyStrategy has a string representation
	 */
	@Test
	public void test_Entity_spritePath(){
		String path = "poison.png";
		Potion p = new Potion("",path, 1);
		assertEquals(path,p.getSpritePath());
	}
}