package com.swen.herebethetitle.parser.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
					String line1 = in1.readLine();
					String line2 = in2.readLine();
					while(!line1.contains("entities:")) {
						if(!line1.equals(line2)) {
							System.out.println(line1 + " does not match " + line2);
							fail("failure during region portion of reverser tests.");
						}
						line1 = in1.readLine();
						line2 = in2.readLine();
					}
					while(line1.length() < 2) {
						line1 = in1.readLine();
						line2 = in2.readLine();
					}
					HashMap<String, String> entityArray1 = new HashMap<String, String>();
					HashMap<String, String> entityArray2 = new HashMap<String, String>();
					while(line1.contains(" = ")) {
						String[] split1 = line1.split("="); 
						String[] split2 = line2.split("=");
						entityArray1.put(split1[0], split1[1]);
						entityArray2.put(split2[0], split2[1]);
						line1 = in1.readLine();
						line2 = in2.readLine();
					}
					while(line1.length() < 2) {
						line1 = in1.readLine();
						line2 = in2.readLine();
					}
					ArrayList<String> map1 = new ArrayList<String>();
					ArrayList<String> map2 = new ArrayList<String>();
					line1 = in1.readLine();
					line2 = in2.readLine();
					while(line1.contains("map:")) {
						if(!line1.equals(line2)) {
							System.out.println(line1 + " does not match " + line2);
							fail("failure during map portion of reverser tests.");
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
				File outputFile = rp.writeToFile("full reversal file");
				assert(compareFiles(inputFile, outputFile));
			}

}
