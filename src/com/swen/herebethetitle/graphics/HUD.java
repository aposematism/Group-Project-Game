package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Inventory;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Armour;
import com.swen.herebethetitle.entity.items.Weapon;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.omg.CORBA.DynAnyPackage.Invalid;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * The Heads Up Display that will be drawn to the GameCanvas. Currently supports
 * drawing of weapon and armour slots
 * @author weirjosh
 */
public class HUD {

    private final int slotSize = 45;
    private final int hGap = 6;
    private final int vGap = 6;

    private GridManager armourGrid;
    private GridManager weaponGrid;

    /**
     * Create a new HUD on the specified Canvas
     * @param c
     * @weirjosh
     */
    public HUD(Canvas c){
        createGridLayouts(c);
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
    public void drawAll(Sprite weapon, Sprite[] armour, Canvas c){
        GraphicsContext gc = c.getGraphicsContext2D();
        renderSlot(weapon, weaponGrid, gc);
        for(Sprite s: armour){
            renderSlot(s, armourGrid, gc);
        }
    }

    private void createGridLayouts(Canvas c){
        weaponGrid = new GridManager(slotSize/2,
                (int)c.getHeight()-slotSize*(3/2)-vGap,
                slotSize, 0,0);

        armourGrid = new GridManager(
                slotSize*(2) + weaponGrid.getRealCoordinates(new GridLocation(0,0)).x,
                (int)c.getHeight()-slotSize*(3/2)-vGap,
                slotSize, vGap, hGap);
    }

    private void renderSlot(Sprite sprite, GridManager grid, GraphicsContext gc){
        GridLocation loc = sprite.getLocation();
        Point p = grid.getRealCoordinates(loc);
        System.out.println(loc);

        gc.setStroke(Color.GRAY);
        gc.setFill(new Color(0,0,0,0.5));
        gc.setLineWidth(4);
        gc.fillRect(p.x, p.y, slotSize, slotSize);
        gc.strokeRect(p.x, p.y, slotSize, slotSize);
        sprite.draw(gc, grid);

    }
}
