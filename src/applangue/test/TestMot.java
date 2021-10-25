package applangue.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import applangue.Mot;


public class TestMot {

	@Test
	public void TestAll() {
		TestMotCreate();
		TestRetrieveMot();
	}
	
	public void TestMotCreate(){
		Mot m = new Mot("test","test1","test2");
		assertEquals("test",m.getMot1());
		assertEquals("test1",m.getMot2());
		assertEquals("test2",m.getCate());
	}
	
	public void TestRetrieveMot() {
		List<Mot> l = new ArrayList<Mot>();
		for (int i = 0;i<50000;i++) {
			l.add(RandomMot());
		}
		for (int i = 0;i<50000;i++) {
			l.add(RandomMot("Sports"));
		}
		Mot m = new Mot("","","");
		assertEquals(100000, l.size());
		List<Mot> l2 = m.getAllMotFromCate(l, "Sports");
		assertEquals(50000,l2.size());
	}
	
	private Mot RandomMot() {
		Mot m=new Mot(null,null,null);
		String Character = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		String rand_word = "";
		for (int j=0;j<3;j++) {
			for (int i=0; i<5;i++) {
				rand_word = rand_word+Character.charAt(rand.nextInt(Character.length()));
			}
			m.setMot1(rand_word);
			rand_word="";
			switch(j) {
			case 0:
				m.setMot1(rand_word);
			case 1:
				m.setMot2(rand_word);
			case 2:
				m.setCate(rand_word);
			default:
				rand_word="";
			}
		}
		return m;
	}
	
	private Mot RandomMot(String cate) {
		Mot m=new Mot(null,null,null);
		String Character = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rand = new Random();
		String rand_word = "";
		for (int j=0;j<2;j++) {
			for (int i=0; i<5;i++) {
				rand_word = rand_word+Character.charAt(rand.nextInt(Character.length()));
			}
			m.setMot1(rand_word);
			rand_word="";
			switch(j) {
			case 0:
				m.setMot1(rand_word);
			case 1:
				m.setMot2(rand_word);
			default:
				rand_word="";
			}
		}
		m.setCate(cate);
		return m;
	}
}
