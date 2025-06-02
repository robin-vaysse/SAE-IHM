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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ParametresController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTheme;

    @FXML
    private ComboBox<String> comboBoxFormeElements;    

    private String origine; // pour savoir d'où on vient

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisation des ComboBox
        if (comboBoxTheme != null) {
            comboBoxTheme.getItems().addAll("Classique", "Sombre", "Clair");
            comboBoxTheme.setValue("Classique");
        }
        
        if (comboBoxFormeElements != null) {
            comboBoxFormeElements.getItems().addAll("Carré", "Cercle", "Triangle");
            comboBoxFormeElements.setValue("Carré");
        }
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    @FXML
    private void changerTheme(ActionEvent event) {
        // Logique pour changer le thème
        String selectedTheme = comboBoxTheme.getValue();
        System.out.println("Thème sélectionné : " + selectedTheme);
    }

    @FXML
    private void changerCouleurElements(ActionEvent event) {
        // Logique pour changer la couleur des éléments
    }

    @FXML
    private void changerImageFond(ActionEvent event) {
        // Logique pour changer l'image de fond
    }

    @FXML
    private void changerFormeElements(ActionEvent event) {
        // Logique pour changer la forme des éléments
        String selectedForme = comboBoxFormeElements.getValue();
        System.out.println("Forme sélectionnée : " + selectedForme);
    }

    @FXML
    private void changerEmplacementStockage(ActionEvent event) {
        // Logique pour changer l'emplacement de stockage
    }

    @FXML
    private void clicComboBoxTheme() {
        if (comboBoxTheme.getValue() != null) {
            String selected = comboBoxTheme.getValue();
            System.out.println("Thème sélectionné : " + selected);
        }
    }
    
    @FXML
	private void clicBoutonRetourArriere(ActionEvent event) {
    	
		if (origine == null || origine.isEmpty()) {
			JeuDuQuinzeMain.activerFenetreMenu();
		}
		
		if ("pause".equals(origine)) {
			JeuDuQuinzeMain.activerFenetreJeu();
		} else if ("mode".equals(origine)) {
			JeuDuQuinzeMain.activerFenetreMode();
		}
    }

    @FXML
    private void cliquerBoutonRetour(ActionEvent event) {
            FXMLLoader loader;
            if ("pause".equals(origine)) {
                JeuDuQuinzeMain.activerFenetreJeu();
            } else if ("mode".equals(origine)) {
                JeuDuQuinzeMain.activerFenetreMode();
            } else {
                JeuDuQuinzeMain.activerFenetreMenu();
            }
    }
}