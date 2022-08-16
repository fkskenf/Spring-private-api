package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserInfo.UserInfo;
import com.dto.UserInfo.UserInfoBuilder;
import com.dto.UserInfo.UserInfoLombok;

@RestController
public class UserInfoContoller {

	@GetMapping(value = "/userinfo/test")
	public ResponseEntity<?> test(HttpServletRequest request) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		// 1. 기본적인 객체 생성		
		UserInfo userInfo = new UserInfo("기본", "객체생성", 1);
		System.out.println(userInfo.getUserInfo());
		
		// 2. 빌더클래스를 이용한 객체 생성
		UserInfoBuilder userInfoBuilder = new UserInfoBuilder();
		UserInfo userInfo2 = userInfoBuilder
		        .setFirstName("빌더클래스 적용")
		        .setSecondName("객체생성")
		        .setAge(26)
		        .build();
		System.out.println(userInfo2.getUserInfo());
		
		// 3. 룸북을 이용한 객체 생성
		UserInfoLombok userInfoLombok = UserInfoLombok.builder()
		        .firstName("Lombok 적용")
		        .secondName("객체생성")
		        .build();
		System.out.println(userInfoLombok.getUserInfo());
		
		return ResponseEntity.ok(resultMap);
	}
}
