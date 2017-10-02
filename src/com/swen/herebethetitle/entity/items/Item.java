package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

/**
 * Item - can be picked up by the player and stored in their inventory,
 * and then can be interacted with. Its actions are defined in an actions interface.
 *
 * Created by Mark on 19/09/17.
 *
 * @author Mark Metcalfe
 */
public abstract class Item extends Entity {

	public Item(String name, String spritePath) { super(name, spritePath); }

	/**
	 * Checks if the player is on the same tile as the item, and if so picks it up
	 * If the player has the item in their inventory, it is used
	 */
	@Override
	public void interact(GameContext context, Notifier notifier) {
		if(context.currentRegion.getPlayerTile().contains(this))
			pickup(context);
		else if(context.player.possesses(this))
			use(context);
	}

	/**
	 * Removes the Item from the map and places it in the player's inventory
	 */
	public void pickup(GameContext context){
		context.getCurrentRegion().remove(this);
		context.getPlayer().add(this);
	}

	/**
	 * Removes the item from the player's inventory, removing it from the game
	 */
	public void use(GameContext context){
		context.getPlayer().inventory().remove(this);
	}

	/**
	 * Items are penetrable by default, as the player needs to stand on them to pick them up
	 */
	@Override
	public boolean isPenetrable() { return true; }
}
