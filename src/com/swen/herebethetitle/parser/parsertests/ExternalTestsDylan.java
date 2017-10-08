package com.swen.herebethetitle.parser.parsertests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.parser.TerrainParser;

public class ExternalTestsDylan {
    @Test
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
    
    @Test
    public void testMapDiagonalWall() {
        Region region = parse("w...\n.w..\n..w.\n...w");
        
        for (int y=0; y<4; y++) {
            for (int x=0; x<4; x++) {
                Tile tile = region.get(x, y);
                
                // Check for diagonal
                if (x == y) {
                    assertEquals(0, tile.getInteractives().size());
                    
                    assertEquals("TudorWall", tile.getMapFloor().getName());
                } else { // not a diagonal
                    assertTrue(tile.getInteractives().isEmpty());
                }
            }
        }
    }
    
    private static Region parseTrivialRegion() {
        Region region = parse(".....\n.....\n.....\n.....");
        return region;
    }
    
    /**
     * Parses a region.
     */
    private static Region parse(String inputText) {
        try {
            TerrainParser.init_scanner(new StringReader(inputText));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        Tile[][] tiles = TerrainParser.parseTiles();
        return new Region(tiles);
    }
}
