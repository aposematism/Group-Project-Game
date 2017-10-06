package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.graphics.WorldRenderer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

import javafx.util.Duration;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldRendererTests extends TestWindow {

    private Sprite player;

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

    @Test
    @Ignore
    /**
     * Create a WorldRenderer preset with a 10x10 grid of sprites
     * with grass images as the background sprite, and Sprites
     * with cobblestone images along the diagonal of the grid, with
     * the player being offset in the center.
     * @author weirjosh
     */
    public void createTerrain(){
        testCode = new Operation(){public void run(Canvas c){


            Map<Sprite, List<Sprite>> sprites = new HashMap<>();
            Sprite player = new Sprite(new Image("file:res/wizard.png"), new GridLocation(0,0));

            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    Sprite grass = new Sprite(new Image("file:res/grass.png"), new GridLocation(i,j));
                    List<Sprite> interactives = new ArrayList<>();

                    if(i==j){
                        interactives.add(new Sprite(new Image("file:res/cobble master.png"),
                                new GridLocation(i,j)));
                    }
                    sprites.put(grass, interactives);
                    if(i==5 && j==5){
                        player = new Sprite(new Image("file:res/wizard.png"), new GridLocation(i,j));
                        interactives.add(player);
                    }
                }
            }

            WorldRenderer world = new WorldRenderer();
            world.drawAll(sprites, player.getLocation(), c);
        }};
    }

    @Test
    @Ignore
    /**
     * Move an imaginary player to the right 9 times. Note
     * that the player isn't supposed to display on the screen.
     * Only the offset of the terrain is show. And it should move
     * from the top left to the top right every 4 milisecs.
     * @author weirjosh
     */
    public void testTranslation(){
        testCode = new Operation(){public void run(Canvas c){
            player = new Sprite(new Image("file:res/wizard.png"), new GridLocation(0,0));
            WorldRenderer world = new WorldRenderer();
            Map<Sprite, List<Sprite>> sprites = new HashMap<>();

            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    Sprite grass = new Sprite(new Image("file:res/grass.png"), new GridLocation(i,j));
                    List<Sprite> interactives = new ArrayList<>();

                    if(i==j){
                        interactives.add(new Sprite(new Image("file:res/cobble master.png"),
                                new GridLocation(i,j)));
                    }
                    sprites.put(grass, interactives);
                }
            }
            Timeline updateTimeline = new Timeline(new KeyFrame(
                    Duration.millis(400),
                    ae -> {
                        movePlayerRight();
                        world.drawAll(sprites, player.getLocation(), c);
                    }));
            updateTimeline.setCycleCount(9);
            updateTimeline.play();
        }};
    }

    private void movePlayerRight(){
        player.setLocation(new GridLocation(player.getLocation().x+1,
                player.getLocation().y));
    }
}
