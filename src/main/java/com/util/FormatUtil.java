package com.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class FormatUtil {
	 public static String time(Timestamp time, String format) {
		    if (null == time)
		      return ""; 
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(time.getYear() + 1900, time.getMonth(), time.getDate());
		    calendar.set(10, time.getHours());
		    calendar.set(12, time.getMinutes());
		    calendar.set(13, time.getSeconds());
		    SimpleDateFormat df = new SimpleDateFormat(format);
		    return df.format(calendar.getTime());
		  }
		  
		  public static String date(String date, String format) {
		    if (null == date || "".equals(date) || 6 > date.length())
		      return ""; 
		    if (7 > date.length())
		      date = date + "01"; 
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
		    SimpleDateFormat df = new SimpleDateFormat(format);
		    return df.format(calendar.getTime());
		  }
		  
		  public static String date(Object date, String format) {
		    SimpleDateFormat df = new SimpleDateFormat(format);
		    return df.format(date);
		  }
		  
		  public static String dateDB(String date) {
		    return dateDB(date, "yyyyMMdd");
		  }
		  
		  public static String dateDB(String date, String format) {
		    if (null == date || "".equals(date) || 6 > date.length())
		      return ""; 
		    date = ReplaceUtil.replace(date, "-", "");
		    date = ReplaceUtil.replace(date, "/", "");
		    date = ReplaceUtil.replace(date, "\\", "");
		    date = ReplaceUtil.replace(date, ",", "");
		    date = ReplaceUtil.replace(date, ".", "");
		    format = ReplaceUtil.replace(format, "-", "");
		    format = ReplaceUtil.replace(format, "/", "");
		    format = ReplaceUtil.replace(format, "\\", "");
		    format = ReplaceUtil.replace(format, ",", "");
		    format = ReplaceUtil.replace(format, ".", "");
		    int yStart = format.indexOf("y");
		    int yFinish = format.lastIndexOf("y");
		    int mStart = format.indexOf("M");
		    int mFinish = format.lastIndexOf("M");
		    if (0 < format.indexOf("d")) {
		      int dStart = format.indexOf("d");
		      int dFinish = format.lastIndexOf("d");
		      return date.substring(yStart, yFinish + 1) + date.substring(mStart, mFinish + 1) + date.substring(dStart, dFinish + 1);
		    } 
		    return date.substring(yStart, yFinish + 1) + date.substring(mStart, mFinish + 1);
		  }
		  
		  public static String number(String num, String format) {
		    if (null == num || "".equals(num))
		      return ""; 
		    DecimalFormat oDecimalFormat = new DecimalFormat(format);
		    return oDecimalFormat.format(Float.parseFloat(num));
		  }
		  
		  public static String number(Object num, String format) {
		    DecimalFormat oDecimalFormat = new DecimalFormat(format);
		    return oDecimalFormat.format(num);
		  }
		  
		  public static String numberDB(String num) {
		    if (null == num || "".equals(num))
		      return ""; 
		    return ReplaceUtil.replace(num, ",", "");
		  }
}
