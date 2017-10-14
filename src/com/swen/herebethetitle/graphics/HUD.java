package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.Mob;
import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Collection;
import java.util.Optional;


/**
 * The Heads Up Display that will be drawn to the GameCanvas. Currently supports
 * drawing of weapon and armour slots
 * @author weirjosh
 */
public class HUD {

    private final int slotSize = 45;

    private GridManager armourGrid;
    private GridManager weaponGrid;
	private GridManager itemGrid;
	private GridManager textBoxGrid;

    private Optional<TextBox> textBox = Optional.empty();

    private final int textBoxWidth;
    private final int textBoxHeight;

    private final int healthBarWidth;
    private final int healthBarHeight;
    private final Point healthBarPos;
    private double health;
    private HealthBar healthBar;

    private final int GAP = 10;

    /**
     * Create a new HUD on the specified Canvas
     * @param c
     * @weirjosh
     */
    public HUD(Canvas c){
        createGridLayouts(c);

        textBoxWidth = (int)(3*c.getWidth()/5);
        textBoxHeight = (int)(c.getHeight()/4);

        healthBarWidth = (int)(c.getWidth()/4);
        healthBarHeight = slotSize/2;
        healthBarPos = new Point(slotSize/2, slotSize/2);
        health = Mob.FULL_HEALTH;
        healthBar = new HealthBar();


    }

//    public void drawAll(Canvas c){
//        GraphicsContext gc = c.getGraphicsContext2D();
//        Armour[] armour = inventory.getArmour();
//        for(int i=0;i<armour.length;i++){
//            Entity e = armour[i];
//            Image sprite = getImage(e);
//            renderSlot(sprite, armourGrid, gc, 0, i);
//        }
//
//        Image weaponImg = null;
//        Optional<Weapon> weapon = inventory.getWeapon();
//        if(weapon.isPresent()){
//            weaponImg = getImage(weapon.get());
//        }
//        renderSlot(weaponImg, weaponGrid, gc, 0,0);
//
//    }

    /**
     * renders the HUD to the given Canvas
     * @param weapon The sprite that goes in the weapon slot
     * @param armour The sprites that go in the armour slots
     * @param c The canvas to render to.
     * @author weirjosh
     */
    public void drawAll(Sprite weapon, Sprite[] armour, Collection<Sprite> items, Canvas c) {
        GraphicsContext gc = c.getGraphicsContext2D();
        renderSlot(weapon, weaponGrid, gc);
        for(Sprite s: armour){
            renderSlot(s, armourGrid, gc);
        }
	    for (Sprite s : items) {
		    renderSlot(s, itemGrid, gc);
	    }

	    textBox.ifPresent(textBox1 -> textBox1.draw(gc, textBoxGrid));
        drawHealthMeter(gc);
    }

    /**
     * Adds a text box to the HUD, with the icon displayed in the
     * top right corner and the message displayed
     */
    public void createTextBox(String msg, Image icon, String name){
        textBox = Optional.of(new TextBox(icon,
                new GridLocation(0,0), msg, textBoxWidth, textBoxHeight, name));
    }

    /**
     * Remove any text boxes that might be present in this HUD.
     */
    public void removeTextBox(){
        textBox = Optional.empty();
    }


    /**
     * Updates the health meter for the health bar.
     */
    public void updateHealth(double health){
        this.health = health;
    }


    private void drawHealthMeter(GraphicsContext gc){
        healthBar.draw(healthBarPos.x, healthBarPos.y,
                health, gc, healthBarWidth, healthBarHeight);
    }


    private void createGridLayouts(Canvas c){
        weaponGrid = new GridManager(slotSize/2,
		        (int) c.getHeight() - slotSize * (3 / 2) - GAP,
		        slotSize);

        armourGrid = new GridManager(
                slotSize/2,
		        (int) c.getHeight() - slotSize * (3 / 2) - 7 * GAP - slotSize,
		        slotSize);

        itemGrid = new GridManager(
                slotSize * (2) + armourGrid.getRealCoordinates(new GridLocation(0, 0)).x,
		        (int) c.getHeight() - slotSize * (3 / 2) - GAP,
		        slotSize);

        textBoxGrid = new GridManager(
                (int)c.getWidth()/4, (int)c.getHeight()/2, slotSize);

        weaponGrid.setGap(GAP);
        armourGrid.setGap(GAP);
        itemGrid.setGap(GAP);
    }


    private void renderSlot(Sprite sprite, GridManager grid, GraphicsContext gc){
        GridLocation loc = sprite.getLocation();
        Point p = grid.getRealCoordinates(loc);

        gc.setStroke(Color.GRAY);
        gc.setFill(new Color(0,0,0,0.5));
        gc.setLineWidth(4);
        gc.fillRect(p.x, p.y, slotSize, slotSize);
        gc.strokeRect(p.x, p.y, slotSize, slotSize);
        sprite.draw(gc, grid);

    }
}
