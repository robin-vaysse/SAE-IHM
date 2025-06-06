
package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.JeuDuQuinzeMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Commencer à jouer".
     * Change la scène pour afficher la vue du jeu.
     */
    @FXML
    private void cliquerCommencerAJouer(ActionEvent event) {
        JeuDuQuinzeMain.activerFenetreMode();
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Comment jouer".
     * Change la scène pour afficher la vue des instructions.
     */
    @FXML
    private void cliquerBoutonCommentJouer(ActionEvent event) {
    	JeuDuQuinzeMain.activerFenetreExplication();
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton "Plein écran".
     * Active ou désactive le mode plein écran.
     */
    
// Fonctionnalité en moins, voir pour le rajouter 
//    @FXML
//    private void cliquerBoutonPleinEcran(ActionEvent event) {
//        JeuDuQuinzeMain.activerFenetreMenu().setFullScreen(!JeuDuQuinzeMain.activerFenetreMenu().isFullScreen());
//    }
}