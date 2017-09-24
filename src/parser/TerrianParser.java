package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TerrianParser{

	/** 
	 * This class is the terrain parser, for parsing any objects which do not interact with the player, NPCs or enemies.
	 * I.E. Walls, floors, Trees, Cliffs etc.
	 * @author Jordan
	 * */
	
	
	/** 
	 * This method is for the initialization of the region file.
	 * */
	public void init_scanner(File region)throws IOException{
		try{
			BufferedReader regionBuff = new BufferedReader(new FileReader(region));
			
		}
		catch(IOException e){
			throw new IOException("File failed to initialise!");
		}
		finally{
			
		}
	}
}
