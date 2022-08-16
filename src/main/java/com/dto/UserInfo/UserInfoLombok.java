package com.dto.UserInfo;

import lombok.Builder;
import lombok.Data;

/*
 * 룸북을 활용한 객체
 * 
 * [장점]
 * 1) 대상클래스, 빌더클래스 없이 한번으로 동일한 기능 사용가능 
 */

@Data
@Builder
public class UserInfoLombok {
	private String firstName;
	private String secondName;
	@Builder.Default
	private int age = 30; // 디폴트 적용가능

	public String getUserInfo() {
		return String.format("firstName: %s, secondName: %s, age: %d", firstName, secondName, age);
	}

}
