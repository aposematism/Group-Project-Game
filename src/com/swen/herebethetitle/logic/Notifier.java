package com.swen.herebethetitle.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * A notifier for a game.
 * @author dylan
 */
public class Notifier implements Iterable<GameListener> {
	/**
	 * The observers that are subscribed to events.
	 */
	private List<GameListener> listeners;
	
	/**
	 * Creates a new notifier.
	 */
	public Notifier() {
		this.listeners = new ArrayList<GameListener>();
	}
	
	/**
	 * Calls a function for every game listener.
	 */
	public void notify(Consumer<GameListener> notifier) {
		for (GameListener listener : this) {
			notifier.accept(listener);
		}
	}
	
	/**
	 * Adds a listener.
	 * @param listener
	 */
	public void addListener(GameListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public Iterator<GameListener> iterator() {
		return this.listeners.iterator();
	}
}
