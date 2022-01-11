package com.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONObject;

public class JsonUtil {

	  public static List<HashMap> toList(String jsonStr) {
	    List<HashMap> list = null;
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	      list = (List<HashMap>)mapper.readValue(jsonStr, List.class);
	    } catch (JsonParseException e) {
	      System.out.println(e);
	    } catch (JsonMappingException e) {
	      System.out.println(e);
	    } catch (IOException e) {
	      System.out.println(e);
	    } 
	    return list;
	  }
	  
	  public static HashMap toHashMap(String jsonStr) {
	    HashMap<Object, Object> map = new HashMap<>();
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	      map = (HashMap<Object, Object>)mapper.readValue(jsonStr, new TypeReference<HashMap<String, String>>() {
	          
	          });
	    } catch (JsonParseException e) {
	      System.out.println(e);
	    } catch (JsonMappingException e) {
	      System.out.println(e);
	    } catch (IOException e) {
	      System.out.println(e);
	    } 
	    return map;
	  }
	  
	  public static HashMap toHashMapObject(String jsonStr) {
	    HashMap<Object, Object> map = new HashMap<>();
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	      map = (HashMap<Object, Object>)mapper.readValue(jsonStr, new TypeReference<HashMap<String, Object>>() {
	          
	          });
	    } catch (JsonParseException e) {
	      System.out.println(e);
	    } catch (JsonMappingException e) {
	      System.out.println(e);
	    } catch (IOException e) {
	      System.out.println(e);
	    } 
	    return map;
	  }
	  
	  public static String toJson(Object obj) {
	    ObjectMapper mapper = new ObjectMapper();
	    String json = "";
	    try {
	      json = mapper.writer().writeValueAsString(obj);
	    } catch (JsonParseException e) {
	      System.out.println(e);
	    } catch (JsonMappingException e) {
	      System.out.println(e);
	    } catch (IOException e) {
	      System.out.println(e);
	    } 
	    return json;
	  }
	  
	  public static HashMap<String, Object> jsonObjectToHashMap(String str) {
	    HashMap<String, Object> result = new HashMap<>();
	    Gson gson = new Gson();
	    JSONObject json = new JSONObject();
	    result = (HashMap<String, Object>)gson.fromJson(str, HashMap.class);
	    json.putAll(result);
	    result = toHashMap(json.toString());
	    return result;
	  }
	  
	  public static HashMap<String, Object> jsonObjectToList(String[] key, HashMap<String, Object> map) {
	    ObjectMapper mapper = new ObjectMapper();
	    String keyAttribute = null;
	    try {
	      for (int i = 0; i < key.length; i++) {
	        if (ValidationUtil.checkContainsKey(key[i], map) && 
	          map.get(key[i]).getClass().toString().equals("class org.postgresql.util.PGobject"))
	          map.put(key[i], mapper.readValue(map.get(key[i]).toString(), List.class)); 
	      } 
	    } catch (JsonParseException e) {
	      System.out.println(e);
	    } catch (JsonMappingException e) {
	      System.out.println(e);
	    } catch (IOException e) {
	      System.out.println(e);
	    } 
	    return map;
	  }
	  
	  public static HashMap<String, Object> jsonObjectToList(HashMap<String, Object> map) {
	    ObjectMapper mapper = new ObjectMapper();
	    String keyAttribute = null;
	    try {
	      Iterator<String> itr = map.keySet().iterator();
	      while (itr.hasNext()) {
	        keyAttribute = itr.next();
	        if (map.get(keyAttribute) != null && map.get(keyAttribute).getClass().toString().equals("class org.postgresql.util.PGobject") && 
	          map.get(keyAttribute) != null) {
	          if (map.get(keyAttribute).toString().indexOf("[") > -1) {
	            map.put(keyAttribute, mapper.readValue(map.get(keyAttribute).toString(), List.class));
	            continue;
	          } 
	          map.put(keyAttribute, mapper.readValue(map.get(keyAttribute).toString(), HashMap.class));
	        } 
	      } 
	    } catch (JsonParseException e) {
	      System.out.println(e);
	    } catch (JsonMappingException e) {
	      System.out.println(e);
	    } catch (IOException e) {
	      System.out.println(e);
	    } 
	    return map;
	  }
	  
	  public static List<HashMap<String, Object>> jsonObjectToList(String[] key, List<HashMap<String, Object>> list) {
	    for (int i = 0; i < list.size(); i++) {
	      HashMap<String, Object> map = list.get(i);
	      map = jsonObjectToList(key, map);
	      list.set(i, map);
	    } 
	    return list;
	  }
	  
	  public static List<HashMap<String, Object>> jsonObjectToList(List<HashMap<String, Object>> list) {
	    for (int i = 0; i < list.size(); i++) {
	      HashMap<String, Object> map = list.get(i);
	      map = jsonObjectToList(map);
	      list.set(i, map);
	    } 
	    return list;
	  }

}
