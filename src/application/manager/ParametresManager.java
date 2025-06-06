package application.manager;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Gestionnaire de personnalisation de l'interface utilisateur du jeu.
 * Permet de modifier les couleurs et formes des éléments de jeu.
 * Utilise le pattern Singleton pour garantir une configuration unique.
 * 
 * @author Votre nom
 * @version 1.0
 */
public class ParametresManager {
    
    // Instance unique du gestionnaire
    private static ParametresManager instance;
    
    // Paramètres actuels de personnalisation
    private String couleurActuelle = "Classique";
    private String formeActuelle = "Carré arrondi";
    
    /**
     * Constructeur privé pour empêcher l'instanciation directe
     */
    private ParametresManager() {
        // Constructeur vide
    }
    
    /**
     * Récupère l'instance unique du gestionnaire
     * @return L'instance du ParametresManager
     */
    public static ParametresManager getInstance() {
        if (instance == null) {
            instance = new ParametresManager();
        }
        return instance;
    }
    
    /**
     * @return La couleur actuellement sélectionnée
     */
    public String getCouleurActuelle() { 
        return couleurActuelle; 
    }
    
    /**
     * @return La forme actuellement sélectionnée
     */
    public String getFormeActuelle() { 
        return formeActuelle; 
    }
    
    /**
     * Change la couleur des éléments de jeu
     * @param nouvelleCouleur Le nom du thème de couleur à appliquer
     */
    public void changerCouleur(String nouvelleCouleur) {
        this.couleurActuelle = nouvelleCouleur;
        appliquerParametresGlobal();
    }
    
    /**
     * Change la forme des boutons de jeu
     * @param nouvelleForme Le nom de la forme à appliquer
     */
    public void changerForme(String nouvelleForme) {
        this.formeActuelle = nouvelleForme;
        appliquerParametresGlobal();
    }
    
    /**
     * Applique les paramètres actuels à une scène spécifique
     * @param scene La scène à personnaliser
     * 
     * Note: Cette méthode maintient la compatibilité avec l'ancien nom applyToScene()
     */
    public void applyToScene(Scene scene) {
        appliquerParametres(scene);
    }
    
    /**
     * Applique les paramètres actuels à une scène spécifique
     * @param scene La scène à personnaliser
     */
    public void appliquerParametres(Scene scene) {
        if (scene == null || scene.getRoot() == null) {
            return;
        }
        
        Parent racine = scene.getRoot();
        
        // Application des couleurs et formes
        appliquerCouleurs(racine);
        appliquerFormes(racine);
        
        // Force la mise à jour de l'affichage
        racine.applyCss();
    }
    
