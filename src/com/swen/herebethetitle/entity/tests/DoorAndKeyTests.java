package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.DoorStrategy;
import com.swen.herebethetitle.entity.Key;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.model.GameContext;
import org.junit.Test;

import static com.swen.herebethetitle.entity.tests.ItemTests.addtoFloor;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

		DoorStrategy d = new DoorStrategy(123, DoorStrategy.STATE.LOCKED, "", "");
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 123);
		addtoFloor(context, k);

		k.interact(context);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertTrue(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());

		k.interact(context);

		s.interact(context);

		assertTrue(s.isPenetrable());

		assertFalse(p.possesses(k));
	}

	/**
	 * Makes sure door is unlocked with the correct key
	 * Two keys are added to the players inventory - both an incorrect and correct one
	 */
	@Test
	public void test_correctKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		DoorStrategy d = new DoorStrategy(123, DoorStrategy.STATE.LOCKED, "", "");
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 234);
		Key k2 = new Key("", null, 123);
		addtoFloor(context,k,k2);

		k.interact(context);
		k2.interact(context);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertTrue(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());

		k.interact(context);
		k2.interact(context);

		s.interact(context);

		assertTrue(s.isPenetrable());

		assertFalse(p.possesses(k2));

		assertTrue(p.possesses(k));
	}

	/**
	 * Makes sure door isn't unlocked with a wrong key
	 * One key is added to the players inventory - one that doesn't match the door
	 */
	@Test
	public void test_incorrectKey_1(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		DoorStrategy d = new DoorStrategy(123, DoorStrategy.STATE.LOCKED, "", "");
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 234);
		addtoFloor(context, k);

		k.interact(context);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());

		assertTrue(p.possesses(k));
	}

	/**
	 * Makes sure door isn't unlocked with a wrong key
	 * Two keys are added to the players inventory - both are incorrect
	 */
	@Test
	public void test_incorrectKey_2(){
		GameContext context = new GameContext();
		Player p = context.getPlayer();

		DoorStrategy d = new DoorStrategy(123, DoorStrategy.STATE.LOCKED, "", "");
		Static s = new Static("", null);
		s.setBehavior(d);

		Key k = new Key("", null, 234);
		Key k2 = new Key("", null, 345);
		addtoFloor(context,k,k2);

		k.interact(context);
		k2.interact(context);

		assertFalse(s.isPenetrable());

		s.interact(context);

		assertFalse(s.isPenetrable());

		assertTrue(p.possesses(k, k2));
	}
}
