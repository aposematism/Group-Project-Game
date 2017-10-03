package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.ai.Monster;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.entity.statics.Door;

import javafx.scene.image.Image;

/**
 * This is the interactive entity parser, which is the second stage of the parsing process. It initialises in a similar way, but it is fundamentally different.
 * The file is to be read "item_enum x_position y_position"
 * @author - Jordan
 * */
public class InteractiveParser {

	final static Pattern ITEM = Pattern.compile("(Weapon|Armour|Potion|Key)");

	ArrayList<String> stringArray;
	HashMap<String, String> spriteMap;
	
	/** 
	 * So this hardcodes the string for each item. It ensures the level designer only needs to give
	 * an item name and co-ordinates to generate an item or a level.
	 * */
	public InteractiveParser() {
		spriteMap = new HashMap<String, String>();
		spriteMap.put("weapon", "path/to/sprite.png true 5");
		spriteMap.put("helmet", "path/to/sprite.png HELMET 5");
		spriteMap.put("boots", "path/to/sprite.png BOOTS 5");
		spriteMap.put("legs", "path/to/sprite.png LEGS 5");
		spriteMap.put("torso", "path/to/sprite.png TORSO 5");
		spriteMap.put("key", "path/to/sprite.png true 5");
		spriteMap.put("potion", "path/to/sprite.png true 5");
		spriteMap.put("static", "path/to/sprite.png true 5");
	}

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
	 * Mostly unused after hardcoded map update.
	 * @author - Jordan Milburn
	 * */
	public void parseStringArray(Region reg){
		for(String s : stringArray){
			Scanner scanner = new Scanner(s);
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			Tile t = reg.get(x, y);
			String className = scanner.next();
			Entity item = parseInteractive(scanner, className);
			t.add(item);
		}
	}
	/** 
	 * Same as above, but enables independent testing without a region.
	 * @Author - Jordan Milburn
	 * */
	public Entity parseStringArray(String s) {
		Scanner scanner = new Scanner(s);
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		//Tile t = reg.get(x, y);
		String className = scanner.next();
		Entity item = parseInteractive(scanner, className);
		return item;
	}

	/**
	 * This is the parser for each item. Takes the name, x/y position, then the image(for now) and finally its properties.
	 * this looks terrible. I might split it into other classes.
	 * @author - Jordan
	 * */
	public Entity parseInteractive(Scanner scanner, String className){
		switch(className){
			case "door":	   return parseStatic(scanner, className);
			case "static":	   return parseStatic(scanner, className);
			case "Player":     return parsePlayer(scanner);
			case "NPC":        return parseNPC(scanner);
			default:           return parseItem(scanner, className);
		}
	}

	/** 
	 * This handles strings and creates the appropriate string for the objects at hand.
	 * @author - Mark Metcalfe
	 * */
	public String parseString(Scanner s){
		String string = "";
		String next = s.next(".");
		while(!next.equals(".")){
			string = string.concat(next);
			next = s.next(".");
		}
		return string;
	}
	
	/** 
	 * Checking against spriteMap hashmap for the image. So it checks if that item is there and returns a matching path string.
	 * */
	public String parseString(String item_name) {
		if(spriteMap.containsKey(item_name)) {
			return spriteMap.get(item_name);
		}
		return null;
	}
	
	/** 
	 * This method handles the items.
	 * @author - Mark Metcalfe
	 * */
	public Item parseItem(Scanner s){
		String className = s.next();
		return parseItem(s, className);
	}
	/** 
	 * This method parses onto the appropriate category.
	 * @author - Mark Metcalfe
	 * */
	public Item parseItem(Scanner s, String className){
		switch (className){
			case "weapon": return parseWeapon(s, className);
			case "helmet": return parseArmour(s, className);
			case "torso": return parseArmour(s, className);
			case "boots": return parseArmour(s, className);
			case "legs": return parseArmour(s, className);
			case "key":    return parseKey(s, className);
			case "potion": return parsePotion(s, className);
			default:       throw new InputMismatchException("Couldn't Interpret Entity");
		}
	}
	
	/** 
	 * This method parses weapons. Takes from the hardcoded spritemap which contains relevant info on the item.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	public Weapon parseWeapon(Scanner s, String className){
		String name = className;
		String returned = parseString(name);
		String[] split = returned.split(" ");
		String sprite = split[0];
		boolean isMelee = Boolean.parseBoolean(split[1]);
		double strength = Double.parseDouble(split[2]);
		return new Weapon(name, sprite, isMelee, strength);
	}
	
	/** 
	 * This method parses armour.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	public Armour parseArmour(Scanner s, String className){
		String name = className;
		String returned = parseString(name);
		String[] split = returned.split(" ");
		String sprite = split[0];
		Armour.TYPE type = Armour.TYPE.valueOf(split[1]);
		double strength = Double.parseDouble(split[0]);
		return new Armour(name, sprite, type, strength);
	}
	
	/** 
	 * This method parses keys.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	public Key parseKey(Scanner s, String className){
		String name = className;
		String returned = parseString(name);
		String[] split = returned.split(" ");
		String sprite = split[0];
		int key = Integer.parseInt(split[1]);
		return new Key(name, sprite, key);
	}
	
	/** 
	 * This method parses potions.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */ 
	public Potion parsePotion(Scanner s, String className){
		String name = className;
		String returned = parseString(name);
		String[] split = returned.split(" ");
		String sprite = split[0];
		int value = Integer.parseInt(split[1]);
		return new Potion(name, sprite, value);
	}
	
	/** 
	 * This method parses statics like Doors.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	public Static parseStatic(Scanner s, String className){
		String name = className;
		String returned = parseString(name);
		String[] split = returned.split(" ");
		String sprite = split[0];
		Static aStatic = new Static(name, sprite);
		if(split[1].equals("Door")){
			s.next(); //Consume "Door" token
			Door door = parseDoor(split);
			aStatic.setBehavior(door);
		}

		return aStatic;
	}
	
	/** 
	 * This method parses doors explicitly for generating a key.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	public Door parseDoor(String[] s){
		int key = Integer.parseInt(s[2]);
		Door.STATE state = Door.STATE.valueOf(s[3]);
		return new Door(key, state);
	}
	
	/** 
	 * This method parses NPCs.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	public NPC parseNPC(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		double health = Double.parseDouble(s.next());
		Direction direction = Direction.valueOf(s.next());

		NPC npc = new NPC(name, sprite, health, direction);

		if(s.hasNext("Monster")){
			s.next(); //Consume "Monster" token
			Monster monster = parseMonster(s);
			npc.setBehavior(monster);
		}

		return npc;
	}
	
	/** 
	 * This method parses monsters.
	 * @author - Mark Metcalfe
	 * */
	public Monster parseMonster(Scanner s){
		double strength = Double.parseDouble(s.next());

		return new Monster(strength);
	}
	
	/** 
	 * This method parses players.
	 * @author - Mark Metcalfe
	 * */
	public Player parsePlayer(Scanner s){
		String sprite = parseString(s);
		double health = Double.parseDouble(s.next());
		Direction direction = Direction.valueOf(s.next());
		int wallet = Integer.parseInt(s.next());

		Player player = new Player(sprite, health, wallet, direction);

		s.next(); //Consume "Inventory" token

		while(s.hasNext(ITEM))
			player.add(parseItem(s));

		return player;
	}
}
