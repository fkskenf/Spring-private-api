package com.comtroller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600) 
@RestController //RestController로 정의되어 있어야 jsp파일을 읽을 수 있다. (@Controller + @ResponseBody)
//@Controller (@RestController에 포함) 
public class commonController {

	//@ResponseBody (@RestController에 포함)
	@RequestMapping(value = "test")
	public String test(Map<String, Object> commandMap) throws Exception {
		return "index";
	}

}
