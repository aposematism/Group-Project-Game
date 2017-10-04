package com.swen.herebethetitle.parser.parsertests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.statics.Door;
import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.parser.InteractiveParser;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.regex.Matcher;

public class InteractiveParserTests {
	@Test
	public void test_Player_empty_inventory(){
		Player in = new Player("/res/silly boi.png", Direction.Down);

		InteractiveParser i = new InteractiveParser();
		Scanner s = new Scanner(in.toString());
		Entity out = i.parsePlayer(s);

		assertEquals(in.toString(), out.toString());
	}

	@Test
	public void test_Player_with_inventory(){
		Player in = new Player("/res/silly boi.png", Direction.Down);

		in.add(new Potion("Health Potion", "/res/health potion.png", 50));
		in.add(new Armour("Bronze Chestplate", "/res/chestplate one.png", Armour.TYPE.TORSO, 5.6));
		in.add(new Weapon("Iron Sword", "/res/swordasdasd.png", true, 8.8));
		in.add(new Key("Church Key", "/res/basickey1.png", 101));

		InteractiveParser i = new InteractiveParser();
		Scanner s = new Scanner(in.toString());
		Entity out = i.parsePlayer(s);

		assertEquals(in.toString(), out.toString());
	}

	@Test
	public void test_Static_no_Behavior(){
		Static in = new Static("Large Rock","/src/gigantic ass rock.png");

		InteractiveParser i = new InteractiveParser();
		Scanner s = new Scanner(in.toString());
		Entity out = i.parseStatic(s);

		assertEquals(in.toString(), out.toString());
	}

	@Test
	public void test_Static_with_Door(){
		Static in = new Static("Large Rock","/src/gigantic ass rock.png");
		in.setBehavior(new Door(101, Door.STATE.LOCKED));

		InteractiveParser i = new InteractiveParser();
		Scanner s = new Scanner(in.toString());
		Entity out = i.parseStatic(s);

		assertEquals(in.toString(), out.toString());
	}
}
