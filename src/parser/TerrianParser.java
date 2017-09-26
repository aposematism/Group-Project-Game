package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import entity.Entity;
import model.Tile;

/** 
 * This class is the terrain parser, which is the first stage in the parsing process. for parsing any objects which do not interact with the player, NPCs or enemies.
 * I.E. Walls, floors, Trees, Cliffs etc.
 * @author Jordan
 * */
public class TerrianParser{	
	ArrayList<String[]> stringArray;
	Tile[][] regionArray;
	
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
			System.out.println("File Loaded to StringArray in Terrain Parser");
		}
	}
	
	/** 
	 * parses the single character string array such that it generates a new Node and gives that node a mapEntity
	 * @author - Jordan
	 * */
	public void parseStringArray(){
		regionArray = new Tile[stringArray.get(0).length][stringArray.size()];
		for(int i = 0; i < stringArray.size(); i++){
			for(int j = 0; j < stringArray.get(i).length; j++){
				Tile z = new Tile(i, j);
				z.setMapEntity(parseMapEntity(stringArray.get(i)[j]));
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
	public Entity parseMapEntity(String p){
		//TODO: Implement the parsing of map entities as they are created. 
		if(p.equals("")){//
			
		}
		return null;
	}
	
	/** 
	 * This method attempts to connect the various nodes together for the parser.
	 * @author - Jordan
	 * */
	public Tile[][] connectNetworks(Tile[][] toConnect){
		for(int i = 0; i < toConnect.length; i++){
			for(int j = 0; j < toConnect[i].length; j++){
				for(int k = -1; k < 1; k++){
					for(int l = -1; l < 1; l++){
						toConnect[i][j].getNeighbours().add(toConnect[k][l]);
					}
				}
				
			}
		}
		return toConnect;
	}
}
