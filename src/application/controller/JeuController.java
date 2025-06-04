package application.controller;

import java.io.IOException;
import java.net.URL;

import application.JeuDuQuinzeMain;
import application.model.JeuModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JeuController {
	
		private JeuModel model;
	    
	    public void initialize() {
	    	model = new JeuModel();
	    	metAJourInterface();
	    }

	    @FXML
	    private Button bouton1;
	    
	    @FXML
	    private Button bouton2;
	    
	    @FXML
	    private Button bouton3;
	    
	    @FXML
	    private Button bouton4;
	    
	    @FXML
	    private Button bouton5;
	    
	    @FXML
	    private Button bouton6;
	    
	    @FXML
	    private Button bouton7;
	    
	    @FXML
	    private Button bouton8;
	    
	    @FXML
	    private Button bouton9;
	    
	    @FXML
	    private Button boutonMenu;
	    
	    @FXML
	    private Button boutonSauvegarder;
	    
	    @FXML
	    private Button boutonReset;
	    
	    @FXML
	    private Button boutonValider;
	    
	    @FXML
	    private Button boutonDemarrer;
	    
	    @FXML
	    private Label nomJoueur1;
	    
	    @FXML
	    private Label nomJoueur2;
	    
	    @FXML
	    private Label ScoreJoueur1;
	    
	    @FXML
	    private Label ScoreJoueur2;
	    


		
		@FXML
		public void cliquerBouton1(ActionEvent event) {
		    model.ajouterScore(1);
		}
		
		@FXML
		public void cliquerBouton2(ActionEvent event) {
		    model.ajouterScore(2);
		}
		
		@FXML
		public void cliquerBouton3(ActionEvent event) {
		    model.ajouterScore(3);
		}
		
		@FXML
		public void cliquerBouton4(ActionEvent event) {
		    model.ajouterScore(4);
		}
		
		@FXML
		public void cliquerBouton5(ActionEvent event) {
		    model.ajouterScore(5);
		}
		
		@FXML
		public void cliquerBouton6(ActionEvent event) {
		    model.ajouterScore(6);
		}
		
		@FXML
		public void cliquerBouton7(ActionEvent event) {
		    model.ajouterScore(7);
		}
		
		@FXML
		public void cliquerBouton8(ActionEvent event) {
		    model.ajouterScore(8);
		}
		
		@FXML
		public void cliquerBouton9(ActionEvent event) {
		    model.ajouterScore(9);
		}


	    @FXML
	    private void cliquerBoutonMenu(ActionEvent event) {
			JeuDuQuinzeMain.activerFenetreMenu();
	    }

	    @FXML
	    private void cliquerBoutonSauvegarder(ActionEvent event) {
			//JeuDuQuinzeMain.activerFenetreSauvegarde();
	    }

	    @FXML
	    private void cliquerBoutonReset(ActionEvent event) {
	    	model.reset();
	    	metAJourInterface();
	    }

	    @FXML
	    private void cliquerBoutonValider(ActionEvent event) {
	    	metAJourInterface();
	    	model.valider();
	    }

	    @FXML
	    private void cliquerBoutonDemarrer(ActionEvent event) {

	    }
	    
	    @FXML
	    private void metAJourInterface() {
	        ScoreJoueur1.setText(String.valueOf(model.getScoreJoueur1()));
	        ScoreJoueur2.setText(String.valueOf(model.getScoreJoueur2()));
	    }


	    

	    
	    
	    
	    
}
