package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.util.*;
import com.swen.herebethetitle.model.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mark on 1/10/2017.
 *
 * @author Mark Metcalfe
 */
public class NPCTests {

	/**
	 * Asserts that Behavior is constructed correctly
	 */
	@Test
	public void test_construct(){
		Behavior a = new Monster(50);
		NPC n = new NPC("", null, a, 80, Direction.Down);
		assertTrue(n.getBehavior().isPresent());
		assertEquals(a, n.getBehavior().get());
	}

	/**
	 * Asserts that Behavior can be set
	 */
	@Test
	public void test_setBehavior(){
		Behavior a = new Monster(50);
		NPC n = new NPC("", null,80, Direction.Down);
		assertFalse(n.getBehavior().isPresent());
		n.setBehavior(a);
		assertTrue(n.getBehavior().isPresent());
		assertEquals(a, n.getBehavior().get());
	}

	/**
	 * Asserts that a Monster can attack the player correctly
	 */
	@Test
	public void test_Monster_ping(){
		Behavior a = new Monster(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();

		context.getCurrentRegion().getPlayerTile().add(n);

		assertEquals(100, context.player.getHealth(), 0);

		n.ping(context);

		assertEquals(50, context.player.getHealth(), 0);
	}

	/**
	 * Asserts that the Monster can be attacked and the correct amount of damage is applied
	 */
	@Test
	public void test_Monster_interact_noWeapon(){
		Behavior a = new Monster(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();
		context.getCurrentRegion().get(1, 0).add(n);

		assertEquals(80, n.getHealth(), 0);
		n.interact(context);
		assertEquals(75, n.getHealth(), 0);
	}

	/**
	 * Asserts that the Monster can be attacked and the correct amount of damage is applied
	 */
	@Test
	public void test_Monster_interact_weapon(){
		Behavior a = new Monster(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();
		context.getCurrentRegion().get(1, 0).add(n);

		Weapon w = new Weapon("", null, false, 10);
		context.player.add(w);
		assertTrue(context.player.inventory().getWeapon().isPresent());

		assertEquals(80, n.getHealth(), 0);
		n.interact(context);
		assertEquals(65, n.getHealth(), 0);
	}

	/**
	 * Asserts that the monster is removed from the game when its health is zero
	 */
	@Test
	public void test_Monster_interact_death(){
		Behavior a = new Monster(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();

		Weapon w = new Weapon("", null, false, 80);
		context.player.add(w);
		assertTrue(context.player.inventory().getWeapon().isPresent());

		assertEquals(80, n.getHealth(), 0);

		context.getCurrentRegion().getPlayerTile().add(n);

		assertNotNull(context.getCurrentRegion().getTile(n));

		n.interact(context);

		assertEquals(0, n.getHealth(), 0);

		try {
			assertNull(context.getCurrentRegion().getTile(n));
			fail();
		} catch (IllegalArgumentException e){}
	}

	/**
	 * Asserts that the Monster isn't penetrable
	 */
	@Test
	public void test_Monster_isPenetrable(){
		Behavior a = new Monster(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		assertFalse(n.isPenetrable());
	}

	/**
	 * Asserts that the Monster can't have a negative attack strength value
	 */
	@Test
	public void test_Monster_invalidStrength(){
		try {
			new Monster(-50);
			fail();
		} catch(IllegalArgumentException e){}
	}
}