    /**
     * Applique les paramètres à toutes les scènes du jeu
     */
    private void appliquerParametresGlobal() {
        try {
            // Liste des scènes principales du jeu
            Scene[] scenes = {
                application.JeuDuQuinzeMain.getSceneAccueil(),
                application.JeuDuQuinzeMain.getSceneJeu(),
                application.JeuDuQuinzeMain.getSceneParametres(),
                application.JeuDuQuinzeMain.getSceneMenu(),
                application.JeuDuQuinzeMain.getSceneMode(),
                application.JeuDuQuinzeMain.getSceneExplication()
            };
            
            for (Scene scene : scenes) {
                if (scene != null) {
                    appliquerParametres(scene);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'application des paramètres : " + e.getMessage());
        }
    }
    
    /**
     * Applique le thème de couleur à tous les éléments de la scène
     * @param racine L'élément racine de la scène
     */
    private void appliquerCouleurs(Parent racine) {
        String styleCouleur = obtenirStyleCouleur(couleurActuelle);
        
        if (!styleCouleur.isEmpty()) {
            String styleActuel = racine.getStyle();
            racine.setStyle(styleActuel + styleCouleur);
        }
    }
    
    /**
     * Génère le style CSS pour un thème de couleur donné
     * @param themeCouleur Le nom du thème de couleur
     * @return Le style CSS correspondant
     */
    private String obtenirStyleCouleur(String themeCouleur) {
        switch (themeCouleur) {
            case "Classique":
                return genererStyleClassique();
                
            case "Pastel":
                return genererStylePastel();
                
            case "Vif":
                return genererStyleVif();
                
            default:
                return "";
        }
    }
    
    /**
     * @return Le style CSS pour le thème classique
     */
    private String genererStyleClassique() {
        return "; -fx-couleur-jeu-bouton: #FFD54F" +
               "; -fx-couleur-tour-joueur-rouge: #E57373" +
               "; -fx-couleur-bordure-tour-joueur-rouge: #D32F2F" +
               "; -fx-couleur-tour-joueur-bleu: #64B5F6" +
               "; -fx-couleur-bordure-tour-joueur-bleu: #1976D2" +
               "; -fx-couleur-case-changement: #81C784" +
               "; -fx-couleur-bordure-case-changement: #388E3C" +
               "; -fx-couleur-selection-pour-bouger: #FF8A65";
    }
    
    /**
     * @return Le style CSS pour le thème pastel
     */
    private String genererStylePastel() {
        return "; -fx-couleur-jeu-bouton: #FFE4B5" +
               "; -fx-couleur-tour-joueur-rouge: #FFB6C1" +
               "; -fx-couleur-bordure-tour-joueur-rouge: #FF91A4" +
               "; -fx-couleur-tour-joueur-bleu: #ADD8E6" +
               "; -fx-couleur-bordure-tour-joueur-bleu: #87CEEB" +
               "; -fx-couleur-case-changement: #98FB98" +
               "; -fx-couleur-bordure-case-changement: #90EE90" +
               "; -fx-couleur-selection-pour-bouger: #FFFFE0";
    }
    
    /**
     * @return Le style CSS pour le thème vif
     */
    private String genererStyleVif() {
        return "; -fx-couleur-jeu-bouton: #FF8C00" +
               "; -fx-couleur-tour-joueur-rouge: #DC143C" +
               "; -fx-couleur-bordure-tour-joueur-rouge: #B22222" +
               "; -fx-couleur-tour-joueur-bleu: #0066FF" +
               "; -fx-couleur-bordure-tour-joueur-bleu: #0052CC" +
               "; -fx-couleur-case-changement: #00FF00" +
               "; -fx-couleur-bordure-case-changement: #00CC00" +
               "; -fx-couleur-selection-pour-bouger: #FFFF00";
    }
    
    /**
     * Applique la forme sélectionnée à tous les boutons de la scène
     * @param racine L'élément racine de la scène
     */
    private void appliquerFormes(Parent racine) {
        // Boutons de jeu (grille principale)
        String[] idsBoutonsJeu = {"bouton1", "bouton2", "bouton3", "bouton4", "bouton5", 
                                  "bouton6", "bouton7", "bouton8", "bouton9"};
        
        for (String idBouton : idsBoutonsJeu) {
            Node bouton = racine.lookup("#" + idBouton);
            if (bouton instanceof Button) {
                appliquerFormeBouton((Button) bouton);
            }
        }
        
        // Boutons du menu factice
        String[] classesBoutonsMenu = {".bouton1JeuFacticeMenu", ".bouton2JeuFacticeMenu", 
                                       ".bouton3JeuFacticeMenu", ".bouton4JeuFacticeMenu", 
                                       ".bouton5JeuFacticeMenu", ".bouton6JeuFacticeMenu",
                                       ".bouton7JeuFacticeMenu", ".bouton8JeuFacticeMenu", 
                                       ".bouton9JeuFacticeMenu"};
        
        for (String classeCSS : classesBoutonsMenu) {
            racine.lookupAll(classeCSS).forEach(noeud -> {
                if (noeud instanceof Button) {
                    appliquerFormeBouton((Button) noeud);
                }
            });
        }
        
        // Boutons génériques de jeu
        racine.lookupAll(".game-button-jeu").forEach(noeud -> {
            if (noeud instanceof Button) {
                appliquerFormeBouton((Button) noeud);
            }
        });
    }
    
    /**
     * Applique la forme actuelle à un bouton spécifique
     * @param bouton Le bouton à modifier
     */
    private void appliquerFormeBouton(Button bouton) {
        String styleActuel = bouton.getStyle();
        
        // Nettoie les anciens styles de forme
        styleActuel = nettoyerStyleForme(styleActuel);
        
        // Ajoute le nouveau style de forme
        String nouveauStyle = styleActuel + obtenirStyleForme(formeActuelle);
        
        bouton.setStyle(nouveauStyle);
    }
    
    /**
     * Supprime les anciens styles de forme d'un style CSS
     * @param style Le style CSS à nettoyer
     * @return Le style nettoyé
     */
    private String nettoyerStyleForme(String style) {
        StringBuilder nouveauStyle = new StringBuilder();
        String[] proprietes = style.split(";");
        
        for (String propriete : proprietes) {
            propriete = propriete.trim();
            if (!propriete.isEmpty() && 
                !propriete.contains("-fx-background-radius") && 
                !propriete.contains("-fx-border-radius")) {
                nouveauStyle.append(propriete).append("; ");
            }
        }
        
        return nouveauStyle.toString();
    }
    
    /**
     * Génère le style CSS pour une forme donnée
     * @param forme Le nom de la forme
     * @return Le style CSS correspondant
     */
    private String obtenirStyleForme(String forme) {
        switch (forme) {
            case "Carré":
                return "; -fx-background-radius: 0; -fx-border-radius: 0";
                
            case "Carré arrondi":
                return "; -fx-background-radius: 10; -fx-border-radius: 10";
                
            case "Rond":
                return "; -fx-background-radius: 50%; -fx-border-radius: 50%";
                
            default:
                return "";
        }
    }
}