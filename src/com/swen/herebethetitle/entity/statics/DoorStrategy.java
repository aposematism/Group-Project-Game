package com.swen.herebethetitle.entity.statics;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.items.Key;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

/**
 * NPCBehavior for Doors
 *
 * Created by Mark on 19/09/2017.
 *
 * @author Mark Metcalfe
 */
public final class DoorStrategy implements Static.Behavior {

	/**
	 * A door can either be locked, unlocked (but shut), and open
	 */
	public enum STATE { LOCKED, UNLOCKED, OPEN }

	private STATE state;

	private final int KEY;

	/**
	 * @param key The Key code ID of the door
	 * @param state Whether the door is locked, unlocked or open
	 */
	public DoorStrategy(int key, STATE state){
		KEY = key;
		this.state = state;
	}

	/**
	 * Attempt to unlock/open the door
	 */
	@Override
	public void interact(GameContext context, Static door, Notifier notifier) {
	    switch(state) {
	    case LOCKED:
		    if (hasKey(context.player)) {
                state = STATE.OPEN;
                notifier.notify(l -> l.onDoorUnlocked(door));
		    } else {
		        notifier.notify(l -> l.onDoorUnlockFailed(door,
		                "You don't have the key for this door!"));
		    }
		    break;
	    case UNLOCKED:
			state = STATE.OPEN;
			notifier.notify(l -> l.onDoorOpened(door));
			break;
	    case OPEN:
			state = STATE.UNLOCKED;
			notifier.notify(l -> l.onDoorClosed(door));
			break;
	    }
	}

	/**
	 * Check that the Player possess a key with the same key ID as this door
	 */
	private boolean hasKey(Player player){
		for(Item i: player.inventory())
			if(i instanceof Key)
				if(((Key)i).equals(KEY))
					return true;
		return false;
	}

	/**
	 * The door can be moved through if it is open
	 */
	@Override
	public boolean isPenetrable() { return state==STATE.OPEN; }

	@Override
	public String toString() {
		return getClass().getSimpleName().replace("Strategy","")+" "+KEY+" "+state.toString();
	}
}
