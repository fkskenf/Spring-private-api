package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.JhResult;
import com.util.RequestUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;
import com.vo.testVO;
import com.service.*;

@CrossOrigin(origins = "*", maxAge = 3600) 
@RestController //RestController로 정의되어 있어야 jsp파일을 읽을 수 있다. (@Controller + @ResponseBody)
//@Controller (@RestController에 포함) 
public class commonController {

	// 서비스단과 열결하기 위한 객체선언 및 @Autowired설정
	private final testService testService;

	@Autowired
	commonController(testService testService) {
			this.testService = testService;
	}

	//1. HTTP URL test용
	// @ResponseBody (@RestController에 포함)
	@RequestMapping(value = "test")
	public String test(Map<String, Object> commandMap) throws Exception {
		System.out.println("hi");
		return "hi";
	}

	//2. DB연동 test용
	@GetMapping(value = "test2")
	public ResponseEntity<?> test2(HttpServletRequest request) {
		HashMap<String, Object> param = RequestUtil.paramToHashMap(request);
		HashMap<String, Object> newParam = new HashMap<String, Object>();
		JhResult result = new JhResult();

		newParam.put("id", StringUtil.fixNull(param.get("id")));
		newParam.put("pw", StringUtil.fixNull(param.get("pw")));

		result = testService.testService(newParam);
		return ResponseUtil.JhResponse(result);
	}
	
	//3. VO test용
		@GetMapping(value = "test3")
		public ResponseEntity<?> test3(HttpServletRequest request) {
			testVO vo= new testVO();
			vo.setId(3);
			vo.setPw("333");
			
			return ResponseUtil.JhResponse(result);
		}

}
