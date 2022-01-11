package com.util;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.model.JhResult;

public class ValidationUtil {

	  public static boolean checkContainsKey(String requirementKey, HashMap param) {
	    boolean flag = false;
	    if (param == null)
	      return false; 
	    if (param.containsKey(requirementKey) && !StringUtil.fixNull(param.get(requirementKey)).equals(""))
	      flag = true; 
	    if (!flag)
	      return false; 
	    return true;
	  }
	  
	  public static boolean checkContainsKeys(String[] requirementKeys, HashMap param) {
	    if (param == null)
	      return false; 
	    for (String requirementKey : requirementKeys) {
	      boolean flag = false;
	      for (Object key : param.keySet()) {
	        String stringKey = StringUtil.fixNull(key.toString());
	        if (requirementKey.equals(stringKey) && !StringUtil.fixNull(param.get(key)).equals("")) {
	          flag = true;
	          break;
	        } 
	      } 
	      if (!flag)
	        return false; 
	    } 
	    return true;
	  }
	  
	  public static boolean checkAnyContainsKeys(String[] keys, HashMap<String, Object> param) {
	    if (param == null)
	      return false; 
	    for (String key : keys) {
	      if (param.containsKey(key))
	        return true; 
	    } 
	    return false;
	  }
	  
	  public static JhResult checkContainsKeysValue(String[] requirementKeys, HashMap param) {
	    JhResult jhResult = new JhResult();
	    String emptyList = "";
	    if (param == null) {
	    	jhResult.setResultCode(400);
	    	jhResult.setResultMsg("Parameter Is Not Valid. Parameter is null ]");
	    } 
	    for (String requirementKey : requirementKeys) {
	      boolean flag = false;
	      for (Object key : param.keySet()) {
	        String stringKey = StringUtil.fixNull(key.toString());
	        if (requirementKey.equals(stringKey) && !StringUtil.fixNull(param.get(key)).equals("")) {
	          flag = true;
	          break;
	        } 
	      } 
	      if (!flag)
	        if (emptyList.isEmpty()) {
	          emptyList = "[" + requirementKey;
	        } else {
	          emptyList = emptyList + ", " + requirementKey;
	        }  
	    } 
	    if (!emptyList.isEmpty()) {
	    	jhResult.setResultCode(400);
	    	jhResult.setResultMsg("Parameter Is Not Valid. " + emptyList + "]");
	    } 
	    return jhResult;
	  }
	  
	  public static boolean checkDuplicate3Character(String d) {
	    int p = d.length();
	    byte[] b = d.getBytes();
	    for (int i = 0; i < p * 2 / 3; i++) {
	      int b1 = b[i + 1];
	      int b2 = b[i + 2];
	      if (b[i] == b1 && b[i] == b2)
	        return true; 
	    } 
	    return false;
	  }
	  
	  public static HashMap<String, Object> checkContainKeyFillValue(String requirementKey, HashMap<String, Object> param) {
	    if (param == null)
	      return null; 
	    if (!param.containsKey(requirementKey))
	      param.put(requirementKey, ""); 
	    return param;
	  }
	  
	  public static HashMap<String, Object> checkContainKeysFillValue(String[] requirementKeys, HashMap<String, Object> param) {
	    if (param == null)
	      return null; 
	    for (String requirementKey : requirementKeys) {
	      if (!param.containsKey(requirementKey))
	        param.put(requirementKey, ""); 
	    } 
	    return param;
	  }
	  
	  public static HashMap<String, Object> checkContainKeysCopyMap(String[] requirementKeys, HashMap<String, Object> param) {
	    if (param == null)
	      return null; 
	    HashMap<String, Object> result = new HashMap<>();
	    for (String requirementKey : requirementKeys) {
	      if (!param.containsKey(requirementKey)) {
	        result.put(requirementKey, "");
	      } else {
	        result.put(requirementKey, param.get(requirementKey));
	      } 
	    } 
	    return result;
	  }
	  
	  public static boolean checkFileExtension(String fileName, String[] possible_ext, String[] impossible_ext) {
	    boolean isSuccess = true;
	    String fileExtension = FileUtil.getFileExtension(StringUtil.fixNull(fileName));
	    if (fileExtension.isEmpty())
	      return false; 
	    for (String possibleExt : possible_ext) {
	      if (fileExtension.equalsIgnoreCase(possibleExt))
	        return true; 
	    } 
	    for (String impossibleExt : impossible_ext) {
	      if (fileExtension.equalsIgnoreCase(impossibleExt))
	        return false; 
	    } 
	    return isSuccess;
	  }
	  
	  public static boolean checkListData(List list) {
	    boolean isCheck = false;
	    if (list != null && list.size() > 0)
	      isCheck = true; 
	    return isCheck;
	  }
	  
	  public static boolean checkPermissionServiceCode(HttpServletRequest request, String[] service_code_list) {
	    String service_code = "";
	    try {
	      service_code = StringUtil.fixNull(request.getHeader("service-code"));
	    } catch (Exception e) {
	      return false;
	    } 
	    if ("".equals(service_code))
	      return false; 
	    for (String serviceCode : service_code_list) {
	      if (service_code.equals(serviceCode))
	        return true; 
	    } 
	    return false;
	  }
	  
	  public static void main(String[] args) throws Exception {
	    HashMap<String, Object> param = new HashMap<>();
	    param.put("user_no", "1");
	    param.put("exe", "1");
	    JhResult checkParamResult = checkContainsKeysValue("user_no,company_no".split(","), param);
	    if (checkParamResult.getResultCode() != 200) {
	      System.out.println(checkParamResult.getResultCode());
	      System.out.println(checkParamResult.getResultMsg());
	    } 
	  }

}
