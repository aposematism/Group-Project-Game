package model;

/**
 * A non-interacting world object that exists pretty much just for collision and 
 * rendering.
 * @author J Woods
 *
 */
public class StaticObject extends WorldObject {
	
	public StaticObject(SpriteSet ss, int x, int y, int h, int w, int xo, int yo, boolean c) {
		state = 0;	//statics, for now, aren't animated
		spriteSet = ss;
		xCoord = x;
		yCoord = y;
		height = h;
		width = w;
		xOffset = xo;
		yOffset = yo;
		collision = c;
	}
}
