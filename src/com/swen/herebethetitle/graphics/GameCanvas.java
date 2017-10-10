package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Inventory;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.Weapon;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * This Canvas is responsible for drawing everything that is going on in the GameContext,
 * Terrain, entities and HUD included.
 * @author weirjosh
 */
public class GameCanvas extends Canvas {
    private GameContext context;

    private Map<String, Image> imageMap = new HashMap<>();

    private HUD hud;
    private WorldRenderer world;

    /**
     * Create a new GameCanvas
     * @param context GameContext that will frequently be queried
     * @param width width of the canvas
     * @param height height of the canvas
     * @author weirjosh
     */
    public GameCanvas(GameContext context, int width, int height){
        super(width, height);
        world = new WorldRenderer();
        hud = new HUD(this);
        this.context = context;

        update();
    }

    /**
     * Draw the world graphics, and the HUD.
     * @author weirjosh
     */
    public void update(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0,0,getWidth(),getHeight());
        updateWorld();
        updateHUD();
    }

    /**
     * Get a GridLocation from the given mouse coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return GridLocation of the cell that was clicked
     * @author weirjosh
     */
    public GridLocation getMousePos(int x, int y){
        return world.getLocation(new Point(x,y));
    }




    private void updateWorld() {
        //Maps the background sprite of each time to the list of
        //front layer sprites of the tile.
        Map<Sprite, List<Sprite>> worldSprites = new HashMap<>();

        Region r = context.getCurrentRegion();
        Iterator<Tile> tiles = r.iterator();
        GridLocation playerLoc = new GridLocation(0,0);

        //For each tile in the region
        while(tiles.hasNext()) {
            //Get current tile and location
            Tile t = tiles.next();
            GridLocation l = t.getLocation();
            List<Sprite> frontLayer = new ArrayList<>();

            //Get all interactive entities on this tile
            for(Entity e: t.getInteractives()){
                frontLayer.add(new Sprite(getImage(e), l));
                //Record the player
                if(e instanceof Player){
                    playerLoc = l;
                }
            }
            worldSprites.put(new Sprite(getImage(t.getMapFloor()), l), frontLayer);
        }
        world.drawAll(worldSprites, playerLoc, this);
    }

    private void updateHUD(){
        //Query the Inventory of the player.
        Inventory inv = context.getPlayer().inventory();

        //List of inventory entities from player inventory
        Entity[] armourEntities = inv.getArmour();
        Optional<Weapon> weaponEntities = inv.getWeapon();

        //List of armour sprites to render
        Sprite[] armourSprites = new Sprite[inv.getArmour().length];
        for(int i=0;i<armourEntities.length; i++) {
            Entity e = armourEntities[i];
            Image img = getImage(e);

            //Armour entities will be placed along the bottom row, so increase
            //x value as going along.
            armourSprites[i] = new Sprite(img, new GridLocation(i, 0));
        }

        Image weaponImage = null;
        if(weaponEntities.isPresent()){
            weaponImage = getImage(weaponEntities.get());
        }
        Sprite weaponSprite = new Sprite(weaponImage, new GridLocation(0,0));

        hud.drawAll(weaponSprite, armourSprites, this);
    }



    private Image getImage(Entity e) {
        if(e == null) return null;
        if (!imageMap.containsKey(e.getSpritePath())) {
            imageMap.put(e.getSpritePath(), new Image("file:res/"+e.getSpritePath()));
         }
        return imageMap.get(e.getSpritePath());
    }
}
