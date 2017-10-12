package com.swen.herebethetitle.graphics.tests;

import com.swen.herebethetitle.graphics.HUD;
import com.swen.herebethetitle.graphics.Sprite;
import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

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
            Sprite [] armourSprites = new Sprite[4];
            for(int i=0;i<armourSprites.length;i++){
                armourSprites[i] = new Sprite(null, new GridLocation(i,0));
            }
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
                    armourSprites[i] = new Sprite(new Image("file:res/tudorwall.png"), new GridLocation(i, 0));
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
                Sprite[] armourSprites = new Sprite[4];
                for (int i = 0; i < armourSprites.length; i++) {
                    armourSprites[i] = new Sprite(null, new GridLocation(i, 0));
                }
                Sprite weapon = new Sprite(new Image("file:res/wizard.png"), new GridLocation(0, 0));

                HUD hud = new HUD(c);
	            hud.drawAll(weapon, armourSprites, new ArrayList<>(), c);
            }

        };
    }

}
