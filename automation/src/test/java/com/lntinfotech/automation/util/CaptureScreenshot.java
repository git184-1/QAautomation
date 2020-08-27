package com.lntinfotech.automation.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class CaptureScreenshot implements Keyword {

	String filepath = "";
	@Override
	public boolean execute(HashMap<String, Object> params) {
		boolean status = false;
		if (params.containsKey("folderpath")) {
			String path = (String) params.get("folderpath");
			File folder = new File(path);
			if(!folder.exists()){
				folder.mkdir();
			}
			status = captureScreenshot(params.get("TCName"),params.get("folderpath"));
		} else {
			System.out.println("Filepath to store screenshots not defined in config.properties");
		}
		return status;

	}

	private boolean captureScreenshot(Object TCName, Object filepath) {
		String format = "jpg";
		String filename = TCName+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
		
		this.filepath = filepath+"/"+filename; 
		
		try {
			Thread.sleep(2000);
			Robot robot = new Robot();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(filepath+"/"+filename));
            
            return true;
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getFilepath(){
		return this.filepath;
	}

}
