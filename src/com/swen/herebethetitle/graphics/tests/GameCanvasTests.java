package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.Terrain;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.Assert.*;

public class GameCanvasTests{

    Operation testMethod;
    TestStage ts;

    @Test
    public void test01_initialize(){
        testMethod = new Operation(){public void run(GameCanvas c){
           c.drawAll();
        }};
        showWindow();
    }



    private void showWindow(){
        ts = new TestStage();
        ts.setTestMethod(testMethod);
        ts.startWindow();
    }
}
