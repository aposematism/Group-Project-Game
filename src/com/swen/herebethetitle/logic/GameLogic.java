package com.swen.herebethetitle.logic;

import com.swen.herebethetitle.model.GameContext;

/**
 * Provides updating and logic support to a game model.
 * @author dylan
 */
public class GameLogic {
	private GameContext game;
	
	/**
	 * Creates a new game logic class.
	 */
	public GameLogic(GameContext game) {
		this.game = game;
	}
	
	public void update(float delta) {
		// FIXME: implement
	}
	
	public GameContext getGame() { return this.game; }
}
