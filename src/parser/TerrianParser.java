package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Entity;
import graph.JordanNode;

public class TerrianParser{

	/** 
	 * This class is the terrain parser, for parsing any objects which do not interact with the player, NPCs or enemies.
	 * I.E. Walls, floors, Trees, Cliffs etc.
	 * @author Jordan
	 * */
	
	ArrayList<String[]> stringArray;
	JordanNode[][] regionArray;
	
	/** 
	 * This method is for the initialization of the region file.
	 * */
	public void init_scanner(File region)throws IOException{
		BufferedReader regionBuff = null;
		stringArray = new ArrayList<String[]>();
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
			throw new IOException("File failed to initialise!");
		}
		finally{
			regionBuff.close();
			System.out.println("File Loaded to charArray");
		}
	}
	
	/** 
	 * parses the single character string array such that it generates a new Node and gives that node a mapEntity
	 * */
	public void parseStringArray(){
		regionArray = new JordanNode[stringArray.get(0).length][stringArray.size()];
		for(int i = 0; i < stringArray.size(); i++){
			for(int j = 0; j < stringArray.get(i).length; j++){
				JordanNode z = new JordanNode(i, j);
				z.setMapEntity(parseMapEntity(stringArray.get(i)[j]));
				regionArray[i][j] = z;
			} 
		}
	}
	
	/** 
	 * This method turns the string[] within the ArrayList into a region entity to be set for that region.
	 * */
	public Entity parseMapEntity(String p){
		
		return null;
	}
}
