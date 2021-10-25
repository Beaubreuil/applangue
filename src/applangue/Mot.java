package applangue;

import java.util.ArrayList;
import java.util.List;

public class Mot {
	private String mot1;
	private String mot2;
	private String cate;
	
	public Mot(String mot1, String mot2, String cate) {
		this.mot1=mot1;
		this.mot2=mot2;
		this.cate=cate;
	}
	
	public List<Mot> getAllMotFromTrad(List<Mot> l, String trad){
		List<Mot> l1 = new ArrayList<Mot>();
		l.forEach((mot)->{
			if (mot.getMot1()==trad || mot.getMot2()==trad) {
				l1.add(mot);
			}
		});
		return l1;
	}
	
	public List<Mot> getAllMotFromCate(List<Mot> l, String cate){
		List<Mot> l1 = new ArrayList<Mot>();
		l.forEach((mot)->{
			if (mot.getCate()==cate) {
				l1.add(mot);
				
			}
		});
		return l1;
	}
	
	//Getter and Setter
	public String getMot1() {
		return mot1;
	}
	public void setMot1(String mot1) {
		this.mot1 = mot1;
	}
	public String getMot2() {
		return mot2;
	}
	public void setMot2(String mot2) {
		this.mot2 = mot2;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
}
