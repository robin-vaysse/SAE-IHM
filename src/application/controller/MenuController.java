package application.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        chargerVue("/application/view/jeu.fxml"); 
    }

    @FXML
    private void cliquerBoutonCommentJouer(ActionEvent event) {
        chargerVue("/application/view/commentJouer.fxml"); 
    }

    @FXML
    private void cliquerBoutonPleinEcran(ActionEvent event) {
    	//TODO à faire
    }
    
    private void chargerVue(String fxmlFile) {
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
}
