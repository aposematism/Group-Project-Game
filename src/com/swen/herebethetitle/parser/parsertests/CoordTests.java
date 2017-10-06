package com.swen.herebethetitle.parser.parsertests;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.ai.Behavior;
import com.swen.herebethetitle.entity.ai.Monster;
import com.swen.herebethetitle.parser.Coord;
import com.swen.herebethetitle.parser.SyntaxError;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import java.util.Scanner;

import static com.swen.herebethetitle.parser.Coord.parseCoordinate;
import static org.junit.Assert.fail;

/**
 * Created by Mark on 5/10/2017.
 */
public class CoordTests {
	@Test
	public void test_Coord_single_digit(){
		String line = "(0,1) ";
		Behavior behavior = new Monster(50);
		NPC in = new NPC("Zombie","zombie.png", behavior, 50, Direction.Down);
		line += in.toString();
		Scanner s = new Scanner(line);

		try {
			Coord c = parseCoordinate(s);
			System.out.println(c.toString());
		} catch(SyntaxError e){
			fail(e.getMessage());
		}
	}

	@Test
	public void test_Coord_multi_digit(){
		String line = "(04,11) ";
		Behavior behavior = new Monster(50);
		NPC in = new NPC("Zombie","zombie.png", behavior, 50, Direction.Down);
		line += in.toString();
		Scanner s = new Scanner(line);

		try {
			Coord c = parseCoordinate(s);
			System.out.println(c.toString());
		} catch(SyntaxError e){
			fail(e.getMessage());
		}
	}

	@Test
	public void test_Coord_invalid(){
		String coord = "((5,,0)";

		Scanner s = new Scanner(coord);

		try {
			Coord c = parseCoordinate(s);
			fail("Should've thrown exception");
		} catch(SyntaxError e){}
	}
}
