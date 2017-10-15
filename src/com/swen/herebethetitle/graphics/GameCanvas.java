package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.logic.GameListener;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;
import com.swen.herebethetitle.util.Direction;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This Canvas is responsible for drawing everything that is going on in the GameContext,
 * Terrain, entities and HUD included.
 * @author weirjosh
 */
public class GameCanvas extends Canvas implements GameListener {
	private static final Image ERROR = new Image("file:res/error.png");
    private static boolean grid;

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
        if(grid){
            world.getGrid().toggleBorder();
        }
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

    /**
     * Toggles the gap between cells on or off.
     */
    public static void toggleGrid(){
        if(grid){
            grid = false;
        }else{
            grid = true;
        }
    }

    /**
     * Toggle the grid from the pause menu
     */
    public void toggleGridInGame(){

    }

    /**
     * @param msg The String message of the NPC conversation
     * @param npc The NPC of which the player is talking to
     */
    public void createTextBox(String msg, Entity npc, String name){
        hud.createTextBox(msg, getImage(npc), name);
    }

    /**
     * Remove any current text box from the HUD (when the player finishes
     * speaking with an NPC).
     */
    public void removeTextBox(){
        hud.removeTextBox();
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
            armourSprites[i] = new Sprite(img, new GridLocation(0, -i));
        }

        Image weaponImage = null;
        if(weaponEntities.isPresent()){
            weaponImage = getImage(weaponEntities.get());
        }
        Sprite weaponSprite = new Sprite(weaponImage, new GridLocation(0,0));

	    List<Sprite> itemSprites = new ArrayList<>();
	    for (int i=0;i<inv.getItems().size();i++) {

		    Image img = getImage(inv.getItems().get(i));
		    itemSprites.add(new Sprite(img, new GridLocation(i, 0)));
	    }

        hud.updateHealth(context.getPlayer().getHealth());
	    hud.drawAll(weaponSprite, armourSprites, itemSprites, this);
    }



    private Image getImage(Entity e) {
        if(e == null) return null;
        if (!imageMap.containsKey(e.getSpritePath())) {
	        Image image = new Image("file:res/" + e.getSpritePath());

	        if (image.getHeight() == 0 && image.getWidth() == 0) //Image didn't load properly
		        image = ERROR;

	        imageMap.put(e.getSpritePath(), image);
        }

//        if(e instanceof Mob){
//            Mob mob = (Mob)e;
//            Direction d = mob.getDirection();
//
//            ImageView iv = new ImageView(imageMap.get(mob.getSpritePath()));
//            SnapshotParameters params = new SnapshotParameters();
//            params.setFill(Color.TRANSPARENT);
//            iv.setRotate(d.ordinal()*90);
//            //System.out.println(iv.getRotate());
//            return iv.snapshot(params, null);
//        }


        return imageMap.get(e.getSpritePath());
    }

	@Override
	public void onGameCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerMoved(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerAttacked(Player player, NPC attacker) {
    }

	@Override
	public void onPlayerKilled(Player player, Optional<NPC> aggressor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerPickup(Player player, Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDrop(Player player, Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCAttacked(NPC victim) {
		
	}

	@Override
	public void onNPCKilled(NPC npc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCDialogBegin(NPC npc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNPCDialogMessage(NPC npc, String message) {
        createTextBox(message, npc, npc.getName());
	}

	@Override
	public void onNPCDialogEnd(NPC npc) {
		removeTextBox();
	}

	@Override
	public void onDoorUnlocked(Static door) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoorUnlockFailed(Static door, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoorOpened(Static door) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoorClosed(Static door) {
		// TODO Auto-generated method stub
		
	}

}
