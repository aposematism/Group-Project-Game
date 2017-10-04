package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.graphics.WorldRenderer;
import javafx.application.Application;
import com.swen.herebethetitle.graphics.GridManager;
import com.swen.herebethetitle.graphics.HUD;
import com.swen.herebethetitle.graphics.Sprite;
import com.swen.herebethetitle.util.GridLocation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldRendererTests extends TestWindow {


    @Test
    @Ignore
    /**
     * Create a WorldRenderer preset with a grid of randomly assorted
     * sprites with no images, and one Player Sprite with the wizard
     * image
     * @author weirjosh
     */
    public void initialize(){
        testCode = new Operation(){public void run(Canvas c){


            Map<Sprite, List<Sprite>> sprites = new HashMap<>();
            Sprite player = new Sprite(new Image("file:res/wizard.png"), new GridLocation(0,0));

            for(int i=0;i<5;i++){
                List<Sprite> interactives = new ArrayList<>();
                for(int j=0;j<3;j++){
                    interactives.add(new Sprite(null, new GridLocation(j,0)));
                }
                sprites.put(
                        new Sprite(null,
                                new GridLocation(i,0)),
                        interactives);
                if(i==0) interactives.add(player);
            }

            WorldRenderer world = new WorldRenderer();
            world.drawAll(sprites, player.getLocation(), c);
        }};
    }

}
