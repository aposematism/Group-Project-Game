package com.swen.herebethetitle.logic.ai;

import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.Region;

public interface Interaction {
	/**
	 * Thrown when an interaction is over.
	 */
	public static class InteractionOver extends Exception {
		private static final long serialVersionUID = 651923904811737086L;
	}

	/**
	 * Updates the interaction after a single tick.
	 * 
	 * @param region
	 *            the region the interaction is occurring in.
	 * @throws InteractionOver
	 *             when something interrupts the interaction.
	 */
	public void tick(Region region, Notifier notifier) throws InteractionOver;

	/**
	 * Checks if the interaction is the same as another.
	 * 
	 * We do not want to use equality here because two fights could be with the same
	 * entities but at different health levels.
	 */
	public boolean isSameAs(Interaction interaction);
}
