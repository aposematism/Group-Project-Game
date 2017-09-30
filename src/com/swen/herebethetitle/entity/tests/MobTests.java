package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.Actions;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mark on 30/09/2017.
 */
public class MobTests {
	@Test
	public void test_direction(){
		Actions a = new Actions() {
			@Override
			public void interact(GameContext context, NPC npc) {

			}

			@Override
			public boolean isPenetrable() {
				return false;
			}
		};
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(Direction.Down, m.getDirection());

		m.setDirection(Direction.Left);
		assertEquals(Direction.Left, m.getDirection());
	}

	@Test
	public void test_health(){
		Actions a = new Actions() {
			@Override
			public void interact(GameContext context, NPC npc) {}

			@Override
			public boolean isPenetrable() {
				return false;
			}
		};
		Mob m = new NPC(null, a, 80, Direction.Down);
		assertEquals(80, m.getHealth(), 0);

		m.heal(10);
		assertEquals(90, m.getHealth(), 0);
	}
}
