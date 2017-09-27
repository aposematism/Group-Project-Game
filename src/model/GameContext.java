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
		this.player = new Player(this, currentRegion.getTiles()[0][0], new Image(""), Direction.Up);
	}
	
	public static Region CreateTestRegion() {
		/*define weather*/
		Region.Weather weather = Region.Weather.SUNNY;

		Tile[][] tiles = new Tile[10][10];
		for(int col=0;col<tiles[0].length;col++){
			for(int row=0;row<tiles.length;row++){
				tiles[col][row] = new Tile(col, row, "");
			}
		}
		
		/*create test region*/
		return new Region(weather, tiles);
	}
	
	public Region getCurrentRegion() { return this.currentRegion; }

	public Player getPlayer() { return this.player; }
}
