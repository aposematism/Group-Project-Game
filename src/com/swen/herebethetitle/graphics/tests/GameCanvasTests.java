package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.graphics.GridManager;
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

import static org.junit.Assert.fail;

public class GameCanvasTests{

    Operation testMethod;
    TestStage ts = new TestStage();

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
        try {
            testMethod = new Operation() {
                public void run(GameCanvas c) {
                    c.drawAll();
                    c.switchRegions(TestStage.playerless(5, 1));
                    c.drawAll();
                }
            };
            update(ts);
            fail("shouldn't be able to switch to region without player");
        } catch(RuntimeException e){}
    }

    @Test
    public void test04_translated(){
        testMethod = new Operation(){public void run(GameCanvas c){
            //c.drawAll();
            c.switchRegions(TestStage.emptyGrassField(5,5, 4, 3));
            c.drawAll();
        }};
        update(ts);
    }

    @Test
    public void test05_changeGridManager(){
        testMethod = new Operation(){public void run(GameCanvas c){
            //c.drawAll();
            c.setGrid(new GridManager(40, 2,2));
            c.drawAll();
        }};
        update(ts);
    }



    private void update(TestStage ts){
        ts.setTestMethod(testMethod);
        ts.startWindow();
    }
}
