package com.service.serviceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.LogManager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.service.APICallService;
import com.util.JsonUtil;

/*
 * 예시용 
 */
public class APICallServiceImpl implements APICallService {


//	protected Logger logger = LogManager.getLogger(this.getClass());

	
	@SuppressWarnings("unused")
	private final HttpServletRequest request;

	@Autowired
	APICallServiceImpl(HttpServletRequest request) {
		this.request = request;
	}
	
	public HashMap<String, Object> sendGet(String url) {
		return sendApi(url,"GET", new HashMap<String, Object>());
	}

	public HashMap<String, Object> sendGet(String url, HashMap<String, Object> param) {
		return sendApi(url, "GET", param);
	}

	public HashMap<String, Object> sendPost(String url) {
		return sendApi(url, "POST", new HashMap<String, Object>());
	}

	public HashMap<String, Object> sendPost(String url, HashMap<String, Object> param) {
		return sendApi(url, "POST", param);
	}
	
	private HashMap<String, Object> sendApi(String url, String method, HashMap<String, Object> param) {
		
//		url = ruruProperties.getProperty("globals.PRIVATE_CDW_DOMAIN")  + url;
//		logger.debug("url >>> " + url);
		try 
		{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setConnectTimeout(5000);
			con.setReadTimeout(60000);

			con.setRequestMethod(method.toUpperCase());
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			//con.setRequestProperty("Authorization", StringUtil.fixNull(request.getHeader("Authorization")));
			//con.setRequestProperty("transaction-id", StringUtil.fixNull(request.getHeader("transaction-id")));
			//con.setRequestProperty("client-id", StringUtil.fixNull(request.getHeader("client-id")));
			//con.setRequestProperty("service", StringUtil.fixNull(luluProperties.getProperty("globals.SERVICE_CODE")));
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			con.setDoOutput(true);
			
			if(!method.toUpperCase().equals("GET"))
			{
				String paramStr = JsonUtil.toJson(param);
//				logger.debug("Request paramStr >>> " + paramStr);
				
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(paramStr.getBytes("UTF_8"));
				wr.flush();
				wr.close();
			}

			int responseCode = con.getResponseCode();
//			logger.debug("Response Code >>> " + responseCode);
		
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null){
				response.append(inputLine);
			}
			in.close();
//			logger.debug("Response Value >>> " + response.toString());
			
			//ObjectMapper mapper = new ObjectMapper();
			String[] results = response.toString().split(System.lineSeparator());
			try {
//				ruruResult.setResultCode(results.length<2 ? 504 : responseCode);
				HashMap<String,Object> resultData = new HashMap<String,Object>();
				
				if (results.length>1) {
					resultData.put("col", results[0]);
					List<String> rows = new ArrayList<String>();
					
					for(int ii = 1; ii < results.length; ii++) {
						rows.add(results[ii]);
					}
					resultData.put("row"   , rows);
				}
				
//				ruruResult.setResultData(resultData);
			} 
			catch(Exception e) {
//				ruruResult.setResultData(response.toString());
			}
		} 
		catch (Exception e) 
		{
//			logger.error("******************* Error *******************");
//			if(e.getCause() != null){
//				logger.error("Error Message >> " + e.getCause().getMessage());
//			}else{
//				logger.error("Error Message >> " + e.getMessage());
//			}
//			logger.error("**********************************************");
//			e.printStackTrace();
//			
//			ruruResult.setResultCode(500);
		}
//		return ruruResult;
		return param;
	}


}
