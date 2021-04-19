package com.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.JhResult;
import com.util.StringUtil;
import com.vo.testVO;
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
	
	// 2. DB연동 test용
	public JhResult testService (HashMap<String, Object> param){
		HashMap<String, Object> newParam = new HashMap<String, Object>();
		JhResult result = new JhResult();

		newParam.put("id", StringUtil.fixNull(param.get("id")));
		newParam.put("pw", StringUtil.fixNull(param.get("pw")));
		
		List<HashMap<String, Object>> selectValue = testMapper.select(newParam);
		result.setResultData(selectValue);
		
		return result;
	}
	
	// 3. VO test용
	public JhResult testService2 (testVO vo){
		HashMap<String, Object> newParam = new HashMap<String, Object>();
		JhResult result = new JhResult();
		
		newParam.put("id", StringUtil.fixNull(vo.getId()));
		newParam.put("pw", StringUtil.fixNull(vo.getPw()));
		
		try {
			testMapper.insert(newParam);
		}
		catch(Exception e) {
			System.out.println("실패입니다.");
			result.setResultCode(400);
			return result;
		}
		result.setResultCode(200);
		return result;
	}

}
