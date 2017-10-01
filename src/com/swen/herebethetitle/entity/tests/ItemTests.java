package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 */
public class ItemTests {
	@Test
	public void test_itemEquals_1(){
		Item i1 = new Key(null,0);
		Item i2 = new Key(null,0);

		assertFalse(i1.equals(i2));
	}

	@Test
	public void test_interactions_1(){
		GameContext context = new GameContext();

		Armour worse = new Armour(null, Armour.TYPE.TORSO, 5);
		Armour better = new Armour(null, Armour.TYPE.TORSO, 10);

		worse.pickup(context);

		assertEquals(worse, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.pickup(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));
	}

	@Test
	public void test_armourPickup_1(){
		GameContext context = new GameContext();

		Armour worse = new Armour(null, Armour.TYPE.TORSO, 5);
		Armour better = new Armour(null, Armour.TYPE.TORSO, 10);

		worse.pickup(context);

		assertEquals(worse, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.pickup(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.use(context);

		assertTrue(context.player.possesses(better));
	}

	@Test
	public void test_armourPickup_2(){
		GameContext context = new GameContext();

		Armour worse = new Armour(null, Armour.TYPE.TORSO, 5);
		Armour better = new Armour(null, Armour.TYPE.TORSO, 10);

		better.pickup(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		worse.pickup(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.use(context);

		assertTrue(context.player.possesses(better));
	}

	@Test
	public void test_Item_use(){
		GameContext context = new GameContext();
		Item i = new Key(null, 0);
		context.player.add(i);
		assertTrue(context.player.possesses(i));
		i.interact(context);
		assertFalse(context.player.possesses(i));
	}

	@Test
	public void test_Item_pickup(){
		GameContext context = new GameContext();
		Item i = new Key(null, 0);
		context.getCurrentRegion().getPlayerTile().add(i);
		assertFalse(context.player.possesses(i));
		i.interact(context);
		assertTrue(context.player.possesses(i));
	}

	@Test
	public void test_Item_isPenetrable(){
		Item i = new Key(null, 0);
		assertTrue(i.isPenetrable());
	}

	@Test
	public void test_Potion(){
		GameContext context = new GameContext();

		Item p1 = new Potion(null, 50);
		Item p2 = new Potion(null, -50);

		context.player.add(p1, p2);

		assertEquals(100, context.player.getHealth(), 0);

		p2.interact(context);

		assertEquals(50, context.player.getHealth(), 0);

		p1.interact(context);

		assertEquals(100, context.player.getHealth(), 0);

		assertFalse(context.player.possesses(p1, p2));
	}

	@Test
	public void test_Weapon(){
		GameContext context = new GameContext();

		Weapon w = new Weapon(null, true, 50);
		context.player.add(w);

		assertTrue(w.isMelee());
		assertTrue(context.player.possesses(w));

		assertEquals(50, w.getStrength(), 0);

		w.use(context);

		assertTrue(context.player.possesses(w));
	}
}
