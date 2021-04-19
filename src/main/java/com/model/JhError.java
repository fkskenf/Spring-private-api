package com.model;

public class JhError {
	private int errorCode;
	  
	  private String errorMsg;
	  
	  public JhError() {
	    this.errorCode = 500;
	    this.errorMsg = "";
	    setErrorCode(500);
	  }
	  
	  public int getErrorCode() {
	    return this.errorCode;
	  }
	  
	  public void setErrorCode(int errorCode) {
	    this.errorCode = errorCode;
	  }
	  
	  public String getErrorMsg() {
	    return this.errorMsg;
	  }
	  
	  public void setErrorMsg(String errorMsg) {
	    this.errorMsg = errorMsg;
	  }
}
