package com.swen.herebethetitle.graphics.tests;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.graphics.GridManager;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Tile;

import javafx.scene.image.Image;

public class GameCanvasTests{
	static Operation testMethod;
	static TestStage ts;

	static boolean failTestMethod = false;

	@BeforeClass
	public static void runBeforeTest(){
		ts = new TestStage();
	}

	@AfterClass
	public static void runAfterTest() {
		try {
			ts.startWindow();
		}catch(RuntimeException E){
			if(!failTestMethod) {
				failTestMethod = true;
				fail(E.getMessage());
			}
		}
	}

	@Test
	public void test01_initialize(){
		testMethod = new Operation(){public void run(GameCanvas c){
			c.drawAll();
		}};
		update(ts);
	}

	@Test
	public void test02_switchCanvas(){
		testMethod = new Operation(){public void run(GameCanvas c){
			c.drawAll();
			c.switchRegions(TestStage.emptyGrassField(5,1, 0, 0));
			c.drawAll();
		}};
		update(ts);
	}

	@Test
	public void test03_CanvasWithoutPlayer(){
		failTestMethod = true;
		try {
			testMethod = new Operation() {
				public void run(GameCanvas c) {
					c.drawAll();
					c.switchRegions(TestStage.playerless(5, 1));
					c.drawAll();
				}
			};
			update(ts);
			//fail("shouldn't be able to switch to region without player");
		} catch(RuntimeException e){}
	}

	@Test
	public void test04_translated(){
		testMethod = new Operation(){public void run(GameCanvas c){
			c.drawAll();
			c.switchRegions(TestStage.emptyGrassField(5,5, 4, 3));
			c.drawAll();
		}};
		update(ts);
	}

	@Test
	public void test05_changeGridManager(){
		testMethod = new Operation(){public void run(GameCanvas c){
			c.drawAll();
			c.setGrid(new GridManager(40, 2,2));
			c.drawAll();
		}};
		update(ts);
	}

	@Test
	public void test06_ModifyGridManager(){
		testMethod = new Operation(){public void run(GameCanvas c){
			c.drawAll();
			c.getGrid().setCellSize(10);
			c.getGrid().sethGap(5);
			c.drawAll();
		}};
		update(ts);
	}

	@Test
	public void test07_ModifyRegion(){
		testMethod = new Operation(){public void run(GameCanvas c){
			Tile tile = c.getRegion().get(0,1);
			Entity extra = new DummyEntity(new Image("file:res/cobble snow.png"));
			tile.add(extra);
			c.drawAll();
		}};
		update(ts);
	}

	@Test
	public void test08_StackedEntities(){
		testMethod = new Operation(){public void run(GameCanvas c){
			c.switchRegions(TestStage.grasslandWithExtras(2,2,0,0));
			c.drawAll();
		}};
		update(ts);
	}

	private void update(TestStage ts){
		ts.setTestMethod(testMethod);
	}

	public static class DummyEntity extends Entity{
		public boolean isPenetrable(){ return false; }
		public void interact(GameContext context, Notifier notifiier){}
		public String toString(){return null;}
		public DummyEntity(Image image){super("","");}
	}
}
