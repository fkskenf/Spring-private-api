package com.vo;

public class testVO {
	
	private long id;
	private String pw;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public testVO() {}
	
	public testVO(long id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
		
	public String toString() {
		return "testVO [id=" + id + ", pw=" + pw + "]";
	}

}
