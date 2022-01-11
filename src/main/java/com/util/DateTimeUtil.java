package com.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateTimeUtil {
	  public static Timestamp getTimeStamp() {
		    return new Timestamp(System.currentTimeMillis());
		  }
		  
		  public static Timestamp getTimeStamp(String dtm) {
		    dtm = ReplaceUtil.replace(dtm, "-", "");
		    dtm = ReplaceUtil.replace(dtm, ":", "");
		    dtm = ReplaceUtil.replace(dtm, ".", "");
		    dtm = ReplaceUtil.replace(dtm, " ", "");
		    return getTimeStampCustom(dtm.substring(0, 4), dtm.substring(4, 6), dtm.substring(6, 8), dtm.substring(8, 10), dtm.substring(10, 12), dtm.substring(12, 14), dtm.substring(14));
		  }
		  
		  public static Timestamp getTimeStampCustom(String year, String month, String day, String hour, String min, String sec, String ms) {
		    Calendar cal = Calendar.getInstance();
		    cal.set(1, Integer.parseInt(year));
		    cal.set(2, Integer.parseInt(month) - 1);
		    cal.set(5, Integer.parseInt(day));
		    cal.set(11, Integer.parseInt(hour));
		    cal.set(12, Integer.parseInt(min));
		    cal.set(13, Integer.parseInt(sec));
		    cal.set(14, Integer.parseInt(ms));
		    return new Timestamp(cal.getTimeInMillis());
		  }
		  
		  public static Timestamp getTimeStamp(String year, String month, String day, String hour, String min, String sec, String ms) {
		    Calendar cal = Calendar.getInstance();
		    cal.set(1, Integer.parseInt(year));
		    cal.set(2, Integer.parseInt(month) - 1);
		    cal.set(5, Integer.parseInt(day) - 1);
		    cal.set(11, Integer.parseInt(hour));
		    cal.set(12, Integer.parseInt(min));
		    cal.set(13, Integer.parseInt(sec));
		    cal.set(14, Integer.parseInt(ms));
		    return new Timestamp(cal.getTimeInMillis());
		  }
		  
		  public static String getDateTime(String format) {
		    String dateTime = null;
		    if (format != null) {
		      SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		      Date currentTime = new Date();
		      dateTime = formatter.format(currentTime);
		    } 
		    return dateTime;
		  }
		  
		  public static Date getDateTimeToDate(String format) {
		    String strDateTime = null;
		    Date dateTime = null;
		    if (format != null) {
		      SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		      Date currentTime = new Date();
		      strDateTime = formatter.format(currentTime);
		      try {
		        dateTime = formatter.parse(strDateTime);
		      } catch (ParseException e) {
		        e.printStackTrace();
		      } 
		    } 
		    return dateTime;
		  }
		  
		  public static String getDate() {
		    Calendar cal = Calendar.getInstance();
		    String date = String.valueOf(cal.get(1));
		    date = date + StringUtil.lPad(String.valueOf(cal.get(2) + 1), "0", 2);
		    date = date + StringUtil.lPad(String.valueOf(cal.get(5)), "0", 2);
		    return date;
		  }
		  
		  public static String getFirstDate() {
		    Calendar cal = Calendar.getInstance();
		    String date = String.valueOf(cal.get(1));
		    date = date + StringUtil.lPad(String.valueOf(cal.get(2) + 1), "0", 2);
		    date = date + StringUtil.lPad(String.valueOf(cal.getActualMinimum(5)), "0", 2);
		    return date;
		  }
		  
		  public static String getLastDate() {
		    Calendar cal = Calendar.getInstance();
		    String date = String.valueOf(cal.get(1));
		    date = date + StringUtil.lPad(String.valueOf(cal.get(2) + 1), "0", 2);
		    date = date + StringUtil.lPad(String.valueOf(cal.getActualMaximum(5)), "0", 2);
		    return date;
		  }
		  
		  public static String getDateOfWeek(int day) {
		    Calendar cal = Calendar.getInstance();
		    cal.set(3, getWeek());
		    cal.set(7, day);
		    String date = String.valueOf(cal.get(1));
		    date = date + StringUtil.lPad(String.valueOf(cal.get(2) + 1), "0", 2);
		    date = date + StringUtil.lPad(String.valueOf(cal.get(5)), "0", 2);
		    return date;
		  }
		  
		  public static String getDateOfWeek(int day, int week) {
		    Calendar cal = Calendar.getInstance();
		    cal.set(3, week);
		    cal.set(7, day);
		    String date = String.valueOf(cal.get(1));
		    date = date + StringUtil.lPad(String.valueOf(cal.get(2) + 1), "0", 2);
		    date = date + StringUtil.lPad(String.valueOf(cal.get(5)), "0", 2);
		    return date;
		  }
		  
		  public static String getDateOfWeek(int day, int week, int year) {
		    Calendar cal = Calendar.getInstance();
		    cal.set(1, year);
		    cal.set(3, week);
		    cal.set(7, day);
		    String date = String.valueOf(cal.get(1));
		    date = date + StringUtil.lPad(String.valueOf(cal.get(2) + 1), "0", 2);
		    date = date + StringUtil.lPad(String.valueOf(cal.get(5)), "0", 2);
		    return date;
		  }
		  
		  public static List getDateOfPeriod(int day, String frDate, String toDate) {
		    int frWeek = parseWeek(frDate);
		    int toWeek = parseWeek(toDate);
		    List<String> list = new ArrayList();
		    if (frWeek <= toWeek) {
		      int year = Integer.parseInt(frDate.substring(0, 4));
		      for (int i = frWeek; i <= toWeek; i++) {
		        String date = getDateOfWeek(day, i, year);
		        if (Integer.parseInt(date) >= Integer.parseInt(frDate) && Integer.parseInt(date) <= Integer.parseInt(toDate))
		          list.add(date); 
		      } 
		    } else {
		      int year = Integer.parseInt(frDate.substring(0, 4));
		      toWeek = parseWeek(frDate.substring(0, 4) + "1231");
		      int i;
		      for (i = frWeek; i <= toWeek; i++) {
		        String date = getDateOfWeek(day, i, year);
		        if (Integer.parseInt(date) >= Integer.parseInt(frDate) && Integer.parseInt(date) <= Integer.parseInt(toDate))
		          list.add(date); 
		      } 
		      year = Integer.parseInt(toDate.substring(0, 4));
		      frWeek = parseWeek(frDate.substring(0, 4) + "0101");
		      toWeek = parseWeek(toDate);
		      for (i = frWeek; i <= toWeek; i++) {
		        String date = getDateOfWeek(day, i, year);
		        if (Integer.parseInt(date) >= Integer.parseInt(frDate) && Integer.parseInt(date) <= Integer.parseInt(toDate))
		          list.add(date); 
		      } 
		    } 
		    return list;
		  }
		  
		  public static List getDateOfPeriod(Integer[] day, String frDate, String toDate) {
		    int frWeek = parseWeek(frDate);
		    int toWeek = parseWeek(toDate);
		    List<String> list = new ArrayList();
		    if (frWeek <= toWeek) {
		      int year = Integer.parseInt(frDate.substring(0, 4));
		      for (int i = frWeek; i <= toWeek; i++) {
		        for (int j = 0; j < day.length; j++) {
		          if (null != day[j]) {
		            String date = getDateOfWeek(day[j].intValue(), i, year);
		            if (Integer.parseInt(date) >= Integer.parseInt(frDate) && Integer.parseInt(date) <= Integer.parseInt(toDate))
		              list.add(date); 
		          } 
		        } 
		      } 
		    } else {
		      int year = Integer.parseInt(frDate.substring(0, 4));
		      toWeek = parseWeek(frDate.substring(0, 4) + "1231");
		      int i;
		      for (i = frWeek; i <= toWeek; i++) {
		        for (int j = 0; j < day.length; j++) {
		          if (null != day[j]) {
		            String date = getDateOfWeek(day[j].intValue(), i, year);
		            if (Integer.parseInt(date) >= Integer.parseInt(frDate) && Integer.parseInt(date) <= Integer.parseInt(toDate))
		              list.add(date); 
		          } 
		        } 
		      } 
		      year = Integer.parseInt(toDate.substring(0, 4));
		      frWeek = parseWeek(frDate.substring(0, 4) + "0101");
		      toWeek = parseWeek(toDate);
		      for (i = frWeek; i <= toWeek; i++) {
		        for (int j = 0; j < day.length; j++) {
		          if (null != day[j]) {
		            String date = getDateOfWeek(day[j].intValue(), i, year);
		            if (Integer.parseInt(date) >= Integer.parseInt(frDate) && Integer.parseInt(date) <= Integer.parseInt(toDate))
		              list.add(date); 
		          } 
		        } 
		      } 
		    } 
		    return list;
		  }
		  
		  public static List getDateOfPeriod(String[] day, String frDate, String toDate) {
		    Integer[] days = new Integer[day.length];
		    for (int i = 0; i < day.length; i++) {
		      if (!"".equals(day[i]))
		        days[i] = new Integer(day[i]); 
		    } 
		    return getDateOfPeriod(days, frDate, toDate);
		  }
		  
		  public static int getDay() {
		    Calendar cal = Calendar.getInstance();
		    return cal.get(7);
		  }
		  
		  public static int getDay(String date) {
		    if (null == date || "".equals(date))
		      return getDay(); 
		    Calendar cal = Calendar.getInstance();
		    cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
		    return cal.get(7);
		  }
		  
		  public static int getWeek() {
		    Calendar cal = Calendar.getInstance();
		    return cal.get(3);
		  }
		  
		  public static String getMonth() {
		    Calendar cal = Calendar.getInstance();
		    String date = String.valueOf(cal.get(1));
		    date = date + StringUtil.lPad(String.valueOf(cal.get(2) + 1), "0", 2);
		    return date;
		  }
		  
		  public static String getFirstMonth() {
		    Calendar cal = Calendar.getInstance();
		    String date = String.valueOf(cal.get(1));
		    date = date + "01";
		    return date;
		  }
		  
		  public static String getLastMonth() {
		    Calendar cal = Calendar.getInstance();
		    String date = String.valueOf(cal.get(1));
		    date = date + "12";
		    return date;
		  }
		  
		  public static String addDay(String date, int increment) {
		    Calendar cal = Calendar.getInstance();
		    cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
		    cal.add(5, increment);
		    return FormatUtil.date(cal.getTime(), "yyyyMMdd");
		  }
		  
		  public static String addDay(Date date, int increment, String format) {
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    cal.add(5, increment);
		    return FormatUtil.date(cal.getTime(), format);
		  }
		  
		  public static String addWeek(String date, int increment) {
		    return addDay(date, increment * 7);
		  }
		  
		  public static String addMonth(String month, int increment) {
		    Calendar cal = Calendar.getInstance();
		    if (6 < month.length()) {
		      cal.set(Integer.parseInt(month.substring(0, 4)), Integer.parseInt(month.substring(4, 6)) - 1, Integer.parseInt(month.substring(6, 8)));
		    } else {
		      cal.set(Integer.parseInt(month.substring(0, 4)), Integer.parseInt(month.substring(4, 6)) - 1, 1);
		    } 
		    cal.add(2, increment);
		    if (6 < month.length())
		      return FormatUtil.date(cal.getTime(), "yyyyMMdd"); 
		    return FormatUtil.date(cal.getTime(), "yyyyMM");
		  }
		  
		  public static int parseWeek(String date) {
		    Calendar cal = Calendar.getInstance();
		    cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
		    return cal.get(3);
		  }
		  
		  public static long diffDate(String begin, String end) throws Exception {
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		    Date beginDate = formatter.parse(begin);
		    Date endDate = formatter.parse(end);
		    long diff = endDate.getTime() - beginDate.getTime();
		    long diffDays = diff / 86400000L;
		    return diffDays;
		  }
		  
		  public static int gapDate(String startDay, String endDay) {
		    Calendar cal1 = Calendar.getInstance();
		    Calendar cal2 = Calendar.getInstance();
		    startDay = startDay.replaceAll("-", "");
		    endDay = endDay.replaceAll("-", "");
		    if (startDay.equals("") || startDay.length() != 8 || endDay.equals("") || endDay.length() != 8)
		      return 0; 
		    cal1.set(Integer.parseInt(startDay.substring(0, 4)), Integer.parseInt(startDay.substring(4, 6)), Integer.parseInt(startDay.substring(6, 8)));
		    cal2.set(Integer.parseInt(endDay.substring(0, 4)), Integer.parseInt(endDay.substring(4, 6)), Integer.parseInt(endDay.substring(6, 8)));
		    int sDay = cal1.get(6);
		    int eDay = cal2.get(6);
		    int sYear = cal1.get(1);
		    int eYear = cal2.get(1);
		    if (sYear == eYear)
		      return eDay - sDay; 
		    if (sYear < eYear)
		      return eDay + (eYear - sYear) * 365 - sDay; 
		    if (sYear > eYear)
		      return eDay - sDay + (sYear - eYear) * 365; 
		    return 0;
		  }
		  
		  public static boolean isValidDate(String date, String format) {
		    SimpleDateFormat dateFormatParser = new SimpleDateFormat(format);
		    try {
		      dateFormatParser.parse(date);
		    } catch (Exception Ex) {
		      return false;
		    } 
		    return true;
		  }
		  
		  public static String getTimestampDisplay(Timestamp timestamp, String format) {
		    String date = "";
		    SimpleDateFormat convertFormat = new SimpleDateFormat(format);
		    try {
		      date = convertFormat.format(timestamp);
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return date;
		  }
		  
		  public static String getTimestampDisplay(Date timestamp, String format) {
		    String date = "";
		    SimpleDateFormat convertFormat = new SimpleDateFormat(format);
		    try {
		      date = convertFormat.format(timestamp);
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return date;
		  }
		  
		  public static String getDateTimeDisplay(Date datetime, String format) {
		    String date = "";
		    SimpleDateFormat convertFormat = new SimpleDateFormat(format);
		    try {
		      date = convertFormat.format(datetime);
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return date;
		  }
		  
		  public static Date getStringtoDate(String strDate, String format) {
		    Date date = new Date();
		    SimpleDateFormat convertFormat = new SimpleDateFormat(format);
		    try {
		      date = convertFormat.parse(strDate);
		    } catch (Exception e) {
		      date = null;
		      e.printStackTrace();
		    } 
		    return date;
		  }
		  
		  public static Date getStringtoDate(String strDate) {
		    Date date = null;
		    String format = "yyyyMMddHHmmss";
		    SimpleDateFormat convertFormat = null;
		    strDate = strDate.replaceAll(" ", "").replaceAll("/", "").replaceAll("-", "").replaceAll("\\.", "").replaceAll(":", "");
		    try {
		      if (strDate.length() == 8) {
		        format = "yyyyMMdd";
		      } else if (strDate.length() == 12) {
		        format = "yyyyMMddHHmm";
		      } else if (strDate.length() == 14) {
		        format = "yyyyMMddHHmmss";
		      } 
		      convertFormat = new SimpleDateFormat(format);
		      date = convertFormat.parse(strDate);
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return date;
		  }
		  
		  public static long getDiffDate(String reqDateStr, String format) throws ParseException {
		    Date curDate = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		    Date reqDate = dateFormat.parse(reqDateStr);
		    long reqDateTime = reqDate.getTime();
		    curDate = dateFormat.parse(dateFormat.format(curDate));
		    long curDateTime = curDate.getTime();
		    long minute = (curDateTime - reqDateTime) / 60000L;
		    return minute;
		  }
		  
		  public static void main(String[] args) throws ParseException {
		    System.out.println(getStringtoDate("11", "yyyy-MM-dd"));
		  }
}
