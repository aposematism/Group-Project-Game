package com.swen.herebethetitle.graphics.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;

/**
 * Canvas container for rendering the various components of the graphics library.
 * This class sets up a javafx application, creating a window containing a canvas.
 *
 * Tests are parsed to it through the 'testCode' Operation object. The reason for this
 * is so the test code can be run in the same thread as the javafx application.
 */
public class TestWindow extends Application {
    protected static Operation testCode;

    @Before
    public void initOperation(){
        testCode = new Operation() {
            public void run(Canvas c) {
            }};
    }

    @After
    public void launchWindow(){
       // launch();
    }

    public void start(Stage window){
        window.setTitle("HUD test");
        StackPane root = new StackPane();

        Canvas c = new Canvas(350, 350);
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0,0,c.getWidth(),c.getHeight());

        root.getChildren().add(c);
        window.setScene(new Scene(root));
        window.show();

        if(testCode != null) testCode.run(c);
    }

}
