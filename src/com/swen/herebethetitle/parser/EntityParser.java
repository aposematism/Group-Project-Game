package com.swen.herebethetitle.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

/**
 * Helper class with static methods for interpreting a single line in the save file.
 *
 * @author Mark Metcalfe and Jordan Milburn
 */
public class EntityParser {

	private final static Pattern STATIC_BEHAVIOR = Pattern.compile("(Door)");
	private final static Pattern NPC_BEHAVIOR = Pattern.compile("(Monster|monster|Friendly|friendly)");
	private final static Pattern STRING = Pattern.compile("\"[^\"]*\"");
	
	static ArrayList<Coord> coordinates = new ArrayList<Coord>();
	static ArrayList<Entity> entityList = new ArrayList<Entity>();
	
	/** 
	 * This is the constructor for files. It essentially contains all behaviour within the entityParser for that file.
	 * @author - Jordan Milburn
	 * */
	public EntityParser(File interactives){
		interactiveScanner(interactives);
	}
	
	/** 
	 * This is the constructor for a single item from a scanner.
	 * */
	public EntityParser(Scanner s) {
		
	}
	
	/** 
	 * Interactive scanner which takes input from a file and produces all entites from it.
	 * */
	private void interactiveScanner(File interactives){
		BufferedReader interactivesBuff = null;
		coordinates = new ArrayList<Coord>();
		entityList = new ArrayList<Entity>();
		try{
			interactivesBuff = new BufferedReader(new FileReader(interactives));
			String line = interactivesBuff.readLine();
			while(line != null){
				Scanner s = new Scanner(line);
				Coord c = Coord.parseCoordinate(s);
				coordinates.add(c);
				Entity e = parseEntity(s);
				entityList.add(e);
				line = interactivesBuff.readLine();
			}
			interactivesBuff.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(SyntaxError z) {
			z.printStackTrace();
		}
	}
	
	public Region parseEntityToRegion(Region reg) {
		for(int i = 0; i < entityList.size(); i++) {
			Tile t = reg.get(coordinates.get(i).convert());
			t.add(entityList.get(i));
		}
		return reg;
	}
	
	/**
	 * Takes the remainder of a line and constructs an entity object from it
	 *
	 * @param s Scanner with the next token being an entity class name
	 * @return Constructed entity instance
	 * @throws SyntaxError 
	 */
	public Entity parseEntity(Scanner s){
		try {
			String className = s.next();
			switch (className) {
				case "Static":
					return parseStatic(s);
				case "Player":
					return parsePlayer(s);
				case "NPC":
					return parseNPC(s);
				case "Floor":
					return parseFloor(s);
				default:
					return parseItem(s, className);
			}
		} 
		catch(InputMismatchException e){
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * Interprets a string formatted as "Text Here"
	 */
	public String parseString(Scanner s) throws InputMismatchException {
		String string = s.findInLine(STRING);
		string = string.replaceAll("\"","");
		return string;
	}
	
	/** 
	 * parses item from scanner. Never used currently
	 * */
	private Item parseItem(Scanner s) throws InputMismatchException {
		String className = s.next();
		return parseItem(s, className);
	}

	/** 
	 * parses each item in the inventory
	 * */
	private Item parseInventoryItem(Scanner s) throws InputMismatchException {
		s.next(); //Consume opening brace
		String className = s.next();
		Item i = parseItem(s, className);
		s.next(); //Consume closing brace
		return i;
	}
	
	/** 
	 * parses each type of item
	 * */
	private Item parseItem(Scanner s, String className) throws InputMismatchException {
		switch (className){
			case "Weapon": return parseWeapon(s);
			case "Armour": return parseArmour(s);
			case "Key":    return parseKey(s);
			case "Potion": return parsePotion(s);
			default:       throw new InputMismatchException("Couldn't Interpret Entity");
		}
	}

	/** 
	 * parses weapons
	 * */
	private Weapon parseWeapon(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		boolean isMelee = s.nextBoolean();
		double strength = s.nextDouble();

		return new Weapon(name, sprite, isMelee, strength);
	}

	/** 
	 * parses armour types.
	 * */
	private Armour parseArmour(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		Armour.TYPE type = Armour.TYPE.valueOf(s.next());
		double strength = s.nextDouble();

		return new Armour(name, sprite, type, strength);
	}

	/** 
	 * parses keys.
	 * */
	private Key parseKey(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		int key = s.nextInt();

		return new Key(name, sprite, key);
	}

	/** 
	 * parses potions.
	 * */
	private Potion parsePotion(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		double value = s.nextDouble();

		return new Potion(name, sprite, value);
	}

	/** 
	 * parses floors.
	 * */
	private Floor parseFloor(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);

		Floor floor = new Floor(name, sprite);

		return floor;
	}

	/** 
	 * parses statics
	 * */
	private Static parseStatic(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);

		Static aStatic = new Static(name, sprite);

		if(s.hasNext(STATIC_BEHAVIOR)){
			Static.Behavior behavior = parseStaticBehavior(s);
			aStatic.setBehavior(behavior);
		}

		return aStatic;
	}

	/** 
	 * 
	 * */
	private Static.Behavior parseStaticBehavior(Scanner s) throws InputMismatchException {
		switch(s.next()) { //Check Class Token
			case "Door": return parseDoor(s);
			default:     throw new InputMismatchException("Couldn't Parse NPCBehavior");
		}
	}

	/** 
	 * 
	 * */
	private DoorStrategy parseDoor(Scanner s) throws InputMismatchException {
		int key = s.nextInt();
		DoorStrategy.STATE state = DoorStrategy.STATE.valueOf(s.next());
		return new DoorStrategy(key, state);
	}

	/** 
	 * 
	 * */
	private NPC parseNPC(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		double health = s.nextDouble();
		Direction direction = Direction.valueOf(s.next());

		NPC npc = new NPC(name, sprite, health, direction);

		if(s.hasNext(NPC_BEHAVIOR)){
			NPCBehavior behavior = parseNPCBehavior(s);
			npc.setBehavior(behavior);
		}

		return npc;
	}

	/** 
	 * 
	 * */
	private NPCBehavior parseNPCBehavior(Scanner s) throws InputMismatchException {
		switch(s.next()){ //Check Class Token
			case "Monster":  return parseMonster(s);
			case "Friendly": return parseFriendly(s);
			default:         throw new InputMismatchException();
		}
	}

	/** 
	 * 
	 * */
	private MonsterStrategy parseMonster(Scanner s) throws InputMismatchException {
		double strength = s.nextDouble();

		return new MonsterStrategy(strength);
	}

	/** 
	 * 
	 * */
	private FriendlyStrategy parseFriendly(Scanner s) throws InputMismatchException {
		s.next(); //Consume opening brace

		FriendlyStrategy f = new FriendlyStrategy();

		while(!s.hasNext("}"))
			f.addDialog(parseString(s));

		s.next(); //Consume closing brace

		return f;
	}

	/** 
	 * 
	 * */
	private Player parsePlayer(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		double health = s.nextDouble();
		Direction direction = Direction.valueOf(s.next());
		int wallet = s.nextInt();

		Player player = new Player(name, sprite, health, wallet, direction);

		s.next(); //Consume "Inventory" token
		ArrayList<Item> items = new ArrayList<>();
		while(s.hasNext("\\{"))
			items.add(parseInventoryItem(s));

		return new Player(name, sprite, health, wallet, direction, items.toArray(new Item[items.size()]));
	}
}