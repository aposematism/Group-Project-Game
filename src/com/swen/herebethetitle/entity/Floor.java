package com.swen.herebethetitle.entity;

import com.swen.herebethetitle.model.GameContext;
import javafx.scene.image.Image;

/**
 * Represents the Floor of each tile, the ground that other entities sit on top of
 *
 * Created by Mark on 27/09/17.
 *
 * @author Mark Metcalfe
 */
public class Floor extends Entity {

    public Floor(Image sprite){ super("Floor", sprite); }

	/**
	 * Deliberately Blank, Floor can't be interacted with by design
	 */
	@Override
    public void interact(GameContext context){}

	/**
	 * The floor is designed to be underneath other entities, so other entities
	 * need to be able to sit on top of it
	 */
	@Override
    public boolean isPenetrable() { return false; }

    @Override
    public String toString() { return super.toString(); }
}
