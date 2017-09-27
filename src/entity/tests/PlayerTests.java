package entity.tests;

import entity.*;
import entity.items.Armour;
import model.GameContext;
import util.*;
import javafx.scene.image.Image;
import org.junit.Test;


/**
 * Created by Mark on 19/09/2017.
 */
public class PlayerTests {
	@Test
	public void test_1(){
		GameContext context = new GameContext();

		GridLocation[][] grid = new GridLocation[10][10];
		for(int row=0; row<grid.length; row++){
			for(int col=0; col<grid[row].length; col++){
				grid[row][col] = new GridLocation(col, row);
			}
		}

		Player p = new Player(context, grid[2][2], new Image(""), Direction.Down);

		Armour a = new Armour(context, grid[2][3], new Image(""), Armour.TYPE.HELMET, 3.5);


	}
}
