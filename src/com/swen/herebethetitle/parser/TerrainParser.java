package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;
import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.model.Tile;

/** 
 * This class is the terrain parser, which is the first stage in the parsing process. for parsing any objects which do not interact with the player, NPCs or enemies.
 * I.E. Walls, floors, Trees, Cliffs etc.
 * @author Jordan
 * */
public class TerrainParser{	
	private ArrayList<String[]> stringArray = new ArrayList<String[]>();
	private Tile[][] regionArray;
	private String[] neighbouringRegions = new String[4];
	
	
	public TerrainParser(File region) throws IOException{
		this(new FileReader(region));
	}
	
	/**
	 * Creates a new terrain parser that reads from an arbitrary reader object.
	 */
	public TerrainParser(Reader reader) throws IOException {
	    initScanner(reader);
	    parseStringArray();
	}
	/** 
	 * This method is for the initialization of the region file.
	 * */
	private void initScanner(Reader reader)throws IOException{
		BufferedReader regionBuff = null;
		stringArray = new ArrayList<String[]>();
		try{
			regionBuff = new BufferedReader(reader);
			String line = regionBuff.readLine();
			Scanner s = new Scanner(line);
			while(s.hasNext("neighbours:")) {//only used in regions with neighbours.
				s.next();
				parseNeighbouringRegions(s);
				line = regionBuff.readLine();
			}
			while(line != null){
				String[] split = line.split("");
				stringArray.add(split);
				line = regionBuff.readLine();
			}

		}
		catch(IOException e){
			System.out.println("I/O exception: " + e.toString());
			throw new FileNotFoundException("File failed to initialise!");
		}
		finally{
			regionBuff.close();
		}
	}
	
	/** 
	 * Generates a list of neighbours for the Region Manager to use.
	 * @author - Jordan Milburn
	 * */
	private void parseNeighbouringRegions(Scanner s) {
		for(int i = 0; i < 4; i++) {
			neighbouringRegions[i] = s.next();
		}
	}
	/** 
	 * parses the single character string array such that it generates a new Node and gives that node a mapEntity
	 * @author - Jordan Milburn
	 * */
	private void parseStringArray(){
		regionArray = new Tile[stringArray.size()][stringArray.get(0).length];
		System.out.print(stringArray.get(0).length + " " + stringArray.size());
		for(int i = 0; i < stringArray.size(); i++){
			for(int j = 0; j < stringArray.get(i).length; j++){
				Tile z = new Tile(i, j, stringArray.get(i)[j]);
				
				Entity possiblyFloor = parseMapEntity(stringArray.get(i)[j]);
				
				if (possiblyFloor instanceof Floor) {
                    z.setMapFloor((Floor)possiblyFloor);
				} else if (possiblyFloor instanceof Static){
					z.add((Static)possiblyFloor);
				} else {
				    throw new IllegalArgumentException("malformed terrain, floor must be Floor");
				}
				regionArray[i][j] = z;
			}
		}
		
	}
	
	/** 
	 * This method turns the string[] within the ArrayList into a region entity to be set for that region.
	 * It is essentially going to be a big ass if-elseif-else method for every type of terrain possible.
	 * @author - Jordan
	 * @return Entity implementing object.
	 * */
	private Entity parseMapEntity(String p){
		//TODO: Implement the parsing of map entities as they are created. 
		if(p.equals(".")){//
			Floor f = new Floor("Grass","grass.png");
			return f;
		}
		else if(p.equals("w")) {
			Floor w = new Floor("TudorWall", "tudorwall.png");

			return w;
		}
		return null;
	}
	
	/** 
	 * This method attempts to connect the various nodes together for the parser. Checks first if it should connect within the limits of the 2d array structure
	 * Delete this method if it isn't used by integration stage.
	 * @author - Jordan
	 * */
	public Tile[][] connectNetworks(Tile[][] toConnect){
		try{
			for(int i = 0; i < toConnect.length; i++){
				for(int j = 0; j < toConnect[i].length; j++){
					for(int k = -1; k <= 1; k++){
						for(int l = -1; l <= 1; l++){
							if(!connectNode(toConnect.length, toConnect[i].length, i, j, k, l)){
								
							}
							else{
								toConnect[i][j].getNeighbours().add(toConnect[i+k][j+l]);
								
							}
						}
					}
				}
			}
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("Index Out of Bounds in the connection of the node network!");
		}
		return toConnect;
	}
	
	/**
	 * Parses a tile array.
	 */
	public Tile[][] parseTiles() {
	    parseStringArray();
	    connectNetworks(getRA());
	    return getRA();
	}
	
	/**
	 * Boundary checking method for ensuring only neighbours within acceptable ranges are added.
	 * @author - Jordan Milburn
	 *  */
	
	private boolean connectNode(int rowLength, int columnLength, int i, int j, int k, int l){
		if(i+k < 0 || j+l < 0){//if less than zero
			return false;
		}
		if(rowLength <= i+k || columnLength <= j+l){//if outside the boundary of the array.
			return false;
		}
		if(k == 0 && l == 0){
			return false;
		}
		return true;
	}
	
	public ArrayList<String[]> getStringArray(){
		return stringArray;
	}
	
	public Tile[][] getRA(){
		return regionArray;
	}
	
	public String[] getNeighbouringRegions() {
		return neighbouringRegions;
	}
}
