package com.swen.herebethetitle.control.tests;

import org.junit.Test;

import com.swen.herebethetitle.control.Controller;

public class ControlTests {

	@Test
	public void testInputs(){
		Controller.isTesting=1;
		Controller.launch(Controller.class);
	}
}
