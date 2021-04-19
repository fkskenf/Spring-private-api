package com.mapper;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.dao.AbstractDAO;

@Repository("testmapper")
public class testMapper extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> select(HashMap<String,Object> param) {
		return selectList("mapper.testmapper.select", param); 
	}
	
	public Object insert(HashMap<String,Object> param) {
		return insert("mapper.testmapper.insert", param); 
	}
}
