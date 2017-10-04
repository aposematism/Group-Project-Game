package com.swen.herebethetitle.parser.parsertests;

import static com.swen.herebethetitle.parser.Coord.parseCoordinate;
import static com.swen.herebethetitle.parser.EntityParser.parseEntity;
import static org.junit.Assert.*;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.statics.*;
import com.swen.herebethetitle.parser.Coord;
import com.swen.herebethetitle.parser.EntityParser;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import java.util.Scanner;

public class EntityParserTests {
	@Test
	public void test_Player_empty_inventory(){
		Player in = new Player("/res/silly bopng", Direction.Down);

		
		Scanner s = new Scanner(in.toString());
		Entity out = parseEntity(s);

		assertEquals(in.toString(), out.toString());

		System.out.println(in.toString());
	}

	@Test
	public void test_Player_with_inventory(){
		Player in = new Player("/res/silly bo.png", Direction.Down);

		in.add(new Potion("Health Potion", "/res/health potion.png", 50));
		in.add(new Armour("Bronze Chestplate", "/res/chestplate one.png", Armour.TYPE.TORSO, 5.6));
		in.add(new Weapon("Iron Sword", "/res/swordasdasd.png", true, 8.8));
		in.add(new Key("Church Key", "/res/basickey1.png", 101));

		
		Scanner s = new Scanner(in.toString());
		Entity out = parseEntity(s);

		assertEquals(in.toString(), out.toString());

		System.out.println(in.toString());
	}

	@Test
	public void test_Static_no_Behavior(){
		Static in = new Static("Large Rock","/res/gigantic ass rock.png");

		
		Scanner s = new Scanner(in.toString());
		Entity out = parseEntity(s);

		assertEquals(in.toString(), out.toString());

		System.out.println(in.toString());
	}

	@Test
	public void test_Floor(){
		Floor in = new Floor("Grass","/res/grass.png");
		
		Scanner s = new Scanner(in.toString());
		Entity out = parseEntity(s);

		assertEquals(in.toString(), out.toString());

		System.out.println(in.toString());
	}

	@Test
	public void test_NPC_with_Behavior(){
		Behavior behavior = new Monster(50);
		NPC in = new NPC("Zombie","/res/zombie.png", behavior, 50, Direction.Down);

		
		Scanner s = new Scanner(in.toString());
		Entity out = parseEntity(s);

		assertEquals(in.toString(), out.toString());

		System.out.println(in.toString());
	}

	@Test
	public void test_Coord(){
		String line = "(0,1) ";
		Behavior behavior = new Monster(50);
		NPC in = new NPC("Zombie","/res/zombie.png", behavior, 50, Direction.Down);
		line += in.toString();
		Scanner s = new Scanner(line);

		Coord c = parseCoordinate(s);

		System.out.println(c.toString());
	}

	@Test
	public void test_Line(){
		String line = "(0,2) Player \"Player\" \"/res/silly bopng\" 100.0 Down 0 Inventory Weapon \"Iron Sword\" \"/res/swordasdasd.png\" true 8.8 Armour \"Bronze Chestplate\" \"/res/chestplate one.png\" TORSO 5.6 Potion \"Health Potion\" \"/res/health potion.png\" 50 Key \"Church Key\" \"/res/basickey1.png\" 101";

		Scanner s = new Scanner(line);

		Coord coord = parseCoordinate(s);
		Entity entity = parseEntity(s);

		System.out.println(coord.toString()+" "+entity.toString());
	}
}
