package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.npcs.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mark on 30/09/2017.
 */
public class MobTests {
	@Test
	public void test_direction(){
		Mob m = new NPC(null, 80, Direction.Down);
		assertEquals(Direction.Down, m.getDirection());

		m.setDirection(Direction.Left);
		assertEquals(Direction.Left, m.getDirection());
	}

	@Test
	public void test_health(){
		Mob m = new NPC(null, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.heal(10);
		assertEquals(90, m.getHealth(), 0);
	}
}
