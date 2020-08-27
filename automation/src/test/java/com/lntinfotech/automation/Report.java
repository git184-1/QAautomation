package com.lti.report;

import org.apache.log4j.Logger;

public class Report {
	
	final static Logger logger = Logger.getLogger(com.lti.report.Report.class);
	
	
	public void print(String operation, String value, String valueType, String data, String status){
		if(operation != null){
			logger.debug("Operation  : "+operation);
		}
		
		if(value != null){
			logger.debug("Value      : "+value);
		}
		
		if(valueType != null){
			logger.debug("Value Type : "+valueType);
		}
		
		if(data != null){
			logger.debug("Data	     : "+data);
		}
		
		if(status != null){
			logger.debug("Status	 : "+status);
		}
		System.out.println();
		
		
	}	
}
