package application.manager;

import application.model.JeuModel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Gestionnaire de sauvegarde/chargement des parties
 * Version simplifiée utilisant la sérialisation Java native
 * 
 * Cette classe permet de :
 * - Sauvegarder l'état actuel d'une partie dans un fichier
 * - Charger une partie précédemment sauvegardée
 * - Gérer les conflits de sauvegarde existante
 * - Proposer à l'utilisateur de continuer une partie en cours
 */
public class SaveManager {
    
	// Chemin du fichier de sauvegarde : construit automatiquement dans le dossier personnel de l'utilisateur
    private static final String SAVE_FILE = System.getProperty("user.home") + "/jeuduxinze_save.dat";
    
    /**
     * Classe pour représenter une partie sauvegardée
     * Contient tous les attributs nécessaires pour restaurer l'état exact du jeu
     */
    public static class SavedGame implements Serializable {
    	
    	// Numéro de version pour la compatibilité des sauvegardes
        // Si cette valeur change, les anciennes sauvegardes ne pourront plus être chargées
    	// Donc changez-la uniquement si vous modifiez la structure de la classe SavedGame
    	// Sinon laissez comme ca pour éviter les bugs svp
    	
        private static final long serialVersionUID = 1L;
        
        // Scores joueurs
        public int scoreJoueur1, scoreJoueur2, pionJoueur1, pionJoueur2;
        
        // Compteurs et valeurs de jeu
        public int compteurJoueur1, compteurJoueur2, valeurPrecedente, pionADeplacer;
        
        // États du jeu
        public boolean tourJoueur1, jeuTermine, modeDeplacement, boutonsCliquer;
        
        // Tableaux pour suivre les boutons cliqués par chaque joueur
        public boolean[] boutonsCliquesJoueur1 = new boolean[9];
        public boolean[] boutonsCliquesJoueur2 = new boolean[9];
        
        // Noms des joueurs
        public String nomJoueur1, nomJoueur2;
        
        // Date de la sauvegarde
        public long timestamp = System.currentTimeMillis();
        
        // Constructeur vide
        public SavedGame() {} 
        
        /**
         * Constructeur principal qui capture l'état du modèle de jeu
         * @param model Le modèle de jeu actuel
         * @param nom1 Nom du premier joueur
         * @param nom2 Nom du second joueur
         */
        public SavedGame(JeuModel model, String nom1, String nom2) {
            // Utilisation de la réflexion pour accéder aux champs privés du modèle
            try {
                var fields = model.getClass().getDeclaredFields();
                // Parcours de tous les champs de la classe JeuModel
                for (var field : fields) {
                    field.setAccessible(true); // Permet l'accès aux champs privés
                    processFieldForSaving(field, model); // Traite chaque champ individuellement
                }
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la sauvegarde", e);
            }
            nomJoueur1 = nom1;
            nomJoueur2 = nom2;
        }
        
