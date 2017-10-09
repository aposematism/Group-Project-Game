package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static com.swen.herebethetitle.entity.tests.ItemTests.addtoFloor;
import static org.junit.Assert.*;

/**
 * Created by Mark on 1/10/2017.
 *
 * @author Mark Metcalfe
 */
public class NPCTests {

	public static void changeHealth(GameContext context, NPC npc, double amount) {
		Weapon weapon = new Weapon("", "", false, amount);
		addtoFloor(context, weapon);
		weapon.interact(context);
		context.getCurrentRegion().getPlayerTile().add(npc);
		npc.interact(context);
	}

	/**
	 * Asserts that NPCBehavior is constructed correctly
	 */
	@Test
	public void test_construct(){
		NPCBehavior a = new MonsterStrategy(50);
		NPC n = new NPC("", null, a, 80, Direction.Down);
		assertTrue(n.getBehavior().isPresent());
		assertEquals(a, n.getBehavior().get());
	}

	/**
	 * Asserts that NPCBehavior can be set
	 */
	@Test
	public void test_setBehavior(){
		NPCBehavior a = new MonsterStrategy(50);
		NPC n = new NPC("", null,80, Direction.Down);
		assertFalse(n.getBehavior().isPresent());
		n.setBehavior(a);
		assertTrue(n.getBehavior().isPresent());
		assertEquals(a, n.getBehavior().get());
	}

	/**
	 * Asserts that a MonsterStrategy can attack the player correctly
	 */
	@Test
	public void test_Monster_ping(){
		NPCBehavior a = new MonsterStrategy(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();

		context.getCurrentRegion().getPlayerTile().add(n);

		assertEquals(100, context.player.getHealth(), 0);

		n.ping(context);

		assertEquals(50, context.player.getHealth(), 0);
	}

	/**
	 * Asserts that the MonsterStrategy can be attacked and the correct amount of damage is applied
	 */
	@Test
	public void test_Monster_interact_noWeapon(){
		NPCBehavior a = new MonsterStrategy(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();
		context.getCurrentRegion().get(1, 0).add(n);

		assertEquals(80, n.getHealth(), 0);
		n.interact(context);
		assertEquals(75, n.getHealth(), 0);
	}

	/**
	 * Asserts that the MonsterStrategy can be attacked and the correct amount of damage is applied
	 */
	@Test
	public void test_Monster_interact_weapon(){
		NPCBehavior a = new MonsterStrategy(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();
		context.getCurrentRegion().get(1, 0).add(n);

		Weapon w = new Weapon("", null, false, 10);
		addtoFloor(context,w);
		w.interact(context);

		assertTrue(context.player.inventory().getWeapon().isPresent());

		assertEquals(80, n.getHealth(), 0);
		n.interact(context);
		assertEquals(65, n.getHealth(), 0);
	}

	/**
	 * Asserts that the monster is removed from the game when its health is zero
	 */
	@Test
	public void test_Monster_interact_death(){
		NPCBehavior a = new MonsterStrategy(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		GameContext context = new GameContext();

		Weapon w = new Weapon("", null, false, 80);
		addtoFloor(context,w);
		w.interact(context);

		assertTrue(context.player.inventory().getWeapon().isPresent());

		assertEquals(80, n.getHealth(), 0);

		context.getCurrentRegion().getPlayerTile().add(n);

		assertNotNull(context.getCurrentRegion().getTile(n));

		n.interact(context);

		assertEquals(0, n.getHealth(), 0);

		try {
			assertNull(context.getCurrentRegion().getTile(n));
			fail();
		} catch (IllegalArgumentException e){}
	}

	/**
	 * Asserts that the MonsterStrategy isn't penetrable
	 */
	@Test
	public void test_Monster_isPenetrable(){
		NPCBehavior a = new MonsterStrategy(50);
		NPC n = new NPC("", null, a,80, Direction.Down);

		assertFalse(n.isPenetrable());
	}

	/**
	 * Assert that a MonsterStrategy is aggressive
	 */
	@Test
	public void test_Monster_isAggressive(){
		NPCBehavior a = new MonsterStrategy(1);
		NPC n = new NPC("", null, a,80, Direction.Down);

		assertTrue(n.isAggressive());
	}

	/**
	 * Asserts that the MonsterStrategy can't have a negative attack strength value
	 */
	@Test
	public void test_Monster_invalidStrength(){
		try {
			new MonsterStrategy(-50);
			fail();
		} catch(IllegalArgumentException e){}
	}

	/**
	 * Asserts that adding and getting dialog functions correctly
	 */
	@Test
	public void test_Friendly_dialog(){
		FriendlyStrategy friendly = new FriendlyStrategy();

		String message = "Hit there!";
		friendly.addDialog(message);

		NPC npc = new NPC("Villager", "", friendly, NPC.FULL_HEALTH, Direction.Down);

		assertTrue(friendly.canTalkTo());
		assertEquals(message, friendly.nextMessage());
		assertFalse(friendly.canTalkTo());

		String[] message2 = new String[]{"Hi1","Hi2","Hi3"};
		friendly.addDialog(message2);
		assertTrue(friendly.canTalkTo());

		for(String t: message2){
			assertEquals(t, friendly.nextMessage());
		}
		assertFalse(friendly.canTalkTo());
	}

	@Test
	public void test_Friendly_ping(){
		FriendlyStrategy friendly = new FriendlyStrategy();

		Direction starting = Direction.Down;

		NPC npc = new NPC("Villager", "", friendly, NPC.FULL_HEALTH, starting);


		GameContext g = new GameContext();

		npc.ping(g);

		//Direction shouldn't change as the friendly has nothing to say
		assertEquals(starting, npc.getDirection());

		friendly.addDialog("Hello!");

		npc.ping(g);

		//Direction should now be different as the the friendly now has dialog
		assertNotEquals(starting, npc.getDirection());
	}

	@Test
	public void test_Friendly_isAggressive(){
		NPCBehavior a = new FriendlyStrategy();
		NPC n = new NPC("", null, a,80, Direction.Down);

		assertFalse(n.isAggressive());
	}
}
