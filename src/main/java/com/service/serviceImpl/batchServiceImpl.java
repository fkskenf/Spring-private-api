package com.service.serviceImpl;

import org.springframework.stereotype.Service;

@Service("batchService")
public class batchServiceImpl {
	//배치스케줄러 test용
	//@Scheduled(cron="0 52 21 * * ?") //방법3시 적용 //
	public void batchMethod() {
		System.out.println("배치스케줄러가 정상적으로 동작합니다.");
	}

}
