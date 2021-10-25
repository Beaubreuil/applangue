package applangue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

import java.text.Collator;

public class GetAction5 extends GetAction {
	private FenetreSaisie fenetre;
	private String texte;
	
	public GetAction5(FenetreSaisie fenetre, String texte){
		super(fenetre,texte);
		this.fenetre = fenetre;
		this.texte=texte;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.texte==fenetre.getResourceBundle().getString("lang_fr")) {
			fenetre.setLocale(new Locale("fr",""));
			fenetre.setResourceBundle(fenetre.getLocale());
			//fenetre.getPanel().removeAll();
			System.out.println("FRRRR");
			//fenetre.getPanel().removeAll();
			fenetre.getPanel().revalidate();
			fenetre.getPanel().repaint();
			System.out.println(fenetre.getButton());
		}
		else if (this.texte==fenetre.getResourceBundle().getString("lang_en")) {
			
		}
		else if (this.texte==fenetre.getResourceBundle().getString("lang_es")) {
			
		}
		System.out.println("test");
	}
}