package application.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.JeuDuQuinzeMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

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

	// INITIALISATION

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Pas configuration spéciale est nécessaire pour cette vue-là
	}
	@FXML
    private void cliquerSauvegarder(ActionEvent event) {
        // TODO: Implémenter la sauvegarde mais dispo dans le jeu de base
    }
	// NAVIGATION

	@FXML
	private void cliquerContinuerLaPartie(ActionEvent event) {
		JeuDuQuinzeMain.activerFenetreJeu();
	}

	@FXML
	private void cliquerNouvellePartie(ActionEvent event) {
		JeuDuQuinzeMain.activerFenetreMode();
	}

	@FXML
	private void cliquerBoutonParametre(ActionEvent event) {
		JeuDuQuinzeMain.activerFenetreParametres("pause");
	}

	@FXML
	private void cliquerCommentJouer(ActionEvent event) {
		JeuDuQuinzeMain.activerFenetreExplication();
	}

	@FXML
	private void cliquerBoutonQuitter(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Quitter le jeu");
		alert.setContentText("Êtes-vous sûr de vouloir quitter le jeu ?");

		ButtonType boutonOui = new ButtonType("Oui");
		ButtonType boutonNon = new ButtonType("Non");
		alert.getButtonTypes().setAll(boutonOui, boutonNon);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == boutonOui) {
			System.exit(0);
		}
	}
}