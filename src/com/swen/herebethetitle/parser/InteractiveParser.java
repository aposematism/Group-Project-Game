//package com.swen.herebethetitle.parser;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//import java.util.regex.Pattern;
//
//import com.swen.herebethetitle.entity.Entity;
//import com.swen.herebethetitle.entity.Player;
//import com.swen.herebethetitle.entity.items.Armour;
//import com.swen.herebethetitle.entity.items.Armour.TYPE;
//import com.swen.herebethetitle.entity.items.Weapon;
//import com.swen.herebethetitle.entity.NPC;
//import com.swen.herebethetitle.model.Region;
//import com.swen.herebethetitle.model.Tile;
//import com.swen.herebethetitle.util.Direction;
//import com.swen.herebethetitle.util.GridLocation;
//import com.swen.herebethetitle.entity.stationeries.Door;
//import com.swen.herebethetitle.entity.stationeries.Door.STATE;
//
//import javafx.scene.image.Image;
//
///**
// * This is the interactive entity parser, which is the second stage of the parsing process. It initialises in a similar way, but it is fundamentally different.
// * The file is to be read "item_enum x_position y_position"
// * @author - Jordan
// * */
//public class InteractiveParser {
//	ArrayList<String> stringArray;
//
//	/**
//	 * This method is for the initialization of the region file.
//	 * */
//	public void init_scanner(File region)throws IOException{
//		BufferedReader regionBuff = null;
//		stringArray = new ArrayList<String>();
//		try{
//			regionBuff = new BufferedReader(new FileReader(region));
//			String line = regionBuff.readLine();
//			while(line != null){
//				stringArray.add(line);
//				line = regionBuff.readLine();
//			}
//
//		}
//		catch(IOException e){
//			throw new IOException("File failed to initialise!");
//		}
//		finally{
//			regionBuff.close();
//			System.out.println("File Loaded to charArray");
//		}
//	}
//
//	/**
//	 * This method does actually process the line in the interactive file for each item it intends to load.
//	 * @author - Jordan
//	 * */
//	public void parseStringArray(Region reg){
//		for(String s : stringArray){
//			Scanner scanner = new Scanner(s);
//			String className = scanner.next();
//
//			int x = scanner.nextInt();
//			int y = scanner.nextInt();
//			Tile t = reg.get(x, y);
//
//			Entity item = parseInteractive(reg, scanner, className);
//
//			t.add(item);
//		}
//	}
//
//	/**
//	 * This is the parser for each item. Takes the name, x/y position, then the image(for now) and finally its properties.
//	 * this looks terrible. I might split it into other classes.
//	 * @author - Jordan
//	 * */
//	public Entity parseInteractive(Region reg, Scanner scanner, String className){
//		TYPE t;
//		STATE s;
//		double d;
//		int n;
//
//		switch(className){
//			case "Stationary": return parseStationary(scanner);
//			case "Player":     return parsePlayer(scanner);
//			case "NPC":        return parseNPC(scanner);
//			case "Weapon":     return parseWeapon(scanner);
//			case "Armour":     return parseArmour(scanner);
//			case "Key":        return parseKey(scanner);
//			case "Potion":     return parsePotion(scanner);
//			default:           throw new InputMismatchException("Couldn't Interpret Class");
//		}
//	}
//
//	public String parseName(Scanner s){
//		String name = "";
//		String next = s.next(".");
//		while(!next.equals("\"")){
//			name = name.concat(next);
//			next = s.next(".");
//		}
//		return name;
//	}
//
//	public Image parseSprite(Scanner s){
//
//	}
//
//	public Weapon parseWeapon(Scanner s){
//		String name = parseName(s);
//		Image sprite = parseSprite(s);
//		boolean isMelee = Boolean.parseBoolean(s.next());
//		double strength = Double.parseDouble(s.next());
//		return new Weapon(name, sprite, isMelee, strength);
//	}
//
//	public Weapon parseArmour(Scanner s){
//		String name = parseName(s);
//		Image sprite = parseSprite(s);
//		Armour.TYPE type = Armour.TYPE.valueOf(s.next());
//		double strength = Double.parseDouble(s.next());
//		return new Armour(name, sprite, isMelee, strength);
//	}
//
//	public Door parseDoor(String imagePath, int n, STATE s){
//		Image i = new Image(imagePath, true);
//		Door d1 = new Door(i,n,s);
//		return d1;
//	}
//
//
//
//}
