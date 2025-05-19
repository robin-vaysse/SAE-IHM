
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.JeuDuQuinzeMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PauseController implements Initializable {

    @FXML
    private Button boutonContinuerLaPartie;

    @FXML
    private Button boutonSauvegarder;

    @FXML
    private Button boutonParamètre;

    @FXML
    private Button boutonNouvellePartie;

    @FXML
    private Button boutonCommentJouer;

    @FXML
    private Button boutonQuitter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void cliquerContinuerLaPartie(ActionEvent event) {

    }

    @FXML
    private void cliquerSauvegarder(ActionEvent event) {

    }

    @FXML
    private void cliquerBoutonParametre(ActionEvent event) {
    	JeuDuQuinzeMain.activerFenetreParametres();
    }

    @FXML
    private void cliquerNouvellePartie(ActionEvent event) {
    	JeuDuQuinzeMain.activerFenetreMode();
    }

    @FXML
    private void cliquerCommentJouer(ActionEvent event) {

    }

    @FXML
    private void cliquerBoutonQuitter(ActionEvent event) {

    }
}
