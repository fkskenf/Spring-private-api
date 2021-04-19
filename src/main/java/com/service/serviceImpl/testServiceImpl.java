package com.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.JhResult;
import com.util.StringUtil;
import com.service.*;
import com.mapper.*;

@Service("testService")
public class testServiceImpl implements testService{
	
	@Resource(name = "testmapper")
	private final testMapper testMapper;
	
	@Autowired
	testServiceImpl(testMapper testMapper){
		this.testMapper = testMapper;
	}
	
	
	public JhResult testService (HashMap<String, Object> param){
		HashMap<String, Object> newParam = new HashMap<String, Object>();
		JhResult result = new JhResult();

		newParam.put("id", StringUtil.fixNull(param.get("id")));
		newParam.put("pw", StringUtil.fixNull(param.get("pw")));
		
		List<HashMap<String, Object>> selectValue = testMapper.select(newParam);
		result.setResultData(selectValue);
		
		return result;
	}

}
