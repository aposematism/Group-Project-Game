package com.swen.herebethetitle.logic.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;

/**
 * Controllers the enemies in the game.
 * @author dylan
 */
public class NpcController {
    /**
     * A list of all interactions that are currently in progress.
     */
    protected List<Interaction> activeInteractions;

    /**
     * Creates a new enemy controller.
     */
    public NpcController() {
        this.activeInteractions = new ArrayList<Interaction>();
    }
    
    /**
     * Updates the game after a single tick.
     */
    public void tick(GameContext context, Notifier notifier) {
        Iterator<Interaction> interactions = activeInteractions.iterator();

        while (interactions.hasNext()) {
            Interaction interaction = interactions.next();

            try {
                interaction.tick(context.getCurrentRegion(), notifier);
            } catch (Interaction.InteractionOver e) {
                // If the enemy gives up, the interaction no longer exists.
                interactions.remove();
                continue;
            }
        }
    }

    /**
     * Starts a interaction.
     */
    public void startFight(Player player, NPC npc) {
        startInteraction(new Fight(player, npc));
    }
    
    /**
     * Starts an interaction if it is not already in progress.
     */
    protected void startInteraction(Interaction interaction) {
        if (!isActive(interaction))
            this.activeInteractions.add(interaction);
    }
    
    /**
     * Checks if a player and a npc are interactioning.
     */
    protected boolean isActive(Interaction interaction) {
        return this.activeInteractions.stream()
                .anyMatch(i -> i.isSameAs(interaction));
    }
}
