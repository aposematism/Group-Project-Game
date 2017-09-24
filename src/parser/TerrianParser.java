package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TerrianParser{

	/** 
	 * This class is the terrain parser, for parsing any objects which do not interact with the player, NPCs or enemies.
	 * I.E. Walls, floors, Trees, Cliffs etc.
	 * @author Jordan
	 * */
	
	ArrayList<char[]> charArray;
	
	/** 
	 * This method is for the initialization of the region file.
	 * */
	public void init_scanner(File region)throws IOException{
		BufferedReader regionBuff = null;
		charArray = new ArrayList<char[]>();
		try{
			regionBuff = new BufferedReader(new FileReader(region));
			String line = regionBuff.readLine();
			while(line != null){
				char[] split = line.toCharArray();
				charArray.add(split);
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
	 * This method turns the char[] within the ArrayList into the game graph. 
	 * */
	public void parseCharArray(){
		
	}
}
