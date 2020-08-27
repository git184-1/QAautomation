package com.lntinfotech.automation.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;

public class Browser {

	BrowserInterface browser;
	String browserName;
	final static Logger logger = Logger.getLogger(Browser.class);
	
	public Browser(String browserName, String driverName) {
		
		
		this.browserName = browserName;
		logger.info("Closing already running "+driverName);
		close(driverName);
		
		logger.info("Launching New Browser "+browserName+" with driver "+driverName);
		switch (browserName.toLowerCase()) {
			case "chrome"   :  	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/"+driverName);
								browser = new ChromeBrowser();
								break;
								
			case "firefox"  :   browser = new FirefoxBrowser();
								break;
								
			case "ie"		:	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/"+"InternetExplorerDriver.exe");
								browser = new IEBrowser();
			case "safari"	:	
			default			:   System.out.println("Browser "+browserName+" is not supported in this library");;
								break;
		}
	}
	
	public WebDriver getDriver(){
		logger.info("Returning driver instance of "+browserName);
		return browser.getDriver();
	}
	
	public void close(String processName){
		try {
			if (isProcessRunning(processName)) {
				WindowsUtils.killByName(processName);
			}
		} catch (Exception t) {
			System.out.println(("Unable to kill process " + processName));
			logger.error("Unable to kill process " + processName);
		}
	}
	
	public static boolean isProcessRunning(String processName) {
		logger.info("Checking if "+processName+" is running in background.");
		boolean r_value = false;
		try {
			String line;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (line.contains(processName)) {
					r_value = true;
					break;
				}
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
			r_value = false;

		}

		return r_value;
	}
	
}
