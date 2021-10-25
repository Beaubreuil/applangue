package applangue;

import java.awt.Desktop;
import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.AbstractAction;

public class GetAction2 extends GetAction {
	private FenetreSaisie fenetre;
	
	public GetAction2(FenetreSaisie fenetre, String texte){
		super(fenetre,texte);		
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent e) { 
		
		//Get the file and verify the extension
        File myFile = FileSelect();
        
          if (myFile==null) {
        	  return;
          }
          else {
          //lire le fichier excel
          FileInputStream fis = null;
          try {
			fis = new FileInputStream(myFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

          // Finds the workbook instance for XLSX file
          XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook (fis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
         
          // Return first sheet from the XLSX workbook
          XSSFSheet mySheet = myWorkBook.getSheetAt(0);
         
          // Get iterator to all the rows in current sheet
          Iterator<Row> rowIterator = mySheet.iterator();
          Row row = rowIterator.next();
          
          List<Mot> lmot = new ArrayList<Mot>();
          List<String> lcate = new ArrayList<String>();
          Set<String> scate = new HashSet<String>();
          // Traversing over each row of XLSX file
          while (rowIterator.hasNext()) {
              
              //pour passer la premier ligne
              row = rowIterator.next();

              // For each row, iterate through each columns
              Iterator<Cell> cellIterator = row.cellIterator();
              
              //compteur et liste créer pour se rappeler du mot d'avant
              int compteur = 0;
              List<String> couple = new ArrayList<String>();
              while (cellIterator.hasNext()) {

                  Cell cell = cellIterator.next();
                  compteur++;
                  switch (cell.getCellType()) {
                  case Cell.CELL_TYPE_STRING:
                      //System.out.print(cell.getStringCellValue() + "\t");
                      couple.add(cell.getStringCellValue());
                      
                      //Si on a 2 mot dans la liste on les ajoute
                	  if (compteur == 2){
                		  fenetre.getDico_esp_fr().put(couple.get(0),couple.get(1));
                		  fenetre.getDico_fr_esp().put(couple.get(1),couple.get(0));
                	  }
                	  if (compteur==3) {
                		  lmot.add(new Mot(couple.get(0),couple.get(1),couple.get(2)));
                		  scate.add(couple.get(2));
                	  }
                
                      break;
                  default :
               
                  }
              }
          }
          
          //liste mot
          fenetre.setListMot(lmot);
          //fenetre.getMenu().removeAllItems();
          scate.forEach((mot)->{
        	  fenetre.getMenu().addItem(mot);
          });
          fenetre.getMenu().addItem("All");
          fenetre.getMenu().addItem("Recent");
          
          //modifier score pour mettre le total
          MajScore();
          
          //Clone the dictionnarys into temporary ones
          fenetre.getDicoTmp().putAll(fenetre.getDico_esp_fr());
          fenetre.getDicoTmp2().putAll(fenetre.getDico_fr_esp());
          fenetre.setDicoJetable(fenetre.getDicoTmp());
          
          //We put the button Valider visible
          fenetre.getButton().setVisible(true);
          
          //We pick the first random word
          fenetre.setLabel(pick_random(fenetre));
          
  		  //We display the message of failure visible and we set invible this button
          fenetre.getMessageLabel().setVisible(true);
          fenetre.getButtonExcel().setVisible(false);
          fenetre.getScore().setVisible(true);
          fenetre.getButtonSwitch().setVisible(true);
          fenetre.getMenu().setVisible(true);
        
       }
	} 
	
	
	private File FileSelect() {
		/*JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        //Check the extension of file
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          System.out.println(selectedFile.getName());
          return selectedFile;
        }
        else {
        	System.out.print("File could not open");
        	return null;
        }*/
		return new File("I:\\ressource espagnol\\mot espagnol.xlsx");
	}
	
}
