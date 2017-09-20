package model;

import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import javafx.scene.image.Image;
import util.Direction;
import util.GridLocation;

/**
 * Top-level class for dealing with game model information.
 * @author J Woods
 *
 */
public class GameContext {
	public Region currentRegion;
	public Player player;
	
	public GameContext() {
		this.currentRegion = CreateTestRegion();
		this.player = new Player(new GridLocation(0,0), Direction.Up);
	}
	
	public static Region CreateTestRegion() {
		/*define weather*/
		Region.Weather weather = Region.Weather.SUNNY;
		/*define default tile to be drawn if no other tiles are present*/
		Sprite defaultTile = new Sprite(new Image("file:res/grass.png"));
		/*create world objects*/
		ArrayList<Entity> worldObjects = new ArrayList<Entity>();
		
		/*create test region*/
		return new Region(weather, defaultTile, worldObjects);
	}
	
	public Region getCurrentRegion() { return this.currentRegion; }
}
