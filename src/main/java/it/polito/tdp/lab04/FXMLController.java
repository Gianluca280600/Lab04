package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private List<Corso> corsi;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private Button btnComplet;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtArea.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    
    	try {

    		int mat=Integer.parseInt(txtMatricola.getText());
			if (txtMatricola == null) {
				txtArea.setText("Inserire matricola");
				return;
			}
			Studente studente=model.getNMStudenti(mat);

			List<Corso> corsi= model.getCorsiFromStudente(studente);

			StringBuilder sb = new StringBuilder();

			for (Corso c : corsi) {

				sb.append(String.format("%-10s ", c.getCodins()));
				sb.append(String.format("%-20s ", c.getCrediti()));
				sb.append(String.format("%-20s ", c.getNome()));
				sb.append(String.format("%-10s ", c.getPd()));
				sb.append("\n");
			}

			txtArea.appendText(sb.toString());

		} catch (RuntimeException e) {
			txtArea.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	txtArea.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    
    	try {

			Corso corso = boxCorsi.getValue();
			if (corso == null) {
				txtArea.setText("Selezionare un corso.");
				return;
			}

			List<Studente> studenti = model.getStudentiIscrittiAlCorso(corso);

			StringBuilder sb = new StringBuilder();

			for (Studente studente : studenti) {

				sb.append(String.format("%-10s ", studente.getMatricola()));
				sb.append(String.format("%-20s ", studente.getCognome()));
				sb.append(String.format("%-20s ", studente.getNome()));
				sb.append(String.format("%-10s ", studente.getCds()));
				sb.append("\n");
			}

			txtArea.appendText(sb.toString());

		} catch (RuntimeException e) {
			txtArea.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    }

    @FXML
    void doComplet(ActionEvent event) {	
    	txtArea.clear();
		txtNome.clear();
		txtCognome.clear();
    	try {
	    	int mat=Integer.parseInt(txtMatricola.getText());
	    	Studente st= model.getNMStudenti(mat);
	    	if (st == null) {
				txtArea.appendText("Nessun risultato: matricola inesistente");
				return;
			}
	    	txtNome.setText(st.getNome());
	    	txtCognome.setText(st.getCognome());
    	}catch(NumberFormatException e) {
			txtArea.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtArea.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    		
    	

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtArea.clear();
		txtNome.clear();
		txtCognome.clear();
    	try {
	    	int mat=Integer.parseInt(txtMatricola.getText());
	    	Studente st= model.getNMStudenti(mat);
	    	Corso corso = boxCorsi.getValue();
	    	
	    	if (st == null) {
				txtArea.appendText("Nessun risultato: matricola inesistente");
				return;
			}
	    	if (corso == null) {
				txtArea.setText("Selezionare un corso.");
				return;
			}
	    	
	    	txtNome.setText(st.getNome());
	    	txtCognome.setText(st.getCognome());
	    	
	    	if (model.IsIscritto(st, corso)) {
				txtArea.appendText("Studente già iscritto a questo corso");
				return;
			}
	    	
	    	if (!model.inscriviStudenteACorso(st, corso)) {
				txtArea.appendText("Errore durante l'iscrizione al corso");
				return;
			} else {
				txtArea.appendText("Studente iscritto al corso!");
			}
	    	
    	}catch(NumberFormatException e) {
			txtArea.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtArea.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    		

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtArea.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	boxCorsi.getSelectionModel().clearSelection();
    	
    }

    
    private void setComboItems() {
   
    	corsi = model.getTuttiICorsi();
    	boxCorsi.getItems().addAll(corsi);
    }

    @FXML
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnComplet != null : "fx:id=\"btnComplet\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	setComboItems();
    }
}