        /**
         * Traite un champ spécifique pour la sauvegarde
         * Copie la valeur du champ du modèle vers cette instance de sauvegarde
         */
        private void processFieldForSaving(java.lang.reflect.Field field, JeuModel model) throws Exception {
            String fieldName = field.getName();
            
            // Scores
            if ("scoreJoueur1".equals(fieldName)) {
                scoreJoueur1 = field.getInt(model);
                return;
            }
            if ("scoreJoueur2".equals(fieldName)) {
                scoreJoueur2 = field.getInt(model);
                return;
            }
            // Pions
            if ("pionJoueur1".equals(fieldName)) {
                pionJoueur1 = field.getInt(model);
                return;
            }
            if ("pionJoueur2".equals(fieldName)) {
                pionJoueur2 = field.getInt(model);
                return;
            }
            // Compteurs
            if ("compteurJoueur1".equals(fieldName)) {
                compteurJoueur1 = field.getInt(model);
                return;
            }
            if ("compteurJoueur2".equals(fieldName)) {
                compteurJoueur2 = field.getInt(model);
                return;
            }
            // Valeurs de jeu
            if ("valeurPrecedente".equals(fieldName)) {
                valeurPrecedente = field.getInt(model);
                return;
            }
            if ("pionADeplacer".equals(fieldName)) {
                pionADeplacer = field.getInt(model);
                return;
            }
            // Traitement des états booléens
            if ("tourJoueur1".equals(fieldName)) {
                tourJoueur1 = field.getBoolean(model);
                return;
            }
            if ("jeuTermine".equals(fieldName)) {
                jeuTermine = field.getBoolean(model);
                return;
            }
            if ("modeDeplacement".equals(fieldName)) {
                modeDeplacement = field.getBoolean(model);
                return;
            }
            if ("boutonsCliquer".equals(fieldName)) {
                boutonsCliquer = field.getBoolean(model);
                return;
            }
            // Traitement des tableaux de boutons cliqués (copie nécessaire)
            if ("boutonsCliquesJoueur1".equals(fieldName)) {
                boolean[] orig1 = (boolean[]) field.get(model);
                boutonsCliquesJoueur1 = Arrays.copyOf(orig1, orig1.length);
                return;
            }
            if ("boutonsCliquesJoueur2".equals(fieldName)) {
                boolean[] orig2 = (boolean[]) field.get(model);
                boutonsCliquesJoueur2 = Arrays.copyOf(orig2, orig2.length);
                return;
            }
        }
        
        /**
         * Restaure l'état sauvegardé dans le modèle de jeu
         * @param model Le modèle de jeu à modifier
         */
        public void restoreToModel(JeuModel model) {
            try {
                var fields = model.getClass().getDeclaredFields();
                // Parcours de tous les champs pour restaurer leurs valeurs
                for (var field : fields) {
                    field.setAccessible(true);
                    processFieldForRestoring(field, model);
                }
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors du chargement", e);
            }
        }
        
        /**
         * Traite un champ spécifique pour la restauration
         * Copie la valeur sauvegardée vers le champ correspondant du modèle
         */
        private void processFieldForRestoring(java.lang.reflect.Field field, JeuModel model) throws Exception {
            String fieldName = field.getName();
            
            // Restauration des scores
            if ("scoreJoueur1".equals(fieldName)) {
                field.setInt(model, scoreJoueur1);
                return;
            }
            if ("scoreJoueur2".equals(fieldName)) {
                field.setInt(model, scoreJoueur2);
                return;
            }
            // Restauration des pions
            if ("pionJoueur1".equals(fieldName)) {
                field.setInt(model, pionJoueur1);
                return;
            }
            if ("pionJoueur2".equals(fieldName)) {
                field.setInt(model, pionJoueur2);
                return;
            }
            // Restauration des compteurs
            if ("compteurJoueur1".equals(fieldName)) {
                field.setInt(model, compteurJoueur1);
                return;
            }
            if ("compteurJoueur2".equals(fieldName)) {
                field.setInt(model, compteurJoueur2);
                return;
            }
            // Restauration des valeurs de jeu
            if ("valeurPrecedente".equals(fieldName)) {
                field.setInt(model, valeurPrecedente);
                return;
            }
            if ("pionADeplacer".equals(fieldName)) {
                field.setInt(model, pionADeplacer);
                return;
            }
            // Restauration des états booléens
            if ("tourJoueur1".equals(fieldName)) {
                field.setBoolean(model, tourJoueur1);
                return;
            }
            if ("jeuTermine".equals(fieldName)) {
                field.setBoolean(model, jeuTermine);
                return;
            }
            if ("modeDeplacement".equals(fieldName)) {
                field.setBoolean(model, modeDeplacement);
                return;
            }
            if ("boutonsCliquer".equals(fieldName)) {
                field.setBoolean(model, boutonsCliquer);
                return;
            }
            // Restauration des tableaux (copie à cause des références partagées)
            if ("boutonsCliquesJoueur1".equals(fieldName)) {
                field.set(model, Arrays.copyOf(boutonsCliquesJoueur1, boutonsCliquesJoueur1.length));
                return;
            }
            if ("boutonsCliquesJoueur2".equals(fieldName)) {
                field.set(model, Arrays.copyOf(boutonsCliquesJoueur2, boutonsCliquesJoueur2.length));
                return;
            }
        }
        
