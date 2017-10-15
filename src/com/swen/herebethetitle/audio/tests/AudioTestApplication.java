package com.swen.herebethetitle.audio.tests;

import com.swen.herebethetitle.audio.AudioManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class AudioTestApplication extends Application{

	public static AudioManager audio;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Audio testing");
		audio = new AudioManager();
		primaryStage.show();
	}
	
	/**
	 * Tests if the audiomanager can play multiple sounds.
	 * @return
	 */
	public static boolean testMulitpleSoundsPlaying() {
		audio.playSound(AudioManager.SOUNDCODE_DEMON1);
		audio.playSound(AudioManager.SOUNDCODE_DEMON2);
		audio.playSound(AudioManager.SOUNDCODE_ROBOT1);
		audio.playSound(AudioManager.SOUNDCODE_ROBOT2);
		//check they are playing
		if(!audio.isPlaying(AudioManager.SOUNDCODE_DEMON1)) {
			return false;
		}
		if(!audio.isPlaying(AudioManager.SOUNDCODE_DEMON2)) {
			return false;
		}
		if(!audio.isPlaying(AudioManager.SOUNDCODE_ROBOT1)) {
			return false;
		}
		if(!audio.isPlaying(AudioManager.SOUNDCODE_ROBOT2)) {
			return false;
		}
		return true;
	}
	
	public static boolean testOneSongPlaying() {
		audio.setSong(AudioManager.SOUNDCODE_MENUSONG);
		audio.setSong(AudioManager.SOUNDCODE_TOWNSONG);
		if(audio.isPlaying(AudioManager.SOUNDCODE_MENUSONG)) {
			return false;
		}
		if(audio.isPlaying(AudioManager.SOUNDCODE_TOWNSONG)) {
			return true;
		}
		return false;
	}
}
