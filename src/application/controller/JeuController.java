package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import application.manager.SaveManager;
import application.JeuDuQuinzeMain;
import application.model.JeuModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JeuController {
	
	// Variables principales du jeu
	private JeuModel model;
	private Button[] boutons;
	private String nomJoueur1Text = "Joueur 1";
	private String nomJoueur2Text = "Joueur 2";

	// Boutons de jeu
	@FXML
	private Button bouton1;
	@FXML
	private Button bouton2;
	@FXML
	private Button bouton3;
	@FXML
	private Button bouton4;
	@FXML
	private Button bouton5;
	@FXML
	private Button bouton6;
	@FXML
	private Button bouton7;
	@FXML
	private Button bouton8;
	@FXML
	private Button bouton9;
	
	// Boutons de contrôle
	@FXML
	private Button boutonMenu;
	@FXML
	private Button boutonSauvegarder;
	@FXML
	private Button boutonReset;
	@FXML
	private Button boutonValider;
	@FXML
	private Button boutonDemarrer;
	@FXML
	private Button boutonAnnuler;
	
	// Labels et affichage et texte
	@FXML
	private Label nomJoueur1;
	@FXML
	private Label nomJoueur2;
	@FXML
	private Label ScoreJoueur1;
	@FXML
	private Label ScoreJoueur2;
	@FXML
	private Label labelInstructions;

	// Initialisation
	public void initialize() {
		model = new JeuModel();
		
		// tableau de boutons - index 0 inutilisé vu qu'on commence à 1
		boutons = new Button[]{
			null, // index 0 pas utilisé
			bouton1, bouton2, bouton3, bouton4, bouton5, 
			bouton6, bouton7, bouton8, bouton9
		};
		
		mettreAJourAffichage();
	}

	// Noms de joueurs
	public void setNomsJoueurs(String nom1, String nom2) {
		this.nomJoueur1Text = nom1;
		this.nomJoueur2Text = nom2;
		if (nomJoueur1 != null && nomJoueur2 != null) {
			nomJoueur1.setText(nom1);
			nomJoueur2.setText(nom2);
			mettreAJourInstructions();
		}
	}
	
	// Chargement d'une partie sauvegardée avec le SaveManager
	public void chargerPartie() {
		SaveManager.SavedGame save = SaveManager.loadGame();
		if (save != null) {
			save.restoreToModel(model);
			setNomsJoueurs(save.nomJoueur1, save.nomJoueur2);
			mettreAJourAffichage();
		}
	}
    /////////////////////////////////////////////////////////
	////////// GERE LES CLICS SUR LES BOUTONS DE JEU/////////
	/////////////////////////////////////////////////////////
	
	@FXML
	public void cliquerBouton1(ActionEvent event) {
		cliquerBoutonJeu(1);
	}
	
	@FXML
	public void cliquerBouton2(ActionEvent event) {
		cliquerBoutonJeu(2);
	}
	
	@FXML
	public void cliquerBouton3(ActionEvent event) {
		cliquerBoutonJeu(3);
	}
	
	@FXML
	public void cliquerBouton4(ActionEvent event) {
		cliquerBoutonJeu(4);
	}
	
	@FXML
	public void cliquerBouton5(ActionEvent event) {
		cliquerBoutonJeu(5);
	}
	
	@FXML
	public void cliquerBouton6(ActionEvent event) {
		cliquerBoutonJeu(6);
	}
	
	@FXML
	public void cliquerBouton7(ActionEvent event) {
		cliquerBoutonJeu(7);
	}
	
	@FXML
	public void cliquerBouton8(ActionEvent event) {
		cliquerBoutonJeu(8);
	}
	
	@FXML
	public void cliquerBouton9(ActionEvent event) {
		cliquerBoutonJeu(9);
	}

	// GERE LES BOUTONS DE CONTRÔLE

	@FXML
	private void cliquerBoutonMenu(ActionEvent event) {
		JeuDuQuinzeMain.activerFenetreMenu();
	}

	@FXML
	private void cliquerBoutonSauvegarder(ActionEvent event) {
		if (SaveManager.saveGame(model, nomJoueur1Text, nomJoueur2Text)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Sauvegarde");
			alert.setHeaderText("Succès");
			alert.setContentText("Partie sauvegardée avec succès !");
			alert.showAndWait();
		}
	}

	@FXML
	private void cliquerBoutonReset(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de réinitialisation");
		alert.setHeaderText("Attention !");
		alert.setContentText("Vous allez réinitialiser le jeu en cours.\nToutes les données de la partie seront perdues.\n\nÊtes-vous sûr de vouloir continuer ?");        
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(
			javafx.scene.control.ButtonType.YES,
			javafx.scene.control.ButtonType.NO
		);
		
		alert.showAndWait().ifPresent(response -> {
			if (response == javafx.scene.control.ButtonType.YES) {
				model.reset();
				mettreAJourAffichage();
			}
		});
	}

	@FXML
	private void cliquerBoutonValider(ActionEvent event) {
		try {
			if (model.isJeuTermine()) {
				return;
			}
			
			if (model.isModeDeplacement()) {
				if (model.getPionADeplacer() != 0) {
					montrerErreur("Finissez d'abord le déplacement !");
					return;
				}
			} else {
				
				int boutonSelectionne = model.getBoutonSelectionne();
				if (boutonSelectionne != 0) {
					model.setBouttonCliqueJoueur(boutonSelectionne, model.isTourJoueur1());
				}
			}
			
			model.valider();
			mettreAJourAffichage();
			
		} catch (IllegalStateException e) {
			montrerErreur(e.getMessage());
		}
	}

	@FXML
	private void cliquerBoutonDemarrer(ActionEvent event) {

	}
	
	@FXML
	private void cliquerBoutonAnnuler(ActionEvent event) {
		if (model.isJeuTermine()) {
			return;
		}
		
		if (model.isModeDeplacement() && model.getPionADeplacer() != 0) {
			model.annulerSelectionDeplacement();
			mettreAJourAffichage();
		}
	}

	// LOGIQUE DE JEU

	// Traitement d'un clic sur un bouton de jeu
	private void cliquerBoutonJeu(int buttonId) {
		try {
			if (model.isJeuTermine()) {
				return;
			}
			
			model.ajouterScore(buttonId);
			mettreAJourAffichage();
			
		} catch (IllegalStateException e) {
			montrerErreur(e.getMessage());
		}
	}

	// AFFICHAGE ET DE STYLE

	// Mise à jour de l'interface dans la phase 2
	private void mettreAJourAffichage() {
		ScoreJoueur1.setText(String.valueOf(model.getScoreJoueur1()));
		ScoreJoueur2.setText(String.valueOf(model.getScoreJoueur2()));
		
		changerStyleBoutons();
		mettreAJourInstructions();
		
		// Configuration des boutons selon la phase
		if (boutonValider != null) {
			if (model.isModeDeplacement()) {
				boutonValider.setVisible(model.getPionADeplacer() == 0);
				boutonValider.setText("Passer le tour");
			} else {
				boutonValider.setVisible(true);
				boutonValider.setText("Valider");
			}
		}
		
		if (boutonAnnuler != null) {
			boutonAnnuler.setVisible(model.isModeDeplacement() && model.getPionADeplacer() != 0);
		}

		// compteur de déplacements
		if (model.isModeDeplacement()) {
			String compteurInfo = "Déplacements restants - " + 
				nomJoueur1.getText() + ": " + (10 - model.getCompteurJoueur1()) + " | " +
				nomJoueur2.getText() + ": " + (10 - model.getCompteurJoueur2());
			
			if (!model.isCompteurEpuise()) {
				String instructionsActuelles = labelInstructions.getText();
				labelInstructions.setText(instructionsActuelles + "\n" + compteurInfo);
			}
		}

		// Fin de game
		if (model.isJeuTermine()) {
			// Désactive tous les boutons
			for (int i = 1; i <= 9; i++) {
				boutons[i].setDisable(true);
			}
			boutonValider.setDisable(true);
			boutonAnnuler.setDisable(true);
			
			// qui a gagné 
			if (model.getScoreJoueur1() == 15) {
				montrerVictoire(nomJoueur1.getText() + " a gagné !");
			} else if (model.getScoreJoueur2() == 15) {
				montrerVictoire(nomJoueur2.getText() + " a gagné !");
			} else if (model.isCompteurEpuise()) {
				String resultat = model.determinerResultatFinal(nomJoueur1.getText(), nomJoueur2.getText());
				montrerVictoire(resultat);
			}
		} else {
			// activation des boutons après un reset
			for (int i = 1; i <= 9; i++) {
				boutons[i].setDisable(false);
			}
			boutonValider.setDisable(false);
			boutonAnnuler.setDisable(false);
		}
	}

	// Application des styles CSS selon l'état du jeu
	// Changer les cases de déplacement, pas assez ergonomique ?
	
	private void changerStyleBoutons() {
		for (int i = 1; i <= 9; i++) {
			Button bouton = boutons[i];
			resetBoutonStyle(bouton);
			
			if (model.isModeDeplacement()) {
				// Styles pour la phase 2 (déplacement)
				if (model.getPionADeplacer() == i) {
					bouton.getStyleClass().add("selectionne-pour-bouger");
				} else if (model.isBouttonCliquer(i, model.isTourJoueur1())) {
					if (model.isTourJoueur1()) {
						bouton.getStyleClass().add("validated-joueur1");
					} else {
						bouton.getStyleClass().add("validated-joueur2");
					}
				} else if (model.isBouttonCliquer(i, !model.isTourJoueur1())) {
					if (model.isTourJoueur1()) {
						bouton.getStyleClass().add("validated-joueur2");
					} else {
						bouton.getStyleClass().add("validated-joueur1");
					}
				} else if (model.getPionADeplacer() != 0) {
					bouton.getStyleClass().add("disponible-pour-bouger");
				}
			} else {
				// Styles pour la phase 2 (placement)
				if (model.isBouttonCliquer(i, true)) {
					bouton.getStyleClass().add("validated-joueur1");
				} else if (model.isBouttonCliquer(i, false)) {
					bouton.getStyleClass().add("validated-joueur2");
				} else if (model.getBoutonSelectionne() == i) {
					if (model.isTourJoueur1()) {
						bouton.getStyleClass().add("selected-joueur1");
					} else {
						bouton.getStyleClass().add("selected-joueur2");
					}
				}
			}
		}
	}

	// Nettoyage des styles CSS d'un bouton
	private void resetBoutonStyle(Button bouton) {
		bouton.getStyleClass().removeAll("selected-joueur1", "selected-joueur2", 
										"validated-joueur1", "validated-joueur2",
										"selectionne-pour-bouger", "disponible-pour-bouger");
	}

	// Mise à jour du texte d'instructions
	private void mettreAJourInstructions() {
		if (labelInstructions == null) return;
		
		String nomJoueurActuel = model.isTourJoueur1() ? 
			nomJoueur1.getText() : nomJoueur2.getText();
		
		String instructions = "";
		
		if (model.isModeDeplacement()) {
			if (model.getPionADeplacer() != 0) {
				instructions = nomJoueurActuel + " : Choisissez où déplacer votre pion";
			} else {
				instructions = nomJoueurActuel + " : Sélectionnez un pion à déplacer";
			}
		} else {
			instructions = nomJoueurActuel + " : Choisissez une case puis validez";
		}
		
		labelInstructions.setText(instructions);
	}

	// AFFICHAGE DES MESSAGES

	// message d'erreur
	private void montrerErreur(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erreur");
		alert.setHeaderText("Problème");
		alert.setContentText(message);
		alert.showAndWait();
	}

	// fin de partie
	private void montrerVictoire(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Fin de partie");
		alert.setHeaderText("Bravo !");
		alert.setContentText(message + "\nCliquez OK pour revenir au menu.");
		alert.getDialogPane().getScene().getWindow().setOnCloseRequest(e -> {
			e.consume();
		});
		
		alert.showAndWait();
		
		// Retour au menu principal
		JeuDuQuinzeMain.activerPrincipale();
	}
}