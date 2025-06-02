package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.IOException;

import application.controller.ParametresController;

/**
 * Classe principale de l'application "Jeu du Quinze".
 * Gère les différentes scènes et la fenêtre principale.
 */
public class JeuDuQuinzeMain extends Application {

    /** Scène principale de l'application */
    private static Scene sceneAccueil;

    /** Scène permettant de gérer le jeu */
    private static Scene sceneJeu;

    /** Scène permettant de gérer la page des paramètres */
    private static Scene sceneParametres;

    /** Scène permettant de gérer la page du menu */
    private static Scene sceneMenu;

    /** Scène permettant de gérer la page du choix du jeu */
    private static Scene sceneMode;
    
    /** Scène permettant de gérer la page d'explication du jeu */
    private static Scene sceneExplication;

    /**
     * Fenêtre principale de l'application.
     * La scène qui lui est associée sera modifiée en fonction des
     * clics de l'utilisateur sur les boutons.
     */
    private static Stage fenetreMenu;

    /**
     * Permet de modifier la scène de la fenêtre principale
     * pour qu'elle devienne celle de jeu.
     */
    public static void activerFenetreJeu() {
        fenetreMenu.setScene(sceneJeu);
    }

    /**
     * Permet de modifier la scène de la fenêtre principale
     * pour qu'elle devienne celle des paramètres.
     */
    public static void activerFenetreParametres(String origine) {
		fenetreMenu.setScene(sceneParametres);
    }

    /**
     * Permet de modifier la scène de la fenêtre principale
     * pour qu'elle devienne celle du mode de jeu.
     */
    public static void activerFenetreMode() {
        fenetreMenu.setScene(sceneMode);
    }

    /**
     * Permet de modifier la scène de la fenêtre principale
     * pour qu'elle devienne celle du menu.
     */
    public static void activerFenetreMenu() {
        fenetreMenu.setScene(sceneMenu);
    }

    /**
     * Permet de modifier la scène de la fenêtre principale
     * pour qu'elle devienne la scène principale, celle qui permet d'accéder au jeu.
     */
    public static void activerPrincipale() {
        fenetreMenu.setScene(sceneAccueil);
    }
    
	public static void activerFenetreExplication() {
		fenetreMenu.setScene(sceneExplication);
	}

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            fenetreMenu = primaryStage;
            
            // Debug: vérification des ressources
            System.out.println("Chargement des ressources FXML...");
            System.out.println("Menu: " + getClass().getResource("/application/view/menu.fxml"));
            System.out.println("Jeu: " + getClass().getResource("/application/view/jeu.fxml"));
            System.out.println("Paramètres: " + getClass().getResource("/application/view/parametrePartie.fxml"));
            System.out.println("Mode: " + getClass().getResource("/application/view/selectionModeJeu.fxml"));
            System.out.println("Pause: " + getClass().getResource("/application/view/pause.fxml"));
            System.out.println("Explication: " + getClass().getResource("/application/view/commentJouer.fxml"));

            /** Chargement de la scène d'accueil */
            Parent accueilRoot = FXMLLoader.load(getClass().getResource("/application/view/menu.fxml"));
            sceneAccueil = new Scene(accueilRoot, 680, 750);

            /** Chargement de la scène de jeu */
            Parent jeuRoot = FXMLLoader.load(getClass().getResource("/application/view/jeu.fxml"));
            sceneJeu = new Scene(jeuRoot, 680, 750);
            
            /** Chargement de la scène de mode de jeu */
            Parent modeRoot = FXMLLoader.load(getClass().getResource("/application/view/selectionModeJeu.fxml"));
            sceneMode = new Scene(modeRoot, 680, 750);
            
            /** Chargement de la scène de menu (pause) */
            Parent pauseRoot = FXMLLoader.load(getClass().getResource("/application/view/pause.fxml"));
            sceneMenu = new Scene(pauseRoot, 680, 750);
            
            /** Chargement de la scène des paramètres */
            Parent parametresRoot = FXMLLoader.load(getClass().getResource("/application/view/parametrePartie.fxml"));
            sceneParametres = new Scene(parametresRoot, 680, 750);
            
            /** Chargement de la scène d'explication */
            Parent explicationRoot = FXMLLoader.load(getClass().getResource("/application/view/commentJouer.fxml"));
            sceneExplication = new Scene(explicationRoot, 680, 750);
            
            /** Configuration de la fenêtre principale */
            primaryStage.setTitle("Jeu du Quinze");
            primaryStage.setScene(sceneAccueil);
            primaryStage.setWidth(680);
            primaryStage.setHeight(750);
            primaryStage.show();

            System.out.println("Application démarrée avec succès!");

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des ressources : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Programme principal.
     * @param args non utilisé
     */
    public static void main(String[] args) {
        launch(args);
    }
}