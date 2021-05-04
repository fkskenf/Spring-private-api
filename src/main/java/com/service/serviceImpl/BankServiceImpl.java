package com.service.serviceImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.BankService;
import com.service.DepositeService;
import com.service.WithdrawService;

@Service("bankService")
public class BankServiceImpl implements BankService {

	private DepositeService depositeService;
	private WithdrawService withdrawService;

	@Autowired
	public void bankService(DepositeService depositeService, WithdrawService withdrawService) {
		this.depositeService = depositeService;
		this.withdrawService = withdrawService;
	}

	@Test
	public void bankMethod() {
		depositeService.depositeMethod();
		withdrawService.withdrawMethod();
	}

}
