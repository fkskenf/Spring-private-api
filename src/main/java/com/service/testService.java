package com.service;

import java.util.HashMap;
import com.model.JhResult;
import com.vo.testVO;

public interface testService {
	public JhResult testService (HashMap<String, Object> param);
	
	public JhResult testService2 (testVO vo);
}
