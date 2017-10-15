package com.swen.herebethetitle.audio.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;

/**
 * Tests for the audio package.
 * @author J Woods
 *
 */
public class AudioTests {

	/**
	 * Tests everything, passed to the audio test app.
	 */
	@Test
	@Ignore
	public void tests() {
		/*create new javaFX testing application*/
		AudioTestApplication.launch(AudioTestApplication.class);
		if(!AudioTestApplication.testMulitpleSoundsPlaying()) {
			fail("Multiple sounds playing at once test failure: an arbitrary number of sounds should be able to be played");
		}
		if(!AudioTestApplication.testOneSongPlaying()) {
			fail("One song playing at once test failure: only one song should be allowed to play at any one time");
		}
	}

}
