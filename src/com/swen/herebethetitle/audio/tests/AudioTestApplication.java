package com.swen.herebethetitle.audio.tests;

import com.swen.herebethetitle.audio.AudioManager;
import com.swen.herebethetitle.entity.Item;
import com.swen.herebethetitle.entity.Key;
import javafx.application.Application;
import javafx.stage.Stage;

public class AudioTestApplication extends Application{

	public static AudioManager audio;
	
	/**
	 * Tests if the audiomanager can play multiple sounds.
	 * @return
	 */
	public static boolean testMulitpleSoundsPlaying() {
		audio.playSound(AudioManager.SOUNDCODE_DEMON1);
		audio.playSound(AudioManager.SOUNDCODE_DEMON2);
		audio.playSound(AudioManager.SOUNDCODE_ROBOT1);
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

		audio.playSound(AudioManager.SOUNDCODE_DEMON1);
		audio.playSound(AudioManager.SOUNDCODE_DEMON2);
		audio.playSound(AudioManager.SOUNDCODE_ROBOT1);
		audio.playSound(AudioManager.SOUNDCODE_FOOTSTEP1);
		//check they are playing
		if(audio.isPlaying(AudioManager.SOUNDCODE_DEMON1)) {
			return false;
		}
		if(!audio.isPlaying(AudioManager.SOUNDCODE_DEMON2)) {
			return false;
		}
		if(!audio.isPlaying(AudioManager.SOUNDCODE_ROBOT1)) {
			return false;
		}
		return audio.isPlaying(AudioManager.SOUNDCODE_FOOTSTEP1);
	}
	
	/**
	 * Tests that one song and only one song can be playing at any given time.
	 * @return
	 */
	public static boolean testOneSongPlaying() {
		audio.setSong(AudioManager.SOUNDCODE_MENUSONG);
		if(audio.isPlaying(AudioManager.SOUNDCODE_MENUSONG)) {
			return true;
		}
		audio.setSong(AudioManager.SOUNDCODE_TOWNSONG);
		if(audio.isPlaying(AudioManager.SOUNDCODE_MENUSONG)) {
			return false;
		}
		return audio.isPlaying(AudioManager.SOUNDCODE_TOWNSONG);
	}
	
	/**
	 * Tests that game listener methods for the audiomanager play sounds properly.
	 * @return
	 */
	public static boolean testGameListenerHandling() {
		/*game completed*/
	    audio.onGameCompleted();
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_GAMEEND)) {
	    	System.out.println("Game listener fail: onGameCompleted");
	    	return false;
	    }
	    /*player moved*/
	    audio.onPlayerMoved(null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_FOOTSTEP1) && !audio.isPlaying(AudioManager.SOUNDCODE_FOOTSTEP2)) {
	    	System.out.println("Game listener fail: onPlayerMove");
	    	return false;
	    }
	    /*player attacked*/
	    audio.onPlayerAttacked(null, null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_PLAYERDAMAGE1) && !audio.isPlaying(AudioManager.SOUNDCODE_PLAYERDAMAGE2)) {
	    	System.out.println("Game listener fail: onPlayerAttacked");
	    	return false;
	    }
	    /*player killed*/
	    audio.onPlayerKilled(null, null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_PLAYERDAMAGE2)) {
	    	System.out.println("Game listener fail: onPlayerKilled");
	    	return false;
	    }
	    /*player picked up nonkey item*/
	    audio.onPlayerPickup(null, new DummyItem("dummy", "null"));
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_ITEMPICKUP)) {
	    	System.out.println("Game listener fail: onPlayerPickup (non-key)");
	    	return false;
	    }
	    /*player picked up key*/
	    audio.onPlayerPickup(null, new Key("dummy", "dummy", 0));
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_KEYPICKUP)) {
	    	System.out.println("Game listener fail: onPlayerPickup (key)");
	    	return false;
	    }
	    /*player dropped item*/
	    audio.onPlayerDrop(null, new DummyItem("dummy", "null"));
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_ITEMDROPPED)) {
	    	System.out.println("Game listener fail: onPlayerDrop");
	    	return false;
	    }
	    /*player attacks npc*/
	    audio.onNPCAttacked(null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_DEMON1)) {
	    	System.out.println("Game listener fail: onNPCAttacked");
	    	return false;
	    }
	    /*player kills npc*/
	    audio.onNPCKilled(null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_DEMON2)) {
	    	System.out.println("Game listener fail: onNPCKilled");
	    	return false;
	    }
	    /*dialog begin*/
		audio.onNPCDialogBegin(null);
		if(!audio.isPlaying(AudioManager.SOUNDCODE_PLAYERDAMAGE1)) {
	    	System.out.println("Game listener fail: onNPCDialogBegin");
	    	return false;
	    }
	    /*dialog message*/
	    audio.onNPCDialogMessage(null, null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_PLAYERDAMAGE1)) {
	    	System.out.println("Game listener fail: onNPCDialogMessage");
	    	return false;
	    }
	    /*dialog ends*/
	    audio.onNPCDialogEnd(null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_PLAYERDAMAGE2)) {
	    	System.out.println("Game listener fail: NPCDialogEnd");
	    	return false;
	    }
	    /*door unlocked*/
	    audio.onDoorUnlocked(null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_UNLOCK)) {
	    	System.out.println("Game listener fail: onDoorUnlocked");
	    	return false;
	    }
	    /*door unlock failure*/
	    audio.onDoorUnlockFailed(null, null);
		if (!audio.isPlaying(AudioManager.SOUNDCODE_ERROR)) {
			System.out.println("Game listener fail: onDoorUnlockFailed");
	    	return false;
	    }
	    /*door opened*/
	    audio.onDoorOpened(null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_DOOR)) {
	    	System.out.println("Game listener fail: onDoorOpened");
	    	return false;
	    }
	    /*door closed*/
	    audio.onDoorClosed(null);
	    if(!audio.isPlaying(AudioManager.SOUNDCODE_DOOR)) {
	    	System.out.println("Game listener fail: onDoorClosed");
	    	return false;
	    }
	    return true;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Audio testing");
		audio = new AudioManager();
		primaryStage.show();
	}
	
	static class DummyItem extends Item {
		
		public DummyItem(String name, String spritePath) {
			//do nothing
			super("dummy", "hopethisworks");
		}
		
	}
}
