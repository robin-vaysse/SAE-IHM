package application.controller;

import java.io.IOException;
import java.net.URL;
import application.model.JeuModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JeuController {
	
		private JeuModel model;
	    
	    public void initialize() {
	    	model = new JeuModel();
	    	//init de l'interface
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
	    private void cliquerBoutonMenu(ActionEvent event) {

	    }

	    @FXML
	    private void cliquerBoutonSauvegarder(ActionEvent event) {

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
