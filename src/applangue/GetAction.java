package applangue;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public abstract class GetAction extends AbstractAction{
	private FenetreSaisie fenetre;
	
	public GetAction(FenetreSaisie fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	public String pick_random(FenetreSaisie fenetre) {
		Random generator = new Random();
		Object[] values = fenetre.getDicoJetable().values().toArray();
		if(values.length>0) {
			Object randomValue = values[generator.nextInt(values.length)];
			//On change le texte du label et on reset le texte de réussite
			return (String) randomValue;
		}
		else {
			return "Dico vide";
		}
	}
	
	public void MajScore() {
		String tmp = fenetre.getScore().getText();
        String[] parts = tmp.split("/");
        fenetre.getScore().setText("0"+"/"+String.valueOf(fenetre.getDicoJetable().size()));
	}
	
	/*public void Dialogue() {
		int reponse = JOptionPane.showConfirmDialog(this,
                "Voulez-vous quitter l'application",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
		if(reponse == JOptionPane.YES_OPTION ){
			actionPerformed(null);
		}
	}*/

}
