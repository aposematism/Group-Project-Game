package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;

import javafx.scene.image.Image;

/**
 * Item - can be picked up by the player and stored in their inventory,
 * and then can be interacted with. Its actions are defined in an actions interface.
 *
 * Created by Mark on 19/09/17.
 */
public class Item extends Entity {

	private Actions actions;

	public Item(GameContext context, Image sprite){
		super(context, sprite);
	}

	public void interact(GameContext context) {
		if(player().possesses(this)) {
			actions.use(this, context.getCurrentRegion());
		} else {
		    Tile playerTile = context.getCurrentRegion().getTile(player());
		    Tile itemTile = context.getCurrentRegion().getTile(this);
		    if(playerTile == itemTile)
                actions.pickup(this, context.getCurrentRegion());
		}
	}

	public void setActions(Actions actions){
		this.actions = actions;
	}

	public Actions getActions() { return this.actions; }

	public interface Actions {
		void pickup(Item item, Region region);
		void use(Item item, Region region);
		String toString();
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public boolean isPenetrable(){
		return true;
	}
}
