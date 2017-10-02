package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.*;
import com.swen.herebethetitle.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 *
 * @author Mark Metcalfe
 */
public class MobTests {

	/**
	 * Asserts that the direction getters and setters work as intended
	 */
	@Test
	public void test_direction(){
		Behavior a = new Monster(50);
		Mob m = new NPC("", null, a, 80, Direction.Down);
		assertEquals(Direction.Down, m.getDirection());

		m.setDirection(Direction.Left);
		assertEquals(Direction.Left, m.getDirection());
	}

	/**
	 * Asserts that the correct amount of health is added
	 */
	@Test
	public void test_health(){
		Behavior a = new Monster(50);
		Mob m = new NPC("", null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.heal(10);
		assertEquals(90, m.getHealth(), 0);
	}

	/**
	 * Asserts that the health value doesn't go over the maximum
	 */
	@Test
	public void test_health_overflow(){
		Behavior a = new Monster(50);
		Mob m = new NPC("", null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.heal(30);
		assertEquals(100, m.getHealth(), 0);
	}

	/**
	 * Asserts that a negative value can't be added
	 */
	@Test
	public void test_health_invalid(){
		Behavior a = new Monster(50);
		Mob m = new NPC("", null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		try {
			m.heal(-10);
			fail();
		} catch (IllegalArgumentException e){
			assertEquals(80, m.getHealth(), 0);
		}
	}

	/**
	 * Asserts that the correct amount of health is added
	 */
	@Test
	public void test_damage(){
		Behavior a = new Monster(50);
		Mob m = new NPC("", null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.damage(10);
		assertEquals(70, m.getHealth(), 0);
	}

	/**
	 * Asserts that the health value doesn't go below zero
	 */
	@Test
	public void test_damage_underflow(){
		Behavior a = new Monster(50);
		Mob m = new NPC("", null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.damage(90);
		assertEquals(0, m.getHealth(), 0);
	}

	/**
	 * Asserts that a negative value can't be removed
	 */
	@Test
	public void test_damage_invalid(){
		Behavior a = new Monster(50);
		Mob m = new NPC("", null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		try {
			m.damage(-10);
			fail();
		} catch (IllegalArgumentException e){
			assertEquals(80, m.getHealth(), 0);
		}
	}
}
