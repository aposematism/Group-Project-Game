package com.swen.herebethetitle.logic.ai.test;

import static org.junit.Assert.fail;

import com.swen.herebethetitle.entity.ai.FriendlyStrategy;
import org.junit.Before;
import org.junit.Test;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.logic.ai.Discussion;
import com.swen.herebethetitle.logic.ai.Interaction.InteractionOver;
import com.swen.herebethetitle.util.Direction;

public class DiscussionTest {
    /**
     * The discussion speaker.
     */
    private NPC speaker;
    
    @Test
    public void canSayASingleMessage() {
    	FriendlyStrategy friendly = new FriendlyStrategy();
	    friendly.addDialog("Hello!");
    	speaker.setBehavior(friendly);

    	Discussion discussion = new Discussion(speaker);

        try {
            discussion.sayNext(new Notifier());
        } catch (InteractionOver e) {
            fail("discussion should not be over immediately after first sayNext");
        }

        try {
            discussion.sayNext(new Notifier());
            fail("interaction should now be over");
        } catch (InteractionOver e) {
            // happy path
        }
    }
    
    @Before
    public void setup() {
        this.speaker = new NPC("bob", null, NPC.FULL_HEALTH, Direction.Down);
    }
}
