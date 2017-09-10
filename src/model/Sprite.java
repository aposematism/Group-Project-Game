package model;

import javafx.scene.image.Image;

/**
 * Represents the image (for rendering) of a game world object at a single state
 * (e.g. idle, leftStepDown, etc)
 * @author J Woods
 *
 */
public class Sprite {
	
	private Image image;
	
	public Sprite(Image i) {

		image = i;
	}

	public Image getImage() {
		return image;
	}
}
