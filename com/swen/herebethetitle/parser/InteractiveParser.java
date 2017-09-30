package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * This is the interactive entity parser, which is the second stage of the parsing process. It initialises in a similar way, but it is fundamentally different.
 * The file is to be read "item_enum x_position y_position"
 * @author - Jordan
 * */
public class InteractiveParser {
	ArrayList<String[]> stringArray;
	
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
				String[] split = line.split(" ");
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
	 * This method does actually process the line in the interactive file for each item it intends to load.
	 * @author - Jordan
	 * */
	public void parseStringArray(){
		
	}
}
