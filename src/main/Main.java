package main;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import model.GameContext;
import view.GUI;

/**
 * Main class for the 2D RPG.
 * @author J Woods
 *
 */
public class Main{
	
	private static GUI gui;
	
	public static void main(String[] args) throws Exception {
		GameContext game = new GameContext();
		GameLogic gameLogic = new GameLogic(game);
		/*
		 * the game and logic are both static in the GUI class.
		 *this lets the application launch a new instance properly.
		 */
		GUI.setGame(game);
		GUI.setLogic(gameLogic);
		GUI.launch(GUI.class);
	}
}
