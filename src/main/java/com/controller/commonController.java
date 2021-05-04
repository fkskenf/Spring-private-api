package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.JhResult;
import com.util.RequestUtil;
import com.util.ResponseUtil;
import com.util.StringUtil;
import com.vo.testVO;
import com.service.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController // (@Controller + @ResponseBody)
@Controller //(@RestController에 포함)Controller로 정의되어 있어야 jsp파일을 읽을 수 있다. 
public class commonController {

	// 서비스단과 열결하기 위한 객체선언 및 @Autowired설정
	private final testService testService;
	private final BankService bankService;

	@Autowired
	commonController(testService testService, BankService bankService) {
		this.testService = testService;
		this.bankService = bankService;
	}

	// 1. HTTP URL test용
	// @ResponseBody (@RestController에 포함)
	@RequestMapping(value = "test")
	public String test(Map<String, Object> commandMap) throws Exception {
		System.out.println("hi");
		return "hi";
	}

	// 2. DB연동 test용
	@GetMapping(value = "test2")
	public ResponseEntity<?> test2(HttpServletRequest request) {
		JhResult result = new JhResult();
		HashMap<String, Object> param = RequestUtil.paramToHashMap(request);
		HashMap<String, Object> newParam = new HashMap<String, Object>();

		newParam.put("id", StringUtil.fixNull(param.get("id")));
		newParam.put("pw", StringUtil.fixNull(param.get("pw")));

		result = testService.testService(newParam);
		return ResponseUtil.JhResponse(result);
	}

	// 3. VO test용
	@GetMapping(value = "test3")
	public ResponseEntity<?> test3(@RequestBody testVO vo) {
		JhResult result = new JhResult();
		vo.getId();
		vo.getPw();

		result = testService.testService2(vo);

		return ResponseUtil.JhResponse(result);
	}
	
	// 3. VO (POST시 직렬화문제)test용
	@PostMapping(value = "test4")
	public ResponseEntity<?> test4(@RequestBody testVO vo) {
		JhResult result = new JhResult();
		vo.getId();
		vo.getPw();

		result = testService.testService2(vo);

		return ResponseUtil.JhResponse(result);
	}
	
	@Test
	@GetMapping(value = "injectionTest")
	public void injecttionTest(HttpServletRequest request) {
		bankService.bankMethod();
	}
	

}
