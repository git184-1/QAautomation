package com.lntinfotech.automation.stepdefs;

import com.lti.util.Action;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC_login {

	Action action = new Action("tc_login", 10);
	

	@Given("^Plato is open in chrome browser$")
	public void plato_is_open_in_chrome_browser() throws Exception {
		

		action.perform(Action.OpenURL, "url");

		action.perform(Action.CheckPagetitle, "title");

	}

	@When("^username and password is given$")
	public void username_and_password_is_given() throws Exception {

		action.perform(Action.Click, "defaultLogin");
		
		action.perform(Action.CaptureScreenshot);

		action.perform(Action.SendKeys, "usernameElement", "username");

		action.perform(Action.SendKeys, "passwordElement", "password");

		action.perform(Action.CaptureScreenshot);

		action.perform(Action.Click, "loginButton");

	}

	@Then("^user should see dashboard$")
	public void user_should_see_dashboard() throws Exception {
		
		String title = (String) action.get("GetPageTitle");
		
		System.out.println("Website title : " + title);

		action.perform(Action.SaveInDB, "platoDashboardTitle", title);
		
		String valueFromOutparam = (String) action.get(Action.FromOutparam, "platoDashboardTitle");

		System.out.println("Value fetched from DB for 'platoDashboardTitle' is : "+valueFromOutparam);		
				
//		action.perform(Action.CheckBrokenLinks);

		action.perform(Action.CloseBrowser);

	}
	
}
