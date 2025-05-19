
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


public class ParametresController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTheme;

    @FXML
    private ComboBox<String> comboBoxCouleurElements;

    @FXML
    private ComboBox<String> comboBoxImageFond;

    @FXML
    private ComboBox<String> comboBoxFormeElements;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxTheme.getItems().addAll("Thème sombre", "Thème clair");
        comboBoxTheme.setValue("Thème sombre");

        comboBoxCouleurElements.getItems().addAll("Rouge", "Bleu", "Vert", "Jaune");
        comboBoxCouleurElements.setValue("Rouge");

        comboBoxImageFond.getItems().addAll("Image 1", "Image 2", "Image 3");
        comboBoxImageFond.setValue("Image 1");

        comboBoxFormeElements.getItems().addAll("Carré", "Cercle", "Triangle");
        comboBoxFormeElements.setValue("Carré");
    }

    @FXML
    private void changerTheme(ActionEvent event) {

    }

    @FXML
    private void changerCouleurElements(ActionEvent event) {

    }

    @FXML
    private void changerImageFond(ActionEvent event) {

    }

    @FXML
    private void changerFormeElements(ActionEvent event) {
    	
    }

    @FXML
    private void changerEmplacementStockage(ActionEvent event) {

    }
}
