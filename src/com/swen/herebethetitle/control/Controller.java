package com.swen.herebethetitle.control;

import com.swen.herebethetitle.audio.AudioManager;
import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.logic.GameListener;
import com.swen.herebethetitle.logic.GameLogic;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.logic.ai.Interaction;
import com.swen.herebethetitle.logic.ai.PlayerMove;
import com.swen.herebethetitle.logic.exceptions.InvalidDestination;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.parser.MapParser;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is the main, top-level class for the conceptual controller.
 * This will handle initializing all the other classes on start().
 * @author J Woods
 *
 */

/*
 * TODO
 *
 * -audio functionality
 *
 * Immediately:
 * -input events for interactions and attacking
 * -Add in subordinate menu functionality for load game & settings
 * -player pathfinding
 *
 */
public class Controller extends Application{
	//constants
	public static final int DEFAULT_WIDTH = 1000;
	public static final int DEFAULT_HEIGHT = 650;
	public static final int FRAMES_PER_SECOND = 30;
	public static final int TESTCODE_INPUTS = 1;
	//Testing mode field
	public static int isTesting;
	//window field
	private Stage window;
	//main menu fields
	private Scene mainMenu;
	private BorderPane mainMenuLayout;
	private GridPane settingsMenu;
	//main game UI fields
	private Scene worldGraphics;
	private Timeline updateTimeline;
	private Group gameGUIRoot;
	private GameCanvas gameCanvas;
	//Game fields
	private GameContext game;
	private GameLogic logic;
	private boolean isPlaying;
	private Optional<PlayerMove> playerMove;
	//AudioManager
	private AudioManager audio;
	private boolean settingsOpen = false;


	/**
	 * Default constructor, needed for use with the Main class.
	 */
	public Controller() {
		super();
	}

	@Override
	public void start(Stage s) throws Exception {
		/*if testing, launch in testing mode*/
		if(isTesting>0) {
			window = s;
			window.setResizable(false);
			window.setWidth(DEFAULT_WIDTH);
			window.setHeight(DEFAULT_HEIGHT);
			/*input testing*/
			if(isTesting == TESTCODE_INPUTS) {
				window.setTitle("2D RPG ***INPUT TESTING MODE***");
				gameGUIRoot = new Group();
				Scene testScene = new Scene(gameGUIRoot);
				window.setScene(testScene);
				window.show();

				Canvas testCanv = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				gameGUIRoot.getChildren().add(testCanv);
				testCanv.getGraphicsContext2D().setFill(Color.BLUE.darker());
				testCanv.getGraphicsContext2D().fillRect(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);

				gameGUIRoot.setOnKeyPressed(e->{
					testCanv.getGraphicsContext2D().setFill(Color.BLUE.darker());
					testCanv.getGraphicsContext2D().fillRect(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
					testCanv.getGraphicsContext2D().setFill(Color.GREEN.brighter());
					testCanv.getGraphicsContext2D().fillText("Key pressed code: " + e.getCode(), DEFAULT_WIDTH/2-50, DEFAULT_HEIGHT/2-50);
				});
				gameGUIRoot.setOnMousePressed(e->{
					testCanv.getGraphicsContext2D().setFill(Color.BLUE.darker());
					testCanv.getGraphicsContext2D().fillRect(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
					testCanv.getGraphicsContext2D().setFill(Color.GREEN.brighter());
					testCanv.getGraphicsContext2D().fillText("Mouse pressed at: " + e.getX() + "," + e.getY(), DEFAULT_WIDTH/2-50, DEFAULT_HEIGHT/2-50);
				});
				gameGUIRoot.requestFocus();
				return;
			}
		}


		/*initialize the stage*/
		window = s;
		window.setTitle("Here Be The Title");
		window.setResizable(false);

		/*initialize the main menu*/
		initMainMenu();
		mainMenu = new Scene(mainMenuLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		mainMenu.getStylesheets().add("file:res/mainmenu.css");
		window.setScene(mainMenu);

		/*initialize the audio manager*/
		audio = new AudioManager();

		/*show the stage*/
		window.show();
	}

	/**
	 * Initializes the main menu.
	 */
	private void initMainMenu() {
		/*initialize layout*/
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(60));

		/*add buttons*/
		HBox buttons = new HBox(10);
		buttons.setId("button-box");
		//quit
		Button quit = new Button("Quit");
		quit.setId("quit");
		quit.setOnAction(e -> System.exit(0));
		quit.setPrefSize(100, 20);
		//new game
		Button newGame = new Button("New Game");
		newGame.setId("newGame");
		newGame.setOnAction(e ->
				loadGame(new File("res/new_game.txt"))
		);
		newGame.setPrefSize(100, 20);
		//load game
		Button loadGame = new Button("Load Game");
		loadGame.setId("loadGame");
		loadGame.setOnAction(e ->
				chooseFile().ifPresent(file ->
						loadGame(file)
				));
		loadGame.setPrefSize(100, 20);
		//settings
		Button settings = new Button("Settings");
		settings.setId("settings");
		settings.setOnAction(e -> {
			if (!settingsOpen) initSettingsMenu(layout);
			else initMainMenu();
		});
		settings.setPrefSize(100, 20);
		//add all the buttons
		buttons.getChildren().addAll(newGame,loadGame,settings,quit);
		layout.setCenter(buttons);

		mainMenuLayout = layout;
	}

	/**
	 * Initializes the settings menu.
	 * @return the settings menu
	 */
	private void initSettingsMenu(BorderPane layout) {
		HBox settingsBox = new HBox();

		Button toggleBorder = new Button("Turn Off Tile Borders");
		toggleBorder.setId("turnOffTileBorders");
		toggleBorder.setOnAction(e -> {
			if (toggleBorder.getText().equals("Turn Off Tile Borders")) {
				toggleBorder.setText("Turn On Tile Borders");
				toggleBorder.setId("turnOnTileBorders");
			} else {
				toggleBorder.setText("Turn Off Tile Borders");
				toggleBorder.setId("turnOffTileBorders");
			}
			GameCanvas.toggleGrid();
		});

		settingsBox.getChildren().add(toggleBorder);

		VBox menuAndSettings = new VBox();
		menuAndSettings.setId("menuBox");
		menuAndSettings.getChildren().add(layout.getChildren().get(0));
		settingsBox.setId("settingsBox");
		menuAndSettings.getChildren().add(settingsBox);

		layout.setCenter(menuAndSettings);

		settingsOpen = true;
	}

	private Optional<File> chooseFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load a map file");
		fileChooser.setInitialDirectory(new File("res/"));
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		File mapFile = fileChooser.showOpenDialog(window);
		if (mapFile == null) {
			System.out.println("File failure: map file null");
			return Optional.empty();
		}
		return Optional.of(mapFile);
	}

	/**
	 * Initializes the game loading menu.
	 *
	 * @return the load game menu
	 */
	private void loadGame(File file) {
		//initialize game
		initializeNewGame();

		//parse map
		Region r;
		try {
			r = new MapParser(file).getRegion();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}

		//TODO Needs to be replaced by something better.
		game = new GameContext(r);
		Player p = game.getPlayer();
		//System.out.println(p.toString());
		System.out.println(game.currentRegion.getLocation(p));

		//play the game
		worldGraphics = initGameGUI();
		window.setScene(worldGraphics);
		unpauseGame();
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
		
		//set the audio manager to play the default town song
		audio.setSong(AudioManager.SOUNDCODE_TOWNSONG);
		
		//add the game canvas to the logic's listeners
		logic.addGameListener(gameCanvas);
		
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
		if(updateTimeline==null) {
			updateTimeline = new Timeline(new KeyFrame(
					Duration.millis(6000.0 / FRAMES_PER_SECOND),
					ae -> update()));
			updateTimeline.setCycleCount(Animation.INDEFINITE);
			gameGUIRoot.requestFocus();
			isPlaying = true;
		}
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
			if (isPlaying) pauseGame();
			else unpauseGame();
		}

		/*If paused, don't handle events*/
		if (!isPlaying) return;

		/*movement - may want to implement pathfinding mouse-based movement instead*/
		try {
			switch (e.getCode()) {
				case W:
					logic.movePlayer(Direction.Up);
				case A:
					logic.movePlayer(Direction.Left);
				case S:
					logic.movePlayer(Direction.Down);
				case D:
					logic.movePlayer(Direction.Right);
			}
		} catch (InvalidDestination er) {
			er.printStackTrace();
		}
	}

