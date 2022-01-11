package com.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReplaceUtil {
	 public static String replace(String source, String target, String replace) {
		    if (source == null)
		      return ""; 
		    int iTargetLen = target.length();
		    StringBuffer sbfReplace = new StringBuffer();
		    int i = 0;
		    int j = 0;
		    while (j > -1) {
		      j = source.indexOf(target, i);
		      if (j > -1) {
		        sbfReplace.append(source.substring(i, j)).append(replace);
		        i = j + iTargetLen;
		      } 
		    } 
		    sbfReplace.append(source.substring(i, source.length()));
		    return sbfReplace.toString();
		  }
		  
		  public static HashMap<String, Object> removeNullforHashMap(HashMap<String, Object> param) {
		    Iterator<Map.Entry<String, Object>> iter = param.entrySet().iterator();
		    while (iter.hasNext()) {
		      Map.Entry<String, Object> entry = iter.next();
		      String str = (entry.getValue() == null) ? "" : StringUtil.fixNull(entry.getValue().toString());
		      if ("null".equals(str) || str == null || str == "" || str.equals(""))
		        iter.remove(); 
		    } 
		    return param;
		  }
}
