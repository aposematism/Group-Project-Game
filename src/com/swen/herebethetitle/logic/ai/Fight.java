package com.swen.herebethetitle.logic.ai;

import java.util.Optional;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.pathfinding.Graph;
import com.swen.herebethetitle.pathfinding.Path;
import com.swen.herebethetitle.util.GridLocation;

/**
 * A single, self-contained fight between a player and a npc.
 * @author dylan
 */
class Fight implements Interaction {
    public final Player player;
    public final NPC npc;
    
    /**
     * Creates a new fight between a player and a npc.
     */
    public Fight(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }
    
    /**
     * Updates the fight after a single tick.
     * @param region The region the fight is occurring in.
     * @throws InteractionOver if the player is inaccessible to the enemy.
     */
    public void tick(Region region, Notifier notifier) throws InteractionOver {
        Optional<Path> optimalPath = optimalPathToPlayer(region);
        
        if (optimalPath.isPresent()) {
            GridLocation nextLocation = optimalPath.get().next();
            
            // Do not move the enemy onto the player.
            if (!nextLocation.equals(region.getLocation(player)))
                region.move(npc, nextLocation);
        } else {
            // No path to the player, just give up.
            throw new InteractionOver();
        }
        
        dispatchEvents(notifier);
        
        if (getWinner().isPresent())
            throw new InteractionOver();
    }
    
    private void dispatchEvents(Notifier notifier) {
        if (!getWinner().isPresent()) return;
        
        if (getWinner().get() == player) {
            notifier.notify(l -> l.onNPCKilled(npc));
        } else {
            notifier.notify(l -> l.onPlayerKilled(player, Optional.of(npc)));
        }
    }
    
    /**
     * Gets the winner of the fight.
     */
    public Optional<Entity> getWinner() {
        if (npc.getHealth() <= 0.0) return Optional.of(player);
        if (player.getHealth() <= 0.0) return Optional.of(npc);

        // FIXME: No winner yet.
        return Optional.empty();
    }

    /**
     * Finds the optimal path from the enemy to the player.
     */
    protected Optional<Path> optimalPathToPlayer(Region region) {
        Tile source = region.getTile(this.npc);
        Tile dest = region.getTile(this.player);
        Graph graph = new Graph(region, source, dest);
        return graph.findPath();
    }

    @Override
    public boolean isSameAs(Interaction interaction) {
        if (!(interaction instanceof Fight))
            return false;

        Fight f = (Fight)interaction;
        return f.player == this.player &&
                f.npc == this.npc;
    }
}