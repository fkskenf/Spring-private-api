package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	 @SuppressWarnings("unchecked")
	public static HashMap<String, Object> paramToHashMap(HttpServletRequest request) {
		    HashMap<String, Object> returnVal = new HashMap<String, Object>();
		    Enumeration<String> enums = request.getParameterNames();
		    String paramName = "";
		    if (request.getMethod().equalsIgnoreCase("GET")) {
		      while (enums.hasMoreElements()) {
		        paramName = enums.nextElement();
		        try {
		          returnVal.put(paramName, URLDecoder.decode(StringUtil.fixNull(request.getParameter(paramName)).replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8"));
		        } catch (UnsupportedEncodingException e) {
		          e.printStackTrace();
		          returnVal.put(paramName, StringUtil.fixNull(request.getParameter(paramName)));
		        } catch (Exception e) {
		          System.out.println(e);
		        } 
		      } 
		    } else {
		      while (enums.hasMoreElements()) {
		        paramName = enums.nextElement();
		        returnVal.put(paramName, StringUtil.fixNull(request.getParameter(paramName)));
		      } 
		    } 
		    return returnVal;
		  }

}
