package com.swen.herebethetitle.parser.parsertests;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Weapon;
import com.swen.herebethetitle.parser.InteractiveParser;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Scanner;

public class InteractiveParserTests {
			@Test
			public void test_generic_item_creation(){
				InteractiveParser ip = new InteractiveParser();
				Entity e = ip.parseStringArray("0 0 weapon true 5");
				assertTrue(e instanceof Weapon);
			}
//	@Test
//	public void test_Player_input(){
//		Player p = new Player(null, Direction.Down);
//		System.out.println(p.toString());
//		InteractiveParser i = new InteractiveParser();
//		Scanner s = new Scanner(p.toString());
//		Player p2 = i.parsePlayer(s);
//	}
}
