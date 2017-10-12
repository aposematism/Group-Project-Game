package com.swen.herebethetitle.parser;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static com.swen.herebethetitle.parser.EntityParser.parse;

/**
 * This class is the terrain parser, which is the first stage in the parsing process.
 * For parsing any objects which do not interact with the player, NPCs or enemies.
 *
 * @author Jordan Milburn & Mark Metcalfe
 */
public class MapParser {
	private static final Pattern REGION_NAME = Pattern.compile("(this|north|east|south|west):");

	private CharArray charArray;
	private Tile[][] regionArray;
	private String regionName;
	private String[] neighbouringRegions;
	private Map<Character, List<Entity>> characterMap;

	private Region region;

	public MapParser(File file) throws IOException {
		charArray = new CharArray();
		neighbouringRegions = new String[4];
		characterMap = new HashMap<>();

		try {
			readLines(new BufferedReader(new FileReader(file)));
			region = new Region(regionArray);
		} catch (IOException e) {
			System.out.println("I/O exception: " + e.toString());
			throw new FileNotFoundException("Failed to read file!");
		}
	}

	/**
	 * This method is for the initialization of the region file.
	 *
	 * @author Jordan Milburn
	 */
	private void readLines(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		Scanner s = new Scanner(line);

		while (s.hasNext(REGION_NAME)) {//only used in regions with neighbours.
			parseRegionName(s);
			line = reader.readLine();
			s = new Scanner(line);
		}

		while (line.length() < 2) {
			line = reader.readLine(); //skip whitespace
			s = new Scanner(line);
		}

		while (line.contains("=")) {
			mapCharToEntities(s);
			line = reader.readLine();
			s = new Scanner(line);
		}

		while (line.length() < 2) {
			line = reader.readLine(); //skip whitespace
		}

		while (line != null) {
			charArray.addLine(line.split(""));
			line = reader.readLine();
		}

		parseStringArray();


		System.out.println(this.regionName);
		for (String ss : this.neighbouringRegions) System.out.println(ss);
	}

	/** 
	 * parses the single character string array such that it generates a new Node and gives that node a mapEntity
	 * @author - Jordan Milburn
	 * */
	private void parseStringArray() {
		regionArray = new Tile[charArray.height()][charArray.width()];
		for (int row = 0; row < charArray.height(); row++) {
			for (int col = 0; col < charArray.width(); col++) {
				Tile tile = new Tile(row, col, charArray.get(row, col) + "");

				List<Entity> entities = characterMap.get(charArray.get(row, col));

				tile.add(entities.toArray(new Entity[entities.size()]));

				regionArray[row][col] = tile;
			}
		}
	}

	/**
	 * Create the map of chars to entities
	 *
	 * @author Mark Metcalfe
	 */
	private void mapCharToEntities(Scanner s) throws IOException {
		char c = s.next().charAt(0);
		s.next(); //consume "=" token
		List<Entity> entities = new ArrayList<>();
		entities.add(parse(s));
		while (s.hasNext("\\+")) {
			s.next(); //consume + token
			entities.add(parse(s));
		}
		characterMap.put(c, entities);
	}

	/**
	 * Generates a list of neighbours for the Region Manager to use.
	 * @author - Jordan Milburn
	 * */
	private void parseRegionName(Scanner s) {
		String next = s.findInLine(REGION_NAME);
		if (next.contains("this"))
			regionName = s.next();
		else if (next.contains("north"))
			neighbouringRegions[0] = s.next();
		else if (next.contains("east"))
			neighbouringRegions[1] = s.next();
		else if (next.contains("south"))
			neighbouringRegions[2] = s.next();
		else if (next.contains("west"))
			neighbouringRegions[3] = s.next();
	}

	public Region getRegion() {
		return region;
	}
	
	public String[] getNeighbouringRegions() {
		return neighbouringRegions;
	}

	/**
	 * Container class for the character array used by the MapParser
	 *
	 * @author Mark Metcalfe
	 */
	private class CharArray {

		private ArrayList<Character[]> charArray = new ArrayList<>();

		public void addLine(String[] in) {
			Character[] chars = new Character[in.length];
			for (int i = 0; i < chars.length; i++)
				chars[i] = in[i].charAt(0);
			charArray.add(chars);
		}

		private Character get(int row, int col) {
			return charArray.get(row)[col];
		}

		private int width() {
			return charArray.get(0).length;
		}

		private int height() {
			return charArray.size();
		}
	}
}
