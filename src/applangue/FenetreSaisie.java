package applangue;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreSaisie extends JFrame {

	private JTextField textField;
	private JLabel label;
	private JLabel score;
	private Map<String, String> dico_esp_fr;
	private Map<String, String> dico_tmp;
	private Map<String, String> dico_tmp2;
	private Map<String, String> dico_fr_esp;
	private JButton bouton;
	private JButton boutonaddexcel;
	private JButton boutonswitch;
	private JPanel panel;
	private JLabel message;
	private Boolean mode;
	
	public FenetreSaisie(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		setTitle("Quizz traduction"); //On donne un titre à l'application
		setSize(640,480); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true); //On permet le redimensionnement
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		
		//We add window listener to ask user confirmation for closing the app
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				CloseConfirmation();
			}
		});
		
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,1920,30));
		textField = new JTextField();
		textField.setColumns(25);
		//Create the different dictionnary
		dico_esp_fr = new HashMap<String, String>();
		dico_tmp = new HashMap<String, String>();
		dico_tmp2 = new HashMap<String, String>();
		dico_fr_esp = new HashMap<String, String>();
		mode = true;
		
		//score a afficher
		score = new JLabel("0/?");
		panel.add(score);
		score.setVisible(false);
		
		//Panel position
		panel.add(textField);
		label = new JLabel("Chargez un fichier excel SVP");
		panel.add(label);
		
		//message panel
		message = new JLabel("");
		panel.add(message);
		message.setVisible(false);
		
		//Button validation
		Action valider= new GetAction(this, "Valider");
		bouton = new JButton(valider);
		panel.add(bouton);
		bouton.setVisible(false);
		
		//We listen to the Enter key for validation
		textField.addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) { 
		    	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            valider.actionPerformed(null);
		    		}
		    	}

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
		    }	
			);
		
		//Create the bouton to open xslx file
		boutonaddexcel = new JButton(new GetAction2(this, "Chargez le fichier excel"));
		panel.add(boutonaddexcel);
		
		//Create button to switch language
		boutonswitch = new JButton(new GetAction3(this, "Traduction : Français -> Espagnol"));
		panel.add(boutonswitch);
		boutonswitch.setVisible(false);
		
		return panel;
	}
	
	
	//Getter
	
	//TextField
	public JTextField getTextField(){
		return textField;
	}
	
	//LABEL 
	public JLabel getLabel(){
		return label;
	}	
	public JLabel getScore(){
		return score;
	}	
	public JLabel getMessageLabel() {
		return message;
	}
	
	//Dictionnary
	public Map<String, String> getDico_esp_fr(){
		return dico_esp_fr;
	}	
	public Map<String, String> getDico_fr_esp(){
		return dico_fr_esp;
	}	
	public Map<String, String> getDicoTmp(){
		return dico_tmp;
	}	
	public Map<String, String> getDicoTmp2(){
		return dico_tmp2;
	}
	
	//Panel
	public JPanel getPanel() {
		return panel;
	}
	
	//Buttons
	public JButton getButton() {
		return bouton;
	}
	public JButton getButtonExcel() {
		return boutonaddexcel;
	}
	public JButton getButtonSwitch() {
		return boutonswitch;
	}
	
	//Variable
	public Boolean getMode() {
		return mode;
	}
	
	//Setter
	//TextField
	public void EmptyTextField() {
		textField.setText("");
	}
	
	//Label
	public void setLabel(String message) {
		label.setText(message);
	}
	public void setScore(String message) {
		score.setText(message);
	}
	public void setMessageLabel(String message) {
		this.message.setText(message);
		
	}

	//Dictionnary
	public void setDicoTmp(Map<String, String> map) {
		dico_tmp.clear();
		dico_tmp.putAll(map);
	}
	public void setDicoTmp(ArrayList<String> l1,ArrayList<String> l2) {
		dico_tmp.clear();
		if (l1.size()==l2.size()) {
			for (int i=0;i<l1.size();i++) {
				dico_tmp.put(l1.get(i), l2.get(i));
			}
		}
		else {
			System.out.println("The two list don't have the same length\n");
		}
	}
	public void setDicoTmp2(Map<String,String> dico) {
		dico_tmp2.clear();
		dico_tmp2.putAll(dico);
	}
	public void setDicoTmp2(ArrayList<String> l1,ArrayList<String> l2) {
		dico_tmp2.clear();
		if (l1.size()==l2.size()) {
			for (int i=0;i<l1.size();i++) {
				dico_tmp2.put(l1.get(i), l2.get(i));
			}
		}
		else {
			System.out.println("The two list don't have the same length\n");
		}
	}
	
	//Variable
	public void setMode(Boolean b) {
		mode=b;
	}
	
	//Main programm
	public static void main(String[] args) {
		//On crée une nouvelle instance de notre FenetreTexte
		FenetreSaisie fenetre = new FenetreSaisie();
		fenetre.setVisible(true);//On la rend visible
	}
	
	private void CloseConfirmation() {
		int reponse = JOptionPane.showConfirmDialog(this,
                "Voulez-vous quitter l'application",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
		if(reponse == JOptionPane.YES_OPTION ){
			dispose();
		}
	}
	
	

}
