package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.model.GameContext;
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

	public Item(Image sprite){
		super(sprite);
	}

	public void interact(GameContext context) {
	    Player player = context.getPlayer();
		if(player.possesses(this)) {
			actions.use(this, context);
		} else {
		    Tile playerTile = context.getCurrentRegion().getTile(player);
		    Tile itemTile = context.getCurrentRegion().getTile(this);
		    if(playerTile == itemTile)
                actions.pickup(this, context);
		}
	}

	public void setActions(Actions actions){
		this.actions = actions;
	}

	public Actions getActions() { return this.actions; }

	public interface Actions {
		void pickup(Item item, GameContext context);
		void use(Item item, GameContext context);
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
