package com.sridhar.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;


public class DateUtils {
	
	
	public static Date getDate(int daysFromToday) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(getServerDate());
		c.add(Calendar.DATE, daysFromToday);
		return c.getTime();
	}
	
	public static Date getServerDate() {
		
	    Date today = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        //df.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
        	date =  formatter.parse(df.format(today));
        } catch(ParseException e) {
        	Assert.fail(e.getMessage());
        }
        
        return date;
		
	}
	
}
