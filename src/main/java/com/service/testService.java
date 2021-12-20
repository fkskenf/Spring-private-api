package com.service;

import java.util.HashMap;
import com.model.JhResult;
import com.vo.testVO;

public interface testService {
	public JhResult testService(HashMap<String, Object> param); // 1. DB연동 테스트

	public JhResult testService22(HashMap<String, Object> param) throws Exception; // DB연동 @Transaction test

	public JhResult testService2(testVO vo); // 2. VO 테스트
}
