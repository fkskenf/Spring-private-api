package com.util;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestUtil {
	public static HashMap<String, Object> paramToHashMap(HttpServletRequest request) {
		HashMap<String, Object> returnVal = new HashMap<>();
		Enumeration<String> enums = request.getParameterNames();
		String paramName = "";
		if (request.getMethod().equalsIgnoreCase("GET")) {
			while (enums.hasMoreElements()) {
				paramName = enums.nextElement();
				try {
					returnVal.put(paramName, URLDecoder.decode(StringUtil.fixNull(request.getParameter(paramName))
							.replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8"));
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

	public static HashMap<String, Object> requestToHashMap(HttpServletRequest request) {
		HashMap<String, Object> returnVal = new HashMap<>();
		Enumeration<String> enums = request.getParameterNames();
		String paramName = "";
		if (request.getMethod().equalsIgnoreCase("GET")) {
			while (enums.hasMoreElements()) {
				paramName = enums.nextElement();
				try {
					returnVal.put(paramName, URLDecoder.decode(StringUtil.fixNull(request.getParameter(paramName))
							.replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					returnVal.put(paramName, StringUtil.fixNull(request.getParameter(paramName)));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			String contentType = request.getHeader("Content-Type").toLowerCase();
			if (contentType.indexOf("application/json") > -1) {
				StringBuffer json = new StringBuffer();
				String line = null;
				try {
					BufferedReader reader = request.getReader();
					while ((line = reader.readLine()) != null)
						json.append(line);
				} catch (Exception e) {
					System.out.println("Error reading JSON string: " + e.toString());
				}
				returnVal = JsonUtil.toHashMapObject(json.toString());
			} else {
				while (enums.hasMoreElements()) {
					paramName = enums.nextElement();
					returnVal.put(paramName, StringUtil.fixNull(request.getParameter(paramName)));
				}
			}
		}
		return returnVal;
	}

	public static Map<String, Object> paramToJSONObject(HttpServletRequest req) {
		JSONObject jsonData = null;
		Map<String, Object> jsonMap = null;
		try {
			if (req.getParameter("json") != null) {
				jsonData = new JSONObject(req.getParameter("json").toString());
			} else {
				jsonMap = paramToHashMap(req);
			}
		} catch (JSONException e) {
			jsonData = null;
		}
		return jsonMap;
	}

	public static HashMap<String, Object> mapToHashMap(Map<String, Object> map) {
		HashMap<String, Object> returnVal = new HashMap<>();
		Set<Map.Entry<String, Object>> set = map.entrySet();
		Iterator<Map.Entry<String, Object>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = it.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (key == null)
				key = "" + null;
			if (val == null)
				val = "" + null;
			returnVal.put((String) key, val);
		}
		return returnVal;
	}

	public static HashMap<String, Object> readJSONStringFromRequestBody(HttpServletRequest request) {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				json.append(line);
		} catch (Exception e) {
			System.out.println("Error reading JSON string: " + e.toString());
		}
		HashMap<String, Object> jsonMap = null;
		jsonMap = JsonUtil.jsonObjectToHashMap(json.toString());
		return jsonMap;
	}
}
