package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestStage extends Application {
	private static String grassPath = "file:res/grass.png";
	private static String playerPath = "file:res/wizard.png";
	private static String tudorWallPath = "file:res/tudorwall.png";

	private static Image grass;
	private static Image player;
	private static Image tudorwall;

	private GameCanvas currentCanvas;
	private static Operation testMethod;

	public TestStage(){ }

	public void setTestMethod(Operation o){
		testMethod = o;
	}

	public void startWindow(){
		launch();
	}

	public void runTestMethod(){
		testMethod.run(currentCanvas);
	}


	public void start(Stage stage){
		grass = new Image(grassPath);
		player = new Image(playerPath);
		tudorwall = new Image(tudorWallPath);

		currentCanvas = new GameCanvas(emptyGrassField(7,7, 0, 0), 350, 350);

		stage.setTitle("Test");
		StackPane root = new StackPane();
		root.getChildren().add(currentCanvas);
		stage.setScene(new Scene(root));
		stage.show();

		testMethod.run(currentCanvas);
	}



	public static Region emptyGrassField(int cols, int rows, int playerCol, int playerRow){
		Region r = new Region(cols, rows);

		for(int row=0;row<rows;row++) {
			for (int col = 0; col < cols; col++) {
				Tile t = new Tile(col, row, "");
				t.setMapFloor(new Floor(grassPath));
				r.set(new GridLocation(col,row), t);
			}
		}
		r.get(playerCol,playerRow).add(new Player(playerPath, Direction.Right));
		return r;
	}

	public static Region grasslandWithExtras(int cols, int rows, int playerCol, int playerRow){
		Region r = emptyGrassField(cols, rows, 0,0);

		r.get(0,1).add(new GameCanvasTests.DummyEntity(tudorwall));
		r.get(0, 1).add(new GameCanvasTests.DummyEntity(player));

		return r;
	}

	public static Region playerless(int cols, int rows){
		Region r = emptyGrassField(cols, rows, 0, 0);
		Player p = null;

		for(Entity e: r.getPlayerTile().getInteractives()){
			if(e instanceof Player) p = (Player)e;
		}

		r.remove(p);
		return r;
	}

}
