package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	 public static void makeDir(String path) {
		    File uploadDir = new File(path);
		    if (!uploadDir.exists())
		      uploadDir.mkdir(); 
		  }
		  
		  public static void writeFileLog(String filePath, String FileName, String logText) {
		    try {
		      makeDir(filePath);
		      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		      String date = dateFormat.format(new Date());
		      BufferedWriter fw = new BufferedWriter(new FileWriter(filePath + FileName + "." + date + ".log", true));
		      StringBuffer sb = new StringBuffer();
		      sb.append(logText);
		      fw.write(sb.toString() + "\n");
		      fw.flush();
		      fw.close();
		    } catch (Exception e) {
		      System.out.println(e);
		    } 
		  }
		  
		  public static File multipartToFile(MultipartFile multipart) {
		    File convFile = new File(multipart.getOriginalFilename());
		    try {
		      multipart.transferTo(convFile);
		    } catch (IllegalStateException|IOException e) {
		      System.out.println(e);
		    } 
		    return convFile;
		  }
		  
		  public static String getFileExtension(String fileName) {
		    String result = "";
		    if (!fileName.equals("") || fileName != null) {
		      int cnt = fileName.lastIndexOf(".");
		      if (cnt > 0) {
		        result = fileName.substring(cnt + 1, fileName.length()).toLowerCase();
		      } else {
		        result = "";
		      } 
		    } 
		    return result;
		  }
		  
		  public static void main(String[] args) {
		    List<HashMap<String, Object>> resultList = getCSVFile("/Users/LeeYongSang/Downloads/2017.03~2018.03_small.txt", "euc-kr", "\\|", 1);
		    System.out.println(resultList.size());
		  }
		  
		  public static List<HashMap<String, Object>> getCSVFile(String path, String encoding, String delimiter, int startReadLine) {
		    BufferedReader br = null;
		    List<HashMap<String, Object>> resultList = new ArrayList<>();
		    HashMap<String, Object> result = new HashMap<>();
		    try {
		      br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
		      int cnt = 0;
		      String[] header = { "business_no", "company_sub_reg_no", "company_name", "ceo_name", "address1", "business_format", "business_type", "is_member" };
		      return resultList;
		    } catch (FileNotFoundException e) {
		      return resultList;
		    } catch (IOException e) {
		      return resultList;
		    } finally {
		      Exception exception = null;
		      if (br != null)
		        try {
		          br.close();
		        } catch (IOException e) {
		          System.out.println(e);
		        }  
		    } 
		  }
}
