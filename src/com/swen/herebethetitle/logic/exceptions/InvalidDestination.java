package com.swen.herebethetitle.logic.exceptions;

import com.swen.herebethetitle.entity.Player;

/**
 * Thrown when a move is attempted that is not possible.
 * @author Dylan McKay
 */
public class InvalidDestination extends Exception {
    private static final long serialVersionUID = -2751815198575618672L;

    /**
     * The player that triggered the invalid move.
     */
    public final Player player;

    /**
     * Creates a new invalid move exception.
     */
    public InvalidDestination(Player player, String message) {
        super(message);
        this.player = player;
    }
}