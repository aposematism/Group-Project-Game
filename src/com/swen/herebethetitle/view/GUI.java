package com.swen.herebethetitle.view;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import com.swen.herebethetitle.exceptions.NotImplementedYetException;
import com.swen.herebethetitle.logic.GameLogic;
import com.swen.herebethetitle.model.GameContext;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This is the main, top-level, wrapper class for the GUI, and for now also the main
 * class for the entire game.
 * Everything GUI-related goes through relationships with this and the controller,
 * and this and the model.
 * @author J Woods
 *
 */

/*
 * TODO
 * 
 * -Add in subordinate menu functionality for new game, load game, and settings
 * -Make the menu pretty somehow
 * -Setup a timer to call 'gameLogic.update()'
 * 
 */
public class GUI extends Application implements Observer, KeyListener, MouseListener{
	//window field
	private Stage window;
	
	//main menu fields
	private Scene mainMenu;
	private BorderPane mainMenuLayout;
	private GridPane settingsMenu;
	private GridPane newGameMenu;
	private GridPane loadGameMenu;
	private GridPane quitMenu;
	private HUD hud;
	
	//main game UI fields
	private WorldGraphics worldGraphics;
	//TODO add in the game model
	
	//Game fields
	private static GameContext game;
	private static GameLogic logic;
	
	//TODO this should perhaps be removed
	public GUI(GameContext game, GameLogic logic) {
		this.launch();
		this.game = game;
		this.logic = logic;
	}
	
	/**
	 * Default constructor, needed for use with the Main class.
	 */
	public GUI() {
		super();
	}
	
	//TODO - maybe remove this? With the addition of our main class this might be unnecessary
//	public static void main (String[] args) {
//		launch(args);
//	}
	

	@Override
	public void start(Stage s) throws Exception {
		/*initialize the stage*/
		window = s;
		window.setTitle("2D RPG");
		
		/*initialize the main menu*/
		mainMenuLayout = initMainMenu();
		mainMenu = new Scene(mainMenuLayout, 1000,650);
		window.setScene(mainMenu);
		/*initialize subordinate menus*/
		settingsMenu = initSettingsMenu();
		newGameMenu = initNewGameMenu();
		loadGameMenu = initLoadGameMenu();
		quitMenu = initQuitMenu();
		
		/*show the stage*/
		window.show();
		

		//FIXME remove this debug code
		System.out.println("Game in GUI object: " + game.toString());
		System.out.println("Logic in GUI object: " + logic.toString());
	}
	
	/**
	 * Initializes the main menu.
	 */
	private BorderPane initMainMenu() {
		/*initialize layout*/
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(60));
		
		/*add the title*/
		//create title
		Label titleLabel = new Label("Here Be The Title");
		titleLabel.setFont(new Font(50));
		//add title
		layout.setTop(titleLabel);
		
		/*add buttons*/
		VBox buttons = new VBox(10);
		//quit
		Button quit = new Button("Quit");
		quit.setOnAction(e->{
			mainMenuLayout.getChildren().removeAll(settingsMenu,newGameMenu,loadGameMenu,quitMenu);
			mainMenuLayout.setCenter(quitMenu);
		});
		quit.setPrefSize(100, 20);
		//new game
		Button newGame = new Button("New Game");
		newGame.setOnAction(e->{
			mainMenuLayout.getChildren().removeAll(settingsMenu,newGameMenu,loadGameMenu,quitMenu);
			mainMenuLayout.setCenter(newGameMenu);
		});
		newGame.setPrefSize(100, 20);
		//load game
		Button loadGame = new Button("Load Game");
		loadGame.setOnAction(e->{
			mainMenuLayout.getChildren().removeAll(settingsMenu,newGameMenu,loadGameMenu,quitMenu);
			mainMenuLayout.setCenter(loadGameMenu);
		});
		loadGame.setPrefSize(100, 20);
		//settings
		Button settings = new Button("Settings");
		settings.setOnAction(e->{
			mainMenuLayout.getChildren().removeAll(settingsMenu,newGameMenu,loadGameMenu,quitMenu);
			mainMenuLayout.setCenter(settingsMenu);
		});
		settings.setPrefSize(100, 20);
		//add all the buttons
		buttons.getChildren().addAll(newGame,loadGame,settings,quit);
		layout.setLeft(buttons);
		
