package com.swen.herebethetitle.parser.parsertests;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.parser.EntityParser;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mark on 9/10/2017.
 */
public class ExternalTestsMark {

	@Test
	public void test_text_to_player_with_inventory() {
		String text = "Player \"Dude\" \"\" 50.0 Down 0 Inventory { Weapon \"Cool ass sword\" \"\" false 5.0 }";
		Scanner s = new Scanner(text);
		EntityParser e = new EntityParser();
		Entity entity = e.parseEntity(s);
		assertEquals(text, entity.toString());
	}

//	@Test
//	public void test_text_to_player_with_inventory(){
//		GameContext context = new GameContext(new Player("Dude","",50.0, 0,Direction.Down,
//				new Weapon("Cool ass sword","",false,50.0)
//		));
//
//		try {
//			ReverseParser p = new ReverseParser(context.getCurrentRegion());
//		} catch(IOException | SyntaxError e){
//			fail("Exception "+e.getMessage());
//		}
//
//		assertEquals(text, entity.toString());
//
//		String text = "Player \"Dude\" \"\" 50.0 Down 0 Inventory { Weapon \"Cool ass sword\" \"\" false 5.0 }";
//	}

}
