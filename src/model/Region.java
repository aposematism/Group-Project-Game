package model;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;

/**
 * Representation of a game region, which could be inside or outside.
 * For now, weather information is internal.
 * @author J Woods
 *
 */
public class Region {
	private Weather weather;
	private List<Entity> entities;

	private Tile[][] tiles;
	
	public Region(Weather w, ArrayList<Entity> o) {
		this.weather = w;
		this.entities = o;
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	/**
	 * Enumerations for weather in regions.
	 * @author J Woods
	 *
	 */
	static enum Weather{
		SUNNY,
		RAINY,
		SNOWY,
		FOGGY
	}
}
