package com.swen.herebethetitle.control;

import java.util.Optional;

import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.items.Item;
import com.swen.herebethetitle.entity.statics.Static;
import com.swen.herebethetitle.exceptions.NotImplementedYetException;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.logic.GameListener;
import com.swen.herebethetitle.logic.GameLogic;
import com.swen.herebethetitle.logic.exceptions.InvalidDestination;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the main, top-level class for the conceptual controller.
 * This will handle initializing all the other classes on start().
 * @author J Woods
 *
 */

/*
 * TODO
 * 
 * -Add in subordinate menu functionality for load game & settings
 * -audio functionality
 * 
 * Immediately:
 * -input event handling implementation
 * 
 */
public class Controller extends Application implements GameListener{
	//constants
	public static final int DEFAULT_WIDTH = 1000;
	public static final int DEFAULT_HEIGHT = 650;
	public static final int FRAMES_PER_SECOND = 60;
	
	
	//window field
	private Stage window;
	
	//main menu fields
	private Scene mainMenu;
	private BorderPane mainMenuLayout;
	private GridPane settingsMenu;
	private GridPane newGameMenu;
	private GridPane loadGameMenu;
	private GridPane quitMenu;
	
	//main game UI fields
	private Scene worldGraphics;
	private Timeline updateTimeline;
	private Group gameGUIRoot;
	private GameCanvas gameCanvas;
	
	//Game fields
	private GameContext game;
	private GameLogic logic;
	private boolean isPlaying;
	
	//Testing mode field
	public static boolean isTesting;

	
	/**
	 * Default constructor, needed for use with the Main class.
	 */
	public Controller() {
		super();
	}
	

	@Override
	public void start(Stage s) throws Exception {
		/*initialize the stage*/
		window = s;
		window.setTitle("2D RPG");
		window.setResizable(false);
		
		/*initialize the main menu*/
		mainMenuLayout = initMainMenu();
		mainMenu = new Scene(mainMenuLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		window.setScene(mainMenu);
		/*initialize subordinate menus*/
		settingsMenu = initSettingsMenu();
		newGameMenu = initNewGameMenu();
		loadGameMenu = initLoadGameMenu();
		quitMenu = initQuitMenu();
		
		/*show the stage*/
		window.show();
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
			/*initialize the game*/
			initializeNewGame();
			worldGraphics = initGameGUI();
			window.setScene(worldGraphics);
			/*set the timer to regularly update*/
			updateTimeline = new Timeline(new KeyFrame(
			        Duration.millis(6000.0/FRAMES_PER_SECOND),
			        ae -> update()));
			updateTimeline.setCycleCount(Animation.INDEFINITE);
			if(!isTesting)updateTimeline.play();
			gameGUIRoot.requestFocus();
			isPlaying = true;
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
	 * @return A scene holding the GameCanvas and root with event handling.
	 */
	private Scene initGameGUI() {
		gameGUIRoot = new Group();
		/*set up event handling*/
		gameGUIRoot.setOnKeyPressed(e->handleKeyPress(e));
		gameGUIRoot.setOnMousePressed(e->handleMousePress(e));
		gameCanvas = new GameCanvas(game, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		gameGUIRoot.getChildren().add(gameCanvas);
		//create new scene
		Scene s = new Scene(gameGUIRoot);
		return s;
	}
	
	/**
	 * Pauses the game.
	 */
	private void pauseGame() {
		updateTimeline.pause();
		//grey out the canvas
		gameCanvas.getGraphicsContext2D().setFill(new Color(0.5,0.5,0.5,0.5));
		gameCanvas.getGraphicsContext2D().fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		isPlaying = false;
		
		/*TODO draw the pause menu*/
	}
	/**
	 * Unpauses the game.
	 */
	private void unpauseGame() {
		updateTimeline.play();
		isPlaying = true;
	}
	
	
	/**
	 * Handles a key press.
	 * @param e the key event
	 */
	private void handleKeyPress(KeyEvent e) {
		//TODO remove test code; implement final handling
		System.out.println("Key pressed: " + e.getText());
		System.out.println("Key pressed: " + e.getCode());
		
		if(e.getCode()==KeyCode.ESCAPE) {
			System.out.println("Escape pressed");	//debug code
			if(isPlaying) {
				pauseGame();
			}else {
				unpauseGame();
			}
		}
		
		/*If paused, don't handle events*/
		if(!isPlaying) {
			return;
		}
		
		/*movement - may want to implement pathfinding mouse-based movement instead*/
		if(e.getCode()==KeyCode.W) {
			//move north
			try {
				logic.movePlayer(Direction.Up);
			} catch (InvalidDestination e1) {
				e1.printStackTrace();
			}
		}
		if(e.getCode()==KeyCode.A) {
			//move west
			try {
				logic.movePlayer(Direction.Left);
			} catch (InvalidDestination e1) {
				e1.printStackTrace();
			}
		}
		if(e.getCode()==KeyCode.S) {
			//move south
			try {
				logic.movePlayer(Direction.Down);
			} catch (InvalidDestination e1) {
				e1.printStackTrace();
			}
		}
		if(e.getCode()==KeyCode.D) {
			//move east
			try {
				logic.movePlayer(Direction.Right);
			} catch (InvalidDestination e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Handles a mouse press.
	 * @param e the mouse event
	 */
	private void handleMousePress(MouseEvent e) {
		// TODO remove test code; implement final handling
		System.out.println("Mouse pressed: " + e.getX() + "," + e.getY());
		/*get the cell the player clicked on*/
		GridLocation mouseLocation = gameCanvas.getMousePos((int)e.getX(), (int)e.getY());
		System.out.println("Grid location clicked: " + mouseLocation.x + "," + mouseLocation.y);
	}

	/**
	 * Runs the "main loop" - i.e., first calls GameLogic to update, then
	 * calls the WorldGraphics to update.
	 */
	private void update() {
		/*update game context via logic*/
		logic.tick();
		/*redraw graphics*/
		gameCanvas.update();
	}
	
	
	/**
	 * Creates a new game in the default world.
	 */
	public void initializeNewGame() {
		game = new GameContext();
		logic = new GameLogic(game);
	}


	@Override
	public void onPlayerMoved(Player player) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPlayerAttacked(Player player, NPC attacker) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPlayerKilled(Player player, Optional<NPC> aggressor) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPlayerPickup(Player player, Item item) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPlayerDrop(Player player, Item item) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNPCAttacked(NPC victim) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNPCKilled(NPC npc) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNPCDialogBegin(NPC npc) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNPCDialogMessage(NPC npc, String message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNPCDialogEnd(NPC npc) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDoorUnlocked(Static door) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDoorUnlockFailed(Static door, String message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDoorOpened(Static door) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onDoorClosed(Static door) {
		// TODO Auto-generated method stub
		
	}
}
