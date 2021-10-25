package applangue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import java.text.Collator;

public class GetAction1 extends GetAction {
	private FenetreSaisie fenetre;
	
	public GetAction1(FenetreSaisie fenetre, String texte) {
		super(fenetre, texte);
		this.fenetre = fenetre;
	}

	
	public void actionPerformed(ActionEvent e) { 
		//We get the text and suppress trail in the beginning and the end of the string
		String question=fenetre.getLabel().getText().trim();
		String reponse=fenetre.getTextField().getText().trim();
		
		//We check if the response is equal to the value of the question in the dictionnary
		
		if (check_string(fenetre.getDicoTmp2().get(question),reponse)) {
			//The answer is Correct : we display the score +1 and a bravo message
			score_up(fenetre);
		}
		else {
			//The answer is Wrong : we display a message with the good answer
			fenetre.getMessageLabel().setForeground(Color.RED);
			fenetre.setMessageLabel(fenetre.getResourceBundle().getString("wrong_answer")+fenetre.getDicoTmp2().get(question));
		}
		//Remove the actual word from the dico
		fenetre.getDicoJetable().remove(fenetre.getDicoTmp2().get(question));
		
		//Check if the dictionnary is empty
		if (fenetre.getDicoJetable().isEmpty()) {
			fenetre.getButton().setVisible(false);
			fenetre.getTextField().setVisible(false);
		}
		else {
			//Get the next random string and display it 	
			fenetre.setLabel((String) pick_random(fenetre));
			fenetre.EmptyTextField();
		}
	} 
	
	public Boolean check_string(String a,String b) {
		Collator col = Collator.getInstance();
		col.setStrength(Collator.PRIMARY);
		int tmp = col.compare(a, b);
		if (tmp==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void score_up(FenetreSaisie fenetre) {
		String string = fenetre.getScore().getText();
		String[] parts = string.split("/");
		int score = Integer.parseInt(parts[0]);
		score++;
		fenetre.setScore(String.valueOf(score)+"/"+parts[1]);
		fenetre.getMessageLabel().setForeground(Color.GREEN);
		fenetre.setMessageLabel(fenetre.getResourceBundle().getString("right_answer"));
	}
	
	private void sanitize_string(String s) {
		System.out.println("BONJOUR"+s);
	}
	
}
