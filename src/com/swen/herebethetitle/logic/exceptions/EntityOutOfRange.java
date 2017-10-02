package com.swen.herebethetitle.logic.exceptions;

import com.swen.herebethetitle.entity.Entity;

/**
 * Called when the player attempts to interact with an out of range entity.
 * @author dylan
 */
public class EntityOutOfRange extends ImpossibleAction {
    private static final long serialVersionUID = 6502856031213289281L;

    /**
     * The entity that was out of range.
     */
    public final Entity entity;
    
    public EntityOutOfRange(Entity entity, String message) {
        super(message);
        this.entity = entity;
    }
}
