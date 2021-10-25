package applangue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import java.text.Collator;

public class GetAction3 extends GetAction {
	private FenetreSaisie fenetre;
	
	public GetAction3(FenetreSaisie fenetre, String texte){
		super(fenetre,texte);
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent e) { 
		
		//If mode = true we keep the translation from Francais to Espagnol
		if(fenetre.getMode()) {
			//DicoTmp = fr_esp ; DicoTmp2 = esp_fr
			fenetre.setDicoJetable(fenetre.getDicoTmp());
			fenetre.setDicoTmp(fenetre.getDicoTmp2());
			fenetre.setDicoTmp2(fenetre.getDicoJetable());
			fenetre.setDicoJetable(fenetre.getDicoTmp());
			fenetre.setMode(false);
			fenetre.getButtonSwitch().setText(fenetre.getResourceBundle().getString("trad_1-2"));
		}
		//Otherwise we switch it to Espagnol -> Francais
		else {
			//DicoTmp = esp_fr ; DicoTmp2 = fr_esp
			fenetre.setDicoJetable(fenetre.getDicoTmp());
			fenetre.setDicoTmp(fenetre.getDicoTmp2());
			fenetre.setDicoTmp2(fenetre.getDicoJetable());
			fenetre.setDicoJetable(fenetre.getDicoTmp());
			fenetre.setMode(true);
			fenetre.getButtonSwitch().setText(fenetre.getResourceBundle().getString("trad_2-1"));
		}
		//We change the random word and that's it
		fenetre.setLabel(pick_random(fenetre));
		
		//Maj score
		MajScore();
		
		//Display button
		fenetre.getMessageLabel().setVisible(true);
        fenetre.getButtonExcel().setVisible(false);
        fenetre.getScore().setVisible(true);
        fenetre.getButtonSwitch().setVisible(true);
        fenetre.getMenu().setVisible(true);
		
	} 
	
}
