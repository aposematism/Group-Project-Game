package com.swen.herebethetitle.logic.ai.test;

import static org.junit.Assert.fail;

import java.util.Arrays;

import com.swen.herebethetitle.entity.ai.Conversational;
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
    	Conversational conversation = new Conversational();
	    conversation.addDialog("Hello!");
    	speaker.setBehavior(conversation);

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
