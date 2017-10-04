package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Floor;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.ai.Behavior;
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
public class LineParser {

	private final static Pattern ITEM = Pattern.compile("(Weapon|Armour|Potion|Key)");
	private final static Pattern STATIC_BEHAVIOR = Pattern.compile("(Door)");
	private final static Pattern NPC_BEHAVIOR = Pattern.compile("(Monster)");
	private final static Pattern STRING = Pattern.compile("\"[^\"]*\"");
	private final static Pattern COORDS = Pattern.compile("\\(\\d,\\d\\)");

	public static Coord parseCoordinate(Scanner s){
		String coordBraces = s.findInLine(COORDS);

		coordBraces = coordBraces.replaceAll("\\(", "");
		coordBraces = coordBraces.replaceAll("\\)", "");
		coordBraces = coordBraces.replaceAll(",", " ");

		Scanner coord = new Scanner(coordBraces);
		return new Coord(coord.nextInt(), coord.nextInt());
	}

	public static Entity parseEntity(Scanner s){
		String className = s.next();
		switch(className){
			case "Static": return parseStatic(s);
			case "Player": return parsePlayer(s);
			case "NPC":    return parseNPC(s);
			case "Floor":  return parseFloor(s);
			default:       return parseItem(s, className);
		}
	}

	private static String parseString(Scanner s){
		String string = s.findInLine(STRING);
		string = string.replaceAll("\"","");
		return string;
	}

	private static Item parseItem(Scanner s){
		String className = s.next();
		return parseItem(s, className);
	}

	private static Item parseItem(Scanner s, String className){
		switch (className){
			case "Weapon": return parseWeapon(s);
			case "Armour": return parseArmour(s);
			case "Key":    return parseKey(s);
			case "Potion": return parsePotion(s);
			default:       throw new InputMismatchException("Couldn't Interpret Entity");
		}
	}

	private static Weapon parseWeapon(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		boolean isMelee = s.nextBoolean();
		double strength = s.nextDouble();

		return new Weapon(name, sprite, isMelee, strength);
	}

	private static Armour parseArmour(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		Armour.TYPE type = Armour.TYPE.valueOf(s.next());
		double strength = s.nextDouble();

		return new Armour(name, sprite, type, strength);
	}

	private static Key parseKey(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		int key = s.nextInt();

		return new Key(name, sprite, key);
	}

	private static Potion parsePotion(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		int value = s.nextInt();

		return new Potion(name, sprite, value);
	}

	private static Floor parseFloor(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);

		Floor floor = new Floor(name, sprite);

		return floor;
	}

	private static Static parseStatic(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);

		Static aStatic = new Static(name, sprite);

		if(s.hasNext(STATIC_BEHAVIOR)){
			Static.Behavior behavior = parseStaticBehavior(s);
			aStatic.setBehavior(behavior);
		}

		return aStatic;
	}

	private static Static.Behavior parseStaticBehavior(Scanner s){
		switch(s.next()) { //Check Class Token
			case "Door": return parseDoor(s);
			default:     throw new InputMismatchException("Couldn't Parse Behavior");
		}
	}

	private static Door parseDoor(Scanner s){
		int key = s.nextInt();
		Door.STATE state = Door.STATE.valueOf(s.next());
		return new Door(key, state);
	}

	private static NPC parseNPC(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		double health = s.nextDouble();
		Direction direction = Direction.valueOf(s.next());

		NPC npc = new NPC(name, sprite, health, direction);

		if(s.hasNext(NPC_BEHAVIOR)){
			Behavior behavior = parseNPCBehavior(s);
			npc.setBehavior(behavior);
		}

		return npc;
	}

	private static Behavior parseNPCBehavior(Scanner s){
		switch(s.next()){ //Check Class Token
			case "Monster": return parseMonster(s);
			default:        throw new InputMismatchException("Couldn't Parse Behavior");
		}
	}

	private static Monster parseMonster(Scanner s){
		double strength = s.nextDouble();

		return new Monster(strength);
	}

	private static Player parsePlayer(Scanner s){
		String name = parseString(s);
		String sprite = parseString(s);
		double health = s.nextDouble();
		Direction direction = Direction.valueOf(s.next());
		int wallet = s.nextInt();

		Player player = new Player(name, sprite, health, wallet, direction);

		s.next(); //Consume "Inventory" token

		while(s.hasNext(ITEM))
			player.add(parseItem(s));

		return player;
	}
}
