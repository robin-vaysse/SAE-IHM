package application.controller;

import application.JeuDuQuinzeMain;
import application.model.JeuModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class JeuController {
	
		private JeuModel model;
	    
	    public void initialize() {
	    	model = new JeuModel();
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
	    private void cliquerBouton1(ActionEvent event) {
	        // Logique pour le bouton 1
	    }

	    @FXML
	    private void cliquerBouton2(ActionEvent event) {
	        
	    }
	    
	    @FXML
	    private void cliquerBouton3(ActionEvent event) {
	        
	    }
	    
	    @FXML
	    private void cliquerBouton4(ActionEvent event) {
	        
	    }
	    
	    @FXML
	    private void cliquerBouton5(ActionEvent event) {
	        
	    }
	    
	    @FXML
	    private void cliquerBouton6(ActionEvent event) {
	        
	    }
	    
	    @FXML
	    private void cliquerBouton7(ActionEvent event) {
	        
	    }
	    
	    @FXML
	    private void cliquerBouton8(ActionEvent event) {
	        
	    }
	    
	    @FXML
	    private void cliquerBouton9(ActionEvent event) {
	        
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

	    }

	    @FXML
	    private void cliquerBoutonValider(ActionEvent event) {
	
	    }

	    @FXML
	    private void cliquerBoutonDemarrer(ActionEvent event) {

	    }
	    
	    @FXML
	    private void metAJourInterface() {
	    	// Met à jour l'interface graphique
	    }    
	    
}
