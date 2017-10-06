package com.swen.herebethetitle.parser.parsertests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.parser.ReverseParser;
import com.swen.herebethetitle.parser.SyntaxError;
import com.swen.herebethetitle.parser.TerrainParser;

public class ReverseParserTests {
			/** 
			 * Test creates an output file from the entities and compares it with the test entity class.
			 * Compares string by string to see if it matches.
			 * */
			@Test
			public void test_currentstate_from_file() throws IOException, SyntaxError{
				ReverseParser.reverse_scanner(new File("res/test_entity_parser.txt"));
				ReverseParser.pullString(ReverseParser.getInteractiveList());
				ReverseParser.writeToFile("testoutputfile.txt");
				
			}
			
			@Test
			public void test_currentstate_from_region() throws IOException {
				TerrainParser.init_scanner(new File("res/test_terrain_file.txt"));
				TerrainParser.parseStringArray();
				TerrainParser.connectNetworks(TerrainParser.getRA());
				Region r = new Region(TerrainParser.getRA());
			}
			
}
