package com.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	  public static String trim(String str) {
		    int idx = 0;
		    char[] val = str.toCharArray();
		    int count = val.length;
		    int len = count;
		    for (; idx < len && val[idx] <= ' '; idx++);
		    for (; idx < len && val[len - 1] <= ' '; len--);
		    return (idx > 0 || len < count) ? str.substring(idx, len) : str;
		  }
		  
		  public static String fixNull(String str) {
		    if (str == null)
		      return ""; 
		    if (str.toLowerCase().equals("null"))
		      return ""; 
		    if (str.toLowerCase().equals("undefined"))
		      return ""; 
		    if (str.trim().length() == 0)
		      return ""; 
		    return str;
		  }
		  
		  public static String fixNull(Object obj) {
		    if (obj == null)
		      return ""; 
		    if (obj.toString().toLowerCase().equals("null"))
		      return ""; 
		    if (obj.toString().toLowerCase().equals("undefined"))
		      return ""; 
		    if (obj.toString().trim().length() == 0)
		      return ""; 
		    return obj.toString();
		  }
		  
		  public static String fixNull(Object obj, String str) {
		    if (obj == null)
		      return str; 
		    if (obj.toString().toLowerCase().equals("null"))
		      return str; 
		    if (obj.toString().toLowerCase().equals("undefined"))
		      return str; 
		    if (obj.toString().trim().length() == 0)
		      return str; 
		    return obj.toString();
		  }
		  
		  public static String lPad(String src, String pad, int len) {
		    return pad(src, pad, len, -1);
		  }
		  
		  public static String rPad(String src, String pad, int len) {
		    return pad(src, pad, len, 1);
		  }
		  
		  private static String pad(String src, String pad, int totLen, int mode) {
		    String paddedString = "";
		    if (src == null)
		      return ""; 
		    int srcLen = src.length();
		    if (totLen < 1 || srcLen >= totLen)
		      return src; 
		    for (int i = 0; i < totLen - srcLen; i++)
		      paddedString = paddedString + pad; 
		    if (mode == -1) {
		      paddedString = paddedString + src;
		    } else {
		      paddedString = src + paddedString;
		    } 
		    return paddedString;
		  }
		  
		  public static String lAppend(String src, String pad, int rep) {
		    return append(src, pad, rep, -1);
		  }
		  
		  public static String rAppend(String src, String pad, int rep) {
		    return append(src, pad, rep, 1);
		  }
		  
		  private static String append(String src, String pad, int rep, int mode) {
		    StringBuffer sb = new StringBuffer();
		    sb.append(src);
		    if (src == null)
		      return ""; 
		    if (-1 == mode) {
		      for (int i = 0; i < rep; i++)
		        sb.insert(0, pad); 
		    } else {
		      for (int i = 0; i < rep; i++)
		        sb.append(pad); 
		    } 
		    return sb.toString();
		  }
		  
		  public static String[] split(String source, String delimiter) {
		    ArrayList<String> result = new ArrayList();
		    int begIdx = 0;
		    while (true) {
		      int endIdx = source.indexOf(delimiter, begIdx);
		      if (0 > endIdx)
		        break; 
		      result.add(source.substring(begIdx, endIdx));
		      begIdx = endIdx + delimiter.length();
		    } 
		    result.add(source.substring(begIdx));
		    String[] resultS = new String[result.size()];
		    for (int i = 0; i < result.size(); i++)
		      resultS[i] = result.get(i); 
		    return resultS;
		  }
		  
		  public static String compose(String[] arrWords, String delimiter) {
		    String composedString = "";
		    for (int i = 0; i < arrWords.length; i++) {
		      if (!arrWords[i].equals("")) {
		        if (!composedString.equals(""))
		          composedString = composedString + delimiter; 
		        composedString = composedString + arrWords[i];
		      } 
		    } 
		    return composedString;
		  }
		  
		  public static boolean companyCodeCheck(String val) {
		    Pattern pattern = Pattern.compile("(^(?=.*[0-9])(?=.*[a-zA-Z]))(^[A-Za-z0-9]{8,20}$)");
		    Matcher matcher = pattern.matcher(val);
		    return matcher.find();
		  }
		  
		  public static String revertXSS(String value) {
		    value = value.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		    return value;
		  }
		  
		  public static boolean isStringDouble(String s) {
		    try {
		      Double.parseDouble(s);
		      return true;
		    } catch (NumberFormatException e) {
		      return false;
		    } 
		  }
		  
		  public static boolean isStringLong(String s) {
		    try {
		      Long.parseLong(s);
		      return true;
		    } catch (NumberFormatException e) {
		      return false;
		    } 
		  }
		  
		  public static String StringCommaArray(List<HashMap<String, Object>> participantsUser, String string) {
		    ArrayList<String> resultList = new ArrayList<String>();
		    String rtnStr = "";
		    if (participantsUser == null)
		      return rtnStr; 
		    if (participantsUser.size() > 0) {
		      int i;
		      for (i = 0; i < participantsUser.size(); i++) {
		        if (!resultList.contains(fixNull(((HashMap)participantsUser.get(i)).get(string))))
		          resultList.add(fixNull(((HashMap)participantsUser.get(i)).get(string))); 
		      } 
		      for (i = 0; i < resultList.size(); i++) {
		        if (i > 0)
		          rtnStr = rtnStr + ","; 
		        rtnStr = rtnStr + fixNull(resultList.get(i));
		      } 
		    } 
		    return rtnStr;
		  }
		  
		  public static String substrByByteLength(String str, int byteLength) {
		    int length = str.length();
		    int retLength = 0;
		    int tempSize = 0;
		    for (int i = 1; i <= length; i++) {
		      int asc = str.charAt(i - 1);
		      if (asc > 127) {
		        if (byteLength > tempSize + 1) {
		          tempSize += 2;
		          retLength++;
		        } 
		      } else if (byteLength > tempSize) {
		        tempSize++;
		        retLength++;
		      } 
		    } 
		    str = str.substring(0, retLength);
		    return str;
		  }
		  
		  public static String getStringByByteSizeCut(String s, int n) {
		    byte[] utf8 = s.getBytes();
		    if (utf8.length < n)
		      n = utf8.length; 
		    int n16 = 0;
		    int advance = 1;
		    int i = 0;
		    while (i < n) {
		      advance = 1;
		      if ((utf8[i] & 0x80) == 0) {
		        i++;
		      } else if ((utf8[i] & 0xE0) == 192) {
		        i += 2;
		      } else if ((utf8[i] & 0xF0) == 224) {
		        i += 3;
		      } else {
		        i += 4;
		        advance = 2;
		      } 
		      if (i <= n)
		        n16 += advance; 
		    } 
		    return s.substring(0, n16);
		  }
		  
		  public static String[] getStringArray(String str) {
		    String tempStr = fixNull(str).replaceAll(",,", ",").replace(" ", "");
		    if (str.isEmpty())
		      return null; 
		    if (tempStr.lastIndexOf(",") == tempStr.length() - 1)
		      tempStr = tempStr.substring(0, tempStr.length() - 1); 
		    if (tempStr.indexOf(",") == 0)
		      tempStr = tempStr.substring(1, tempStr.length()); 
		    String[] tempArr = tempStr.split(",");
		    if (tempArr.length == 1 && tempArr[0].isEmpty())
		      return null; 
		    return tempArr;
		  }
		  
		  public static String[] getStringNumberArray(String str) {
		    String tempStr = fixNull(str).replaceAll(",,", ",").replace(" ", "");
		    if (str.isEmpty())
		      return null; 
		    Pattern SpecialPattern = Pattern.compile("(^[0-9,]+$)");
		    Matcher SpecialMatcher = SpecialPattern.matcher(tempStr);
		    if (!SpecialMatcher.find())
		      return null; 
		    if (tempStr.lastIndexOf(",") == tempStr.length() - 1)
		      tempStr = tempStr.substring(0, tempStr.length() - 1); 
		    if (tempStr.indexOf(",") == 0)
		      tempStr = tempStr.substring(1, tempStr.length()); 
		    String[] tempArr = tempStr.split(",");
		    if (tempArr.length == 1 && tempArr[0].isEmpty())
		      return null; 
		    return tempArr;
		  }
		  
		  public static String replaceAll(String value, String str, String replaceStr) {
		    value = value.replaceAll(Matcher.quoteReplacement(str), Matcher.quoteReplacement(replaceStr));
		    return value;
		  }
		  
		  
		  public static void main(String[] args) throws ParseException {
		    System.out.println(replaceAll("#aaaa#", "#aaaa#", "!@#$%%^^&^*^&*가다라나라붜ㅔㄹ"));
		  }}
