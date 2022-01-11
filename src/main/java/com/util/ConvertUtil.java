package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ConvertUtil {
	 public static HashMap modelToHashMap(Object obj) {
		    try {
		      Field[] fields = obj.getClass().getDeclaredFields();
		      HashMap<Object, Object> resultMap = new HashMap<>();
		      for (int i = 0; i <= fields.length - 1; i++) {
		        fields[i].setAccessible(true);
		        resultMap.put(fields[i].getName(), fields[i].get(obj));
		      } 
		      return resultMap;
		    } catch (IllegalArgumentException e) {
		      e.printStackTrace();
		    } catch (IllegalAccessException e) {
		      e.printStackTrace();
		    } 
		    return null;
		  }
		  
		  public static Object hashMapToModel(HashMap map, Object objClass) {
		    String keyAttribute = null;
		    String setMethodString = "set";
		    String methodString = null;
		    String methodType = null;
		    Object methodObject = null;
		    Iterator<String> itr = map.keySet().iterator();
		    while (itr.hasNext()) {
		      keyAttribute = itr.next();
		      methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
		      try {
		        Method[] methods = objClass.getClass().getDeclaredMethods();
		        for (int i = 0; i <= methods.length - 1; i++) {
		          if (methodString.equals(methods[i].getName())) {
		            methodType = StringUtil.fixNull(methods[i].getParameterTypes()[0]);
		            methodObject = map.get(keyAttribute);
		            if ("class java.lang.Integer".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).isEmpty())
		                methods[i].invoke(objClass, new Object[] { Integer.valueOf(Integer.parseInt(methodObject.toString())) }); 
		            } else if ("class java.lang.Short".equals(methodType) || "short".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).isEmpty())
		                methods[i].invoke(objClass, new Object[] { Short.valueOf(Short.parseShort(methodObject.toString())) }); 
		            } else if ("class java.lang.Long".equals(methodType) || "long".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).isEmpty())
		                methods[i].invoke(objClass, new Object[] { Long.valueOf(Long.parseLong(methodObject.toString())) }); 
		            } else if ("class java.lang.Double".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).isEmpty())
		                methods[i].invoke(objClass, new Object[] { Double.valueOf(Double.parseDouble(methodObject.toString())) }); 
		            } else if ("class java.util.Date".equals(methodType) && methodObject != null && "java.lang.String".equals(methodObject.getClass().getTypeName())) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).isEmpty())
		                methods[i].invoke(objClass, new Object[] { DateTimeUtil.getStringtoDate(StringUtil.fixNull(methodObject)) }); 
		            } else if (map.get(keyAttribute) != null && !map.get(keyAttribute).equals("")) {
		              methods[i].invoke(objClass, new Object[] { methodObject });
		            } 
		          } 
		        } 
		      } catch (SecurityException e) {
		        e.printStackTrace();
		      } catch (IllegalAccessException e) {
		        System.out.println("[ERROR][ IllegalAccessException ] methodString : " + methodString + " / value : " + methodObject + " / type : " + methodType);
		      } catch (IllegalArgumentException e) {
		        System.out.println("[ERROR][ IllegalArgumentException ] methodString : " + methodString + " / value : " + methodObject + " / type : " + methodType);
		      } catch (InvocationTargetException e) {
		        System.out.println("[ERROR][ InvocationTargetException ] methodString : " + methodString + " / value : " + methodObject + " / type : " + methodType);
		      } 
		    } 
		    return objClass;
		  }
		  
		  public static Object hashMapToModelDefaultValue(HashMap map, Object objClass) {
		    String keyAttribute = null;
		    String setMethodString = "set";
		    String methodString = null;
		    String methodType = null;
		    Object methodObject = null;
		    Iterator<String> itr = map.keySet().iterator();
		    while (itr.hasNext()) {
		      keyAttribute = itr.next();
		      methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
		      try {
		        Method[] methods = objClass.getClass().getDeclaredMethods();
		        for (int i = 0; i <= methods.length - 1; i++) {
		          if (methodString.equals(methods[i].getName())) {
		            methodType = StringUtil.fixNull(methods[i].getParameterTypes()[0]);
		            methodObject = map.get(keyAttribute);
		            if ("class java.lang.Integer".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).equals(""))
		                methods[i].invoke(objClass, new Object[] { Integer.valueOf(Integer.parseInt(methodObject.toString())) }); 
		            } else if ("class java.lang.Short".equals(methodType) || "short".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).equals(""))
		                methods[i].invoke(objClass, new Object[] { Short.valueOf(Short.parseShort(methodObject.toString())) }); 
		            } else if ("class java.lang.Long".equals(methodType) || "long".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).equals(""))
		                methods[i].invoke(objClass, new Object[] { Long.valueOf(Long.parseLong(methodObject.toString())) }); 
		            } else if ("class java.lang.Double".equals(methodType)) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).equals(""))
		                methods[i].invoke(objClass, new Object[] { Double.valueOf(Double.parseDouble(map.get(keyAttribute).toString())) }); 
		            } else if ("class java.lang.String".equals(methodType)) {
		              methods[i].invoke(objClass, new Object[] { StringUtil.fixNull(methodObject) });
		            } else if ("class java.util.Date".equals(methodType) && methodObject != null && "java.lang.String".equals(methodObject.getClass().getTypeName())) {
		              if (!StringUtil.fixNull(map.get(keyAttribute)).isEmpty())
		                methods[i].invoke(objClass, new Object[] { DateTimeUtil.getStringtoDate(StringUtil.fixNull(methodObject)) }); 
		            } else if (map.get(keyAttribute) != null && !map.get(keyAttribute).equals("")) {
		              methods[i].invoke(objClass, new Object[] { methodObject });
		            } 
		          } 
		        } 
		      } catch (SecurityException e) {
		        System.out.println("[ERROR][ SecurityException ] methodString : " + methodString + " / value : " + methodObject + " / type : " + methodType);
		      } catch (IllegalAccessException e) {
		        System.out.println("[ERROR][ IllegalAccessException ] methodString : " + methodString + " / value : " + methodObject + " / type : " + methodType);
		      } catch (IllegalArgumentException e) {
		        System.out.println("[ERROR][ IllegalArgumentException ] methodString : " + methodString + " / value : " + methodObject + " / type : " + methodType);
		      } catch (InvocationTargetException e) {
		        System.out.println("[ERROR][ InvocationTargetException ] methodString : " + methodString + " / value : " + methodObject);
		      } 
		    } 
		    return objClass;
		  }
		  
		  public static HashMap hashMapCopy(HashMap resultMap, HashMap targetMap) {
		    try {
		      Iterator<String> iterator = targetMap.keySet().iterator();
		      while (iterator.hasNext()) {
		        String key = iterator.next();
		        resultMap.put(key, targetMap.get(key));
		      } 
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return resultMap;
		  }
		  
		  public static void revertXSSMap(HashMap<String, String> targetMap) {
		    try {
		      Iterator<String> iterator = targetMap.keySet().iterator();
		      while (iterator.hasNext()) {
		        String key = iterator.next();
		        if (StringUtil.fixNull(targetMap.get(key)).indexOf("&lt;") != -1 || StringUtil.fixNull(targetMap.get(key)).indexOf("&gt;") != -1) {
		          targetMap.put(key, StringUtil.revertXSS(StringUtil.fixNull(targetMap.get(key))));
		          continue;
		        } 
		        targetMap.put(key, targetMap.get(key));
		      } 
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		  }
		  
		  public static HashMap<String, Object> joinTable(HashMap<String, Object> basicTable, String basicKey, HashMap<String, Object> addTable, String addKey) {
		    if (basicTable.get(basicKey) == addTable.get(addKey))
		      try {
		        Iterator<String> iterator = addTable.keySet().iterator();
		        while (iterator.hasNext()) {
		          String key = iterator.next();
		          basicTable.put(key, addTable.get(key));
		        } 
		      } catch (Exception e) {
		        e.printStackTrace();
		      }  
		    return basicTable;
		  }
		  
		  public static List<HashMap<String, Object>> joinTable(List<HashMap<String, Object>> basicTable, List<String> basicKey, List<HashMap<String, Object>> addTable, List<String> addKey, String listName) {
		    List<HashMap<String, Object>> resultTable = new ArrayList<>();
		    List<HashMap<String, Object>> list = null;
		    for (HashMap<String, Object> basic : basicTable) {
		      list = new ArrayList<>();
		      for (HashMap<String, Object> add : addTable) {
		        int successCount = 0;
		        for (int i = 0; i < basicKey.size(); i++) {
		          if (StringUtil.fixNull(basic.get(basicKey.get(i))).equals(StringUtil.fixNull(add.get(addKey.get(i)))))
		            successCount++; 
		        } 
		        if (successCount == basicKey.size())
		          list.add(add); 
		      } 
		      basic.put(listName, list);
		      resultTable.add(basic);
		    } 
		    return resultTable;
		  }
		  
		  public static HashMap<String, Object> joinTable(HashMap<String, Object> basicTable, List<String> basicKey, List<HashMap<String, Object>> addTable, List<String> addKey, String listName) {
		    List<HashMap<String, Object>> list = new ArrayList<>();
		    for (HashMap<String, Object> add : addTable) {
		      int successCount = 0;
		      for (int i = 0; i < basicKey.size(); i++) {
		        if (StringUtil.fixNull(basicTable.get(basicKey.get(i))).equals(StringUtil.fixNull(add.get(addKey.get(i)))))
		          successCount++; 
		      } 
		      if (successCount == basicKey.size())
		        list.add(add); 
		    } 
		    basicTable.put(listName, list);
		    return basicTable;
		  }

}
