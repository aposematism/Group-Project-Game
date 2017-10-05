package com.swen.herebethetitle.parser;

/**
 * Created by Mark on 5/10/2017.
 */
public class SyntaxError extends Exception {
	public SyntaxError(String message){ super(message); }
	public SyntaxError(){ super("Couldn't Parse Input"); }
}
