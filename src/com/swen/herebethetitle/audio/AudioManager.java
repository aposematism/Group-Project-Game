package com.swen.herebethetitle.audio;

import com.swen.herebethetitle.entity.Item;
import com.swen.herebethetitle.entity.Key;
import com.swen.herebethetitle.entity.NPC;
import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.entity.Static;
import com.swen.herebethetitle.logic.GameListener;
import javafx.scene.media.AudioClip;

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
	public static final int SOUNDCODE_MENUSONG = 0;
	public static final int SOUNDCODE_TOWNSONG = -1;
	public static final int SOUNDCODE_BATTLESONG = -1;	//TODO get battle music
	/*fields for audioclips*/
	private AudioClip song;
	private Map<Integer, AudioClip> sounds;
	/*fields for volumes*/
	public static double masterVol = 1.0f;
	public static double musicVol = 1.0f;
	public static double sfxVol = 1.0f;
	

	/**
	 * Constructs a new AudioManager with the song set to the main 
	 * menu music and all sounds loaded.
	 */
	public AudioManager() {
		sounds = new HashMap<Integer, AudioClip>();
		
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
		
		sounds.put(SOUNDCODE_MENUSONG, new AudioClip("file:res/sound/forest_adventure.wav"));
		sounds.put(SOUNDCODE_TOWNSONG, new AudioClip("file:res/sound/elfish_docks.wav"));
		sounds.put(SOUNDCODE_BATTLESONG, new AudioClip("file:res/sound/elfish_docks.wav"));
		
		
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
		sounds.get(s).setCycleCount(1);
		sounds.get(s).play();
	}

	
	/*volume setting methods*/
	
	/**
	 * Sets the master volume and readjusts all audioclip volumes accordingly.
	 * @param v
	 */
	public void setMasterVol(double v) {
		masterVol = v;
		adjustVols();
	}	
	
	/**
	 * Sets the sfx volume and readjusts all audioclip volumes accordingly.
	 * @param v
	 */
	public void setSfxVol(double v) {
		sfxVol = v;
		adjustVols();
	}	
	
	/**
	 * Sets the music volume and readjusts all audioclip volumes accordingly.
	 * @param v
	 */	
	public void setMusicVol(double v) {
		musicVol = v;
		adjustVols();
	}
	
	/**
	 * Adjusts the volumes for all the AudioClips appropriately according
	 * to master, music, and sfx volumes.
	 */
	private void adjustVols() {		
		for(AudioClip c: sounds.values()) {
			c.setVolume(masterVol*sfxVol);
		}
		sounds.get(SOUNDCODE_MENUSONG).setVolume(masterVol*musicVol);
		sounds.get(SOUNDCODE_TOWNSONG).setVolume(masterVol*musicVol);
		sounds.get(SOUNDCODE_BATTLESONG).setVolume(masterVol*musicVol);
	}
	
	
	/*GameListener methods*/
	
	@Override
	public void onGameCompleted() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		playSound(SOUNDCODE_PLAYERDAMAGE1);
	}

	@Override
	public void onNPCDialogMessage(NPC npc, String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNPCDialogEnd(NPC npc) {
		playSound(SOUNDCODE_PLAYERDAMAGE2);
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
		playSound(SOUNDCODE_DOOR);	
	}

	@Override
	public void onDoorClosed(Static door) {
		playSound(SOUNDCODE_DOOR);
	}
}
