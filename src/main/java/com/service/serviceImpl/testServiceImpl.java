package com.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.JhResult;
import com.util.StringUtil;
import com.vo.testVO;
import com.service.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mapper.*;

@Service("testService")
public class testServiceImpl implements testService {

	@Resource(name = "testmapper")
	private final testMapper testMapper;

	@Autowired
	testServiceImpl(testMapper testMapper) {
		this.testMapper = testMapper;
	}

	// 2. DB연동 test용
	public JhResult testService(HashMap<String, Object> param) {
		HashMap<String, Object> newParam = new HashMap<String, Object>();
		JhResult result = new JhResult();

		newParam.put("id", StringUtil.fixNull(param.get("id")));
		newParam.put("pw", StringUtil.fixNull(param.get("pw")));

		List<HashMap<String, Object>> selectValue = testMapper.select(newParam);
		result.setResultData(selectValue);

		return result;
	}

	// DB연동 @Transaction test
	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	public JhResult testService22(HashMap<String, Object> param) throws Exception  {
		HashMap<String, Object> newParam = new HashMap<String, Object>();
		JhResult result = new JhResult();

		newParam.put("id", StringUtil.fixNull(param.get("id")));
		newParam.put("pw", StringUtil.fixNull(param.get("pw")));
		System.out.print("테스트중");
		testMapper.insert(newParam);
		//testMapper.update(newParam);
		
		throw new Exception();
	}

	// 3. VO test용
	public JhResult testService2(testVO vo) {
		HashMap<String, Object> newParam = new HashMap<String, Object>();
		JhResult result = new JhResult();

		newParam.put("id", StringUtil.fixNull(vo.getId()));
		newParam.put("pw", StringUtil.fixNull(vo.getPw()));

		try {
			testMapper.insert(newParam);
		} catch (Exception e) {
			System.out.println("실패입니다.");
			result.setResultCode(400);
			return result;
		}
		result.setResultCode(200);
		return result;
	}
	
	// 3. Gson 테스트
		public JhResult testGson(testVO vo) {
			HashMap<String, Object> newParam = new HashMap<String, Object>();
			JhResult result = new JhResult();
			
			String jsonStr = "{\"nodes\":[{\"node_id\":\"2a80ab59-38d3-4a60-8085-2f7c112bdc3f\""
					+ ",\"key\":\"10026\""
					+ ",\"data_type\":\"table\""
					+ ",\"data_name\":\"응급정보\""
					+ ",\"position\":{\"x\":224,\"y\":122}"
					+ ",\"filters\":[{\"co\":\"100000624\""
					+ ",\"operator\":\"between\""
					+ ",\"val\":\"2021.12.01|2021.12.31\""
					+ ",\"checked\":false}]"
					+ ",\"filter\":true}]"
					+ ",\"links\":[]}";
					
			
			//pom.xml에 라이브러리 추가 필요
			JsonParser parser = new JsonParser();
			JsonObject obj = (JsonObject)parser.parse(jsonStr.toString());
			
			Gson gson =new Gson();
			Map map =new HashMap();
			map = (Map)gson.fromJson(obj, map.getClass());
			newParam.put("json", map);	
			
			result.setResultData(newParam);
			result.setResultCode(200);
			return result;
		}

}
