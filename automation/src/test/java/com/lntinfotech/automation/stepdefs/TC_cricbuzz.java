package com.lntinfotech.automation.stepdefs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lti.browser.Browser;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TC_cricbuzz {

	WebDriver driver = new Browser("chrome", "chromedriver_win32.exe").getDriver();
	
	@Given("^cricbuzz live score webpage is open$")
	public void cricbuzz_live_score_webpage_is_open() throws Exception {
		String url = "https://www.cricbuzz.com/cricket-match/live-scores";
		driver.get(url);
	}

	@Then("^check for live scores displayed$")
	public void check_for_live_scores_displayed() throws Exception {
		List<WebElement> matches = driver.findElements(By.cssSelector("#page-wrapper > div:nth-child(6) > div.cb-col-67.cb-col.cb-left.cb-schdl > div > div > div:nth-child(2) > a > div > div:nth-child(1) > div.cb-lv-scrs-col.text-black"));
		for(int i=0; i<10; i++){
			for (WebElement match : matches) {
				System.out.println(match.getText());
			}
			
			System.out.println("++++++++++++++++++++++++");
			Thread.sleep(10000);
			
		}
		
		driver.close();
		
	}

	
}
