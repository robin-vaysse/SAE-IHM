
package application.manager;

import javafx.scene.Scene;
import java.util.HashSet;
import java.util.Set;

/**
 * Gestionnaire de thèmes pour l'application Jeu du Quinze.
 * Permet de changer le thème de toutes les scènes de façon centralisée.
 * Version améliorée avec gestion d'erreurs et registry des scènes.
 *
 * Cette classe gère deux thèmes principaux : Clair et Sombre.
 * Elle maintient un registre des scènes actives pour pouvoir appliquer
 * les changements de thèmes sur toute l'application.
 */
public class ThemeManager {

    /** Thème actuel de l'application */
    private static String currentTheme = "Clair"; // Thème par défaut

    /** Chemins vers les fichiers CSS */
    private static final String THEME_CLAIR = "/application/view/styles-claire.css";
    private static final String THEME_SOMBRE = "/application/view/styles-sombre.css";

    /**
     * Registry des scènes actives pour éviter les références nulles.
     * Utilise un HashSet pour éviter les doublons.
     */
    private static final Set<Scene> registeredScenes = new HashSet<>();

    /**
     * Enregistre une scène dans le registry pour le changement de thème.
     * Cette méthode permet de tenir à jour la liste des scènes qui doivent
     * recevoir les mises à jour de thème.
     *
     * @param scene La scène à enregistrer (null sera ignoré)
     */
    public static void registerScene(Scene scene) {
        if (scene != null) {
            registeredScenes.add(scene);
            // Applique immédiatement le thème actuel à la nouvelle scène
            // pour maintenir la cohérence visuelle
            applyCurrentTheme(scene);
        }
    }

    /**
     * Enlève l'enregistrement d'une scène du registry.
     * Utile pour la fermeture de fenêtres ou la destruction de scènes.
     * Évite les fuites mémoire et les références obsolètes.
     *
     * @param scene La scène à désenregistrer (null sera ignoré)
     */
    public static void unregisterScene(Scene scene) {
        if (scene != null) {
            registeredScenes.remove(scene);
        }
    }

    /**
     * Applique le thème actuel à une scène donnée.
     * Méthode pratique qui utilise le thème actuellement sélectionné.
     *
     * @param scene La scène à laquelle appliquer le thème
     */
    public static void applyCurrentTheme(Scene scene) {
        applyTheme(scene, currentTheme);
    }

