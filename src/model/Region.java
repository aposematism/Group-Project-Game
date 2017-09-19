package model;

import java.util.ArrayList;

/**
 * Representation of a game region, which could be inside or outside.
 * For now, weather information is internal.
 * @author J Woods
 *
 */
public class Region {
	private Weather weather;
	private Sprite defaultTile;	//default tile
	private ArrayList<WorldObject> worldObjects;
	private Grid grid;
	
	public Region(Weather w, Sprite d, ArrayList<WorldObject> o, Grid grid) {
		setWeather(w);
		setDefaultTile(d);
		setWorldObjects(o);
		this.grid = grid;
	}
	
	public ArrayList<WorldObject> getWorldObjects() {
		return worldObjects;
	}

	public void setWorldObjects(ArrayList<WorldObject> worldObjects) {
		this.worldObjects = worldObjects;
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
	
	public Grid getGrid() {
		return this.grid;
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
