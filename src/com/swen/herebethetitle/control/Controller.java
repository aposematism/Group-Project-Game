package com.swen.herebethetitle.control;

import com.swen.herebethetitle.audio.AudioManager;
import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.entity.Item;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.graphics.GameCanvas;
import com.swen.herebethetitle.logic.GameLogic;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.logic.ai.Interaction;
import com.swen.herebethetitle.logic.ai.PlayerMove;
import com.swen.herebethetitle.logic.exceptions.EntityOutOfRange;
import com.swen.herebethetitle.logic.exceptions.InvalidDestination;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.parser.MapParser;
import com.swen.herebethetitle.parser.ReverseParser;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.util.GridLocation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * This is the main, top-level class for the conceptual controller.
 * This will handle initializing all the other classes on start().
 * @author J Woods
 *
 */

/*
 * TODO
 *
 * Immediately:
 * -input events for interactions and attacking
 * -Add in subordinate menu functionality for load game & settings
 * -pause menu
 *
 */
public class Controller extends Application{
	//constants
	public static final int DEFAULT_WIDTH = 1000;
	public static final int DEFAULT_HEIGHT = 650;
	public static final int FRAMES_PER_SECOND = 45;
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
	private	GameCanvas gameCanvas;
	private BorderPane pauseMenuLayout;
	private Scene pauseMenu;
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
				chooseLoadFile().ifPresent(file ->
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

		/*toggle grid button*/
		Button toggleBorder = new Button("Turn On Tile Borders");
		toggleBorder.setId("turnOnTileBorders");
		toggleBorder.setOnAction(e -> {
			if (toggleBorder.getText().equals("Turn On Tile Borders")) {
				toggleBorder.setText("Turn Off Tile Borders");
				toggleBorder.setId("turnOffTileBorders");
			} else {
				toggleBorder.setText("Turn On Tile Borders");
				toggleBorder.setId("turnOnTileBorders");
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

	private Optional<File> chooseLoadFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load your game");
		fileChooser.setInitialDirectory(new File("."));
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

	private Optional<File> chooseSaveFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save your game");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		File mapFile = fileChooser.showSaveDialog(window);
		if (mapFile == null) {
			System.out.println("File failure");
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

		//parse map
		Region r;
		try {
			r = new MapParser(file).getRegion();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		initializeNewGame(r);
		//TODO Needs to be replaced by something better.
		Player p = game.getPlayer();
		//System.out.println(p.toString());
		//System.out.println(game.currentRegion.getLocation(p));

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
		gameGUIRoot.setOnMouseMoved(e -> handleMouseMove(e));
		gameCanvas = new GameCanvas(game, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		gameGUIRoot.getChildren().add(gameCanvas);
		//create new scene
		Scene s = new Scene(gameGUIRoot);
		
		//set the audio manager to play the default town song
		audio.setSong(AudioManager.SOUNDCODE_TOWNSONG);
		
		//add the game canvas & audio manager to the logic's listeners
		logic.addGameListener(gameCanvas);

		initPauseMenu();

		return s;
	}

	private void initPauseMenu() {
		/*initialize the pause menu*/
		pauseMenuLayout = new BorderPane();
		pauseMenu = new Scene(pauseMenuLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);

		HBox titleBox = new HBox();
		titleBox.setAlignment(Pos.CENTER);
		Text titleText = new Text();
		titleText.setFont(new Font(50.0));
		titleText.setText("Paused");
		titleBox.getChildren().add(new Text("Paused"));

		VBox optionBox = new VBox();
		optionBox.setAlignment(Pos.CENTER);

		pauseMenuLayout.setTop(titleBox);
		pauseMenuLayout.setCenter(optionBox);

		Button saveGame = new Button();
		saveGame.setPrefSize(100, 20);
		saveGame.setText("Save");
		saveGame.setOnAction(e ->
				chooseSaveFile().ifPresent(file ->
						new ReverseParser(game.getCurrentRegion(), file)
				)
		);
		optionBox.getChildren().add(saveGame);

		Button unpause = new Button();
		unpause.setPrefSize(100, 20);
		unpause.setText("Resume");
		unpause.setOnAction(e->{
			unpauseGame();
		});
		optionBox.getChildren().add(unpause);
		
		Button quit = new Button();
		quit.setPrefSize(100, 20);
		quit.setText("Quit");
		quit.setOnAction(e->{
			System.exit(0);
		});
		optionBox.getChildren().add(quit);
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
		window.setScene(pauseMenu);
	}
	/**
	 * Unpauses the game.
	 */
	private void unpauseGame() {
		if(updateTimeline ==null) {

			updateTimeline = new Timeline(new KeyFrame(
					Duration.millis(6000.0 / FRAMES_PER_SECOND),
					ae -> update()));

			updateTimeline.setCycleCount(Animation.INDEFINITE);
			gameGUIRoot.requestFocus();
			isPlaying = true;
		}
		window.setScene(worldGraphics);
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
					break;
				case A:
					logic.movePlayer(Direction.Left);
					break;
				case S:
					logic.movePlayer(Direction.Down);
					break;
				case D:
					logic.movePlayer(Direction.Right);
					break;
				case LEFT:
					logic.movePlayer(Direction.Left);
					break;
				case UP:
					logic.movePlayer(Direction.Up);
					break;
				case RIGHT:
					logic.movePlayer(Direction.Right);
					break;
				case DOWN:
					logic.movePlayer(Direction.Down);
					break;
				case KP_LEFT:
					logic.movePlayer(Direction.Left);
					break;
				case KP_UP:
					logic.movePlayer(Direction.Up);
					break;
				case KP_RIGHT:
					logic.movePlayer(Direction.Right);
					break;
				case KP_DOWN:
					logic.movePlayer(Direction.Down);
					break;
					
					
					/*inventory interaction cases*/
				case DIGIT1:
					useItemInPlayerInventory(0);
					break;
				case DIGIT2:
					useItemInPlayerInventory(1);
					break;
				case DIGIT3:
					useItemInPlayerInventory(2);
					break;
				case DIGIT4:
					useItemInPlayerInventory(3);
					break;
				case DIGIT5:
					useItemInPlayerInventory(4);
					break;
				case DIGIT6:
					useItemInPlayerInventory(5);
					break;
				case DIGIT7:
					useItemInPlayerInventory(6);
					break;
				case DIGIT8:
					useItemInPlayerInventory(7);
					break;
				case DIGIT9:
					useItemInPlayerInventory(8);
					break;
				case DIGIT0:
					useItemInPlayerInventory(9);
					break;
			}
		} catch (InvalidDestination er) {
			er.printStackTrace();
		}
	}

	/**
	 * Handles a mouse move.
	 * @param e the mouse event
	 */
	private void handleMouseMove(MouseEvent e){
		gameCanvas.getMousePos((int)e.getX()+15, (int)e.getY()+20);
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
		System.out.println("Entity clicked: " + entity.toString());
		try {
			logic.interact(entity);
			System.out.println("Interacted");
		} catch (EntityOutOfRange ex) {
			System.out.println("Can't interact");
		}
	}
	
	/**
	 * Attempts to interact with an item in the player's inventory at a given slot.
	 * If it doesn't exist, does nothing.
	 * @param i
	 */
	private void useItemInPlayerInventory(int i) {
		try {
			Item item = game.getPlayer().inventory().getItems().get(i);
			if(item==null) {
				return;
			}
			item.interact(game);
		}catch(IndexOutOfBoundsException e) {
			//do nothing, means we've selected an inventory
			//slot with no item in it
		}
	}

	/**
	 * Runs the "main loop" - i.e., first calls GameLogic to update, then
	 * calls the WorldGraphics to update.
	 */
	private void update() {
		/*check victory and loss conditions*/
		if(logic.GameWon()) {
			pauseGame();
			/*build victory scene*/
			BorderPane victory = new BorderPane();
			victory.setPadding(new Insets(50));
			Scene victoryScene = new Scene(victory, DEFAULT_WIDTH, DEFAULT_HEIGHT);
			HBox top = new HBox();
			HBox center = new HBox();
			HBox bottom = new HBox();
			top.setAlignment(Pos.CENTER);
			center.setAlignment(Pos.CENTER);
			bottom.setAlignment(Pos.CENTER);
			victory.setTop(top);
			victory.setBottom(bottom);
			victory.setCenter(center);
			
			Text congratText = new Text("Congratulations!");
			congratText.setFont(new Font(50.0));
			top.getChildren().add(congratText);
			
			Text youWonText = new Text("You have gained a title, and become a noble Knight!");
			youWonText.setFont(new Font(20.0));
			center.getChildren().add(youWonText);
			
			window.setScene(victoryScene);
			return;
		}
		if(logic.gameLost()) {
			pauseGame();
			/*build loss scene*/
			BorderPane loss = new BorderPane();
			loss.setPadding(new Insets(50));
			Scene lossScene = new Scene(loss, DEFAULT_WIDTH, DEFAULT_HEIGHT);
			HBox top = new HBox();
			VBox center = new VBox();
			HBox bottom = new HBox();
			top.setAlignment(Pos.CENTER);
			center.setAlignment(Pos.CENTER);
			bottom.setAlignment(Pos.CENTER);
			loss.setTop(top);
			loss.setBottom(bottom);
			loss.setCenter(center);
			
			Text congratText = new Text("Oh dear!");
			congratText.setFont(new Font(50.0));
			top.getChildren().add(congratText);
			
			Image spooky = new Image("file:res/mob/skeleton.png");
			ImageView spookyView = new ImageView(spooky);
			center.getChildren().add(spookyView);
			
			Text youWonText = new Text("You have died. Reload a previous save or begin your adventure again.");
			youWonText.setFont(new Font(20.0));
			center.getChildren().add(youWonText);
			
			window.setScene(lossScene);
			return;
		}
		
		/*update game context via logic*/
		logic.tick();
		
		try {
		    if (this.playerMove.isPresent()) {
		    	//doing some awful stuff to get the notifier to work without digging into logic
		    	Notifier n = new Notifier();
		    	n.addListener(audio);
		    	n.addListener(gameCanvas);
		        this.playerMove.get().tick(game.getCurrentRegion(), n);
		    }
		} catch (Interaction.InteractionOver e) {
		    this.playerMove = Optional.empty();
		}

		/*redraw graphics*/
		gameCanvas.update();
	}


	/**
	 * Creates a new game in the default world.
	 */
	public void initializeNewGame(Region r) {
		game = new GameContext(r);
		logic = new GameLogic(game);
		playerMove = Optional.empty();
		logic.addGameListener(audio);
	}
}