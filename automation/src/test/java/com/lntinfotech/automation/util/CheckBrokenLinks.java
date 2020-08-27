package com.lntinfotech.automation.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBrokenLinks implements Keyword {

	@Override
	public boolean execute(HashMap<String, Object> params) {
		if(params.containsKey("driver")){
			WebDriver driver = (WebDriver) params.get("driver");
			return checkBrokenLinks(driver,driver.getCurrentUrl());
		}
		return false;
	}

	private boolean checkBrokenLinks(WebDriver driver, String baseurl) {
		String url;
		HttpURLConnection huc = null;
        int respCode = 200;
        
		List<WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
		while(it.hasNext()){
            url = it.next().getAttribute("href");
            System.out.println("URL : "+url);
            if(url == null || url.isEmpty()){
            	System.out.println("Response : URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            if(!url.startsWith(baseurl)){
                System.out.println("Response : URL belongs to another domain, skipping it.");
                continue;
            }
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                if(respCode >= 400){
                	System.out.println("Response : url is a broken link");
                }
                else{
                	System.out.println("Response : url is a valid link");
                }
                    
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
		}
		return true;
	}

}
