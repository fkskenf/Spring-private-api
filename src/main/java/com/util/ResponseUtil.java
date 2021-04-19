package com.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.model.JhError;
import com.model.JhResult;

public class ResponseUtil {
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static ResponseEntity<?> JhResponse(JhResult result) {
	  JhError luluError = new JhError();
    int code = result.getResultCode();
    if (code < 200 || code >= 300) {
      luluError.setErrorCode(result.getResultCode());
      luluError.setErrorMsg(result.getResultMsg());
      return new ResponseEntity(luluError, HttpStatus.valueOf(code));
    } 
    return new ResponseEntity(result, HttpStatus.valueOf(code));
  }
}