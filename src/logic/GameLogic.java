package logic;

import model.GameModel;

/**
 * Provides updating and logic support to a game model.
 * @author dylan
 */
public class GameLogic {
	private GameModel game;
	
	/**
	 * Creates a new game logic class.
	 */
	public GameLogic(GameModel game) {
		this.game = game;
	}
	
	public void update(float delta) {
		// FIXME: implement
	}
	
	public GameModel getGame() { return this.game; }
}
