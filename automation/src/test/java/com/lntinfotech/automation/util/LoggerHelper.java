package com.lntinfotech.automation.util;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;




public class LoggerHelper {
	
	private static boolean root=false;
	public static String logPath;
	public synchronized static void createLogFolder() {
		String userDir= System.getProperty("user.dir");
		String folderName= CalenderUtils.getCalenderUtilObjects().getTimeStamp("ddMMyyyy_HHmmss");
		logPath= userDir + "/logs/" + folderName;
		new File(LoggerHelper.logPath).mkdir();
		
	}
	
	public synchronized Logger getLogger(Class cls) {
		if(root) {
			return Logger.getLogger(cls);
		}
		LoggerHelper.createLogFolder();
		PropertiesConfiguration config;
		try {
			config= new PropertiesConfiguration("log4j.properties");
			config.setProperty("logFolder", LoggerHelper.logPath);
			config.save();
		}catch(ConfigurationException e) {
			e.printStackTrace();
			Assert.fail();
		}
		PropertyConfigurator.configure("log4j.propeties");
		return null;
		
	}
	

}
