package com.swen.herebethetitle.parser.parsertests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.parser.EntityParser;
import com.swen.herebethetitle.parser.ReverseParser;
import com.swen.herebethetitle.parser.SyntaxError;
import com.swen.herebethetitle.parser.TerrainParser;

public class ReverseParserTests {
	
			/** 
			 * Helper method to compare files.
			 * @throws IOException, FileNotFoundException 
			 * @Author - Jordan Milburn
			 * */
			private boolean compareFiles(File f1, File f2) {
				try {
					BufferedReader in1 = new BufferedReader(new FileReader(f1));
					BufferedReader in2 = new BufferedReader(new FileReader(f2));
					String line1 = in1.readLine();
					String line2 = in2.readLine();
					while(line1 != null) {
						if(!line1.equals(line2)) {
							return false;
						}
						line1 = in1.readLine();
						line2 = in2.readLine();
					}
				}catch(FileNotFoundException e) {
					fail("File not Found!");
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				return true;
			}
			/** 
			 * Test creates an output file from the entities and compares it with the test entity class.
			 * Compares string by string to see if it matches.
			 * @throws IOException, SyntaxError 
			 * @Author - Jordan Milburn
			 * */
			@Test
			public void test_currentstate_from_file() throws IOException, SyntaxError{
				File inputFile = new File("res/test_entity_parser.txt");
				ReverseParser rp = new ReverseParser(inputFile);
				rp.pullString(rp.getInteractiveList());
				File outputFile = rp.writeToFile("test output file.txt");
				assert(compareFiles(inputFile, outputFile));
			}
			
			/** 
			 * This is essentially a full chain of action test.
			 * Runs from TerrainParser through Entity Parser to Reverser.
			 * @throws IOException, SyntaxError 
			 * @Author - Jordan Milburn
			 * */
			@Test
			public void test_currentstate_from_region() throws IOException, SyntaxError {
				TerrainParser tp = new TerrainParser(new File("res/test_terrain_file.txt"));
				tp.connectNetworks(tp.getRA());
				Region r = new Region(tp.getRA());
				File inputFile = new File("res/test_entity_parser.txt");
				EntityParser ep = new EntityParser(inputFile);
				ep.parseEntityToRegion(r);
				ReverseParser.parseRegion(r);
				File outputFile = ReverseParser.writeToFile("full reversal file.txt");
				assert(compareFiles(inputFile, outputFile));
			}
			
}
