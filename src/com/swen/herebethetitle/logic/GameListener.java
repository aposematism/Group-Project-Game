package com.swen.herebethetitle.logic;

import java.util.Optional;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.statics.Static;

/**
 * A listener class which can handle updates from the game.
 * 
 * @author dylan
 */
public abstract class GameListener {
    /**
     * Called when the player is moved.
     */
    public void onPlayerMoved(Player player) {
    }

    /**
     * Called when the player is attacked by a NPC.
     */
    public void onPlayerAttacked(Player player, NPC attacker) {
    }

    /**
     * Called when the player is killed.
     * 
     * @param aggressor
     *            The entity that did the deed.
     */
    public void onPlayerKilled(Player player, Optional<NPC> aggressor) {
    }

    /**
     * Called when a player picks up an item.
     */
    public void onPlayerPickup(Player player, Item item) {
    }

    /**
     * Called when a player drops an item.
     */
    public void onPlayerDrop(Player player, Item item) {
    }

    /**
     * Called when a NPC is attacked by the player.
     */
    public void onNPCAttacked(NPC victim) {
    }

    /**
     * Called when an enemy is killed.
     */
    public void onNPCKilled(NPC npc) {
    }
    
    /**
     * Called when dialog between a player and an NPC begins.
     */
    public void onNPCDialogBegin(NPC npc) { }
    
    /**
     * Called when an NPC speaks a message to the player.
     */
    public void onNPCDialogMessage(NPC npc, String message) {
    }
    
    /**
     * Called when a discussion between the NPC and player is over.
     */
    public void onNPCDialogEnd(NPC npc) {
    }
    
    /**
     * Called when a door is unlocked by a key.
     */
    public void onDoorUnlocked(Static door) {
    }
    
    /**
     * Called when we failed to unlock a door.
     */
    public void onDoorUnlockFailed(Static door, String message) { 
    }
    
    /**
     * Called when a door is successfully opened.
     */
    public void onDoorOpened(Static door) {
    }
    
    /**
     * Called when a door is closed.
     * 
     * Note that if the door has been previously unlocked,
     * it will stay unlocked.
     */
    public void onDoorClosed(Static door) {
    }
}
