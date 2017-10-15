package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.entity.Mob;
import com.swen.herebethetitle.graphics.HUD;
import com.swen.herebethetitle.graphics.Sprite;
import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HUDTest extends TestWindow {


    @Test
    @Ignore
    /**
     * Sets up a HUD with Sprites that contain no images. So all
     * we will see from this is where the slots will be positioned in
     * the canvas.
     */
    public  void initialize(){
        testCode = new Operation(){public void run(Canvas c){
            Sprite [] armourSprites = defaultArmourSprites();
            Sprite weapon = new Sprite(null, new GridLocation(0,0));

            HUD hud = new HUD(c);
	        hud.drawAll(weapon, armourSprites, new ArrayList<>(), c);
        }};
    }

    @Test
    @Ignore
    /**
     * Set up a HUD with sprites placed in the armour slots.
     */
    public void armourSlots() {
        testCode = new Operation() {
            public void run(Canvas c) {
                Sprite[] armourSprites = new Sprite[4];
                for (int i = 0; i < armourSprites.length; i++) {
                    armourSprites[i] = new Sprite(new Image("file:res/static/tudorwall.png"), new GridLocation(0, -i));
                }
                Sprite weapon = new Sprite(null, new GridLocation(0, 0));

                HUD hud = new HUD(c);
	            hud.drawAll(weapon, armourSprites, new ArrayList<>(), c);
            }

        };
    }

    @Test
    @Ignore
    /**
     * Set up a HUD with sprites placed in the armour slots.
     */
    public void weaponSpot() {
        testCode = new Operation() {
            public void run(Canvas c) {
                Sprite[] armourSprites = defaultArmourSprites();
                Sprite weapon = new Sprite(new Image("file:res/mob/wizard.png"), new GridLocation(0, 0));

                HUD hud = new HUD(c);
	            hud.drawAll(weapon, armourSprites, new ArrayList<>(), c);
            }

        };
    }
    @Test
    @Ignore
    public void inventorySlots(){
        testCode = new Operation(){public void run(Canvas c) {
            Sprite [] armourSprites = defaultArmourSprites();

            List invSprites = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                invSprites.add(new Sprite(null, new GridLocation(i, 0)));
            }
            Sprite weapon = new Sprite(null, new GridLocation(0, 0));

            HUD hud = new HUD(c);
            hud.drawAll(weapon, armourSprites, invSprites, c);
        }};
    }


    @Test
    @Ignore
    public void drawTextBox() {
        testCode = new Operation() {
            public void run(Canvas c) {
                Sprite [] armourSprites = defaultArmourSprites();
                Sprite weapon = new Sprite(null, new GridLocation(0,0));
                HUD hud = new HUD(c);
                hud.createTextBox("\"Great weather today!\" \"Did you hear about the beast?\" \"It's called THE BEAST",
                        new Image("file:res/mob/wise old man.png"), "Wize old man");
                hud.drawAll(weapon, armourSprites, new ArrayList<>(), c);
                GraphicsContext gc = c.getGraphicsContext2D();
            }
        };
    }

    @Test
    @Ignore
    public void HalfHealth() {
        testCode = new Operation() {
            public void run(Canvas c) {
                Sprite [] armourSprites = defaultArmourSprites();
                Sprite weapon = new Sprite(null, new GridLocation(0,0));
                HUD hud = new HUD(c);
                hud.updateHealth(Mob.FULL_HEALTH/2);
                hud.drawAll(weapon, armourSprites, new ArrayList<>(), c);
            }
        };
    }
    @Test
    @Ignore
    public void QuarterHealth() {
        testCode = new Operation() {
            public void run(Canvas c) {
                Sprite [] armourSprites = defaultArmourSprites();
                Sprite weapon = new Sprite(null, new GridLocation(0,0));
                HUD hud = new HUD(c);
                hud.updateHealth(Mob.FULL_HEALTH/4);
                hud.drawAll(weapon, armourSprites, new ArrayList<>(), c);
            }
        };
    }

    private Sprite[] defaultArmourSprites(){
        Sprite[] armourSprites = new Sprite[4];
        for (int i = 0; i < armourSprites.length; i++) {
            armourSprites[i] = new Sprite(null, new GridLocation(0, -i));
        }
        return armourSprites;
    }

}
