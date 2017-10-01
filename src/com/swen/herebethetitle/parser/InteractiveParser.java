package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Armour;
import com.swen.herebethetitle.entity.items.Armour.TYPE;
import com.swen.herebethetitle.entity.items.Weapon;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;
import com.swen.herebethetitle.entity.stationeries.Door;
import com.swen.herebethetitle.entity.stationeries.Door.STATE;

import javafx.scene.image.Image;

/** 
 * This is the interactive entity parser, which is the second stage of the parsing process. It initialises in a similar way, but it is fundamentally different.
 * The file is to be read "item_enum x_position y_position"
 * @author - Jordan
 * */
public class InteractiveParser {
	ArrayList<String> stringArray;
	
	/** 
	 * This method is for the initialization of the region file.
	 * */
	public void init_scanner(File region)throws IOException{
		BufferedReader regionBuff = null;
		stringArray = new ArrayList<String>();
		try{
			regionBuff = new BufferedReader(new FileReader(region));
			String line = regionBuff.readLine();
			while(line != null){
				stringArray.add(line);
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
	public void parseStringArray(Region reg){
		for(String s : stringArray){
			String[] split = s.split(" ");
			Entity item = parseInteractive(reg, split);
			Tile t = reg.get(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
			t.add(item);
		}
	}
	
	/** 
	 * This is the parser for each item. Takes the name, x/y position, then the image(for now) and finally its properties.
	 * this looks terrible. I might split it into other classes.
	 * @author - Jordan
	 * */
	public Entity parseInteractive(Region reg, String[] splinter){
		String item_name = splinter[0];
		TYPE t;
		STATE s;
		double d;
		int n;
		switch(item_name.toLowerCase()){
			case "weapon":
				return parseWeapon(splinter[3], Double.parseDouble(splinter[4]));
			case "helmet":
				return parseArmour(splinter[3], TYPE.HELMET, Double.parseDouble(splinter[4]));
			case "boots":
				return parseArmour(splinter[3], TYPE.BOOTS, Double.parseDouble(splinter[4]));
			case "legs":
				return parseArmour(splinter[3], TYPE.LEGS, Double.parseDouble(splinter[4]));
			case "torso":
				return parseArmour(splinter[3], TYPE.TORSO, Double.parseDouble(splinter[4]));
			case "open":
				return parseDoor(splinter[3], Integer.parseInt(splinter[4]), STATE.OPEN);
			case "unlocked":
				return parseDoor(splinter[3], Integer.parseInt(splinter[4]), STATE.UNLOCKED);
			case "locked":
				return parseDoor(splinter[3], Integer.parseInt(splinter[4]), STATE.LOCKED);
			case "npc":
				Image i = new Image(splinter[3], true);
				n = Integer.parseInt(splinter[4]);
				NPC n1 = new NPC(i, n, Direction.Down);
				return null;
			case "player":
				i = new Image(splinter[3], true);
				Player p = new Player(i, Direction.Down);
				return p;	
		}
		return null;
	}
	
	public Weapon parseWeapon(String imagePath, Double d){
		Image i = new Image(imagePath, true);
		Weapon w = new Weapon(i, d);
		return w;
	}
	
	public Armour parseArmour(String imagePath, TYPE t, Double d){
		Image i = new Image(imagePath, true);
		Armour a = new Armour(i,t,d);
		return a;
	}
	
	public Door parseDoor(String imagePath, int n, STATE s){
		Image i = new Image(imagePath, true);
		Door d1 = new Door(i,n,s);
		return d1;
	}
	
}
