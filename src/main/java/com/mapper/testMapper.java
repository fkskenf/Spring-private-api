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
	
	public Object update(HashMap<String,Object> param) {
		return update("mapper.testmapper.update", param); 
	}
	
	//최상위 노드 조회
	public List<HashMap<String, Object>> selectRootTree(HashMap<String,Object> param) {
		return selectList("mapper.testmapper.selectRootTree", param); 
	}
	
	//트리 조회
	public List<HashMap<String, Object>> selectTree(HashMap<String,Object> param) {
		return selectList("mapper.testmapper.selectTree", param); 
	}
}
