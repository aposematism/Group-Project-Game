package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.GameContext;
import view.GUI;

/**
 * Main class for the 2D RPG.
 * @author J Woods
 *
 */
public class Main extends Application{
	
	private static GUI gui;
	
	public static void main(String[] args) {
		GameContext game = new GameContext();

		gui = new GUI(game);
		gui.launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
	}
}
