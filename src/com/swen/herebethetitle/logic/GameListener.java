package com.swen.herebethetitle.logic;

import com.swen.herebethetitle.entity.Item;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.Static;

import java.util.Optional;

/**
 * A listener class which can handle updates from the game.
 * 
 * @author dylan
 */
public interface GameListener {
    /**
     * Called when the player obtains a title and completes the game.
     */
    void onGameCompleted();
    
    /**
     * Called when the player is moved.
     */
    void onPlayerMoved(Player player);

    /**
     * Called when the player is attacked by a NPC.
     */
    void onPlayerAttacked(Player player, NPC attacker);

    /**
     * Called when the player is killed.
     * 
     * @param aggressor
     *            The entity that did the deed.
     */
    void onPlayerKilled(Player player, Optional<NPC> aggressor);

    /**
     * Called when a player picks up an item.
     */
    void onPlayerPickup(Player player, Item item);

    /**
     * Called when a player drops an item.
     */
    void onPlayerDrop(Player player, Item item);

    /**
     * Called when a NPC is attacked by the player.
     */
    void onNPCAttacked(NPC victim);

    /**
     * Called when an enemy is killed.
     */
    void onNPCKilled(NPC npc);
    
    /**
     * Called when dialog between a player and an NPC begins.
     */
    void onNPCDialogBegin(NPC npc);
    
    /**
     * Called when an NPC speaks a message to the player.
     */
    void onNPCDialogMessage(NPC npc, String message);
    
    /**
     * Called when a discussion between the NPC and player is over.
     */
    void onNPCDialogEnd(NPC npc);
    
    /**
     * Called when a door is unlocked by a key.
     */
    void onDoorUnlocked(Static door);
    
    /**
     * Called when we failed to unlock a door.
     */
    void onDoorUnlockFailed(Static door, String message);
    
    /**
     * Called when a door is successfully opened.
     */
    void onDoorOpened(Static door);
    
    /**
     * Called when a door is closed.
     * 
     * Note that if the door has been previously unlocked,
     * it will stay unlocked.
     */
    void onDoorClosed(Static door);

    void onGameWin();

    void onGameLose();
}
