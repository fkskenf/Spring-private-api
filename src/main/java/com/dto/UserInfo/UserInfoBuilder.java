package com.dto.UserInfo;

/*
 * 빌더를 이용한 객체
 *
 *[장점]
 *  1) 불필요한 생성자 제거
 *  2) 데이터의 순서에 상관없이 객체생성가능
 *  3) 명시적 선언으로 이해하기 쉬움
 *  
 * [단점]
 * 	1) 대상클래스와 그것을 연결할 빌더클래스 2개를 생성해야한다.
 */

public class UserInfoBuilder {
	private String firstName;
	private String secondName;
	private int age;
	
	public UserInfoBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public UserInfoBuilder setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}
	
	public UserInfoBuilder setAge(int age) {
		this.age = age;
		return this;
	}
	
	public UserInfo build() {
		return new UserInfo(firstName, secondName, age);
	}
}
