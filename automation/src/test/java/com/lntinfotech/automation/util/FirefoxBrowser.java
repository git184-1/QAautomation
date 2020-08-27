package com.lntinfotech.automation.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class FirefoxBrowser implements BrowserInterface{

	private WebDriver driver;
	
	@Override
	public WebDriver getDriver() {
		ProfilesIni prof = new ProfilesIni();
		FirefoxProfile p = prof.getProfile("Automation");
		driver = new FirefoxDriver(p);
		return driver;		
	}
	
	
	
}	
