package com.swen.herebethetitle.entity.stationary;


import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

/**
 * Created by Mark on 19/09/2017.
 */
public class Door extends Obstacle {

	private enum STATE { LOCKED, UNLOCKED, OPEN }

	private STATE state;

	private final int KEY;

	public Door(Image sprite, int key, STATE state){
		super(sprite);
		KEY = key;
		this.state = state;
	}

	@Override
	public void interact(GameContext context) {
		if(state==STATE.LOCKED && hasKey(context.player))
			state = STATE.OPEN;
		else if(state==STATE.UNLOCKED)
			state = STATE.OPEN;
		else
			state = STATE.UNLOCKED;
	}

	private boolean hasKey(Player player){
		while(player.getInventory().hasNext()){
			Item i = player.getInventory().next();
			if(i.getActions() instanceof Key)
				if(((Key)i.getActions()).equals(KEY))
					return true;
		}
		return false;
	}

	@Override
	public boolean isPenetrable() { return state==STATE.OPEN; }

	@Override
	public String toString() { return null; }
}
