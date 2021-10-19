package applangue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import java.text.Collator;

public class GetAction3 extends AbstractAction {
	private FenetreSaisie fenetre;
	
	public GetAction3(FenetreSaisie fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent e) { 
		
		//If mode = true we keep the translation from Francais to Espagnol
		if(fenetre.getMode()) {
			fenetre.setDicoTmp(fenetre.getDico_fr_esp());
			fenetre.setDicoTmp2(fenetre.getDico_esp_fr());
			fenetre.setMode(false);
			fenetre.getButtonSwitch().setText("Traduction : Francais -> Espagnol");
		}
		//Otherwise we switch it to Espagnol -> Francais
		else {
			fenetre.setDicoTmp2(fenetre.getDico_fr_esp());
			fenetre.setDicoTmp(fenetre.getDico_esp_fr());
			fenetre.setMode(true);
			fenetre.getButtonSwitch().setText("Traduction : Espagnol -> Français");
		}
		//We change the random word and that's it
		fenetre.setLabel(pick_random(fenetre));
		
	} 
	
	public String pick_random(FenetreSaisie fenetre) {
		Random generator = new Random();
		Object[] values = fenetre.getDicoTmp().values().toArray();
		Object randomValue = values[generator.nextInt(values.length)];
		//On change le texte du label et on reset le texte de réussite
		return (String) randomValue;
	}
	
}
