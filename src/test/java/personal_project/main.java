package personal_project;

import java.util.HashMap;

import org.junit.Test;

import com.util.StringUtil;

import java.util.*;

public class main {
	public int solution(String[][] clothes) {

		// map(key,value) value는 동일한 종류의 수
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < clothes.length; i++) {
			if (!map.containsKey(clothes[i][1])) {
				map.put(clothes[i][1], 2); // 1개 종류당 있고,없고 : 경우의수 2가지로 시작함
			} else {
				map.put(clothes[i][1], Integer.parseInt(null, map.get(clothes[i][1])) + 1);
			}
		}

		int result = 1;
		for (String key : map.keySet()) {
			result *= map.get(key);
		}

		int answer = result - 1;
		return answer;

	}
	
	@Test
	public void StringBufferTest() {
		
		StringBuffer str = new StringBuffer("안녕");
		System.out.println(str.append("하세요 ! "));
		System.out.println(str.insert(6, "반가워요"));
		System.out.println(str.delete(2, 5));
	
		
	}
	
}
