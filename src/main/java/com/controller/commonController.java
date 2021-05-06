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

import com.enumType.EnumType;
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
	
	// 6. DI 테스트
	@Test
	@GetMapping(value = "injectionTest")
	public void injecttionTest(HttpServletRequest request) {
		bankService.bankMethod();
	}
	
	// 7. enum 테스트
	@GetMapping(value = "enumtest")
	public void enumtest(HttpServletRequest request) {
		System.out.println("EnumType[] values = EnumType.values()");
		EnumType[] values = EnumType.values();
		System.out.println();
		
		System.out.println("values[0] : " + values[0]);
		System.out.println("values[0].ordinal() : " + values[0].ordinal());
		System.out.println("EnumType.valueOf(\"SUCCESS\") : " + EnumType.valueOf("SUCCESS"));
		System.out.println("EnumType.SUCESS.toString() : " + EnumType.SUCCESS.toString());
		System.out.println();
		
		System.out.println("EnumType.SUCCESS.getNumValue() : " + EnumType.SUCCESS.getNumValue());
		System.out.println("EnumType.SUCCESS.getListValue() : " + EnumType.SUCCESS.getListValue());
		System.out.println();
		
		System.out.println("EnumType.FAIL.getNumValue() : " + EnumType.FAIL.getNumValue());
		System.out.println("EnumType.FAIL.getListValue() : " + EnumType.FAIL.getListValue());
		System.out.println();
		
		System.out.println("EnumType.findEnumType(\"Success0\") : " + EnumType.findEnumType("Success0"));
		System.out.println("EnumType.findEnumType(\"Success1\") : " + EnumType.findEnumType("Success1"));
		System.out.println("EnumType.findEnumType(\"Success2\") : " + EnumType.findEnumType("Success2"));
		System.out.println("EnumType.findEnumType(\"False0\") : " + EnumType.findEnumType("False0"));
		System.out.println("EnumType.findEnumType(\"False1\") : " + EnumType.findEnumType("False1"));
		System.out.println("EnumType.findEnumType(\"False2\") : " + EnumType.findEnumType("False2"));
		System.out.println("EnumType.findEnumType(\"\") : " + EnumType.findEnumType(""));
	}

}
