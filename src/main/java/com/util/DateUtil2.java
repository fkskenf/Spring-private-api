package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil2 {

	public static String getDateAgo (int daysAgo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1 * daysAgo);

		Date date = calendar.getTime();
		String prevDay = dateFormat.format(date);
		return prevDay;
	}

	public static String getDateAgo (String dateStr, int daysAgo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		try 
		{
			calendar.setTime(dateFormat.parse(dateStr));
		} 
		catch (ParseException e) 
		{
			return "";
		}
		calendar.add(Calendar.DATE, -1 * daysAgo);

		Date date = calendar.getTime();
		String prevDay = dateFormat.format(date);
		return prevDay;
	}

	public static long getDateDiff (String startDateStr, String endDateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		try 
		{
			start.setTime(dateFormat.parse(startDateStr));
			end.setTime(dateFormat.parse(endDateStr));
		} 
		catch (Exception e) 
		{
			return 0;
		}
		Date startDate = start.getTime();
		Date endDate = end.getTime();

		long startTime = startDate.getTime();
		long endTime = endDate.getTime();

		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);

		return diffDays;
	}

	public static int isHighDateCheck (String standardDateStr, String targetDateStr) {
		int result = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date standardDate = null;
		Date targetDate = null;
		try 
		{
			standardDate = dateFormat.parse(standardDateStr);
			targetDate = dateFormat.parse(targetDateStr);
		} 
		catch (ParseException e) 
		{
			return result;
		}
		// standardDateStr > targetDateStr : 1 / standardDateStr = targetDateStr : 0 / standardDateStr < targetDateStr : -1 
		result = standardDate.compareTo(targetDate); 
		return result;
	}
	
	public static String secondsToDateTime (long seconds) {
		int day = (int) TimeUnit.SECONDS.toDays(seconds);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, day);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = dateFormat.format(calendar.getTime());
		return dateTime;
	}
}
