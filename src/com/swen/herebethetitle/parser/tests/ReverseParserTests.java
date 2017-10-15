package com.swen.herebethetitle.parser.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.Line;

import org.junit.Test;

import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.parser.EntityParser;
import com.swen.herebethetitle.parser.ReverseParser;
import com.swen.herebethetitle.parser.MapParser;

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
					String lineFile = in1.readLine();
					while(!lineFile.contains("entities:")) {//
						lineFile = in1.readLine();
					}
					lineFile = in1.readLine();
					System.out.println(lineFile);
					String ReverseLine = in2.readLine();
					System.out.println(ReverseLine);
					while(lineFile != null) {
						if(!lineFile.equals(ReverseLine)) {
							return false;
						}
						lineFile = in1.readLine();
						ReverseLine = in2.readLine();
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
			public void test_currentstate_from_file() throws IOException{
				File inputFile = new File("res/test_entity_parser.txt");
				ReverseParser rp = new ReverseParser(inputFile);
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
			public void test_currentstate_from_region() throws IOException{
				File inputFile = new File("res/new_game.txt");
				MapParser tp = new MapParser(inputFile);
				Region r = tp.getRegion();
				ReverseParser rp = new ReverseParser(r);
				File outputFile = rp.writeToFile("full reversal file.txt");
				assert(compareFiles(inputFile, outputFile));
			}

}
