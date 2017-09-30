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

public class GameCanvasTests extends Application {

    Image grass = new Image("file:res/grass.png");
    Image player = new Image("file:res/wizard.png");

    public static void main(String[] args){
        launch(args);
    }

    public void test_1(Stage stage){
        stage.setTitle("test_01");
        GameCanvas canvas = new GameCanvas(emptyGrassField(), 328, 328);

        canvas.drawAll();
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.drawImage(player, 0,0,30,30);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void start(Stage stage){
        test_1(stage);
    }



    private Region emptyGrassField(){
        int rows = 5, cols = 7;
        Region r = new Region(cols, rows);

        for(int row=0;row<rows;row++) {
            for (int col = 0; col < cols; col++) {
                Tile t = new Tile(col, row, "");
                t.setMapTerrain(new Terrain(grass));
                r.set(new GridLocation(col,row), t);
            }
        }
        r.get(0,1).add(new Player(player, Direction.Right));
        return r;
    }
}