	/**
	 * Handles a mouse press.
	 * @param e the mouse event
	 */
	private void handleMousePress(MouseEvent e) {
		System.out.println("Mouse pressed: " + e.getX() + "," + e.getY());
		if (e.isSecondaryButtonDown())
			handleMousePressSecondary(e);
		else
			handleMousePressPrimary(e);
	}

	/**
	 * Handles a mouse press.
	 * @param e the mouse event
	 */
	private void handleMousePressSecondary(MouseEvent e) {
		// TODO remove test code; implement final handling
		System.out.println("Secondary mouse press");
		/*get the cell the player clicked on*/
		GridLocation mouseLocation = gameCanvas.getMousePos((int)e.getX(), (int)e.getY());
		System.out.println("Grid location clicked: " + mouseLocation.x + "," + mouseLocation.y);

		/*build the new interaction for the player movement*/
		try {
			if (game.getCurrentRegion().get(mouseLocation).isPenetrable()) {
				Tile playerDestination = game.getCurrentRegion().get(mouseLocation);
			    this.playerMove = Optional.of(new PlayerMove(game.getPlayer(), playerDestination));
			}
			//TODO this
		}catch(IllegalArgumentException exc) {
			//do nothing, means we've clicked somewhere we shouldn't have
			return;
		}
	}

	private void handleMousePressPrimary(MouseEvent e) {
		System.out.println("Primary mouse press");

		Tile tile = game.getCurrentRegion().get(gameCanvas.getMousePos((int) e.getX(), (int) e.getY()));

		Entity entity = tile.getTopEntity();
		entity.interact(game);
		System.out.println("Entity clicked: " + entity.toString());
	}

	/**
	 * Runs the "main loop" - i.e., first calls GameLogic to update, then
	 * calls the WorldGraphics to update.
	 */
	private void update() {
		/*update game context via logic*/
		logic.tick();
		
		try {
		    if (this.playerMove.isPresent())
		        this.playerMove.get().tick(game.getCurrentRegion(), new Notifier());
		} catch (Interaction.InteractionOver e) {
		    this.playerMove = Optional.empty();
		}

		/*redraw graphics*/
		gameCanvas.update();
	}


	/**
	 * Creates a new game in the default world.
	 */
	public void initializeNewGame() {
		game = new GameContext();
		logic = new GameLogic(game);
		playerMove = Optional.empty();
		logic.addGameListener(audio);
	}
}