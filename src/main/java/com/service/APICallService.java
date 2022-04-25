package com.service;

import java.util.HashMap;

public interface APICallService {
	HashMap<String, Object> sendGet(String url);

	HashMap<String, Object> sendGet(String url, HashMap<String, Object> param);

	HashMap<String, Object> sendPost(String url);

	HashMap<String, Object> sendPost(String url, HashMap<String, Object> param);
}
