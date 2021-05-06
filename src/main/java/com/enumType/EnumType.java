package com.enumType;

import java.util.Arrays;
import java.util.List;

import org.springframework.cglib.core.internal.Function;

public enum EnumType {
	
	//1. 리스트를 활용한 Enum
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
	            //enumType을 갖고 있는게 있는지 확인합니다.
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
	
	/*
	 * 3. 리스트 + 함수를 활용한 Enum
	AVG("평균", list -> { return getSum(list) / list.size();} ),
	SUM("합계", list -> { return getSum(list);} );

	private final String name;
	private final Function<List<Integer>, Integer> expression; //

	EnumType(String name, Function<List<Integer>, Integer> expression) {
		this.name = name;
		this.expression = expression;
	}

	private static Integer getSum(List<Integer> list) {
		return list.stream()      //스트림생성
			.reduce(Integer::sum) //.reduce : 결과를 만들어냄, Integer::sum : 합
			.get();
	}

	public Integer calculate(List<Integer> list) {
		return expression.apply(list); //.apply : 작업 수행후 리턴
	}
   */
}
