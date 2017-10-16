package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.logic.GameListener;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.GridLocation;
import com.swen.herebethetitle.util.Resources;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This Canvas is responsible for drawing everything that is going on in the GameContext,
 * Terrain, entities and HUD included.
 * @author weirjosh
 */
public class GameCanvas extends Canvas implements GameListener {
	private static final Image ERROR = Resources.getImage("error.png");
    private static boolean grid;

	private GameContext context;

    private Map<String, Image> imageMap = new HashMap<>();
    private Map<Entity, MobSprite> interactives = new HashMap<>();

    private HUD hud;
    private WorldRenderer world;

    private Optional<String> entityAtMouse = Optional.empty();
    ToolTip toolTip = new ToolTip();

    private int mouseX;
    private int mouseY;

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

        entityAtMouse.ifPresent(e -> toolTip.draw(mouseX, mouseY, entityAtMouse.get(), gc));
    }

    /**
     * Get a GridLocation from the given mouse coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return GridLocation of the cell that was clicked
     * @author weirjosh
     */
    public GridLocation getMousePos(int x, int y){
        mouseX = x;
        mouseY = y;

        GridLocation loc = world.getLocation(new Point(x,y));
        Entity e = context.getCurrentRegion().get(loc).getTopEntity();

        if(e instanceof Mob || e instanceof Item){
            entityAtMouse = Optional.of(e.getName());
        }else{
            entityAtMouse = Optional.empty();
        }
        return loc;
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
                frontLayer.add(getSprite(e, l));
                //Record the player
                if(e instanceof Player){
                    playerLoc = l;
                }
            }
            worldSprites.put(getSprite(t.getMapFloor(), l), frontLayer);
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


    private Sprite getSprite(Entity e, GridLocation l){
        if(e instanceof Mob) {
            if (!interactives.containsKey(e)) {
                MobSprite sprite = new MobSprite(getImage(e), l);
                interactives.put(e, sprite);
                System.out.println(l);
            } else {
                //interactives.get(e).setImage(getImage(e));
                interactives.get(e).setLocation(l);
            }
            return interactives.get(e);
        }else{
            return new Sprite(getImage(e), l);
        }
}

    private Image getImage(Entity e) {
        if(e == null) return null;
        if (!imageMap.containsKey(e.getSpritePath())) {
	        Image image = Resources.getImage(e.getSpritePath());

	        if (image.getHeight() == 0 && image.getWidth() == 0) //Image didn't load properly
		        image = ERROR;

	        imageMap.put(e.getSpritePath(), image);
        }
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
        MobSprite s = interactives.get(player);
        s.damage();
	}

	@Override
	public void onPlayerKilled(Player player, Optional<NPC> aggressor) {
        MobSprite s = interactives.get(player);
        s.damage();
	}

	@Override
	public void onPlayerPickup(Player player, Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerDrop(Player player, Item item) {
		
	}

	@Override
	public void onNPCAttacked(NPC victim) {
        MobSprite s = interactives.get(victim);
        s.damage();
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

	@Override
	public void onGameWin() {

	}

	@Override
	public void onGameLose() {

	}
}
