package com.swen.herebethetitle.audio;

import com.swen.herebethetitle.entity.*;
import com.swen.herebethetitle.logic.GameListener;
import javafx.scene.media.AudioClip;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for playing audio through the main javaFX thread.
 * @author J Woods
 *
 */
public class AudioManager implements GameListener{
	/*audio clip codes for playing*/
	public static final int SOUNDCODE_DEMON1 = 1;
	public static final int SOUNDCODE_DEMON2 = 2;
	public static final int SOUNDCODE_DOOR = 3;
	public static final int SOUNDCODE_PLAYERDAMAGE1 = 4;
	public static final int SOUNDCODE_PLAYERDAMAGE2 = 5;
	public static final int SOUNDCODE_ROBOT1 = 6;
	public static final int SOUNDCODE_ROBOT2 = 7;
	public static final int SOUNDCODE_FOOTSTEP1 = 8;
	public static final int SOUNDCODE_FOOTSTEP2 = 9;
	public static final int SOUNDCODE_KEYPICKUP = 10;
	public static final int SOUNDCODE_ITEMPICKUP = 11;
	public static final int SOUNDCODE_GAMEEND = 12;
	public static final int SOUNDCODE_ITEMDROPPED = 15;
	public static final int SOUNDCODE_UNLOCK = 16;
	public static final int SOUNDCODE_ERROR = 17;
	public static final int SOUNDCODE_DIALOG_START = 18;
	public static final int SOUNDCODE_DIALOG_MESSAGE = 19;
	public static final int SOUNDCODE_DIALOG_END = 20;
	public static final int SOUNDCODE_MENUSONG = 0;
	public static final int SOUNDCODE_TOWNSONG = -1;
	public static final int SOUNDCODE_BATTLESONG = -1;	//TODO get battle music
	/*limit for sounds playing*/
	private static final int MAX_SOUNDS = 3;
	/*fields for volumes*/
	public static double masterVol = 1.0f;
	public static double musicVol = 1.0f;
	public static double sfxVol = 1.0f;
	/*fields for audioclips*/
	private AudioClip song;
	private Map<Integer, AudioClip> sounds;
	private ArrayDeque<AudioClip> playingSounds;
	

	/**
	 * Constructs a new AudioManager with the song set to the main 
	 * menu music and all sounds loaded.
	 */
	public AudioManager() {
		sounds = new HashMap<Integer, AudioClip>();
		playingSounds = new ArrayDeque<AudioClip>();
		
		/*load sounds into the audio clip TODO finish*/
		sounds.put(SOUNDCODE_DEMON1, new AudioClip("file:res/sound/demon1.mp3"));
		sounds.put(SOUNDCODE_DEMON2, new AudioClip("file:res/sound/demon2.mp3"));
		sounds.put(SOUNDCODE_DOOR, new AudioClip("file:res/sound/door.mp3"));
		sounds.put(SOUNDCODE_PLAYERDAMAGE1, new AudioClip("file:res/sound/playerdamage1.mp3"));
		sounds.put(SOUNDCODE_PLAYERDAMAGE2, new AudioClip("file:res/sound/playerdamage2.mp3"));
		sounds.put(SOUNDCODE_ROBOT1, new AudioClip("file:res/sound/robot1.mp3"));
		sounds.put(SOUNDCODE_ROBOT2, new AudioClip("file:res/sound/robot2.mp3"));
		sounds.put(SOUNDCODE_FOOTSTEP1, new AudioClip("file:res/sound/footstep1.mp3"));
		sounds.put(SOUNDCODE_FOOTSTEP2, new AudioClip("file:res/sound/footstep2.mp3"));
		sounds.put(SOUNDCODE_KEYPICKUP, new AudioClip("file:res/sound/keypickup.wav"));
		sounds.put(SOUNDCODE_ITEMPICKUP, new AudioClip("file:res/sound/keypickup.wav"));	//TODO source new sound file
		sounds.put(SOUNDCODE_GAMEEND, new AudioClip("file:res/sound/playerdamage1.mp3"));	//TODO source new sound
		sounds.put(SOUNDCODE_ITEMDROPPED, new AudioClip("file:res/sound/keypickup.wav"));	//TODO source new sound
		sounds.put(SOUNDCODE_UNLOCK, new AudioClip("file:res/sound/keypickup.wav"));		//TODO source new sound
		sounds.put(SOUNDCODE_ERROR, new AudioClip("file:res/sound/error.mp3"));
		sounds.put(SOUNDCODE_DIALOG_START, new AudioClip("file:res/sound/dialogstart.mp3"));
		sounds.put(SOUNDCODE_DIALOG_MESSAGE, new AudioClip("file:res/sound/dialogmessage.mp3"));
		sounds.put(SOUNDCODE_DIALOG_END, new AudioClip("file:res/sound/dialogend.mp3"));
		
		sounds.put(SOUNDCODE_MENUSONG, new AudioClip("file:res/sound/forest_adventure.wav"));
		sounds.put(SOUNDCODE_TOWNSONG, new AudioClip("file:res/sound/elfish_docks.wav"));
		sounds.put(SOUNDCODE_BATTLESONG, new AudioClip("file:res/sound/elfish_docks.wav"));	//TODO decide whether to keep or not
		
		
		/*play the main menu music*/
		setSong(SOUNDCODE_MENUSONG);
	}
	
