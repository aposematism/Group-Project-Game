package com.swen.herebethetitle.entity.ai;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.model.GameContext;

/**
 * Created by Mark on 30/09/2017.
 */
public interface Behavior {
	void interact(GameContext context, NPC npc);
	boolean isPenetrable();
	String toString();
}
