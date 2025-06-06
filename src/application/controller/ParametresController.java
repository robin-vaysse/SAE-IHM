package application.controller;

import java.net.URL;
import java.util.ResourceBundle;
import application.JeuDuQuinzeMain;
import application.manager.ThemeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import application.manager.ParametresManager;

/**
 * Contrôleur pour l'interface des paramètres du jeu.
 * Gère les interactions avec les éléments de personnalisation (thème, couleurs, formes).
 * 
 * @author Votre nom
 * @version 1.0
 */
public class ParametresController implements Initializable {

    // Mémorisation de la fenêtre d'origine pour la navigation
    private String origine;
    
    // Éléments de l'interface graphique
    @FXML
    private ComboBox<String> comboBoxTheme;
    
    @FXML
    private ComboBox<String> comboBoxFormeCase;
    
    @FXML
    private ComboBox<String> comboBoxCouleurCase;

    /**
     * Initialise les éléments de l'interface au chargement de la vue.
     * Configure les options disponibles et les valeurs par défaut.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ParametresManager gestionnaire = ParametresManager.getInstance();
        
        // Configuration du sélecteur de thème
        if (comboBoxTheme != null) {
            comboBoxTheme.getItems().addAll("Clair", "Sombre");
            comboBoxTheme.setValue(ThemeManager.getCurrentTheme());
        }
        
        // Configuration du sélecteur de couleur
        if (comboBoxCouleurCase != null) {
            comboBoxCouleurCase.getItems().addAll("Classique", "Pastel", "Vif");
            comboBoxCouleurCase.setValue(gestionnaire.getCouleurActuelle());
        }
        
        // Configuration du sélecteur de forme
        if (comboBoxFormeCase != null) {
            comboBoxFormeCase.getItems().addAll("Carré", "Carré arrondi", "Rond");
            comboBoxFormeCase.setValue(gestionnaire.getFormeActuelle());
        }
    }

    /**
     * Définit la fenêtre d'origine pour la navigation de retour.
     * @param origine Le nom de la fenêtre d'origine
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    // ========== GESTION DES ÉVÉNEMENTS ==========

    /**
     * Gère le changement de thème via la liste déroulante.
     */
    @FXML
    private void clicComboBoxTheme() {
        if (comboBoxTheme.getValue() != null) {
            String themeSelectionne = comboBoxTheme.getValue();
            System.out.println("Thème sélectionné : " + themeSelectionne);
            
            // Application du nouveau thème
            if (ThemeManager.isValidTheme(themeSelectionne)) {
                System.out.println("Application du thème : " + themeSelectionne);
                ThemeManager.changeTheme(themeSelectionne);
                
                // Mise à jour de la scène actuelle
                ThemeManager.applyThemeOnSceneActivation(
                    JeuDuQuinzeMain.getSceneParametres()
                );
            }
        }
    }
    
    /**
     * Gère le changement de couleur des cases.
     * @param event L'événement de sélection
     */
    @FXML
    private void clicComboBoxCouleurCase(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        String couleurSelectionnee = comboBox.getValue();
        
        if (couleurSelectionnee != null) {
            System.out.println("Couleur de cases sélectionnée : " + couleurSelectionnee);
            ParametresManager.getInstance().changerCouleur(couleurSelectionnee);
            System.out.println("Couleur appliquée à toutes les scènes");
        }
    }

    /**
     * Gère le changement de forme des cases.
     * @param event L'événement de sélection
     */
    @FXML
    private void clicComboBoxFormeCase(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        String formeSelectionnee = comboBox.getValue();
        
        if (formeSelectionnee != null) {
            System.out.println("Forme de cases sélectionnée : " + formeSelectionnee);
            ParametresManager.getInstance().changerForme(formeSelectionnee);
            System.out.println("Forme appliquée à toutes les scènes");
        }
    }

    /**
     * Bouton alternatif pour changer le thème (si nécessaire).
     * @param event L'événement du bouton
     */
    @FXML
    private void changerTheme(ActionEvent event) {
        String themeSelectionne = comboBoxTheme.getValue();
        if (themeSelectionne != null && ThemeManager.isValidTheme(themeSelectionne)) {
            System.out.println("Changement de thème vers : " + themeSelectionne);
            ThemeManager.changeTheme(themeSelectionne);
            
            // Application du thème sur la scène actuelle
            ThemeManager.applyThemeOnSceneActivation(
                JeuDuQuinzeMain.getSceneParametres()
            );
            
            System.out.println("Thème changé avec succès vers : " + themeSelectionne);
        } else {
            System.err.println("Thème invalide sélectionné : " + themeSelectionne);
        }
    }

    // ========== NAVIGATION ==========

    /**
     * Gère le retour à la fenêtre précédente (bouton flèche).
     * @param event L'événement du bouton
     */
    @FXML
    private void clicBoutonRetourArriere(ActionEvent event) {
        naviguerRetour();
    }

    /**
     * Gère le retour à la fenêtre précédente (bouton texte).
     * @param event L'événement du bouton
     */
    @FXML
    private void cliquerBoutonRetour(ActionEvent event) {
        naviguerRetour();
    }
    
    /**
     * Méthode utilitaire pour la navigation de retour.
     */
    private void naviguerRetour() {
        if (origine == null || origine.isEmpty()) {
            JeuDuQuinzeMain.activerFenetreMenu();
            return;
        }
        
        switch (origine) {
            case "pause":
                JeuDuQuinzeMain.activerFenetreJeu();
                break;
            case "mode":
                JeuDuQuinzeMain.activerFenetreMode();
                break;
            default:
                JeuDuQuinzeMain.activerFenetreMenu();
                break;
        }
    }

    // ========== MÉTHODES POUR LA SAUVEGARDE ==========

    /**
     * @return La couleur actuellement sélectionnée
     */
    public String getCurrentCaseColor() {
        return ParametresManager.getInstance().getCouleurActuelle();
    }

    /**
     * @return La forme actuellement sélectionnée
     */
    public String getCurrentCaseShape() {
        return ParametresManager.getInstance().getFormeActuelle();
    }

    /**
     * Définit la couleur des cases (pour le chargement).
     * @param couleur La couleur à appliquer
     */
    public void setCurrentCaseColor(String couleur) {
        ParametresManager.getInstance().changerCouleur(couleur);
        if (comboBoxCouleurCase != null) {
            comboBoxCouleurCase.setValue(couleur);
        }
    }

    /**
     * Définit la forme des cases (pour le chargement).
     * @param forme La forme à appliquer
     */
    public void setCurrentCaseShape(String forme) {
        ParametresManager.getInstance().changerForme(forme);
        if (comboBoxFormeCase != null) {
            comboBoxFormeCase.setValue(forme);
        }
    }
}