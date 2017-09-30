package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Armour;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Key;
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
	}
}
