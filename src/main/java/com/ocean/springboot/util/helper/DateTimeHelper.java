package com.ocean.springboot.util.helper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeHelper 
{
	private static final Logger logger = LoggerFactory.getLogger(DateTimeHelper.class);
	
	public static DateTime parseDateTime(String dateTimeString, String formatPattern)
	{
		logger.info("dateTimeString: ["+dateTimeString+"] | formatPattern: ["+formatPattern+"]");
		if(dateTimeString.contains("T"))
		{
			dateTimeString = dateTimeString.replace("T", " ");
			if(dateTimeString.contains("."));
			{
				dateTimeString = dateTimeString.substring(0, dateTimeString.indexOf("."));
			}
		}
		DateTimeFormatter dtf = DateTimeFormat.forPattern(formatPattern);
		DateTime dateTime = dtf.withOffsetParsed().parseDateTime(dateTimeString);
		return dateTime;
	}
	
	public static String formatDateTime(DateTime dateTime, String formatPattern)
	{
		logger.info("dateTime: ["+dateTime+"] | formatPattern: ["+formatPattern+"]");
		DateTimeFormatter dtf = DateTimeFormat.forPattern(formatPattern);
		return dtf.print(dateTime);
	}
}
