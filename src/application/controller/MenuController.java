package application.controller;

import java.io.IOException;
import java.net.URL;
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

public class MenuController {
    @FXML
    private Button btnCommencerJouer;
    
    @FXML
    private Button btnCommentJouer;
    
    @FXML
    private Button btnPleinEcran;

    @FXML
    private void cliquerCommencerAJouer(ActionEvent event) {
        // Logique pour commencer à jouer
    }

    @FXML
    private void cliquerBoutonCommentJouer(ActionEvent event) {
      
    }

    @FXML
    private void cliquerBoutonPleinEcran(ActionEvent event) {
        
    }
    
    /*private void chargerVue(String fxmlFile) {
        try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
			Parent root = loader.load();
			Stage stage = (Stage) btnCommencerJouer.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    } 
    
    Le copilot me génère ça mais je sais pas si c'est bon donc à verifier
    */
}