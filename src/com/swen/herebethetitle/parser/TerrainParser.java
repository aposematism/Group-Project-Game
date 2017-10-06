package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;
import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.model.Tile;

/** 
 * This class is the terrain parser, which is the first stage in the parsing process. for parsing any objects which do not interact with the player, NPCs or enemies.
 * I.E. Walls, floors, Trees, Cliffs etc.
 * @author Jordan
 * */
public class TerrainParser{	
	static ArrayList<String[]> stringArray = new ArrayList<String[]>();
	static Tile[][] regionArray;
	
	/** 
	 * This method is for the initialization of the region file.
	 * */
	public static void init_scanner(File region)throws IOException{
		BufferedReader regionBuff = null;
		try{
			regionBuff = new BufferedReader(new FileReader(region));
			String line = regionBuff.readLine();
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
	 * parses the single character string array such that it generates a new Node and gives that node a mapEntity
	 * @author - Jordan
	 * */
	public static void parseStringArray(){
		regionArray = new Tile[stringArray.size()][stringArray.get(0).length];
		System.out.print(stringArray.get(0).length + " " + stringArray.size());
		for(int i = 0; i < stringArray.size(); i++){
			for(int j = 0; j < stringArray.get(i).length; j++){
				Tile z = new Tile(i, j, stringArray.get(i)[j]);
				z.setMapFloor(parseMapEntity(stringArray.get(i)[j]));
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
	public static Entity parseMapEntity(String p){
		//TODO: Implement the parsing of map entities as they are created. 
		if(p.equals(".")){//
			Floor f = new Floor("Grass","grass.png");
			return f;
		}
		else if(p.equals("w")) {
			Static w = new Static("TudorWall", "tudorwall.png");
		}
		return null;
	}
	
	/** 
	 * This method attempts to connect the various nodes together for the parser. Checks first if it should connect within the limits of the 2d array structure
	 * @author - Jordan
	 * */
	public static Tile[][] connectNetworks(Tile[][] toConnect){
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
	 * Boundary checking method for ensuring only neighbours within acceptable ranges are added.
	 *  */
	
	public static boolean connectNode(int rowLength, int columnLength, int i, int j, int k, int l){
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
	
	public static Tile[][] getRA(){
		return regionArray;
	}
}
