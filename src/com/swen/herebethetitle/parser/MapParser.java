package com.swen.herebethetitle.parser;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;

import java.io.*;
import java.util.*;

/**
 * This class is the terrain parser, which is the first stage in the parsing process.
 * For parsing any objects which do not interact with the player, NPCs or enemies.
 *
 * @author Jordan Milburn & Mark Metcalfe
 */
public class MapParser {
	private CharArray charArray;
	private Tile[][] regionArray;
	private String[] neighbouringRegions;
	private Map<Character, List<Entity>> characterMap;

	private Region region;

	public MapParser(File file) throws IOException {
		charArray = new CharArray();
		neighbouringRegions = new String[4];
		characterMap = new HashMap<>();

		BufferedReader reader = new BufferedReader(new FileReader(file));

		try {
			readLines(reader);
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

		while (line.contains("=")) {
			mapCharToEntities(s);
			line = reader.readLine();
			s = new Scanner(line);
		}

		while (s.hasNext("neighbours:")) {//only used in regions with neighbours.
			s.next();
			parseNeighbouringRegions(s);
			line = reader.readLine();
		}

		while (line != null) {
			charArray.addLine(line.split(""));
			line = reader.readLine();
		}

		parseStringArray();
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
		entities.add(new EntityParser().parseEntity(s));
		while (s.hasNext("\\+")) {
			s.next(); //consume + token
			entities.add(new EntityParser().parseEntity(s));
		}
		characterMap.put(c, entities);
	}

	/**
	 * Generates a list of neighbours for the Region Manager to use.
	 * @author - Jordan Milburn
	 * */
	private void parseNeighbouringRegions(Scanner s) {
		for (int i = 0; i < 4; i++) {
			neighbouringRegions[i] = s.next();
		}
	}

	public Region getRegion() {
		return region; }
	
	public String[] getNeighbouringRegions() {
		return neighbouringRegions;
	}
}
