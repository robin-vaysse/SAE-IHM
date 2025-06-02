
package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.JeuDuQuinzeMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectionModeJeuController implements Initializable {

    @FXML
    private Button boutonHumain_Humain;

    @FXML
    private Button boutonParametre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void clicBoutonHumain_Humain(ActionEvent event) {
		JeuDuQuinzeMain.activerFenetreJeu();
    }

    @FXML
    private void cliquerParametre(ActionEvent event) {
        JeuDuQuinzeMain.activerFenetreParametres("mode");
    }
    
    @FXML
    private void clicBoutonRetourArriere(ActionEvent event) {
    	JeuDuQuinzeMain.activerFenetreMenu();
    }   
}