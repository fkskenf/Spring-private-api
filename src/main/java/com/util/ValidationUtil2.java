package com.util;

import java.util.HashMap;

public class ValidationUtil2 {

	public static String parameterCheck(String[] containsKeys, HashMap<String, Object> param) {
		String emptyList = "";
		if (EmptyUtil.isEmpty(param)) {
			return emptyList = "INVALID_PARAMETER";
		}
		for (String requirementKey : containsKeys) {
			if (EmptyUtil.isEmpty(param.get(requirementKey))) {
				emptyList += requirementKey + ", ";
			}
		}
		if (!EmptyUtil.isEmpty(emptyList)) {
			emptyList = "INVALID_PARAMETER" + ": " + emptyList.substring(0, emptyList.length() - 2);
		}
		return emptyList;
	}
}