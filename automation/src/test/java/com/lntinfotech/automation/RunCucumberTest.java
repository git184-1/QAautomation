package com.lntinfotech.automation;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.lntinfotech.automation.util.ConfigProperties;
import com.lti.util.ConfigureLibrary;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "html:target/cucumber-html-report", "json:target/cucumber.json",
		"pretty:target/cucumber-pretty.txt", "usage:target/cucumber-usage.json",
		"junit:target/cucumber-results.xml" },
        features = "src/test/java/com/lntinfotech/automation/feature",
        glue = "com/lntinfotech/automation",
        tags = {"not @ignore"})

public class RunCucumberTest {

	final static Logger logger = Logger.getLogger(com.lntinfotech.automation.RunCucumberTest.class);
	
	
	@BeforeClass
	public static void setup(){
		Properties prop =  ConfigProperties.getProperties();
		logger.info("Passing config.properties to library");
		ConfigureLibrary.setProp(prop);
	}
	
	@AfterClass
	public static void destroy(){
		
	}
	
	
}