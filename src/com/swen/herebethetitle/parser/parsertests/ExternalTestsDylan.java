package com.swen.herebethetitle.parser.parsertests;

import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.parser.TerrainParser;
import org.junit.Ignore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ExternalTestsDylan {
	private static Region parseTrivialRegion() {
		Region region = parse(".....\n.....\n.....\n.....");
		return region;
	}

	/**
	 * Parses a region.
	 */
	private static Region parse(String inputText) {
		Tile[][] tiles = null;
		try {
			BufferedReader reader = new BufferedReader(new StringReader(inputText));
			TerrainParser parser = new TerrainParser(reader);
			tiles = parser.parseTiles();
		} catch (IOException e) {
			fail(e.getMessage());
		}
		return new Region(tiles);
	}

	@Ignore
	public void testDefaultFloorIsGrass() {
        Region region = parseTrivialRegion();

		for (int y=0; y<4; y++) {
            for (int x=0; x<4; x++) {
                Tile tile = region.get(x, y);

                assertEquals("Grass", tile.getMapFloor().getName());
                assertTrue(tile.getInteractives().isEmpty());
            }
        }
    }
    
    @Ignore
    public void testMapDiagonalWall() {
        Region region = parse("w...\n.w..\n..w.\n...w");

	    for (int y=0; y<4; y++) {
            for (int x=0; x<4; x++) {
                Tile tile = region.get(x, y);

	            // Check for diagonal
                if (x == y) {
                    assertEquals(1, tile.getInteractives().size());

	                assertEquals("TudorWall", tile.getMapFloor().getName());
                } else { // not a diagonal
                    assertTrue(tile.getInteractives().isEmpty());
                }
            }
        }
    }
}
