package com.swen.herebethetitle.parser.parsertests;
import org.junit.Test;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.parser.TerrainParser;



/** 
 * This class will handle all white-box tests for the Terrain Parser and ensure it functions as far as I can tell.
 * @author - Jordan
 * */
public class TerrainParserTests {
	File testTerrain = new File("res/test_terrain_file.txt");
	/** 
	 * Testing the first method for initializing properly
	 * */
	@Test
	public void initscanner_load() throws IOException{
		System.out.println("Testing scanner initialization for the txt file");
		try{
			TerrainParser tp = new TerrainParser(testTerrain);
		}
		catch(IOException e){
			fail("IOException during the Terrain Parser Initialization");
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
			TerrainParser tp = new TerrainParser(testTerrain);
			Tile[][] rA = tp.getRA();
			ArrayList<String[]> sA = tp.getStringArray();
			for(int i = 0; i < sA.size(); i++){
				for(int j = 0; j < sA.get(i).length; j++){
					//System.out.println(rA[i][j].getCharacter() + " " + sA.get(i)[j]);
					if(!(rA[i][j].getCharacter().equals(sA.get(i)[j]))){
						fail("character pulled from regionArray does not match stringArray");
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
			TerrainParser tp = new TerrainParser(testTerrain);
			Tile[][] rA = tp.getRA();
			tp.connectNetworks(rA);
			for(int i = 0; i < rA.length; i++){
				for(int j = 0; j < rA[i].length; j++){
					System.out.print(rA[i][j].getNeighbours().size());
				}
				System.out.println("");
			}
		}
		catch(IOException e){
			fail("IOException during the Terrain Parser connecting nodes test");
		}
		catch(IndexOutOfBoundsException e){
			fail("Index fell out of bounds during the parsing process or in examining the classes themselves.");
		}
	}
	
	@Test
	public void connecting_nodes_fail_test() {
		try{
			System.out.println("Testing String Parser to see if it connects the right number of nodes");
			TerrainParser tp = new TerrainParser(testTerrain);
			Tile[][] rA = tp.getRA();
			tp.connectNetworks(rA);
			for(int i = 0; i < rA.length; i++){
				for(int j = 0; j < rA[i].length; j++){
					System.out.print(rA[i][j].getNeighbours().size());
				}
				System.out.println("");
			}
		}
		catch(IOException e){
			fail("IOException during the Terrain Parser connecting nodes test");
		}
		catch(IndexOutOfBoundsException e){
			fail("Index fell out of bounds during the parsing process or in examining the classes themselves.");
		}
	}
}
