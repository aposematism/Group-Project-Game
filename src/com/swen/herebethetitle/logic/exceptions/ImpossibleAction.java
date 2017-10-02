package com.swen.herebethetitle.logic.exceptions;


/**
 * Thrown when an impossible action is attempted.
 * @author dylan
 */
public class ImpossibleAction extends RuntimeException {
    private static final long serialVersionUID = 7196720399745147009L;
    
    public ImpossibleAction(String message) {
        super(message);
    }
}