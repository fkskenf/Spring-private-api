package com.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileuploadService {
	public String saveFile(MultipartFile file);
}
