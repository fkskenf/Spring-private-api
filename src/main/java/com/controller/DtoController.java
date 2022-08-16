package com.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.TestDto;
import com.util.JsonUtil;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/dto")
public class DtoController {

	@SuppressWarnings("unchecked")
	@PostMapping("/test")
	public ResponseEntity<?> test(@RequestBody TestDto testDto) throws IOException {
		
		long no           = testDto.getNo();
		String firstName  = testDto.getFirstName();
		String secondName = testDto.getSecondName();
		String pluseMapSt = testDto.getPluseMap();
		
		HashMap<String, Object> pluseMap = JsonUtil.toHashMapObject(pluseMapSt); // JSON Object의 String을 Map으로 변환한 값
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("no"         , no);
		map.put("first_name" , firstName);
		map.put("second_name", secondName);
		map.put("pluse_map"  , pluseMap);

		return ResponseEntity.ok(map);
	}
}
