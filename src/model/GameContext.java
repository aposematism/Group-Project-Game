package model;

import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		this.player = new Player(new GridLocation(0,0), new Image(""), Direction.Up);
	}
	
	public static Region CreateTestRegion() {
		/*define weather*/
		Region.Weather weather = Region.Weather.SUNNY;
		/*create world objects*/
		ArrayList<Entity> worldObjects = new ArrayList<Entity>();
		
		/*create test region*/
		return new Region(weather, worldObjects);
	}
	
	public Region getCurrentRegion() { return this.currentRegion; }

	public Player getPlayer() { return this.player; }
}
