package com.lntinfotech.automation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalenderUtils {
	
	private static CalenderUtils calenderUtilObjs;
	
	//private constructor for singleton approach.
	private CalenderUtils() {
		
	}
	
	public synchronized static CalenderUtils getCalenderUtilObjects() {
		return (calenderUtilObjs==null)? new CalenderUtils() : calenderUtilObjs;
		
	}
	 
	public synchronized String getTimeStamp(String dateFormat) {
		Date currentDate= new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		return dateFormatter.format(currentDate);
		
	}
	/*
	 * @return futuredate or previous date based on days(ex : if user gives +2 then it will return 2 days after today
	 */
	public static String getPreviousOrFutureDate(String futureOrPreDate) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
		calendar.add(Calendar.DATE, Integer.parseInt(futureOrPreDate));
		return dateFormatter.format(calendar.getTime());	
		
	}
	
	/*
	 * change the dateDormat based on user input format
	 */
	public static String changeDateFormat(String dateValue, String givenFormat, String reqFormat) {
		
		SimpleDateFormat formatter= new SimpleDateFormat(givenFormat);
		Date date= null;
		try {
			date = formatter.parse(dateValue);
			System.out.println("date:"+ date);
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatter1= new SimpleDateFormat(reqFormat);
		String dateString = formatter1.format(date);
		return dateString;
		
		
	}

}
