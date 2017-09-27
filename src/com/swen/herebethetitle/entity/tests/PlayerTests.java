package com.swen.herebethetitle.entity.tests;

import org.junit.Test;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Armour;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.Direction;


/**
 * Created by Mark on 19/09/2017.
 */
public class PlayerTests {
	@Test
	public void test_1(){
		GameContext context = new GameContext();

		Tile[][] grid = new Tile[10][10];
		for(int row=0; row<grid.length; row++){
			for(int col=0; col<grid[row].length; col++){
				grid[row][col] = new Tile(col, row, "");
			}
		}

		Player p = new Player(context, null, Direction.Down);

		Armour a = new Armour(context, null, Armour.TYPE.HELMET, 3.5);


	}
}
