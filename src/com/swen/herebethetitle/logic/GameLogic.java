package com.swen.herebethetitle.logic;

import java.util.List;
import java.util.stream.Collectors;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.logic.ai.NpcController;
import com.swen.herebethetitle.logic.exceptions.EntityOutOfRange;
import com.swen.herebethetitle.logic.exceptions.ImpossibleAction;
import com.swen.herebethetitle.logic.exceptions.InvalidDestination;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

/**
 * Provides updating and logic support to a game model.
 * 
 * @author dylan
 */
public class GameLogic {
    public static final double VICINITY_RADIUS = 15.0;

    /**
     * The listener notifier.
     */
    private Notifier notifier;

    /**
     * The game that is being controlled.
     */
    private GameContext context;

    /**
     * The NPC controller.
     */
    private NpcController npcController;

    /**
     * Creates a new game logic class.
     */
    public GameLogic(GameContext context) {
        this.context = context;
        this.notifier = new Notifier();
        this.npcController = new NpcController();
    }

    /**
     * Updates the game logic after some time has passed.
     * 
     * Moves enemies.
     * 
     * @param delta
     *            The number of elapsed seconds.
     */
    public void update(float delta) {
        triggerPossibleInteractions();

        npcController.tick(context, notifier);
    }

    /**
     * Triggers any possible interactions.
     */
    private void triggerPossibleInteractions() {
        GridLocation playerLocation = context.getCurrentRegion().getLocation(getPlayer());
        List<Tile> vicinity = context.getCurrentRegion().vicinityList(playerLocation, VICINITY_RADIUS);

        for (Tile tile : vicinity) {
            List<NPC> aggressiveNpcs = tile.stream().filter(entity -> entity instanceof NPC).map(entity -> (NPC) entity)
                    .filter(NPC::isAggressive).collect(Collectors.toList());
            for (NPC npc : aggressiveNpcs) {
                npcController.startFight(getPlayer(), npc);
            }
        }
    }

    /**
     * Get the player to attack an entity.
     * 
     * The victim
     * 
     * @param victim
     *            The entity to be attacked.
     * @throws ImpossibleAction
     *             if the victim is out of range.
     */
    public void attack(NPC victim) throws EntityOutOfRange {
        // FIXME: unimplemented.
        notifier.notify(listener -> listener.onNPCAttacked(victim));
    }

    /**
     * Picks up an item to the inventory.
     */
    public void pickup(Item item) {
        getPlayer().inventory().add(item);
        notifier.notify(listener -> listener.onPlayerPickup(getPlayer(), item));
    }

    /**
     * Drops an item from the inventory.
     */
    public void drop(Item item) {
        getGame().getPlayer().inventory().remove(item);
        notifier.notify(listener -> listener.onPlayerDrop(getPlayer(), item));
    }

    /**
     * Moves the player in a specific direction.
     * 
     * @param direction
     *            The direction to move in.
     * @throws InvalidDestination
     *             If the move would leave the player out of bounds or there is an
     *             impenetrable obstacle in the way.
     */
    public void movePlayer(Direction direction) throws InvalidDestination {
        GridLocation currentLocation = getCurrentRegion().getLocation(context.getPlayer());
        GridLocation newLocation = currentLocation.adjacent(direction);

        if (!getCurrentRegion().isWithin(newLocation))
            throw new InvalidDestination(context.getPlayer(), "direction is out of bounds");

        if (!getCurrentRegion().isPenetrable(newLocation))
            throw new InvalidDestination(getPlayer(), "an obstacle is in the way");

        context.getCurrentRegion().move(context.getPlayer(), newLocation);
        notifier.notify(listener -> listener.onPlayerMoved(getPlayer()));
    }

    /**
     * Adds a game listener.
     */
    public void addGameListener(GameListener listener) {
        this.notifier.addListener(listener);
    }

    /**
     * Gets the game that is being controlled.
     */
    public GameContext getGame() {
        return this.context;
    }

    /**
     * Gets the current region of the player.
     */
    private Region getCurrentRegion() {
        return context.getCurrentRegion();
    }

    /**
     * Gets the player object.
     */
    private Player getPlayer() {
        return context.getPlayer();
    }
}
