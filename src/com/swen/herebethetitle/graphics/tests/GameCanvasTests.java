package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Terrain;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.Region;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.Assert.*;

public class GameCanvasTests extends Application {

    private Stage window;
   // Image im = new Image("file:res/grass.png");

    @Test
    public void test_1(){
        GameCanvas g = new GameCanvas(new Region(10, 10));
    }

    public void start(Stage stage){

    }



}