        /**
         * Génère un nom d'affichage pour cette sauvegarde
         * Format : "Joueur1 vs Joueur2 (Date Heure)"
         */
        public String getDisplayName() {
            return nomJoueur1 + " vs " + nomJoueur2 + " (" + 
                   new Date(timestamp).toString().substring(4, 19) + ")";
        }
    }
    
    /**
     * Sauvegarde une partie dans le fichier système
     * @param model Le modèle de jeu à sauvegarder
     * @param nomJoueur1 Nom du premier joueur
     * @param nomJoueur2 Nom du second joueur
     * @return true si la sauvegarde a réussi, false sinon
     */
    public static boolean saveGame(JeuModel model, String nomJoueur1, String nomJoueur2) {
        try {
            // Vérifie s'il existe déjà une sauvegarde
            if (hasSavedGame()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Partie existante");
                alert.setHeaderText("Une partie est déjà sauvegardée");
                alert.setContentText("Voulez-vous la remplacer ?");
                if (alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
                    return false;
                }
            }
            
            SavedGame newSave = new SavedGame(model, nomJoueur1, nomJoueur2);
            
            // Écriture dans le fichier avec sérialisation Java native
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
                oos.writeObject(newSave);
            }
            return true;
            
        } catch (Exception e) {
            // Affichage de l'erreur à l'utilisateur et log pour le débogage
            showError("Erreur lors de la sauvegarde", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Charge la partie sauvegardée depuis le fichier
     * @return L'objet SavedGame chargé, ou null si aucune sauvegarde ou erreur
     */
    public static SavedGame loadGame() {
        try {
            // Vérifie l'existence du fichier de sauvegarde
            if (!Files.exists(Paths.get(SAVE_FILE))) {
                return null;
            }
            
            // Lecture du fichier de sauvegarde et récupération de l'objet SavedGame
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
                return (SavedGame) ois.readObject();
            }
            
        } catch (Exception e) {
            // Gestion des erreurs
            showError("Erreur lors du chargement", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Vérifie si une partie est actuellement sauvegardée
     * @return true si un fichier de sauvegarde existe, false sinon
     */
    public static boolean hasSavedGame() {
        return Files.exists(Paths.get(SAVE_FILE));
    }
    
    /**
     * Supprime la partie sauvegardée du système de fichiers
     * @return true si la suppression a réussi, false sinon
     */
    public static boolean deleteSave() {
        try {
            Files.deleteIfExists(Paths.get(SAVE_FILE));
            return true;
        } catch (Exception e) {
            showError("Erreur lors de la suppression", e.getMessage());
            return false;
        }
    }
    
    /**
     * Propose à l'utilisateur de continuer une partie sauvegardée au démarrage
     * Affiche une boîte de dialogue avec les détails de la partie trouvée
     * @return true si l'utilisateur veut continuer, false sinon
     */
    public static boolean promptContinueGame() {
        // Pas de sauvegarde trouvee
        if (!hasSavedGame()) return false;
        
        // Chargement des informations de la sauvegarde
        SavedGame save = loadGame();
        if (save == null) return false;
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Partie sauvegardée");
        alert.setHeaderText("Une partie a été trouvée");
        alert.setContentText("Voulez-vous continuer la partie de " + save.getDisplayName() + " ?");
        
        var result = alert.showAndWait().orElse(ButtonType.CANCEL);
        
        
        
        // Si l'utilisateur refuse de continuer
        if (result == ButtonType.CANCEL) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Supprimer la sauvegarde");
            deleteAlert.setHeaderText("Que faire de la partie sauvegardée ?");
            deleteAlert.setContentText("Voulez-vous supprimer cette partie ?");
            
            //suppression
            if (deleteAlert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                deleteSave();
            }
            return false;
        }
        
        return result == ButtonType.OK;
    }
    
    /**
     * Affiche un message d'erreur standardisé à l'utilisateur
     * @param title Titre de la boîte de dialogue d'erreur
     * @param message Message détaillé de l'erreur
     */
    private static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }
}