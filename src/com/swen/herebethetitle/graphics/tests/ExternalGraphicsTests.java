package com.swen.herebethetitle.graphics.tests;

import org.junit.Ignore;
import org.junit.Test;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * External tests for the graphics library.
 * @author J Woods
 *
 */
public class ExternalGraphicsTests extends Application{

	private Stage window;
	private GameCanvas canvas;
	int playerX =0;
	int playerY=0;

	@Test
	@Ignore
	public void test_initialization(){
		launch();
	}

//	@Test
	public void test_drawing() {
		Group root = new Group();
		canvas = new GameCanvas(TestStage.grasslandWithExtras(10, 20, 0, 0),1000,600);
		root.getChildren().add(canvas);
		window.setScene(new Scene(root, 1000, 600));
		canvas.drawAll();
		Timeline updateTimeline = new Timeline(new KeyFrame(
		        Duration.millis(500.0d),
		        ae -> {	canvas.switchRegions(TestStage.grasslandWithExtras(10, 20, ++playerX, ++playerY));
		        		canvas.drawAll();}));
		updateTimeline.setCycleCount(10);	//10 update calls
		updateTimeline.play();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.show();
		test_drawing();
	}

	public static class DummyEntity extends Entity{
		public boolean isPenetrable(){ return false; }
		public void interact(GameContext context, Notifier notifiier){}
		public String toString(){return null;}
		public DummyEntity(String fileName){super("",fileName);}
	}
}