    /**
     * Applique un thème spécifique à une scène.
     * Cette méthode nettoie les anciens styles
     * et applique la nouvelle feuille de style CSS correspondant au thème.
     *
     * @param scene La scène à modifier
     * @param themeName Le nom du thème ("Clair" ou "Sombre")
     */
    public static void applyTheme(Scene scene, String themeName) {
        // Vérifie si la scène est nulle, car une scène nulle ne peut pas recevoir de thème
        if (scene == null) {
            System.err.println("La scène est nulle, impossible d'appliquer le thème");
            return;
        }

        // Valide le nom du thème, utilise le thème par défaut si le nom est invalide
        if (!isValidTheme(themeName)) {
            System.err.println("Thème invalide : " + themeName + ", utilisation du thème par défaut");
            themeName = "Clair";
        }

        // Supprime les feuilles de styles existantes pour éviter les conflits entre thèmes
        scene.getStylesheets().clear();

        // Détermine le fichier CSS à utiliser selon le thème
        String cssPath = "Sombre".equals(themeName) ? THEME_SOMBRE : THEME_CLAIR;

        try {
            // Vérifie l'existence du fichier CSS avant de l'utiliser
            // pour éviter les erreurs de chargement
            if (ThemeManager.class.getResource(cssPath) == null) {
                System.err.println("Fichier CSS introuvable : " + cssPath);
                return;
            }

            // Convertit le chemin de ressource en URL externe utilisable par JavaFX
            String cssResource = ThemeManager.class.getResource(cssPath).toExternalForm();
            scene.getStylesheets().add(cssResource);
            System.out.println("Thème appliqué à la scène : " + themeName + " (" + cssPath + ")");

        } catch (Exception e) {
            // Gère les erreurs de chargement avec un affichage détaillé
            System.err.println("Erreur lors du chargement du thème " + themeName + " : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Change le thème de toutes les scènes de l'application.
     *
     * @param themeName Le nom du nouveau thème
     */
    public static void changeTheme(String themeName) {

        // Valide le thème avant le changement
        if (!isValidTheme(themeName)) {
            System.err.println("Thème invalide : " + themeName);
            return;
        }

        // Sauvegarde l'ancien thème pour le journal
        String oldTheme = currentTheme;
        currentTheme = themeName;
        System.out.println("Changement de thème de " + oldTheme + " vers : " + themeName);

        // Méthode 1 : Applique le thème via le registry
        // Utilise les scènes explicitement enregistrées
        applyThemeToRegisteredScenes(themeName);

        // Méthode 2 : Applique le thème via l'accès direct
        applyThemeToDirectScenes(themeName);

        System.out.println("Thème changé pour toute l'application : " + themeName);
    }

    /**
     * Applique le thème aux scènes enregistrées dans le registry.
     * Cette méthode parcourt toutes les scènes du registry et leur applique le thème.
     *
     * @param themeName Le nom du thème à appliquer
     */
    private static void applyThemeToRegisteredScenes(String themeName) {
        try {
            // Parcourt toutes les scènes enregistrées
            for (Scene scene : registeredScenes) {
                if (scene != null) {
                    applyTheme(scene, themeName);
                }
            }
            System.out.println("Thème appliqué à " + registeredScenes.size() + " scènes enregistrées");
        } catch (Exception e) {
            // Gère les erreurs globales pour ne pas interrompre l'application
            System.err.println("Erreur lors de l'application du thème aux scènes enregistrées : " + e.getMessage());
        }
    }

    /**
     * Applique le thème aux scènes via l'accès direct.
     * Cette méthode est utilisée pour s'assurer que toutes les scènes
     * sont mises à jour si elles ne sont pas enregistrées dans le registry.
     *
     * @param themeName Le nom du thème à appliquer
     */
    private static void applyThemeToDirectScenes(String themeName) {
        try {
            // Utilise la réflexion pour éviter les erreurs de compilation
            Class<?> mainClass = Class.forName("application.JeuDuQuinzeMain");

            // Ces noms correspondent aux méthodes statiques de JeuDuQuinzeMain
            String[] sceneGetters = {
                "getSceneAccueil", "getSceneJeu", "getSceneMenu",
                "getSceneParametres", "getSceneMode", "getSceneExplication"
            };

            // Parcourt chaque getter pour récupérer et traiter les scènes
            for (String getterName : sceneGetters) {
                try {
                    // Invoque la méthode getter via réflexion
                    Scene scene = (Scene) mainClass.getMethod(getterName).invoke(null);
                    if (scene != null) {
                        applyTheme(scene, themeName);
                    }
                } catch (Exception e) {
                    // Ignore les erreurs individuelles pour ne pas bloquer les autres scènes
                    System.err.println("Impossible d'appliquer le thème à la scène via " + getterName + " : " + e.getMessage());
                }
            }
        } catch (Exception e) {
            // Gère les erreurs globales pour la réflexion
            System.err.println("Erreur lors de l'accès aux scènes par réflexion : " + e.getMessage());
        }
    }

    /**
     * Applique le thème actuel à une scène lors de sa création ou activation.
     * Cette méthode doit être appelée chaque fois qu'une scène devient active
     * pour s'assurer qu'elle utilise le bon thème dès le départ.
     *
     * @param scene La scène qui devient active
     */
    public static void applyThemeOnSceneActivation(Scene scene) {
        if (scene != null) {
            // Enregistre la scène si elle ne l'est pas déjà
            // pour les futurs changements de thème
            registerScene(scene);
            applyCurrentTheme(scene);
        }
    }

    /**
     * Retourne le thème actuellement sélectionné.
     * Permet aux autres composants de connaître le thème actif.
     *
     * @return Le nom du thème actuel ("Clair" ou "Sombre")
     */
    public static String getCurrentTheme() {
        return currentTheme;
    }

    /**
     * Vérifie si un nom de thème est valide.
     * Centralise la validation des noms de thème pour éviter les erreurs.
     *
     * @param themeName Le nom à vérifier
     * @return true si le thème est valide (Clair ou Sombre)
     */
    public static boolean isValidTheme(String themeName) {
        return "Clair".equals(themeName) || "Sombre".equals(themeName);
    }

    /**
     * Retourne la liste des thèmes disponibles.
     * Utile pour construire des interfaces de sélection de thème.
     *
     * @return Un tableau contenant les noms des thèmes disponibles
     */
    public static String[] getAvailableThemes() {
        return new String[]{"Clair", "Sombre"};
    }

    /**
     * Initialise le ThemeManager avec toutes les scènes de l'application.
     * Cette méthode doit être appelée après le chargement de toutes les scènes
     * pour s'assurer que le registry contient toutes les scènes disponibles.
     *
     * Utilise la réflexion pour découvrir automatiquement les scènes.
     */
    public static void initializeWithAllScenes() {
        try {
            // Accède à la classe principale via réflexion
            Class<?> mainClass = Class.forName("application.JeuDuQuinzeMain");

            // Liste des getters des différentes scènes de l'application
            String[] sceneGetters = {
                "getSceneAccueil", "getSceneJeu", "getSceneMenu",
                "getSceneParametres", "getSceneMode", "getSceneExplication"
            };

            // Enregistre chaque scène trouvée
            for (String getterName : sceneGetters) {
                try {
                    Scene scene = (Scene) mainClass.getMethod(getterName).invoke(null);
                    if (scene != null) {
                        registerScene(scene);
                    }
                } catch (Exception e) {
                    System.err.println("Impossible d'initialiser la scène via " + getterName + " : " + e.getMessage());
                }
            }

            // Confirmation de l'initialisation avec le nombre de scènes trouvées
            System.out.println("ThemeManager initialisé avec " + registeredScenes.size() + " scènes");
        } catch (Exception e) {
            // Gère les erreurs globales pour l'initialisation
            System.err.println("Erreur lors de l'initialisation du ThemeManager : " + e.getMessage());
        }
    }

    /**
     * Méthode utilitaire pour le debug - affiche les scènes enregistrées.
     * Permet de diagnostiquer les problèmes de thème en affichant l'état
     * actuel du registry et des styles appliqués.
     */
    public static void debugRegisteredScenes() {
        System.out.println("=== DEBUG ThemeManager ===");
        System.out.println("Thème actuel : " + currentTheme);
        System.out.println("Nombre de scènes enregistrées : " + registeredScenes.size());

        // Affiche les détails de chaque scène enregistrée
        for (Scene scene : registeredScenes) {
            System.out.println("- Scène : " + scene + " (Stylesheets: " + scene.getStylesheets().size() + ")");
        }
        System.out.println("========================");
    }
}
