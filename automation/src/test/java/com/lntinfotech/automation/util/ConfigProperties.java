package com.lntinfotech.automation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigProperties {
	
	final static Logger logger = Logger.getLogger(com.lntinfotech.automation.util.ConfigProperties.class);
	
	static Properties prop = new Properties();
	static InputStream input = null;
	
	public static Properties getProperties(){
		try {

	        input = new FileInputStream("config.properties");
	        prop.load(input);
	        
	        logger.debug("applying config.properties");

	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } finally {
	        if (input != null) {
	            try {
	                input.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		
		return prop;
	}
	
}
