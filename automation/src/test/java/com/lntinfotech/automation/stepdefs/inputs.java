package com.lntinfotech.automation.stepdefs;

import com.lti.util.Action;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class inputs {
	
	Action action = new Action("tc_w3", 10);

	@Given("^website is open and switched to frame$")
	public void website_is_open_and_switched_to_frame() throws Exception {

		action.perform(Action.OpenURL, "url");
		action.perform(Action.SwitchFrameByName, "resultFrame");
	}

	@Then("^fill inputs$")
	public void fill_inputs() throws Exception {
	  
		action.perform(Action.SendKeys, "lastName" ,"last");
		
	}

}
