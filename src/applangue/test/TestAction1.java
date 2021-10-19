package applangue.test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import applangue.FenetreSaisie;
import applangue.GetAction;

public class TestAction1 {
	private FenetreSaisie f = new FenetreSaisie();
	private GetAction g = new GetAction(f,null);

	@Test
	public void TestAction1() {
		TestCheckString("abcd","abcd",true);
		TestCheckString("ABCD","abcd",true);
		TestCheckString("àBcD","abcd",true);
		TestCheckString("bcde","abcd",false);
		
		ArrayList<String> l1 = new ArrayList<String>();
		Collections.addAll(l1,"1","2","3","4","5");
		ArrayList<String> l2 = new ArrayList<String>();
		Collections.addAll(l2,"a","b","c","d","e");
		
		f.setDicoTmp(l1,l2);
		f.setDicoTmp2(l2,l1);
		TestDicoEmpty(f.getDicoTmp(),f.getDicoTmp2());
	}
	
	private void TestCheckString(String a,String b,Boolean c) {
		boolean tmp = g.check_string(a,b);
		assertEquals(c,tmp);
	}
	
	private void TestDicoEmpty(Map<String, String> map, Map<String, String> map2) {
		int actual_size = 0;
		int initial_size = map.values().size();
		for (int i=1;i<=initial_size;i++) {
			String random = g.pick_random(f);
			map.remove(map2.get(random));
			actual_size = map.values().size();
			
			assertEquals(actual_size,initial_size-i);	
		}
		assertTrue(map.isEmpty());
		assertEquals(0,map.values().size());
		
	}
	

}
