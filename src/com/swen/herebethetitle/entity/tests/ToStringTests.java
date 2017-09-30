package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mark on 30/09/2017.
 */
public class ToStringTests {
	@Test
	public void test_Key_toString(){
		Item i = new Key(null, 123);
		assertNotNull(i.toString());
	}

	@Test
	public void test_Player_toString(){
		Player p = new Player(null, Direction.Down);
		assertNotNull(p.toString());
	}
}
