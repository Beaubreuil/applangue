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
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreSaisie extends JFrame {

	private JTextField textField;
	private JLabel label;
	private JLabel score;
	private JMenuBar menu_bar;
	private JMenu submenu_lang;
	private JMenu main_menu;
	private JMenu edit_menu;
	private JMenu help_menu;
	private Map<String, String> dico_esp_fr;
	private Map<String, String> dico_tmp;
	private Map<String, String> dico_tmp2;
	private Map<String, String> dico_fr_esp;
	private Map<String, String> dico_jetable;
	private JButton bouton;
	private JButton boutonaddexcel;
	private JButton boutonswitch;
	private JPanel panel;
	private JLabel message;
	private Boolean mode;
	private JComboBox menu_deroulant;
	private List<Mot> lmot;
	Locale locale;
    ResourceBundle messages;
	
	public FenetreSaisie(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		//Initialize lang in english
		locale = new Locale("en","");
		messages = ResourceBundle.getBundle("applangue.test.lang.messages", locale);
		//Setup the window
		setTitle(messages.getString("window_title")); //On donne un titre à l'application
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
		dico_jetable = new HashMap<String, String>();
		lmot = new ArrayList<Mot>();
		mode = true;
		
		//score a afficher
		score = new JLabel("0/?");
		panel.add(score);
		score.setVisible(false);
		
		//Panel position
		panel.add(textField);
		
		//Label
		label = new JLabel("");
		panel.add(label);
		
		//message panel
		message = new JLabel("");
		panel.add(message);
		message.setVisible(false);
		
		//Button validation
		Action valider= new GetAction1(this, messages.getString("button_submit"));
		bouton = new JButton(valider);
		panel.add(bouton);
		bouton.setVisible(false);
		
		//Menu déroulant
		//String s1[]= {"test","test1","test2","test3"};
		menu_deroulant = new JComboBox();
		System.out.println(menu_deroulant.getItemCount());
		menu_deroulant.addActionListener(new GetAction4(this,"menu"));
		panel.add(menu_deroulant);
		menu_deroulant.setVisible(false);
		
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
		boutonaddexcel = new JButton(new GetAction2(this,  messages.getString("button_load")));
		panel.add(boutonaddexcel);
		boutonaddexcel.setVisible(false);
		
		//Create button to switch language
		boutonswitch = new JButton(new GetAction3(this, messages.getString("trad_1-2")));
		panel.add(boutonswitch);
		boutonswitch.setVisible(false);
		
		InitializeMenu();
		
		return panel;
	}
	
	private void InitializeMenu() {
		//Create menu bar
		menu_bar = new JMenuBar();
		this.setJMenuBar(menu_bar);
		
		//Create main menu
		main_menu = new JMenu(messages.getString("menu_file"));
		main_menu.add(new JMenuItem(new GetAction2(this, messages.getString("button_load"))));
		
		//Submenu for switch language of application
		submenu_lang = new JMenu(messages.getString("button_switch"));
		main_menu.add(submenu_lang);
		submenu_lang.add(new JMenuItem(new GetAction5(this,messages.getString("lang_fr"))));
		submenu_lang.add(new JMenuItem(new GetAction5(this,messages.getString("lang_en"))));
		submenu_lang.add(new JMenuItem(new GetAction5(this,messages.getString("lang_es"))));
		menu_bar.add(main_menu);
		
		//Create menu edit
		edit_menu = new JMenu(messages.getString("menu_edit"));
		menu_bar.add(edit_menu);
		
		//Create menu help
		help_menu = new JMenu(messages.getString("menu_help"));
		menu_bar.add(help_menu);
	}
	
	private void CloseConfirmation() {
		int reponse = JOptionPane.showConfirmDialog(this,
				messages.getString("message_confirmation"),
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
		if(reponse == JOptionPane.YES_OPTION ){
			dispose();
		}
	}
	
	
/*===================================================================================================================*/
	
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
	public Map<String,String> getDicoJetable(){
		return dico_jetable;
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
	public JComboBox getMenu() {
		return menu_deroulant;
	}
	//Variable
	public Boolean getMode() {
		return mode;
	}
	public List<Mot> getListMot(){
		return lmot;
	}
	public Locale getLocale() {
		return locale;
	}
	public ResourceBundle getResourceBundle() {
		return messages;
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
	public void setDicoJetable(Map<String,String> dico) {
		dico_jetable.clear();
		dico_jetable.putAll(dico);
	}
	public void setDicoJetable(ArrayList<String> l1,ArrayList<String> l2) {
		dico_jetable.clear();
		if (l1.size()==l2.size()) {
			for (int i=0;i<l1.size();i++) {
				dico_jetable.put(l1.get(i), l2.get(i));
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
	
	public void setListMot(List<Mot> l) {
		lmot.clear();
		lmot.addAll(l);
	}
	
	public void setLocale(Locale l) {
		locale = l;
	}
	
	public void setResourceBundle(Locale l) {
		messages = ResourceBundle.getBundle("applangue.test.lang.messages", l);
	}
	
/*===============================================================================================================================================*/	
	
	//Main programm
	public static void main(String[] args) {
		//On crée une nouvelle instance de notre FenetreTexte
		FenetreSaisie fenetre = new FenetreSaisie();
		fenetre.setVisible(true);//On la rend visible
	}
	
}
