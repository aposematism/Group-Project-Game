package com.swen.herebethetitle.parser.parsertests;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Armour;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.entity.items.Potion;
import com.swen.herebethetitle.entity.items.Weapon;
import com.swen.herebethetitle.entity.statics.Door;
import com.swen.herebethetitle.parser.InteractiveParser;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Scanner;

/**
 * Tests item generation for every possible item.
 * */
public class InteractiveParserTests {

			@Test
			public void test_generic_item_creation(){
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 weapon true 5");
				assertTrue(e instanceof Weapon);
			}
			@Test
			public void test_weapon_generation() {
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 weapon");
				assertTrue(e instanceof Weapon);
			}
			@Test
			public void test_helmet_generation() {
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 helmet");
				assertTrue(e instanceof Armour);
			}

			@Test
			public void test_boots_generation() {
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 boots");
				assertTrue(e instanceof Armour);
			}

			@Test
			public void test_legs_generation() {
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 legs");
				assertTrue(e instanceof Armour);
			}

			@Test
			public void test_torso_generation() {
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 torso");
				assertTrue(e instanceof Armour);
			}

			//@Test
			//public void test_door_generation() {
			//	InteractiveParser ip = new InteractiveParser();
			//	Entity e = ip.parseStringArray("0 0 door");
			//	assertTrue(e instanceof Door);
			//}

			@Test
			public void test_key_generation() {
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 key");
				assertTrue(e instanceof Key);
			}

			@Test
			public void test_potion_generation() {
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 potion");
				assertTrue(e instanceof Potion);
			}
			
			
}
