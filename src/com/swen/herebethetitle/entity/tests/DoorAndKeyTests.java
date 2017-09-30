package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.entity.stationary.Door;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 */
public class DoorAndKeyTests {
	@Test
	public void test_correctKey_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(null, 123, Door.STATE.LOCKED);

		Item i = new Item(null);
		Key k = new Key(123);
		i.setActions(k);

		p.add(i);

		assertFalse(d.isPenetrable());

		d.interact(context);

		assertTrue(d.isPenetrable());
	}

	@Test
	public void test_correctKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(null, 123, Door.STATE.LOCKED);

		Item i = new Item(null);
		Key k = new Key(234);
		i.setActions(k);

		Item i2 = new Item(null);
		Key k2 = new Key(123);
		i2.setActions(k2);

		p.add(i, i2);

		assertFalse(d.isPenetrable());

		d.interact(context);

		assertTrue(d.isPenetrable());
	}

	@Test
	public void test_incorrectKey_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(null, 123, Door.STATE.LOCKED);

		Item i = new Item(null);
		Key k = new Key(234);
		i.setActions(k);

		p.add(i);

		assertFalse(d.isPenetrable());

		d.interact(context);

		assertFalse(d.isPenetrable());
	}

	@Test
	public void test_incorrectKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(null, 123, Door.STATE.LOCKED);

		Item i = new Item(null);
		Key k = new Key(234);
		i.setActions(k);

		Item i2 = new Item(null);
		Key k2 = new Key(345);
		i2.setActions(k2);

		p.add(i, i2);

		assertFalse(d.isPenetrable());

		d.interact(context);

		assertFalse(d.isPenetrable());
	}
}
