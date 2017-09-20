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
	private Sprite defaultTile;	//default tile
	private List<Entity> entities;
	
	public Region(Weather w, Sprite d, ArrayList<Entity> o) {
		this.weather = w;
		this.defaultTile = d;
		this.entities = o;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> worldObjects) {
		this.entities = worldObjects;
	}

	public Sprite getDefaultTile() {
		return defaultTile;
	}

	public void setDefaultTile(Sprite defaultTile) {
		this.defaultTile = defaultTile;
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