	/**
	 * Sets an audioclip to be the song playing indefinitely in the 
	 * background. Only one song can be playing at a given time.
	 * @param s
	 */
	public void setSong(int s) {
		if(song!=null) {
			song.stop();
		}
		song = sounds.get(s);
		song.setCycleCount(AudioClip.INDEFINITE);
		song.play();
	}
	
	/**
	 * Plays an audioclip once.
	 * @param s
	 */
	public void playSound(int s) {
		if(playingSounds.size()>=MAX_SOUNDS) {
			playingSounds.pop().stop();
		}
		sounds.get(s).setCycleCount(1);
		playingSounds.add(sounds.get(s));
		sounds.get(s).play();
	}

	
	
	/*testing methods*/
	
	/**
	 * Returns whether or not an audioclip associated with a given soundcode is playing.
	 * @param s the soundcode of the audioclip to check is playing or not
	 * @return whether the audioclip is playing
	 */
	public boolean isPlaying(int s) {
		return sounds.get(s).isPlaying();
	}	
	
	/*GameListener methods*/
	
	@Override
	public void onGameCompleted() {
		playSound(AudioManager.SOUNDCODE_GAMEEND);
	}

	@Override
	public void onPlayerMoved(Player player) {
		if (ThreadLocalRandom.current().nextBoolean())
			playSound(AudioManager.SOUNDCODE_FOOTSTEP1);
		else
			playSound(AudioManager.SOUNDCODE_FOOTSTEP2);
	}

	@Override
	public void onPlayerAttacked(Player player, NPC attacker) {
		if (ThreadLocalRandom.current().nextBoolean())
			playSound(AudioManager.SOUNDCODE_PLAYERDAMAGE1);
		else
			playSound(AudioManager.SOUNDCODE_PLAYERDAMAGE2);
	}

	@Override
	public void onPlayerKilled(Player player, Optional<NPC> aggressor) {
		playSound(SOUNDCODE_PLAYERDAMAGE2);
	}

	@Override
	public void onPlayerPickup(Player player, Item item) {
		if(item instanceof Key) {
			playSound(SOUNDCODE_KEYPICKUP);
		}else {
			playSound(SOUNDCODE_ITEMPICKUP);
		}
	}

	@Override
	public void onPlayerDrop(Player player, Item item) {
		// TODO multiple sounds?
		playSound(SOUNDCODE_ITEMDROPPED);
	}

	@Override
	public void onNPCAttacked(NPC victim) {
		//TODO different sounds for different victims
		playSound(SOUNDCODE_DEMON1);		
	}

	@Override
	public void onNPCKilled(NPC npc) {
		// TODO different sounds for different victims
		playSound(SOUNDCODE_DEMON2);		
	}

	@Override
	public void onNPCDialogBegin(NPC npc) {
		playSound(SOUNDCODE_DIALOG_START);
	}

	@Override
	public void onNPCDialogMessage(NPC npc, String message) {
		playSound(SOUNDCODE_DIALOG_MESSAGE);
	}

	@Override
	public void onNPCDialogEnd(NPC npc) {
		playSound(SOUNDCODE_DIALOG_END);
	}

	@Override
	public void onDoorUnlocked(Static door) {
		playSound(SOUNDCODE_DOOR);
	}

	@Override
	public void onDoorUnlockFailed(Static door, String message) {
		playSound(SOUNDCODE_ERROR);
	}

	@Override
	public void onDoorOpened(Static door) {
		playSound(SOUNDCODE_DOOR);	
	}

	@Override
	public void onDoorClosed(Static door) {
		playSound(SOUNDCODE_DOOR);
	}
}
