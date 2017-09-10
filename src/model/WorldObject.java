package model;

/**
 * Represents an object in the world.
 * @author J Woods
 *
 */
public abstract class WorldObject {
	public boolean collision;
	public SpriteSet spriteSet;
	public int state;
	public int xCoord;	//collision coordinate x
	public int yCoord;	//collision coordinate y
	public int width;	//width of bounding box for collision
	public int height;	//height of bounding box for collision
	public int xOffset;	//x offset for rendering the sprite
	public int yOffset;	//y offset for rendering the sprite
}
