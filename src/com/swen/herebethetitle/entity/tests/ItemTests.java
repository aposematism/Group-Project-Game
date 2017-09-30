package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mark on 30/09/2017.
 */
public class ItemTests {
	@Test
	public void test_itemEquals_1(){
		Item i1 = new Key(null,0);
		Item i2 = new Key(null,0);

		assertFalse(i1.equals(i2));
	}
}
