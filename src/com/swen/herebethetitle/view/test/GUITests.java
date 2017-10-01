package com.swen.herebethetitle.view.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.swen.herebethetitle.logic.GameLogic;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.view.GUI;

import javafx.application.Platform;

public class GUITests {
	/**
	 * Tests mouse inputs work properly.
	 */
	@Test
	public void test_inputs() {
		/*start GUI in test mode*/		
		GameContext game = new GameContext();
		GameLogic gameLogic = new GameLogic(game);
		GUI.setGame(game);
		GUI.setLogic(gameLogic);
		GUI.isTesting = true;
		GUI.launch(GUI.class);
	}

}
