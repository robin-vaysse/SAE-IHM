package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class SelectionModeJeuController implements Initializable {

    @FXML
    private Button boutonHumain_Humain;

    @FXML
    private Button boutonParametre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisation du contrôleur
    }
    

    @FXML
    private void clicBoutonHumain_Humain(ActionEvent event) {
        // Dialogue pour le premier joueur
        TextInputDialog dialogJoueur1 = new TextInputDialog("Joueur 1");
        dialogJoueur1.setTitle("Nom du Joueur 1");
        dialogJoueur1.setHeaderText("Saisie du nom du premier joueur");
        dialogJoueur1.setContentText("Nom du Joueur 1 :");

        Optional<String> nomJoueur1 = dialogJoueur1.showAndWait();
        
        // Vérification de la saisie du premier joueur
        if (nomJoueur1.isPresent() && !nomJoueur1.get().trim().isEmpty()) {
            // Dialogue pour le second joueur
            TextInputDialog dialogJoueur2 = new TextInputDialog("Joueur 2");
            dialogJoueur2.setTitle("Nom du Joueur 2");
            dialogJoueur2.setHeaderText("Saisie du nom du second joueur");
            dialogJoueur2.setContentText("Nom du Joueur 2 :");

            Optional<String> nomJoueur2 = dialogJoueur2.showAndWait();
            
            // Lancement du jeu
            if (nomJoueur2.isPresent() && !nomJoueur2.get().trim().isEmpty()) {
                JeuDuQuinzeMain.activerFenetreJeu(nomJoueur1.get().trim(), nomJoueur2.get().trim());
            }
        }
    }

    // Bouton paramètres
    @FXML
    private void cliquerParametre(ActionEvent event) {
        JeuDuQuinzeMain.activerFenetreParametres("mode");
    }
    
    @FXML
    private void clicBoutonRetourArriere(ActionEvent event) {
        JeuDuQuinzeMain.activerFenetreMenu();
    }   
    // Voir pour le bouton Robot vs Humain mais pas implémenté pour l'instant
}