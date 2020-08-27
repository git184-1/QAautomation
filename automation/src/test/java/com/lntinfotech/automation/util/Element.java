package com.lntinfotech.automation.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.ByAngular;

public class Element {
	WebDriver driver;
	
	final static Logger logger = Logger.getLogger(Element.class);
	
	
	public Element(WebDriver driver){
		this.driver = driver;
	}

	public WebElement getElement(String by, String path, int timeout){
		
		logger.debug("Returning webelement for '"+path+"' by '"+by+"' with expected condition: 'visibility'");
		
		switch(by.toLowerCase()){
		
			case "id" 				: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.id(path))));
			
			case "name" 			: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.name(path))));
			
			case "classname" 		: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.className(path))));
			
			case "linktext" 		: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(path))));
			
			case "partiallinktext"  : return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.partialLinkText(path))));
			
			case "xpath" 			: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
			
			case "tagname" 			: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName(path))));
			
			case "cssselector"  	: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(path))));
			
			case "ng-model"			: return driver.findElement(ByAngular.model(path));
			
			case "ng-bind"			: return driver.findElement(ByAngular.binding(path));
			
			case "exactbinding"		: return driver.findElement(ByAngular.exactBinding(path));
			
			case "buttontext"		: return driver.findElement(ByAngular.buttonText(path));
			
			case "partialbuttontext": return driver.findElement(ByAngular.partialButtonText(path));
			
			default					: logger.error("Select by : "+by+" is incorrect!");
									  break;
		}
		return null;
	}
	
	public List<WebElement> getElements(String by, String path, int timeout){
		
		logger.debug("Returning webelements for '"+path+"' by '"+by+"' with expected condition: 'visibility'");
		
		switch(by.toLowerCase()){
		
			case "id" 				: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id(path))));
			
			case "name" 			: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.name(path))));
			
			case "classname" 		: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className(path))));
			
			case "linktext" 		: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.linkText(path))));
			
			case "partiallinktext"  : return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.partialLinkText(path))));
			
			case "xpath" 			: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(path))));
			
			case "tagname" 			: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.tagName(path))));
			
			case "cssselector"  	: return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(path))));
			
			case "ng-model"			: return driver.findElements(ByAngular.model(path));
			
			case "ng-bind"			: return driver.findElements(ByAngular.binding(path));
			
			case "exactbinding"		: return driver.findElements(ByAngular.exactBinding(path));
			
			case "buttontext"		: return driver.findElements(ByAngular.buttonText(path));
			
			case "partialbuttontext": return driver.findElements(ByAngular.partialButtonText(path));
			
			case "repeater"			: return driver.findElements(ByAngular.repeater(path));
				
			default					: logger.error("Select by : "+by+" is incorrect!");
									  break;
		}
		return null;
	}
	
}
