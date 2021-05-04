package com.service.serviceImpl;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.service.WithdrawService;

@Service("withdrawService")
public class WithdrawServiceImpl implements WithdrawService {
	
	public void withdrawMethod() {
		System.out.println("withdrawMethod 실행");
	}

}
