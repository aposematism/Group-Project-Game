package entity.items;

import javafx.scene.image.Image;
import entity.*;
import model.GameContext;
import util.GridLocation;

/**
 * Created by Mark on 19/09/2017.
 */
public class Weapon extends Item {

	private final double STRENGTH;

	private Actions actions;

	public Weapon(GameContext context, GridLocation spawnPos, Image sprite, double strength){
		super(context, spawnPos, sprite);
		this.STRENGTH = strength;
	}

	public double getStrength(){
		return STRENGTH;
	}

	public void attack(){
		actions.attack(this);
	}

	interface Actions extends Item.Actions {
		void attack(Weapon weapon);
	}

	public void setActions(Actions newActions){
		this.actions=newActions;
	}
}
