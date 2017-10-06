package com.swen.herebethetitle.parser.parsertests;

import static com.swen.herebethetitle.parser.Coord.parseCoordinate;
import static com.swen.herebethetitle.parser.EntityParser.parseEntity;
import static org.junit.Assert.*;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.parser.Coord;
import com.swen.herebethetitle.parser.SyntaxError;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import java.util.Scanner;

public class EntityParserTests {

	public void parse(Entity in){
		try {
			Scanner s = new Scanner(in.toString());

			Entity out = parseEntity(s);

			assertEquals(in.toString(), out.toString());

			System.out.println(in.toString());
		} catch(SyntaxError error){
			fail("Syntax Error");
		}
	}

	@Test
	public void test_Player_empty_inventory(){
		parse(new Player("silly boi.png", Direction.Down));
	}

	@Test
	public void test_Player_with_inventory(){
		Player in = new Player("silly bo.png", Direction.Down);
		in.add(new Potion("Health Potion", "health potion.png", 50));
		in.add(new Armour("Bronze Chestplate", "chestplate one.png", Armour.TYPE.TORSO, 5.6));
		in.add(new Weapon("Iron Sword", "swordasdasd.png", true, 8.8));
		in.add(new Key("Church Key", "basickey1.png", 101));

		parse(in);
	}

	@Test
	public void test_Static_no_Behavior(){
		parse(new Static("Large Rock","gigantic ass rock.png"));
	}

	@Test
	public void test_Floor(){
		parse(new Floor("Grass","grass.png"));
	}

	@Test
	public void test_Monster(){
		NPCBehavior behavior = new MonsterStrategy(50);
		NPC in = new NPC("Zombie","zombie.png", behavior, 50, Direction.Down);
		parse(in);
	}

	@Test
	public void test_Friendly_Dialog(){
		FriendlyStrategy friendly = new FriendlyStrategy();

		friendly.addDialog("Great weather today!", "Did you hear about the beast?", "It's called THE BEAST");

		NPC in = new NPC("Fred","man1.png", friendly, 50, Direction.Down);

		parse(in);
	}

	@Test
	public void test_Line(){
		String line = "(0,2) Player \"Player\" \"silly boi.png\" 100.0 Down 0 Inventory Weapon \"Iron Sword\" \"swordasdasd.png\" true 8.8 Armour \"Bronze Chestplate\" \"chestplate one.png\" TORSO 5.6 Potion \"Health Potion\" \"health potion.png\" 50 Key \"Church Key\" \"basickey1.png\" 101";

		Scanner s = new Scanner(line);

		try {
			Coord coord = parseCoordinate(s);
			Entity entity = parseEntity(s);
			System.out.println(coord.toString()+" "+entity.toString());
		} catch(SyntaxError e){
			fail(e.getMessage());
		}
	}
}
