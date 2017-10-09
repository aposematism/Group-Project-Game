package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.util.*;
import com.swen.herebethetitle.model.*;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by Mark on 19/09/2017.
 *
 * @author Mark Metcalfe
 */
public class PlayerTests {

	/**
	 * Asserts that the size of the inventory increases by two when two items are added
	 */
	@Test
	public void test_add(){
		GameContext context = new GameContext();

		new Key("", null,0).interact(context);
		new Key("", null,0).interact(context);

		assertEquals(2, context.getPlayer().inventory().size());
	}

	/**
	 * Asserts that the inventory possesses the item added to it
	 */
	@Test
	public void test_posseses_true(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Item i = new Key("", null,0);
		i.interact(context);
		assertTrue(p.possesses(i));
	}

	/**
	 * Asserts that the inventory doesn't possess an item that wasn't added to it
	 */
	@Test
	public void test_posseses_false(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Item i = new Key("", null,0);

		assertFalse(p.possesses(i));
	}

	/**
	 * Asserts that the player takes full damage when no armour is worn
	 */
	@Test
	public void test_damage_noArmour_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Potion po = new Potion("","",-20);
		po.interact(context);
		po.interact(context);

		assertEquals(Mob.FULL_HEALTH-20, p.getHealth(), 0);
	}

	/**
	 * Asserts that the player takes less damage when one piece of armour is worn
	 */
	@Test
	public void test_damage_armour_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		new Armour("", null, Armour.TYPE.HELMET, 3.5).interact(context);

		Potion po = new Potion("","",-20);
		po.interact(context);
		po.interact(context);

		assertTrue(p.getHealth()>Mob.FULL_HEALTH-20);
		assertTrue(p.getHealth()<Mob.FULL_HEALTH);
	}

	/**
	 * Asserts that the player takes even less damage when multiple pieces of armour is worn
	 */
	@Test
	public void test_damage_armour_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		new Armour("", null, Armour.TYPE.HELMET, 3.5).interact(context);
		new Armour("", null, Armour.TYPE.TORSO, 6).interact(context);
		new Armour("", null, Armour.TYPE.BOOTS, 1.75).interact(context);

		Potion po = new Potion("","",-20);
		po.interact(context);
		po.interact(context);

		assertTrue(p.getHealth()>Mob.FULL_HEALTH-20);
		assertTrue(p.getHealth()<Mob.FULL_HEALTH);
	}

	/**
	 * Asserts that adding and removing funds to the wallet works as intended
	 */
	@Test
	public void test_wallet(){
		Player p = new Player("", null, 100, 100, Direction.Down);
		assertEquals(100, p.getWallet());
		p.removeFunds(50);
		assertEquals(50, p.getWallet());
		p.addFunds(50);
		assertEquals(100, p.getWallet());
	}

	/**
	 * Asserts that the player is not penetrable
	 */
	@Test
	public void test_isPenetrable(){
		Player p = new Player(null, Direction.Down);
		assertFalse(p.isPenetrable());
	}

	/**
	 * Asserts that the player doesn't change when interacted with
	 */
	@Test
	public void test_interact(){
		GameContext context = new GameContext();
		String before = context.player.toString();
		context.player.interact(context);
		String after = context.player.toString();
		assertEquals(before, after);
	}
}
