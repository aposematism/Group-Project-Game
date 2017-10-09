//package com.swen.herebethetitle.entity.tests;
//
//import com.swen.herebethetitle.entity.*;
//import com.swen.herebethetitle.entity.items.*;
//import com.swen.herebethetitle.entity.statics.*;
//import com.swen.herebethetitle.model.*;
//import com.swen.herebethetitle.util.Direction;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import org.junit.Ignore;
//
///**
// * External tests for the Entity package.
// * @author J Woods
// *
// */
//public class ExternalTests {
//
//	/**
//	 * Tests the player's wallet starts empty
//	 */
//	@Test
//	public void external_inventoryTestWalletInitializeProperly(){
//		Player p = new Player(null, Direction.Down);
//		/*make sure the player starts with no money*/
//		assert(p.getWallet()==0);
//	}
//
//	/**
//	 * Tests the player's wallet adds money properly
//	 */
//	@Test
//	public void external_inventoryTestWalletAddProperly() {
//		Player p = new Player(null, Direction.Down);
//		/*Give him some dollarydos*/
//		p.addFunds(500);
//		assert(p.getWallet()==500);
//	}
//
//	/**
//	 * Tests that damaging an unarmoured player works as expected
//	 */
//	@Test
//	public void external_unarmouredPlayerDamageTest() {
//		Player p = new Player(null, Direction.Down);
//		double initHealth = p.getHealth();
//		/*damage the player*/
//		p.damage(10.0d);
//		assertTrue(initHealth-10.0d==p.getHealth());
//	}
//	/**
//	 * Tests that damaging an unarmoured player works as expected
//	 */
//	@Test
//	public void external_armouredPlayerDamageTest() {
//		Player p = new Player(null, Direction.Down);
//		double initHealth = p.getHealth();
//		/*give the player some armour*/
//		Armour w = new Armour("", null, Armour.TYPE.BOOTS, 1.0d);
//		Armour x = new Armour("", null, Armour.TYPE.HELMET, 1.0d);
//		Armour y = new Armour("", null, Armour.TYPE.TORSO, 1.0d);
//		Armour z = new Armour("", null, Armour.TYPE.LEGS, 1.0d);
//		p.add(w,x,y,z);
//
//		/*damage the player*/
//		p.damage(10.0d);
//		//damage should be amount * ((100 - armourTotal)/100)
//		assertTrue(initHealth==p.getHealth() + 10.0d * ((100.0d - 4.0d)/100.0d));
//	}
//
//	/**
//	 * Tests that damaging a player until death kills them
//	 */
//	@Test
//	public void external_playerDeath() {
//		Player p = new Player(null, Direction.Down);
//		double initHealth = p.getHealth();
//
//		/*damage the player*/
//		p.damage(initHealth);
//		//damage should be amount * ((100 - armourTotal)/100)
//		assertTrue(p.isDead());
//	}
//
//	/**
//	 * Tests that damaging a player until death kills them when there is more damage
//	 * than their health.
//	 */
//	@Test
//	public void external_playerDeathExcessive() {
//		Player p = new Player(null, Direction.Down);
//		double initHealth = p.getHealth();
//
//		/*damage the player*/
//		p.damage(initHealth * 2.0d);
//		assertTrue(p.isDead());
//	}
//
//	/**
//	 * Tests that damaging an unarmoured player works as expected
//	 */
//	@Test
//	public void external_npcDamageTest() {
//		NPC n = new NPC(null, null, 10.0d, Direction.Down);
//		double initHealth = n.getHealth();
//
//		/*damage the npc*/
//		n.damage(1.0d);
//		//damage should be amount * ((100 - armourTotal)/100)
//		assertTrue(initHealth==n.getHealth()+1.0d);
//	}
//
//	/**
//	 * Tests that damaging a player until death kills them
//	 */
//	@Test
//	public void external_npcDeath() {
//		NPC n = new NPC(null, null, 10.0d, Direction.Down);
//		double initHealth = n.getHealth();
//
//		/*damage the player*/
//		n.damage(initHealth);
//		//damage should be amount * ((100 - armourTotal)/100)
//		assertTrue(n.isDead());
//	}
//
//	/**
//	 * Tests that damaging a player until death kills them when there is more damage
//	 * than their health.
//	 */
//	@Test
//	public void external_npcDeathExcessive() {
//		NPC n = new NPC(null, null, 10.0d, Direction.Down);
//		double initHealth = n.getHealth();
//
//		/*damage the player*/
//		n.damage(initHealth*2.0d);
//		assertTrue(n.isDead());
//	}
//
//}
