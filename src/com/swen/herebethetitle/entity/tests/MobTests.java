package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.Behavior;
import com.swen.herebethetitle.entity.ai.Monster;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import jdk.internal.util.xml.impl.Input;
import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.*;

/**
 * Created by Mark on 30/09/2017.
 */
public class MobTests {
	@Test
	public void test_direction(){
		Behavior a = new Monster(50);
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(Direction.Down, m.getDirection());

		m.setDirection(Direction.Left);
		assertEquals(Direction.Left, m.getDirection());
	}

	@Test
	public void test_health(){
		Behavior a = new Monster(50);
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.heal(10);
		assertEquals(90, m.getHealth(), 0);
	}

	@Test
	public void test_health_overflow(){
		Behavior a = new Monster(50);
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.heal(30);
		assertEquals(100, m.getHealth(), 0);
	}

	@Test
	public void test_health_invalid(){
		Behavior a = new Monster(50);
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		try {
			m.heal(-10);
			fail();
		} catch (IllegalArgumentException e){
			assertEquals(80, m.getHealth(), 0);
		}
	}

	@Test
	public void test_damage(){
		Behavior a = new Monster(50);
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.damage(10);
		assertEquals(70, m.getHealth(), 0);
	}

	@Test
	public void test_damage_overflow(){
		Behavior a = new Monster(50);
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.damage(90);
		assertEquals(0, m.getHealth(), 0);
	}

	@Test
	public void test_damage_invalid(){
		Behavior a = new Monster(50);
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		try {
			m.damage(-10);
			fail();
		} catch (IllegalArgumentException e){
			assertEquals(80, m.getHealth(), 0);
		}
	}
}
