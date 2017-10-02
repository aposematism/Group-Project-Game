package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.statics.*;
import com.swen.herebethetitle.model.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 *
 * @author Mark Metcalfe
 */
public class DoorAndKeyTests {

	/**
	 * Makes sure door is unlocked with the correct key
	 * One key is added to the players inventory - the correct one
	 */
	@Test
	public void test_correctKey_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(123, Door.STATE.LOCKED);
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 123);

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

	/**
	 * Makes sure door is unlocked with the correct key
	 * Two keys are added to the players inventory - both an incorrect and correct one
	 */
	@Test
	public void test_correctKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(123, Door.STATE.LOCKED);
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 234);
		Key k2 = new Key("", null, 123);

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

	/**
	 * Makes sure door isn't unlocked with a wrong key
	 * One key is added to the players inventory - one that doesn't match the door
	 */
	@Test
	public void test_incorrectKey_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(123, Door.STATE.LOCKED);
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 234);

		p.add(k);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());
	}

	/**
	 * Makes sure door isn't unlocked with a wrong key
	 * Two keys are added to the players inventory - both are incorrect
	 */
	@Test
	public void test_incorrectKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		Door d = new Door(123, Door.STATE.LOCKED);
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 234);
		Key k2 = new Key("", null, 345);

		p.add(k,k2);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());
	}
}
