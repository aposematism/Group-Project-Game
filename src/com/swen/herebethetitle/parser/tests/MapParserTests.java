package com.swen.herebethetitle.parser.tests;

import org.junit.Test;

import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.parser.MapParser;

import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapParserTests {
	File testTerrain = new File("res/test_terrain_file.txt");
	/**
	 * Testing the first method for initializing properly
	 * */
	@Test
	public void initscanner_load() throws IOException{
		System.out.println("Testing scanner initialization for the txt file");
		try{
			MapParser tp = new MapParser(testTerrain);
		}
		catch(IOException e){
			fail("IOException during the Terrain Parser Initialization");
			throw new FileNotFoundException("File not found!");
		}
	}
	/**
	 * Checking if the string stored in character matches that of the terrain map provided.
	 *
	 * @author - Jordan
	 * @throws IOException
	 * */
	@Test
	public void string_parser_test(){

		try{
			System.out.println("Testing String Parser to see Tile matches the txt file");
			MapParser tp = new MapParser(testTerrain);
			Scanner s = new Scanner(testTerrain);
			ArrayList<String[]> terrainArray = tp.getCharArray().getList();
			ArrayList<String[]> fileArray = new ArrayList<String[]>();
			while(s.hasNext()) {//loop the file.
				String line = s.nextLine();
				if(line.contains("map:")) {
					line = s.nextLine();
					while(line != null) {
						System.out.println(line);
						String[] zs = line.split("");
						fileArray.add(zs);
						if(s.hasNext()) {
							line = s.nextLine();
						}
						else {
							line = null;
						}
					}
				}
			}
			for(int i = 0; i < fileArray.size(); i++){
				for(int j = 0; j < fileArray.get(0).length; j++){
					if(!terrainArray.get(i)[j].equals(fileArray.get(i)[j])) {
						System.out.println(terrainArray.get(i)[j] + " != " + fileArray.get(i)[j]);
						fail("Map in generated region does not match file.");
					}
					
				}
				
			}
			
		}
		catch(IOException e){
			fail("IOException during the Terrain Parser string parser test");
		}
		catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			fail("Index fell out of bounds during the parsing process or in examining the classes themselves.");
		}
	}
	/**
	 * This test is for ensuring the tiles which are added together have no IndexOutOfBoundsExceptions and are only neighbours with each other.
	 * It also prints the number of neighbours it knows.
	 *
	 * @Author - Jordan
	* */
	@Test
	public void connecting_nodes_test(){
		try{
			System.out.println("Testing String Parser to see if it connects the right number of nodes");
			MapParser tp = new MapParser(testTerrain);
			
		}
		catch(IOException e){
			fail("IOException during the Terrain Parser connecting nodes test");
		}
		catch(IndexOutOfBoundsException e){
			fail("Index fell out of bounds during the parsing process or in examining the classes themselves.");
		}
	}
}
