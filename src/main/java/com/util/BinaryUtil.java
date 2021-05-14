package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.BinaryCodec;

public class BinaryUtil {
	
	/**
	 * 파일을 바이너리 스트링으로 변경
	 */
	public static String fileToBinary(File file) throws Exception {
	    String out = "";
	    byte[] imageInByte = new byte[(int) file.length()];
		try(InputStream in = new BufferedInputStream(new FileInputStream(file));){
			in.read(imageInByte);
			out = BinaryCodec.toAsciiString(imageInByte);
		}
	 
	    return out;
	}
	
	/**
	 * 바이너리 스트링을 파일로 변환
	 * 특정 디렉토리에 저장
	 */
	public static File binaryToFile(String binaryFile, String filePath, String fileName) throws Exception {
		
	    if ((binaryFile == null || "".equals(binaryFile)) || (filePath == null || "".equals(filePath))
	            || (fileName == null || "".equals(fileName))) { return null; }
	 
	 
	    File fileDir = new File(filePath);
	    if (!fileDir.exists()) {
	        fileDir.mkdirs();
	    }
	 
	    File destFile = new File(filePath + fileName);
	 
	    byte[] buff = BinaryCodec.fromAscii(binaryFile.getBytes(StandardCharsets.UTF_8));
	 
	    try (FileOutputStream fos = new FileOutputStream(destFile);) {
	        fos.write(buff);
	    }
	 
	    return destFile;
	}
	
}
