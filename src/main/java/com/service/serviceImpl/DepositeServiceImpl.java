package com.service.serviceImpl;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.service.DepositeService;

@Service("depositeService")
public class DepositeServiceImpl implements DepositeService{

	public void depositeMethod() {
		System.out.println("depositeMethod 실행");
	}

}
