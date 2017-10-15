package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.GameContext;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 *
 * @author Mark Metcalfe
 */
public class ItemTests {

	public static void addtoFloor(GameContext context, Entity... entites){
		for(Entity e: entites)
			context.getCurrentRegion().getPlayerTile().add(e);
	}

	public static void addItem(GameContext context, Item... items) {
		for (Item i : items) {
			addtoFloor(context, i);
			i.interact(context);
		}
	}

	/**
	 * Assert that Armour with a higher rating replaces the current armour when picked up
	 * and it isn't consumed when used
	 */
	@Test
	public void test_armourPickup_1(){
		GameContext context = new GameContext();

		Armour worse = new Armour("", null, Armour.TYPE.TORSO, 5);
		Armour better = new Armour("", null, Armour.TYPE.TORSO, 10);
		addtoFloor(context,worse,better);

		worse.interact(context);

		assertEquals(worse, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.interact(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.use(context);

		assertTrue(context.player.possesses(better));
	}

	/**
	 * Assert that Armour with a lower rating isn't picked up
	 * and it isn't consumed when used
	 */
	@Test
	public void test_armourPickup_2(){
		GameContext context = new GameContext();

		Armour worse = new Armour("", null, Armour.TYPE.TORSO, 5);
		Armour better = new Armour("", null, Armour.TYPE.TORSO, 10);
		addtoFloor(context,worse,better);

		better.interact(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		worse.interact(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.use(context);

		assertTrue(context.player.possesses(better));
	}

	@Test
	public void test_armourPickup_3(){
		GameContext context = new GameContext();

		Armour worse = new Armour("", null, Armour.TYPE.TORSO, 5);
		Armour better = new Armour("", null, Armour.TYPE.TORSO, 10);
		addtoFloor(context,worse,better);

		better.interact(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		worse.interact(context);

		assertEquals(better, context.getPlayer().inventory().getArmour(Armour.TYPE.TORSO));

		better.use(context);

		assertTrue(context.player.possesses(better));
	}

	/**
	 * Assert that a Key isn't removed from the player's inventory when used
	 */
	@Test
	public void test_Key_use(){
		GameContext context = new GameContext();
		Item i = new Key("", null, 0);
		addtoFloor(context,i);

		i.interact(context);

		assertTrue(context.player.possesses(i));

		i.interact(context);

		assertTrue(context.player.possesses(i));
	}

	/**
	 * Assert that the player possesses an item when it is picked up
	 */
	@Test
	public void test_Item_pickup(){
		GameContext context = new GameContext();
		Item i = new Key("", null, 0);
		addtoFloor(context,i);
		assertFalse(context.player.possesses(i));
		i.interact(context);
		assertTrue(context.player.possesses(i));
	}

	/**
	 * Assert that items are penetrable/can be walked over
	 */
	@Test
	public void test_Item_isPenetrable(){
		Item i = new Key("", null, 0);
		assertTrue(i.isPenetrable());
	}

	/**
	 * Assert that Potions add or subtract health when picked up and used
	 */
	@Test
	public void test_Potion(){
		GameContext context = new GameContext();

		Item p1 = new Potion("", null, 50);
		Item p2 = new Potion("", null, -50);
		addtoFloor(context,p1,p2);

		p1.interact(context);
		p2.interact(context);

		assertEquals(100, context.player.getHealth(), 0);

		p2.interact(context);

		assertEquals(50, context.player.getHealth(), 0);

		p1.interact(context);

		assertEquals(100, context.player.getHealth(), 0);

		assertFalse(context.player.possesses(p1, p2));
	}

	/**
	 * Make sure the current Weapon isn't consumed when it is used
	 */
	@Test
	public void test_Weapon(){
		GameContext context = new GameContext();

		Weapon w = new Weapon("", null, true, 50);
		addtoFloor(context,w);

		w.interact(context);

		assertTrue(w.isMelee());
		assertTrue(context.player.possesses(w));

		assertEquals(50, w.getStrength(), 0);

		w.interact(context);

		assertTrue(context.player.possesses(w));
	}
}
