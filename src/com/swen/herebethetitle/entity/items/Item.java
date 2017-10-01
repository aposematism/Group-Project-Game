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
public abstract class Item extends Entity {

	public Item(Image sprite) { super(sprite); }

	public void interact(GameContext context) {
		if(context.currentRegion.getPlayerTile().contains(this))
			pickup(context);
		else if(context.player.possesses(this))
			use(context);
	}

	public void pickup(GameContext context){
		context.getCurrentRegion().remove(this);
		context.getPlayer().add(this);
	}

	public void use(GameContext context){
		context.getPlayer().inventory().remove(this);
	}

	@Override
	public boolean isPenetrable() { return true; }
}