		return layout;
	}
	
	/**
	 * Initializes the settings menu.
	 * @return the settings menu
	 */
	private GridPane initSettingsMenu() {	
		/*initialize layout*/
		GridPane layout = new GridPane();
		GridPane.setConstraints(layout, 1, 4);
		layout.setPadding(new Insets(10,0,0,60));
		layout.setHgap(10);
		layout.setVgap(10);
		
		/*add the title*/
		//create title
		Label titleLabel = new Label("Settings");
		titleLabel.setFont(new Font(30));
		//add title
		GridPane.setConstraints(titleLabel, 0, 0);
		layout.getChildren().add(titleLabel);
		
		/*add widgety stuff to change settings TODO*/
		
		return layout;
	}
	
	/**
	 * Initializes the new game menu.
	 * @return the new game menu
	 */
	private GridPane initNewGameMenu() {
		/*initialize layout*/
		GridPane layout = new GridPane();
		GridPane.setConstraints(layout, 1, 4);
		layout.setPadding(new Insets(10,0,0,60));
		layout.setHgap(10);
		layout.setVgap(10);
		
		/*add the title*/
		//create title
		Label titleLabel = new Label("New Game");
		titleLabel.setFont(new Font(30));
		//add title
		GridPane.setConstraints(titleLabel, 0, 0);
		layout.getChildren().add(titleLabel);
		
		/*create the button that generates the test region*/
		Button play = new Button("Play");
		play.setPrefSize(100, 20);
		play.setOnAction(e->{
			//TODO initialize the game
			worldGraphics = initGameGUI();
			window.setScene(worldGraphics);
		});
		GridPane.setConstraints(play, 0, 1);
		layout.getChildren().add(play);
		
		return layout;
	}
	
	/**
	 * Initializes the game loading menu.
	 * @return the load game menu
	 */
	private GridPane initLoadGameMenu() {
		/*initialize layout*/
		GridPane layout = new GridPane();
		GridPane.setConstraints(layout, 1, 4);
		layout.setPadding(new Insets(10,0,0,60));
		layout.setHgap(10);
		layout.setVgap(10);
		
		/*add the title*/
		//create title
		Label titleLabel = new Label("Load Game");
		titleLabel.setFont(new Font(30));
		//add title
		GridPane.setConstraints(titleLabel, 0, 0);
		layout.getChildren().add(titleLabel);
		
		/*add widgety stuff to change settings TODO*/
		//here should be Jordan's loading methods integrated to a GUI
		Button loadGame = new Button("Load Game");
		loadGame.setOnAction(e->{throw new NotImplementedYetException();});
		GridPane.setConstraints(loadGame, 0, 1);
		layout.getChildren().add(loadGame);
		return layout;
	}
	
	/**
	 * Initializes the quit menu.
	 * @return the settings menu
	 */
	private GridPane initQuitMenu() {
		/*initialize layout*/
		GridPane layout = new GridPane();
		GridPane.setConstraints(layout, 1, 4);
		layout.setPadding(new Insets(10,0,0,60));
		layout.setHgap(10);
		layout.setVgap(10);
		
		/*add the title*/
		//create title
		Label titleLabel = new Label("Quit");
		titleLabel.setFont(new Font(30));
		//add title
		GridPane.setConstraints(titleLabel, 0, 0);
		layout.getChildren().add(titleLabel);
		
		/*add the actual menu stuff*/
		//create & add label asking user if they want to quit
		Label areYouSure = new Label("Are you sure you want to quit?");
		GridPane.setConstraints(areYouSure, 0, 1);
		layout.getChildren().add(areYouSure);
		
		//create quit button
		Button quit = new Button("Yes");
		quit.setPrefSize(100, 20);
		quit.setOnAction(e->System.exit(0));
		GridPane.setConstraints(quit, 0, 2);
		layout.getChildren().add(quit);
		
		return layout;
	}
	
	/**
	 * Initializes the game GUI.
	 */
	private WorldGraphics initGameGUI() {
		BorderPane pane = new BorderPane();
		WorldGraphics w = new WorldGraphics(game, pane);
		//create new scene
		return w;
	}
	
	/**
	 * initializes the HUD.
	 */
	private Group initHUD() {
		//TODO implement this
		throw new NotImplementedYetException();
	}
	
	public static void setGame(GameContext g) {
		game = g;
	}	
	public static void setLogic(GameLogic l) {
		logic = l;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		/*This should update all of the GUI components, namely the world graphics and HUD.*/
		worldGraphics.update();
		hud.update();
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_P:
			/*if the game is running, add the pause menu to the main scene*/
			throw new NotImplementedYetException();
		}
		
	}

}
