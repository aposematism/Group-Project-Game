package model;

import java.util.HashMap;

/**
 * Represents a collection of sprites.
 * You can use the getSprite(int state) to get the sprite for a given state.
 * TODO look into using a plain old HashMap instead of this class. Don't honestly know if
 * this implementation gives any benefits
 * @author J Woods
 *
 */
public class SpriteSet {
	private HashMap<Integer, Sprite> sprites;
	private Sprite defaultSprite;
	
	public SpriteSet(HashMap<Integer,Sprite> s) {
		sprites = s;
		//attempt to get the default sprite, which should be mapped to 0
		if(sprites.containsKey(0)) {
			defaultSprite = sprites.get(0);
		}else {
			defaultSprite = (Sprite) sprites.values().toArray()[0];
		}
	}
	
	/**
	 * Returns the sprite for a given state if it exists, and if it doesn't,
	 * returns the default sprite.
	 * @param state
	 * @return
	 */
	public Sprite getSprite(int state) {
		if(sprites.containsKey(state)) {
			return sprites.get(state);
		}else {
			return defaultSprite;
		}
	}
}
