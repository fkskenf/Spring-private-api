package com;

public enum EnumType {
	
	SUCESS(200, "true"),
	FAIL(500, "false");
	
	private int num;
	private String code;

	/* [생성자]
	 * : 생성자의 매개변수로 전달된 값은 this.value를 통해서 8행의 인스턴스 변수의 값으로 할당된다.
	 */
	private EnumType(int num, String code) {
		this.num = num;
		this.code = code;
	}

	//인스턴스 변수값 반환
	public int getNumValue() {
		return num;
	}
	
	//인스턴스 변수값 반환
	public String getCodeValue() {
		return code;
	}

}
