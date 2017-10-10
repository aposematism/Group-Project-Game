package com.swen.herebethetitle.parser.parsertests;

import static com.swen.herebethetitle.parser.Coord.parseCoordinate;
import static org.junit.Assert.*;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.parser.Coord;
import com.swen.herebethetitle.parser.EntityParser;
import com.swen.herebethetitle.parser.SyntaxError;
import com.swen.herebethetitle.parser.TerrainParser;
import com.swen.herebethetitle.util.Direction;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EntityParserTests {
	
	/** 
	 * This is a helper method for comparing file outputs.
	 * @author - Jordan Milburn
	 * */
	private boolean compareFiles(File f1, File f2) {
		try {
			BufferedReader in1 = new BufferedReader(new FileReader(f1));
			BufferedReader in2 = new BufferedReader(new FileReader(f2));
			String line1 = in1.readLine();
			String line2 = in2.readLine();
			while(line1 != null) {
				if(!line1.equals(line2)) {
					return false;
				}
				line1 = in1.readLine();
				line2 = in2.readLine();
			}
		}catch(FileNotFoundException e) {
			fail("File not Found!");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Takes a generated item and compares it to itself. 
	 * It effectively checks that the reversed toString method is the same as input.
	 * However, it could contain bugs itself due to merely matching input from the tests with the output from the string.
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	public void parse(Entity in){
		Scanner s = new Scanner(in.toString());
		EntityParser ep = new EntityParser(s);
		Entity out = ep.parseEntity(s);
		assertEquals(in.toString(), out.toString());
		System.out.println(in.toString());
	}

	/**
	 * Constructs generic player with inventory of given items
	 */
	public Player playerWithItems(Item... items){
		return new Player("The Dude","dude.png",Player.FULL_HEALTH,100,Direction.Right,items);
	}
	
	/** 
	 * tests player creation
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	@Test
	public void test_Player_empty_inventory(){
		parse(new Player("silly boi.png", Direction.Down));
	}
	
	/** 
	 * tests player with inventory
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	@Test
	public void test_Player_with_inventory(){
		Player in = playerWithItems(
				new Potion("Health Potion", "health potion.png", 50),
				new Armour("Bronze Chestplate", "chestplate one.png", Armour.TYPE.TORSO, 5.6),
				new Weapon("Iron Sword", "swordasdasd.png", true, 8.8),
				new Key("Church Key", "basickey1.png", 101)
		);
		parse(in);
	}
	
	/** 
	 * Static rock generation test
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	@Test
	public void test_Static_no_Behavior(){
		parse(new Static("Large Rock","gigantic ass rock.png"));
	}

	/** 
	 * static floor generation test
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	@Test
	public void test_Floor(){
		parse(new Floor("Grass","grass.png"));
	}

	/** 
	 * monster generation test
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	@Test
	public void test_Monster(){
		NPCBehavior behavior = new MonsterStrategy(50);
		NPC in = new NPC("Zombie","zombie.png", behavior, 50, Direction.Down);
		parse(in);
	}

	/** 
	 * friendly dialogue test
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	@Test
	public void test_Friendly_Dialog(){
		FriendlyStrategy friendly = new FriendlyStrategy();

		friendly.addDialog("Great weather today!", "Did you hear about the beast?", "It's called THE BEAST");

		NPC in = new NPC("Fred","man1.png", friendly, 50, Direction.Down);

		parse(in);
	}
	
	/** 
	 * Tests weapon generation
	 * @author - Jordan Milburn
	 * */
	@Test
	public void test_line_weapon_generation() {
		ArrayList<String> itemArray = new ArrayList<String>();
		String line = "(3,2) Weapon \"Bronze Sword\" \"swordasdasd.png\" true 6.0";
		itemArray.add(line);
		try {
			for(String l : itemArray){
				Scanner s = new Scanner(l);
				Coord coord = parseCoordinate(s);
				EntityParser ep = new EntityParser(s);
				Entity entity = ep.parseEntity(s);
				assertTrue(entity instanceof Weapon);
			}
		}
		catch(SyntaxError e) {
			fail(e.getMessage());
		}
	}
	
	/** 
	 * Tests armour generation
	 * @author - Jordan Milburn
	 * */
	@Test
	public void test_armour_generation() {
		ArrayList<String> itemArray = new ArrayList<String>();
		String line1 = "(3,3) Armour \"Bronze Helmet\" \"bronzehelm.png\" HELMET 4.0";
		String line2 = "(4,3) Armour \"Bronze Plate\" \"bronzetorso.png\" TORSO 12.0";
		String line3 = "(2,3) Armour \"Bronze Greaves\" \"bronzegreaves.png\" LEGS 7.0";
		String line4 = "(2,2) Armour \"Leather Boots\" \"leatherboots.png\" BOOTS 2.0";
		itemArray.add(line1);itemArray.add(line2);itemArray.add(line3);itemArray.add(line4);
		try {
			for(String l : itemArray){
				Scanner s = new Scanner(l);
				Coord coord = parseCoordinate(s);
				EntityParser ep = new EntityParser(s);
				Entity entity = ep.parseEntity(s);
				assertTrue(entity instanceof Armour);
			}
		}
		catch(SyntaxError e) {
			fail(e.getMessage());
		}
	}
	
	/** 
	 * Tests potion generation
	 * @author - Jordan Milburn
	 * */
	@Test
	public void test_potion_generation() {
		ArrayList<String> itemArray = new ArrayList<String>();
		String line5 = "(1,1) Potion \"Minor Health Potion\"  \"healthpotion.png\" 20.0";
		itemArray.add(line5);
		try {
			for(String l : itemArray){
				Scanner s = new Scanner(l);
				Coord coord = parseCoordinate(s);
				EntityParser ep = new EntityParser(s);
				Entity entity = ep.parseEntity(s);
				assertTrue(entity instanceof Potion);
			}
		}
		catch(SyntaxError e) {
			fail(e.getMessage());
		}
	}
	
	/** 
	 * Tests key generation
	 * @author - Jordan Milburn
	 * */
	@Test
	public void test_key_generation() {
		ArrayList<String> itemArray = new ArrayList<String>();
		String line6 = "(3,2) Key \"Church Key\" \"churchkey.png\" 3";
		itemArray.add(line6);
		try {
			for(String l : itemArray){
				Scanner s = new Scanner(l);
				Coord coord = parseCoordinate(s);
				EntityParser ep = new EntityParser(s);
				Entity entity = ep.parseEntity(s);
				assertTrue(entity instanceof Key);
			}
		}
		catch(SyntaxError e) {
			fail(e.getMessage());
		}
	}
	
	
	/** 
	 * Tests static generation
	 * @author - Jordan Milburn
	 * */
	@Test
	public void test_static_generation() {
		ArrayList<String> staticArray = new ArrayList<String>();
		String line = "(1,2) Static \"Tudor Wall\" \"tudorwall.png\"";
		String line1 = "(1,1) Static \"Church Door\" \"heavydoor.png\" Door 3 LOCKED";
		staticArray.add(line); staticArray.add(line1);
		try {
			for(String l : staticArray) {
				Scanner s = new Scanner(l);
				Coord coord = parseCoordinate(s);
				EntityParser ep = new EntityParser(s);
				Entity entity = ep.parseEntity(s);
				assertTrue(entity instanceof Static);
			}
			
		}
		catch(SyntaxError e) {
			fail(e.getMessage());
		}
	}
	
	/** 
	 * Tests line
	 * @author - Mark Metcalfe and Jordan Milburn
	 * */
	@Test
	public void test_Line(){
		String line = "(0,2) Player \"Player\" \"silly boi.png\" 100.0 Down 0 Inventory Weapon \"Iron Sword\" \"swordasdasd.png\" true 8.8 Armour \"Bronze Chestplate\" \"chestplate one.png\" TORSO 5.6 Potion \"Health Potion\" \"health potion.png\" 50 Key \"Church Key\" \"basickey1.png\" 101";

		Scanner s = new Scanner(line);

		try {
			Coord coord = parseCoordinate(s);
			EntityParser ep = new EntityParser(s);
			Entity entity = ep.parseEntity(s);
			System.out.println(coord.toString()+" "+entity.toString());
		} catch(SyntaxError e){
			fail(e.getMessage());
		}
	}
	
	/** 
	 * Tests the integration of Terrain Parser with this class.
	 * */
	@Test
	public void test_integration() throws IOException, SyntaxError {
		TerrainParser tp = new TerrainParser(new File("res/test_terrain_file.txt"));
		tp.connectNetworks(tp.getRA());
		Region r = new Region(tp.getRA());
		File inputFile = new File("res/test_entity_parser.txt");
		EntityParser ep = new EntityParser(inputFile);
		ep.parseEntitytoRegion(r);
		System.out.println("total items is " + r.getInteractiveTotal());
		//assertTrue(r.getInteractiveTotal() == 10);
	}
}
