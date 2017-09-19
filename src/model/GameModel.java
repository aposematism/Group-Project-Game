package model;

import java.util.ArrayList;

import entity.Player;
import javafx.scene.image.Image;
import util.Direction;
import util.GridLocation;

/**
 * Top-level class for dealing with game model information.
 * @author J Woods
 *
 */
public class GameModel {
	public Region currentRegion;
	public Player player;
	
	public GameModel() {
		this.currentRegion = testRegion();
		this.player = new Player(new GridLocation(0,0), Direction.Up);
	}
	
	public Region testRegion() {
		/*define weather*/
		Region.Weather weather = Region.Weather.SUNNY;
		/*define default tile to be drawn if no other tiles are present*/
		Sprite defaultTile = new Sprite(new Image("file:res/grass.png"));
		/*create world objects*/
		ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();
		Grid grid = new Grid(30, 30);
		
		
		/*create test region*/
		return new Region(weather, defaultTile, worldObjects, grid);
	}
}
