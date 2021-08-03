package com.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	private final FileuploadService fileuploadService;
	
	@Autowired
	private Properties properties;

	@Autowired
	commonController(testService testService, BankService bankService, FileuploadService fileuploadService) {
		this.testService = testService;
		this.bankService = bankService;
		this.fileuploadService = fileuploadService;
	}

	/*
	 * 1. HTTP URL test용
	 */
	// @ResponseBody (@RestController에 포함)
	@RequestMapping(value = "test")
	public String test(Map<String, Object> commandMap) throws Exception {
		System.out.println("hi");
		return "hi";
	}

	/*
	 * 2. DB연동 test용
	 */
	@GetMapping(value = "test2")
	public ResponseEntity<?> test2(HttpServletRequest request) {
		JhResult result = new JhResult();
		HashMap<String, Object> param = RequestUtil.paramToHashMap(request);
		HashMap<String, Object> newParam = new HashMap<String, Object>();

		newParam.put("id", StringUtil.fixNull(param.get("id")));
		newParam.put("pw", StringUtil.fixNull(param.get("pw")));
		System.out.println("test2");
		
		result = testService.testService(newParam);
		return ResponseUtil.JhResponse(result);
	}

	/*
	 * 3. VO test용
	 */
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
	
	/*
	 * 6. DI 테스트
	 */
	@Test
	@GetMapping(value = "injectionTest")
	public void injecttionTest(HttpServletRequest request) {
		bankService.bankMethod();
	}
	
	/*
	 * 7. enum 테스트
	 */
	@GetMapping(value = "enumtest")
	public void enumtest(HttpServletRequest request) {
		
		//1. 리스트를 활용한 Enum
		System.out.println("EnumType.FAIL.getNumValue() : " + EnumType.FAIL.getNumValue());
		System.out.println("EnumType.FAIL.getListValue() : " + EnumType.FAIL.getListValue());
		System.out.println("EnumType.findEnumType(\"Success0\") : " + EnumType.findEnumType("Success0"));

		//3. 리스트 + 함수를 활용한 Enum
		//System.out.println(EnumType.AVG.calculate(Arrays.asList(1,2,3,4,5)));
		//System.out.println(EnumType.SUM.calculate(Arrays.asList(1,2,3,4,5)));
	}
	
	/*
	 * 파일업로드 jsp
	 */
	@RequestMapping(value = "fileUpload_jsp") //jsp파일명이랑 이름이 같아야한다.
	public String fileUploadJsp(MultipartFile uploadfile, Model model) throws Exception {
		
		System.out.println("fileUpload_jsp");
		
		return"fileUpload";
	}
	/*
	 * 8-1.파일업로드 서비스
	 */
	@PostMapping(value = "fileUpload") //jsp파일명이랑 이름이 같아야한다.
	public String fileUpload(MultipartFile uploadfile, Model model) throws Exception {
		//파일을 받을 수 있게끔 MultipartFile을 매개변수 선언
		
		System.out.println("upload() POST 호출");
		//파일 이름을 String 값으로 반환한다
		System.out.println("파일 이름(uploadfile.getOriginalFilename()) : "+ uploadfile.getOriginalFilename());
		//파일 크기를 반환한다
		System.out.println("파일 크기(uploadfile.getSize()) : "+ uploadfile.getSize());
		
		fileuploadService.saveFile(uploadfile);
		
		return "fileUpload";
	}
	
	/*
	 * 8-2. 다중 파일업로드 서비스
	 */
	@PostMapping(value = "multi_fileUpload") //jsp파일명이랑 이름이 같아야한다.
	public String multiFileUpload(MultipartFile[] uploadfiles, Model model) throws Exception {
		//파일을 받을 수 있게끔 MultipartFile 배열을 매개변수 선언

		for(MultipartFile f: uploadfiles) {
			System.out.println("upload() POST 호출");
			//파일 이름을 String 값으로 반환한다
			System.out.println("파일 이름(uploadfile.getOriginalFilename()) : "+ f.getOriginalFilename());
			//파일 크기를 반환한다
			System.out.println("파일 크기(uploadfile.getSize()) : "+ f.getSize());
			
			fileuploadService.saveFile(f);
		}
		
		return "fileUpload";
	}
	
	/*
	 * globals.properites 테스트 
	 */
	@GetMapping(value = "globalsProperties") 
	public void globalsProperties() throws Exception {
		System.out.println("globals.PW= " + properties.getProperty("globals.ID"));
		System.out.println("globals.PW= " + properties.getProperty("globals.PW"));
	}


}
