package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.service.*;

@Controller
public class socketServerController {
	private final socketServerService socketServerService;
	
	@Autowired
	socketServerController(socketServerService socketServerService){
		this.socketServerService = socketServerService;
	}
	
	
	@GetMapping(value = "socketServer")
	public ResponseEntity<?> socketServer(HttpServletRequest requestz){
		socketServerService.socketServer();
		return null;
	}

}
