package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.*;

import static com.swen.herebethetitle.entity.tests.ItemTests.addtoFloor;

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
		NPCBehavior a = new MonsterStrategy(50);
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
		GameContext context = new GameContext(
				new Player("","", Mob.FULL_HEALTH-20, 100, Direction.Up)
		);

		assertEquals(Player.FULL_HEALTH-20, context.getPlayer().getHealth(), 0);

		Potion p = new Potion("","",20);
		addtoFloor(context,p);

		p.interact(context);
		p.interact(context);

		assertEquals(Player.FULL_HEALTH, context.getPlayer().getHealth(), 0);
	}

	/**
	 * Asserts that the health value doesn't go over the maximum
	 */
	@Test
	public void test_health_overflow(){
		GameContext context = new GameContext(
				new Player("","", Mob.FULL_HEALTH-20, 100, Direction.Up)
		);

		assertEquals(Player.FULL_HEALTH-20, context.getPlayer().getHealth(), 0);

		Potion p = new Potion("","",40);
		addtoFloor(context,p);

		p.interact(context);
		p.interact(context);

		assertEquals(Player.FULL_HEALTH, context.getPlayer().getHealth(), 0);
	}

	/**
	 * Asserts that the correct amount of health is removed
	 */
	@Test
	public void test_damage(){
		GameContext context = new GameContext(
				new Player("","", Mob.FULL_HEALTH, 100, Direction.Up)
		);

		assertEquals(Player.FULL_HEALTH, context.getPlayer().getHealth(), 0);

		Potion p = new Potion("","",-40);
		addtoFloor(context,p);

		p.interact(context);
		p.interact(context);

		assertEquals(Player.FULL_HEALTH-40, context.getPlayer().getHealth(), 0);
	}

	/**
	 * Asserts that the health value doesn't go below zero
	 */
	@Test
	public void test_damage_underflow(){
		GameContext context = new GameContext(
				new Player("","", Mob.FULL_HEALTH, 100, Direction.Up)
		);

		assertEquals(Player.FULL_HEALTH, context.getPlayer().getHealth(), 0);

		Potion p = new Potion("","",-120);
		addtoFloor(context,p);

		p.interact(context);
		p.interact(context);

		assertEquals(Player.NO_HEALTH, context.getPlayer().getHealth(), 0);
	}

	@Test
	public void test_isDead(){
		GameContext context = new GameContext(
				new Player("","", Mob.FULL_HEALTH, 100, Direction.Up)
		);

		assertEquals(Player.FULL_HEALTH, context.getPlayer().getHealth(), 0);

		Potion p = new Potion("","",-120);
		addtoFloor(context,p);

		p.interact(context);
		p.interact(context);

		assertEquals(Player.NO_HEALTH, context.getPlayer().getHealth(), 0);

		assertTrue(context.getPlayer().isDead());
	}
}
