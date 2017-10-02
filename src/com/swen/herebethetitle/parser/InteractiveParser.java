package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	 * This is the parser for each item. Takes the name, x/y position, then the image(for now) and finally its properties.
	 * this looks terrible. I might split it into other classes.
	 * @author - Jordan
	 * */
	public Entity parseInteractive(Scanner scanner, String className){
		switch(className){
			case "Static": return parseStatic(scanner);
			case "Player":     return parsePlayer(scanner);
			case "NPC":        return parseNPC(scanner);
			default:           return parseItem(scanner, className);
		}
	}

	public String parseString(Scanner s){
		String string = "";
		String next = s.next(".");
		while(!next.equals("\"")){
			string = string.concat(next);
			next = s.next(".");
		}
		return string;
	}

	public Item parseItem(Scanner s){
		String className = s.next();
		return parseItem(s, className);
	}

	public Item parseItem(Scanner s, String className){
		switch (className){
			case "Weapon": return parseWeapon(s);
			case "Armour": return parseArmour(s);
			case "Key":    return parseKey(s);
			case "Potion": return parsePotion(s);
			default:       throw new InputMismatchException("Couldn't Interpret Entity");
		}
	}

	public Weapon parseWeapon(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		boolean isMelee = Boolean.parseBoolean(s.next());
		double strength = Double.parseDouble(s.next());

		return new Weapon(name, sprite, isMelee, strength);
	}

	public Armour parseArmour(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		Armour.TYPE type = Armour.TYPE.valueOf(s.next());
		double strength = Double.parseDouble(s.next());

		return new Armour(name, sprite, type, strength);
	}

	public Key parseKey(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		int key = Integer.parseInt(s.next());

		return new Key(name, sprite, key);
	}

	public Potion parsePotion(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		int value = Integer.parseInt(s.next());

		return new Potion(name, sprite, value);
	}

	public Static parseStatic(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);

		Static aStatic = new Static(name, sprite);

		if(s.hasNext("Door")){
			s.next(); //Consume "Door" token
			Door door = parseDoor(s);
			aStatic.setBehavior(door);
		}

		return aStatic;
	}

	public Door parseDoor(Scanner s){
		int key = Integer.parseInt(s.next());
		Door.STATE state = Door.STATE.valueOf(s.next());
		return new Door(key, state);
	}

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

	public Monster parseMonster(Scanner s){
		double strength = Double.parseDouble(s.next());

		return new Monster(strength);
	}

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
