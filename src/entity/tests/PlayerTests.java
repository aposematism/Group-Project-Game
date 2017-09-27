package entity.tests;

import entity.*;
import entity.items.Armour;
import util.*;
import javafx.scene.image.Image;
import org.junit.Test;


/**
 * Created by Mark on 19/09/2017.
 */
public class PlayerTests {
	@Test
	public void test_1(){
		GridLocation[][] grid = new GridLocation[10][10];
		for(int row=0; row<grid.length; row++){
			for(int col=0; col<grid[row].length; col++){
				grid[row][col] = new GridLocation(col, row);
			}
		}

		Player p = new Player(grid[2][2], new Image(""), Direction.Down);

		Armour a = new Armour(p, grid[2][3], new Image(""), Armour.TYPE.HELMET, 3.5);


	}
}
