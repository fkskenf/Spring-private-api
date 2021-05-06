package com.enumType;

import java.util.Arrays;
import java.util.List;

public enum EnumType {
	
	SUCCESS("200", Arrays.asList("Success0","Success1","Success2")),
	FAIL("500", Arrays.asList("False0","False1","False2")),
	EMPTY("000", Arrays.asList(""));
	
	private String num;
	private List<String> list;

	/* [생성자]
	 * : 생성자의 매개변수로 전달된 값은 this.value를 통해서 인스턴스 변수의 값으로 할당된다.
	 */
	private EnumType(String num,  List<String> list) {
		this.num = num;
		this.list = list;
	}
	
	public static EnumType findEnumType(String num){
	    return 
	            //EnumType의 Enum 상수들을 순회하며
	            Arrays.stream(EnumType.values())
	            //enumType를 갖고 있는게 있는지 확인합니다.
	            .filter(enumType -> enumType.find(num))
	            .findAny()
	            .orElse(EMPTY);
	}

	public boolean find(String num){
	    return list.stream()
	            .anyMatch(list -> list.equals(num));
	}

	//인스턴스 변수값 반환
	public String getNumValue() {
		return num;
	}
	
	//인스턴스 변수값 반환
	public List<String> getListValue() {
		return list;
	}

}
