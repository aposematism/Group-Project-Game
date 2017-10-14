package com.swen.herebethetitle.logic;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.FriendlyStrategy;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.logic.ai.NpcController;
import com.swen.herebethetitle.logic.exceptions.EntityOutOfRange;
import com.swen.herebethetitle.logic.exceptions.ImpossibleAction;
import com.swen.herebethetitle.logic.exceptions.InvalidDestination;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Provides updating and logic support to a game model.
 * 
 * @author dylan
 */
public class GameLogic {
    public static final double VICINITY_RADIUS = 6.0;

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
     */
    public void tick() {
        triggerPossibleInteractions();

        npcController.tick(context, notifier);
        
        // Check for game over.
        if (getPlayer().inventory().containsTitle()) {
            notifier.notify(l -> l.onGameCompleted());
        }
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
     * Attempts to attack the closest NPC to the player.
     * @throws EntityOutOfRange if there are no entities to attack.
     */
    public void attack() throws EntityOutOfRange {
        Collection<Tile> neighbouringTiles = getCurrentRegion().getNeighbours(getCurrentRegion().getPlayerTile());
        for (Tile tile : neighbouringTiles) {
            Optional<NPC> aggressiveNpc = tile.stream()
                    .filter(entity -> entity instanceof NPC)
                    .map(entity -> (NPC)entity)
                    .filter(NPC::isAggressive)
                    .findFirst();
            
            if (aggressiveNpc.isPresent()) {
                attack(aggressiveNpc.get());
            } else {
                throw new EntityOutOfRange("no monsters in range to attack");
            }
        }
    }

    /**
     * Get the player to attack a specific entity.
     * 
     * The victim
     * 
     * @param victim
     *            The entity to be attacked.
     * @throws ImpossibleAction
     *             if the victim is out of range.
     */
    public void attack(NPC victim) throws EntityOutOfRange {
        ensureCanInteractWith(victim);
        
        if (victim.isDead())
            throw new IllegalArgumentException("cannot attack a dead entity");
        
        victim.interact(context, notifier);
    }
    
    /**
     * Starts a discussion with an NPC if it is possible.
     * 
     * Does nothing if the NPC has no dialog.
     */
    protected void startDiscussion(NPC npc) {
        npcController.startDiscussion(npc);
    }

    /**
     * General-purpose entity interactions.
     */
    public void interact(GridLocation location) throws EntityOutOfRange {
        Tile tile = getCurrentRegion().get(location);

	    Entity entity = tile.getTopEntity();
	    interact(entity);
    }

    /**
     * General-purpose entity interactions.
     * 
     * This should be called on entities that are in the map.
     */
    public void interact(Entity entity) throws EntityOutOfRange {
        if (entity instanceof NPC) {
            NPC npc = (NPC)entity;
            if (npc.isAggressive())
                attack(npc);
            else if (npc.getBehavior().isPresent() && npc.getBehavior().get() instanceof FriendlyStrategy)
                startDiscussion(npc);
            else
                ; // no interactions possible.
        } else {
            System.out.println("LOGIC: "+context.toString());
            entity.interact(context, notifier);
        }
    }

    /***
     * Raises an error if the player cannot interact with an entity.
     * @throws EntityOutOfRange if the entity is too far away from the player.
     */
    private void ensureCanInteractWith(Entity entity) throws EntityOutOfRange {
        if (!canInteractWith(entity))
            throw new EntityOutOfRange(entity, "entity out of range");
    }
    
    /**
     * Checks if an entity can interact with another entity.
     * 
     * This can only happen if the player is neighboring the entity.
     */
    private boolean canInteractWith(Entity entity) {
        GridLocation entityLocation = getCurrentRegion().getLocation(entity);
        GridLocation playerLocation = getCurrentRegion().getLocation(getPlayer());
        return entityLocation.isNeighbouring(playerLocation);
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
