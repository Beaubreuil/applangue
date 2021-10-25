package applangue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.AbstractAction;
import java.text.Collator;

public class GetAction4 extends GetAction {
	private FenetreSaisie fenetre;
	
	public GetAction4(FenetreSaisie fenetre, String texte){
		super(fenetre,texte);
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String all=fenetre.getResourceBundle().getString("all");
		String recent = fenetre.getResourceBundle().getString("recent");
		String item = fenetre.getMenu().getSelectedItem().toString();
		//System.out.println(item+"   "+all+"   "+recent);
		if(item.equals(all)) {
			CreateDicoAll();
			//System.out.println("all dico");
		}
		else if(item.equals(recent)) {
			
		}
		else {
			CreateDico(fenetre.getMenu().getSelectedItem().toString());
		}
		//Pick random
		fenetre.setLabel(pick_random(fenetre));
		
		//Maj score
		MajScore();
		
		System.out.println(fenetre.getMenu().getSelectedItem());
	}
	
	private void CreateDico(String cate) {
		Map<String,String> dico1 = new HashMap<String,String>();
		Map<String,String> dico2 = new HashMap<String,String>();
		fenetre.getListMot().forEach((mot)->{
			if (mot.getCate()==cate) {
				dico1.put(mot.getMot1(), mot.getMot2());
				dico2.put(mot.getMot2(), mot.getMot1());
			}
		});
		if (fenetre.getMode()) {
			fenetre.setDicoTmp(dico2);
			fenetre.setDicoTmp2(dico1);
			fenetre.setDicoJetable(fenetre.getDicoTmp());
		}
		else {
			fenetre.setDicoTmp(dico1);
			fenetre.setDicoTmp2(dico2);
			fenetre.setDicoJetable(fenetre.getDicoTmp());
		}
	}
	
	private void CreateDicoAll() {
		if (fenetre.getMode()) {
			fenetre.setDicoTmp(fenetre.getDico_fr_esp());
			fenetre.setDicoTmp2(fenetre.getDico_esp_fr());
			fenetre.setDicoJetable(fenetre.getDicoTmp());
		}
		else {
			fenetre.setDicoTmp(fenetre.getDico_esp_fr());
			fenetre.setDicoTmp2(fenetre.getDico_fr_esp());
			fenetre.setDicoJetable(fenetre.getDicoTmp());
		}
	}
	
}