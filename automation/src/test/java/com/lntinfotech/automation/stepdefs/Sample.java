package com.lntinfotech.automation.stepdefs;

import java.util.ArrayList;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.lti.util.Action;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class Sample {
	
	Action action = new Action("tc_sample", 10);
	
	@Given("^website is open$")
	public void website_is_open() throws Exception {
		
		action.perform(Action.OpenURL,"frameAndAlertUrl");
		
		//to access value of any key from db
		String key = "onlyButton";
		String value = (String) action.get(key);
		System.out.println("Value of "+key+" is : "+value);
		
		
	}

	@When("^user should be able to switch frame$")
	public void user_should_be_able_to_switch_frame() throws Exception {

		action.perform(Action.SwitchFrameByName, "resultFrame");
		action.perform(Action.Click, "onlyButton");		
		
	}
	

	@Then("^get alert text and accept it$")
	public void get_alert_text_and_accept_it() throws Exception {
		action.perform(Action.ImplicitWait);
		//get access to instance of webdriver for this Test case
		WebDriver driver  = (WebDriver) action.get(Action.Driver);
		
		String aleartText = driver.switchTo().alert().getText();
		System.out.println("Aleart : "+aleartText);
		
		driver.switchTo().alert().accept();
		
	}
	
	@Given("^website is open for right click sample$")
	public void website_is_open_for_right_click_sample() throws Exception {
		action.perform(Action.OpenURL,"rightClickUrl");
	}

	WebElement button;
	WebDriver driver;
	
	@When("^right click button is visible$")
	public void right_click_button_is_visible() throws Exception {
		button = (WebElement) action.get(Action.GetElement,"rightClickButton");
		Assert.assertTrue(button.getText().contains("right click me"));
	}

	@Then("^perform right click and select \"([^\"]*)\"$")
	public void perform_right_click_and_select(String option) throws Exception {
		driver = (WebDriver) action.get("driver");
		Actions action= new Actions(driver);
		action.contextClick(button).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		
	}

	@Given("^Alert box is displayed$")
	public void alert_box_is_displayed() throws Exception {
		driver.switchTo().alert();
	}

	@When("^assert alert text to contain \"([^\"]*)\"$")
	public void assert_alert_text_to_contain(String option) throws Exception {
		String alerttext = driver.switchTo().alert().getText();
		System.out.println("Alert text : "+alerttext);
		Assert.assertTrue(alerttext.contains(option));
	}

	@Then("^accept alert$")
	public void accept_alert() throws Exception {
		
		driver.switchTo().alert().accept();
	}

	
	@Given("^Website is open for radio button sample$")
	public void website_is_open_for_radio_button_sample() throws Exception {
	    action.perform(Action.OpenURL,"radioButtonUrl");
	    action.perform(Action.SwitchFrameByName, "resultFrame");	    
	}

	@Then("^select radio button for \"([^\"]*)\"$")
	public void select_radio_button_for(String name) throws Exception {
		
	    action.perform(Action.ClickRadiobutton, "radioButtonGender");
	}
	
	@Given("^Website is open for scrollTest$")
	public void website_is_open_for_scrollTest() throws Exception {
		action.perform(Action.OpenURL,"scrollVerticalURL");
	}

	@Then("^scroll to the bottom$")
	public void scroll_to_the_bottom() throws Exception {
	    
		action.perform(Action.ScrollByPixel, "300");
	    action.perform(Action.CaptureScreenshot);
	    action.perform(Action.ScrollToBottom);
	    action.perform(Action.ScrollToElement, "scrollHeading");
	    
	}

	@Given("^Website is loaded completely$")
	public void website_is_loaded_completely() throws Exception {
		action.perform(Action.OpenURL, "scrollVerticalURL");
	}

	@Then("^screenshot entire page$")
	public void screenshot_entire_page() throws Exception {
		action.perform(Action.FullPageScreenshot);
	}


	@Given("^Website is open for tabswitch$")
	public void website_is_open_for_tabswitch() throws Exception {
	    
		action.perform(Action.OpenURL,"switchTabURL");
		
	}

	@When("^user click on button and print new tab content$")
	public void user_click_on_button_and_print_new_tab_content() throws Exception {
	    
		action.perform(Action.SwitchFrameByName,"resultFrame");
		
		action.perform(Action.Click,"switchTabButton");
		
	}

	@Then("^switch back to parent tab$")
	public void switch_back_to_parent_tab() throws Exception {
	   
		driver = (WebDriver) action.get(Action.Driver);
		
	}

	

}
