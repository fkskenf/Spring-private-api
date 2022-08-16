package com.dto.UserInfo;

/*
 * 기본적인 객체
 */
public class UserInfo {
	private String firstName;
	private String secondName;
	private int age;

	public UserInfo(String firstName, String secondName, int age) { // 셍성자
		this.firstName = firstName;
		this.secondName = secondName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserInfo() {
		return String.format("firstName: %s, secondName: %s, age: %d", firstName, secondName, age);
	}

}
