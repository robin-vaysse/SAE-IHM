package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.IOException;
import application.manager.ParametresManager;
import application.controller.JeuController;
import application.controller.ParametresController;
import application.manager.ThemeManager;
import application.manager.SaveManager;

/**
 * Application principale du Jeu du Quinze.
 * 
 * Cette classe gère l'interface utilisateur et la navigation entre les différentes
 * fenêtres du jeu (menu principal, jeu, paramètres, etc.).
 * 
 * @author Florian Helg
 * @author Mattieu Liao
 * @author Robin Vaysse
 * @author Noé Rebourg
 */
public class JeuDuQuinzeMain extends Application {

    // Constantes pour les dimensions de la fenêtre
    private static final int LARGEUR_FENETRE = 680;
    private static final int HAUTEUR_FENETRE = 750;

    // Toutes les scènes de l'application
    private static Scene sceneAccueil;
    private static Scene sceneJeu;
    private static Scene sceneParametres;
    private static Scene sceneMenu;
    private static Scene sceneMode;
    private static Scene sceneExplication;

    // Contrôleurs nécessaires pour communiquer avec les interfaces
    private static JeuController jeuController;
    private static ParametresController parametresController;

    // Fenêtre principale de l'application
    private static Stage fenetreMenu;

    /**
     * Point d'entrée de l'application JavaFX.
     * Charge toutes les interfaces FXML et initialise les composants.
     * 
     * @param primaryStage la fenêtre principale fournie par JavaFX
     * @throws IOException si une erreur casse les pieds au moment du chargement des fichiers FXML
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        fenetreMenu = primaryStage;
        
        try {
            // Chargement de toutes les interfaces
            chargerToutesLesScenes();
            
            // Configuration des thèmes et paramètres
            initialiserThemes();
            
            // Configuration de la fenêtre principale
            configurerFenetrePrincipale(primaryStage);
            
            // Vérification s'il y a une partie sauvegardée à reprendre
            verifierPartieSauvegardee();
            
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Charge toutes les interfaces FXML et leurs contrôleurs associés.
     * 
     * @throws IOException si un fichier FXML n'est pas trouvé ou mal formé
     */
    private void chargerToutesLesScenes() throws IOException {
        // Menu principal
        Parent accueilRoot = FXMLLoader.load(getClass().getResource("/application/view/menu.fxml"));
        sceneAccueil = new Scene(accueilRoot, LARGEUR_FENETRE, HAUTEUR_FENETRE);

        // Interface de jeu (avec récupération du contrôleur)
        FXMLLoader jeuLoader = new FXMLLoader(getClass().getResource("/application/view/jeu.fxml"));
        Parent jeuRoot = jeuLoader.load();
        jeuController = jeuLoader.getController();
        sceneJeu = new Scene(jeuRoot, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        
        // Sélection du mode de jeu
        Parent modeRoot = FXMLLoader.load(getClass().getResource("/application/view/selectionModeJeu.fxml"));
        sceneMode = new Scene(modeRoot, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        
        // Menu pause
        Parent pauseRoot = FXMLLoader.load(getClass().getResource("/application/view/pause.fxml"));
        sceneMenu = new Scene(pauseRoot, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        
        // Paramètres (avec récupération du contrôleur)
        FXMLLoader parametresLoader = new FXMLLoader(getClass().getResource("/application/view/parametrePartie.fxml"));
        Parent parametresRoot = parametresLoader.load();
        parametresController = parametresLoader.getController();
        sceneParametres = new Scene(parametresRoot, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        
        // Règles du jeu
        Parent explicationRoot = FXMLLoader.load(getClass().getResource("/application/view/commentJouer.fxml"));
        sceneExplication = new Scene(explicationRoot, LARGEUR_FENETRE, HAUTEUR_FENETRE);
    }

    /**
     * Initialise le système de thèmes pour toutes les scènes.
     */
    private void initialiserThemes() {
        ThemeManager.initializeWithAllScenes();
    }

    /**
     * Configure la fenêtre principale avec son titre et sa scène d'accueil.
     * 
     * @param primaryStage la fenêtre à configurer
     */
    private void configurerFenetrePrincipale(Stage primaryStage) {
        primaryStage.setTitle("Jeu du Quinze");
        primaryStage.setScene(sceneAccueil);
        primaryStage.setWidth(LARGEUR_FENETRE);
        primaryStage.setHeight(HAUTEUR_FENETRE);
        primaryStage.show();
    }

    /**
     * Vérifie s'il existe une partie sauvegardée et propose de la reprendre.
     */
    private void verifierPartieSauvegardee() {
        if (SaveManager.promptContinueGame()) {
            chargerPartieSauvegardee();
        }
    }

    /**
     * Applique les paramètres de personnalisation (thème, etc.) à une scène.
     * Cette méthode est appelée à chaque changement de scène.
     * 
     * @param scene la scène à personnaliser
     */
    private static void appliquerParametres(Scene scene) {
        if (scene != null) {
            ParametresManager.getInstance().applyToScene(scene);
        }
    }

    // ========== MÉTHODES DE NAVIGATION ==========

    /**
     * Passe à l'interface de jeu sans noms de joueurs spécifiés.
     */
    public static void activerFenetreJeu() {
        changerScene(sceneJeu);
    }

    /**
     * Passe à l'interface de jeu avec les noms des joueurs.
     * 
     * @param nomJoueur1 nom du premier joueur
     * @param nomJoueur2 nom du second joueur
     */
    public static void activerFenetreJeu(String nomJoueur1, String nomJoueur2) {
        if (jeuController != null) {
            jeuController.setNomsJoueurs(nomJoueur1, nomJoueur2);
        }
        changerScene(sceneJeu);
    }

    /**
     * Passe à l'interface des paramètres.
     * 
     * @param origine indique d'où vient l'utilisateur (pour le bouton retour)
     */
    public static void activerFenetreParametres(String origine) {
        if (parametresController != null) {
            parametresController.setOrigine(origine);
        }
        changerScene(sceneParametres);
    }

    /**
     * Passe à l'interface de sélection du mode de jeu.
     */
    public static void activerFenetreMode() {
        changerScene(sceneMode);
    }

    /**
     * Passe au menu de pause.
     */
    public static void activerFenetreMenu() {
        changerScene(sceneMenu);
    }

    /**
     * Retourne au menu principal.
     */
    public static void activerPrincipale() {
        changerScene(sceneAccueil);
    }

    /**
     * Passe à l'interface d'explication des règles.
     */
    public static void activerFenetreExplication() {
        changerScene(sceneExplication);
    }

    /**
     * Méthode utilitaire pour changer de scène.
     * Applique automatiquement le thème et les paramètres.
     * 
     * @param nouvelleScene la scène vers laquelle naviguer
     */
    private static void changerScene(Scene nouvelleScene) {
        ThemeManager.applyThemeOnSceneActivation(nouvelleScene);
        appliquerParametres(nouvelleScene);
        fenetreMenu.setScene(nouvelleScene);
    }

    // ========== GESTION DES SAUVEGARDES ==========

    /**
     * Charge et reprend une partie sauvegardée.
     */
    public static void chargerPartieSauvegardee() {
        if (SaveManager.hasSavedGame()) {
            SaveManager.SavedGame save = SaveManager.loadGame();
            if (save != null) {
                activerFenetreJeu(save.nomJoueur1, save.nomJoueur2);
                jeuController.chargerPartie();
            }
        }
    }

    // ========== GETTERS POUR LE THEMEMANAGER ==========
    // Ces méthodes sont nécessaires pour que le ThemeManager puisse accéder aux scènes

    public static Scene getSceneAccueil() { 
        return sceneAccueil; 
    }

    public static Scene getSceneJeu() { 
        return sceneJeu; 
    }

    public static Scene getSceneParametres() { 
        return sceneParametres; 
    }

    public static Scene getSceneMenu() { 
        return sceneMenu; 
    }

    public static Scene getSceneMode() { 
        return sceneMode; 
    }

    public static Scene getSceneExplication() { 
        return sceneExplication; 
    }

    /**
     * Point d'entrée du programme.
     * 
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        launch(args);
    }
}