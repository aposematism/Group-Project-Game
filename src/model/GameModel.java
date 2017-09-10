package model;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * Top-level class for dealing with game model information.
 * @author J Woods
 *
 */
public class GameModel {
	public Region currentRegion;
	
	public GameModel() {
		currentRegion = testRegion();
	}
	
	public Region testRegion() {
		/*define weather*/
		Region.Weather weather = Region.Weather.SUNNY;
		/*define default tile to be drawn if no other tiles are present*/
		Sprite defaultTile = new Sprite(new Image("file:resources/grass.png"));
		/*create world objects*/
		ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();
		
		
		/*create test region*/
		return new Region(weather, defaultTile, worldObjects);
	}
}
