package personal_project;

import org.junit.Test;

import java.util.*;

public class main {
	@Test
	public void solution() {}

	@Test
	public void StringBufferTest() {
		StringBuffer str = new StringBuffer("안녕");

		System.out.println(str.append("하세요 ! "));
		System.out.println(str.insert(6, "반가워요"));
		System.out.println(str.delete(2, 5));
	}

}
