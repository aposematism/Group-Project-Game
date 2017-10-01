package com.swen.herebethetitle.entity.stationeries;


import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.model.GameContext;

import javafx.scene.image.Image;

/**
 * Created by Mark on 19/09/2017.
 */
public class Door implements Stationary.Behavior {

	public enum STATE { LOCKED, UNLOCKED, OPEN }

	private STATE state;

	private final int KEY;

	public Door(int key, STATE state){
		KEY = key;
		this.state = state;
	}

	@Override
	public void interact(GameContext context, Stationary door) {
		if(state==STATE.LOCKED && hasKey(context.player))
			state = STATE.OPEN;
		else if(state==STATE.UNLOCKED)
			state = STATE.OPEN;
		else
			state = STATE.UNLOCKED;
	}

	private boolean hasKey(Player player){
		for(Item i: player.inventory())
			if(i instanceof Key)
				if(((Key)i).equals(KEY))
					return true;
		return false;
	}

	@Override
	public boolean isPenetrable() { return state==STATE.OPEN; }

	@Override
	public String toString() { return super.toString()+" "+KEY+" "+state.toString(); }
}
