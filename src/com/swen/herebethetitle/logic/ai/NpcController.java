package com.swen.herebethetitle.logic.ai;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.FriendlyStrategy;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        progressInteractions(context, notifier);
        updateNpcs(context);
    }
    
    /**
     * Progress all active interactions in the game.
     * 
     * Removes all interactions that are over.
     */
    protected void progressInteractions(GameContext context, Notifier notifier) {
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
     * Pings all NPCs in the region.
     */
    protected void updateNpcs(GameContext context) {
        for (Tile tile : context.getCurrentRegion()) {
            for (Entity entity : tile.getInteractives()) {
                if (entity instanceof NPC) {
                    NPC npc = (NPC)entity;
                    npc.ping(context);
                }
            }
        }
    }

    /**
     * Starts a interaction.
     */
    public void startFight(Player player, NPC npc) {
        if (npc.isAggressive())
            startInteraction(new Fight(player, npc));
    }
    
    /**
     * Starts a discussion with an NPC
     * @throws IllegalArgumentException if the npc has no dialog.
     */
    public void startDiscussion(NPC npc) {
        if (npc.getBehavior().isPresent() & npc.getBehavior().get() instanceof FriendlyStrategy)
            startInteraction(new Discussion(npc));
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
