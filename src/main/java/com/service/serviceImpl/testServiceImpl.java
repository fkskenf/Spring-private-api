package com.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.JhResult;
import com.util.EmptyUtil;
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
	
	protected Logger logger = LogManager.getLogger(this.getClass());

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
		@SuppressWarnings("rawtypes")
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

		public JhResult tree() {
			JhResult result = new JhResult();
			HashMap<String, Object> resultData = new HashMap<String, Object>();
			
			HashMap<String, Object> newParam = new HashMap<String, Object>();
			List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

			List<HashMap<String, Object>> rootList = new ArrayList<HashMap<String, Object>>();
			rootList = testMapper.selectRootTree(newParam);
			
			if(!EmptyUtil.isEmpty(rootList)) 
			{
				List<HashMap<String, Object>> treeList = testMapper.selectTree(newParam);
				int size = treeList.size();
				
				int idx = 0;
				for(HashMap<String, Object> root : rootList) { //최상위 노드별 반복
					idx = makeTree(root, treeList, size, idx);
					resultList.add(root);
				}
			}
			resultData.put("list", resultList);
			result.setResultData(resultData);
			result.setResultCode(200);
			return result;
		}
		
		@SuppressWarnings("unchecked")
		private int makeTree(HashMap<String, Object> root, List<HashMap<String, Object>> treeList, int size, int idx) {
			List<HashMap<String, Object>> child = null;
			if(EmptyUtil.isEmpty(root.get("children"))) 
			{
				child = new ArrayList<HashMap<String, Object>>();
				root.put("children", child);
			}
			else{
				child = (List<HashMap<String, Object>>)root.get("children");
			}
			
			String rootTreeNo = StringUtil.fixNull(root.get("no"),"0");
			while(idx < size) {
				HashMap<String, Object> tree = (HashMap<String, Object>) treeList.get(idx);
				String treeNo =  StringUtil.fixNull(tree.get("no"),"0");
				String pTreeNo = StringUtil.fixNull(tree.get("parent_no"),"0");
				
				if(rootTreeNo.equals(treeNo)) {
					idx++;
					continue;
				}
				else if(rootTreeNo.equals(pTreeNo)) {
					child.add(tree);
					idx = makeTree(tree, treeList, size, idx);
				} 
				else {
					break;
				}
			}
			return idx;
		}

}
