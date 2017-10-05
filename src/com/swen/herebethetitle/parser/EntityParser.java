package com.swen.herebethetitle.parser;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.ai.*;
import com.swen.herebethetitle.entity.items.*;
import com.swen.herebethetitle.entity.statics.*;
import com.swen.herebethetitle.util.Direction;

/**
 * Helper class with static methods for interpreting a single line in the save file
 *
 * @author Mark Metcalfe
 */
public class EntityParser {

	private final static Pattern ITEM = Pattern.compile("(Weapon|Armour|Potion|Key)");
	private final static Pattern STATIC_BEHAVIOR = Pattern.compile("(Door)");
	private final static Pattern NPC_BEHAVIOR = Pattern.compile("(Monster|Friendly)");
	private final static Pattern STRING = Pattern.compile("\"[^\"]*\"");

	/**
	 * Takes the remainder of a line and constructs an entity object from it
	 *
	 * @param s Scanner with the next token being an entity class name
	 * @return Constructed entity instance
     */
	public static Entity parseEntity(Scanner s) throws SyntaxError {
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
		} catch(InputMismatchException e){
			throw new SyntaxError(e.getMessage());
		}
	}

	/**
	 * Interprets a string formatted as "Text Here"
     */
	public static String parseString(Scanner s) throws InputMismatchException {
		String string = s.findInLine(STRING);
		string = string.replaceAll("\"","");
		return string;
	}

	private static Item parseItem(Scanner s) throws InputMismatchException {
		String className = s.next();
		return parseItem(s, className);
	}

	private static Item parseItem(Scanner s, String className) throws InputMismatchException {
		switch (className){
			case "Weapon": return parseWeapon(s);
			case "Armour": return parseArmour(s);
			case "Key":    return parseKey(s);
			case "Potion": return parsePotion(s);
			default:       throw new InputMismatchException("Couldn't Interpret Entity");
		}
	}

	private static Weapon parseWeapon(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		boolean isMelee = s.nextBoolean();
		double strength = s.nextDouble();

		return new Weapon(name, sprite, isMelee, strength);
	}

	private static Armour parseArmour(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		Armour.TYPE type = Armour.TYPE.valueOf(s.next());
		double strength = s.nextDouble();

		return new Armour(name, sprite, type, strength);
	}

	private static Key parseKey(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		int key = s.nextInt();

		return new Key(name, sprite, key);
	}

	private static Potion parsePotion(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);
		int value = s.nextInt();

		return new Potion(name, sprite, value);
	}

	private static Floor parseFloor(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);

		Floor floor = new Floor(name, sprite);

		return floor;
	}

	private static Static parseStatic(Scanner s) throws InputMismatchException {
		String name = parseString(s);
		String sprite = parseString(s);

		Static aStatic = new Static(name, sprite);

		if(s.hasNext(STATIC_BEHAVIOR)){
			Static.Behavior behavior = parseStaticBehavior(s);
			aStatic.setBehavior(behavior);
		}

		return aStatic;
	}

	private static Static.Behavior parseStaticBehavior(Scanner s) throws InputMismatchException {
		switch(s.next()) { //Check Class Token
			case "Door": return parseDoor(s);
			default:     throw new InputMismatchException("Couldn't Parse Behavior");
		}
	}

	private static Door parseDoor(Scanner s) throws InputMismatchException {
		int key = s.nextInt();
		Door.STATE state = Door.STATE.valueOf(s.next());
		return new Door(key, state);
	}

	private static NPC parseNPC(Scanner s) throws InputMismatchException {
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

	private static Behavior parseNPCBehavior(Scanner s) throws InputMismatchException {
		switch(s.next()){ //Check Class Token
			case "Monster":  return parseMonster(s);
			case "Friendly": return parseFriendly(s);
			default:         throw new InputMismatchException();
		}
	}

	private static Monster parseMonster(Scanner s) throws InputMismatchException {
		double strength = s.nextDouble();

		return new Monster(strength);
	}

	private static Friendly parseFriendly(Scanner s) throws InputMismatchException {
		s.next(); //Consume opening brace

		Friendly f = new Friendly();

		while(!s.hasNext("}"))
			f.addDialog(parseString(s));

		s.next(); //Consume closing brace

		return f;
	}

	private static Player parsePlayer(Scanner s) throws InputMismatchException {
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
