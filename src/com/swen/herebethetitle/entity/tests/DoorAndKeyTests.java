package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.entity.stationeries.Door;
import com.swen.herebethetitle.entity.stationeries.Stationary;
import com.swen.herebethetitle.model.GameContext;
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

		Door d = new Door(123, Door.STATE.LOCKED);
		Stationary s = new Stationary(null);
		s.setBehavior(d);

		Key k = new Key(null, 123);

		p.add(k);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertTrue(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());

		p.remove(k);

		s.interact(context);

		assertTrue(s.isPenetrable());
	}

	@Test
	public void test_correctKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(123, Door.STATE.LOCKED);
		Stationary s = new Stationary(null);
		s.setBehavior(d);

		Key k = new Key(null, 234);
		Key k2 = new Key(null, 123);

		p.add(k,k2);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertTrue(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());

		p.remove(k,k2);

		s.interact(context);

		assertTrue(s.isPenetrable());
	}

	@Test
	public void test_incorrectKey_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(123, Door.STATE.LOCKED);
		Stationary s = new Stationary(null);
		s.setBehavior(d);

		Key k = new Key(null, 234);

		p.add(k);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());
	}

	@Test
	public void test_incorrectKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(123, Door.STATE.LOCKED);
		Stationary s = new Stationary(null);
		s.setBehavior(d);

		Key k = new Key(null, 234);
		Key k2 = new Key(null, 345);

		p.add(k,k2);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());
	}
}
