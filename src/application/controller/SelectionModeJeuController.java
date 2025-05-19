
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
        System.out.println("Bouton VS cliqué !");
    }

    @FXML
    private void cliquerParametre(ActionEvent event) {
        System.out.println("Bouton Paramètres cliqué !");
    }
}
