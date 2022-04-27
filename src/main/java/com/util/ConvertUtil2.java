package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConvertUtil2 {
	protected static Logger logger = LogManager.getLogger(ConvertUtil2.class.getName());

	public static HashMap<String, Object> makeParamNewParam(String[] chkArr, HashMap<String, Object> param, HashMap<String, Object> newParam){
		for(String chk: chkArr){
			if(ValidationUtil.checkContainsKey(chk, param)){
				newParam.put(chk, param.get(chk));
			}
		}
		return newParam;
	}

	public static String getAccountNumFormated(String orgAccountNum, String pattern_text, String replace_text) {
	   String accountNum = orgAccountNum.replaceAll("-", "").replaceAll(" ", "");

       if(!Pattern.matches(pattern_text, accountNum)) 
       {
           return orgAccountNum;
       }
       return accountNum.replaceAll(pattern_text, replace_text);
	}
	
	public static String makeQueryStringUrl(String url, HashMap<String, Object> param){
		if(EmptyUtil.isEmpty(url) || EmptyUtil.isEmpty(param)) {
			return url;
		}
		
		String paramStr = param.entrySet().stream()
				.map(p -> {
					try {
						return URLEncoder.encode(StringUtil.fixNull(p.getKey()), "UTF-8") + "=" + URLEncoder.encode(StringUtil.fixNull(p.getValue()), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						return  "";
					}
				})
				.reduce((p1, p2) -> p1 + "&" + p2)
				.orElse("");
		if(url.lastIndexOf("?") == url.length()) {
			url += paramStr;
		} else if(url.lastIndexOf("?") > -1) {
			url += "&" + paramStr;
		} else {
			url += "?" + paramStr;
		}
		return url;
	}
	
	@SuppressWarnings("unchecked")
	public static int makeTree(HashMap<String, Object> root, List<HashMap<String, Object>> treeList, int size, int idx) {
		List<HashMap<String, Object>> children = null;
		if(EmptyUtil.isEmpty(root.get("children"))) 
		{
			children = new ArrayList<HashMap<String, Object>>();
			root.put("children", children);
		}
		else{
			children = (List<HashMap<String, Object>>)root.get("children");
		}
		
		long rootSdNo = Long.parseLong(StringUtil.fixNull(root.get("no"),"0"));
		while(idx < size) {
			HashMap<String, Object> tree = (HashMap<String, Object>) treeList.get(idx);
			long sdNo = Long.parseLong(StringUtil.fixNull(tree.get("no"),"0"));
			long parentSdNo = Long.parseLong(StringUtil.fixNull(tree.get("parent_no"),"0"));
			
			if(rootSdNo == sdNo) {
				idx++;
				continue;
			} 
			else if(rootSdNo == parentSdNo) {
				children.add(tree);
				idx = makeTree(tree, treeList, size, idx);
			} 
			else {
				break;
			}
		}
		return idx;
    }
	
	@SuppressWarnings("unchecked")
	public static int makeTreeReverseOrder(HashMap<String, Object> root, List<HashMap<String, Object>> treeList, int size, int idx) {
		List<HashMap<String, Object>> children = null;
		if(EmptyUtil.isEmpty(root.get("children"))) 
		{
			children = new ArrayList<HashMap<String, Object>>();
			root.put("children", children);
		}
		else{
			children = (List<HashMap<String, Object>>)root.get("children");
		}
		
		long rootSdNo = Long.parseLong(StringUtil.fixNull(root.get("no"),"0"));
		while(idx < size) {
			HashMap<String, Object> tree = (HashMap<String, Object>) treeList.get(idx);
			long sdNo = Long.parseLong(StringUtil.fixNull(tree.get("no"),"0"));
			long parentSdNo = Long.parseLong(StringUtil.fixNull(tree.get("parent_no"),"0"));
			
			if(rootSdNo == sdNo) {
				idx++;
				continue;
			} 
			else if(rootSdNo == parentSdNo) {
				children.add(0, tree);
				idx = makeTreeReverseOrder(tree, treeList, size, idx);
			} 
			else {
				break;
			}
		}
		return idx;
    }
	
	public static int calculatePageNo(String limitStr, String offsetStr){
		if(EmptyUtil.isEmpty(limitStr) && EmptyUtil.isEmpty(offsetStr)) {
			return 0;
		}
		int limit = Integer.parseInt(limitStr); 
		int offset = Integer.parseInt(offsetStr); 
		return (limit + offset) / limit;
    }
	
	public static int calculateOffset(String pageNoStr, String pageSizeStr){
		if(EmptyUtil.isEmpty(pageNoStr) && EmptyUtil.isEmpty(pageSizeStr)) {
			return 0;
		}
		int pageNo = Integer.parseInt(pageNoStr); 
		int pageSize = Integer.parseInt(pageSizeStr); 
		return pageSize * (pageNo -1);
    }
	
}