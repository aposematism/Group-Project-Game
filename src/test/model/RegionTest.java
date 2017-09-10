package test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Region;
import model.Sprite;
import model.WorldObject;

public class RegionTest {
	@Test
	public void canCreateRegion() {
		Sprite sprite = new Sprite(null);
		Region r = new Region(Region.Weather.SUNNY, sprite, new ArrayList<WorldObject>());
		assertEquals(Region.Weather.SUNNY, r.getWeather());
	}
}
