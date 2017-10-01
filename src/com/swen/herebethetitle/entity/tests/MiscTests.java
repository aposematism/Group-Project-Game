package com.swen.herebethetitle.entity.tests;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.Behavior;
import com.swen.herebethetitle.entity.ai.Monster;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.stationeries.Door;
import com.swen.herebethetitle.entity.stationeries.Stationary;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Test;

import javafx.scene.image.*;

import static org.junit.Assert.*;

/**
 * Created by Mark on 1/10/2017.
 */
public class MiscTests {

	@Test
	public void test_Floor(){
		Floor f = new Floor(null);
		assertTrue(f.isPenetrable());
		assertNotNull(f.toString());
	}

	@Test
	public void test_Door_toString(){
		Door d = new Door(123, Door.STATE.LOCKED);
		Stationary s = new Stationary(null);
		s.setBehavior(d);
		assertNotNull(d.toString());
		assertNotNull(s.toString());
	}

	@Test
	public void test_Inventory_toString(){
		Inventory i = new Inventory();
		assertNotNull(i.toString());
	}

	@Test
	public void test_Items_toString(){
		Armour a = new Armour(null, Armour.TYPE.TORSO, 10);
		assertNotNull(a.toString());

		Weapon w = new Weapon(null,false,5);
		assertNotNull(w.toString());

		Key k = new Key(null,0);
		assertNotNull(k.toString());

		Potion p = new Potion(null,5);
		assertNotNull(p.toString());
	}

	@Test
	public void test_Stationary_toString(){
		Stationary s = new Stationary(null);
		assertNotNull(s.toString());
	}

	@Test
	public void test_Entity_toString(){
		Entity e = new Entity(null) {
			public void interact(GameContext context) {}
			public boolean isPenetrable() { return false; }
		};
		assertNotNull(e.toString());
	}

	@Test
	public void test_NPCs_toString(){
		Behavior a = new Monster(50);
		NPC n = new NPC(null, a,80, Direction.Down);

		assertNotNull(n.toString());
		assertNotNull(a.toString());
	}

	@Test
	public void test_Player_toString(){
		Player p = new Player(null, Direction.Down);
		assertNotNull(p.toString());
	}
}