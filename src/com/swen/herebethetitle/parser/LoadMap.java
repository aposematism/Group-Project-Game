package com.swen.herebethetitle.parser;

import com.swen.herebethetitle.model.Region;

import java.io.*;

/**
 * Created by Mark on 12/10/2017.
 */
public class LoadMap {

	private Region region;

	public LoadMap(File file) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
			TerrainParser terrainParser = new TerrainParser(reader);
			region = new Region(terrainParser.getRA());
			EntityParser entityParser = new EntityParser(reader);
			entityParser.parseEntitytoRegion(region);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
	}

	public Region getRegion() {
		return region;
	}
}
