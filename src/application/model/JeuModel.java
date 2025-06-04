
package application.model;

import application.controller.JeuController;

/**
 * La classe `JeuModel` représente la logique et l'état du jeu.
 * Elle gère les scores, les tours des joueurs, l'état des boutons et les règles du jeu.
 */
public class JeuModel {

    /** Score du joueur 1 */
    private int scoreJoueur1;

    /** Score du joueur 2 */
    private int scoreJoueur2;
     
    /** Score de vérification du joueur 1*/
    private int scoreJoueur1Check;

    /** Score de vérification du joueur 2*/
    private int scoreJoueur2Check;

    /** Indique si c'est le tour du joueur 1 ou non */
    private boolean tourJoueur1;

    /** Nombre de pions placés par le joueur 1 */
    private int pionJoueur1 = 0;

    /**
     * Nombre de pions placés par le joueur 2.
     */
    private int pionJoueur2 = 0;

    /** Tableau représentant les boutons cliqués par le joueur 1 */
    private boolean[] boutonsCliquesJoueur1 = new boolean[9];

    /** Tableau représentant les boutons cliqués par le joueur 2 */
    private boolean[] boutonsCliquesJoueur2 = new boolean[9];

    /** Indique si un bouton a été cliqué lors du tour actuel */
    boolean boutonsCliquer = false;

    /**
     * Identifiant du dernier bouton cliqué.
     * Utilisé pour gérer les actions liées au bouton précédent.
     */
    private int valeurPrecedente = 0;


    /**
     * Constructeur de la classe `JeuModel` qui initialise l'état du jeu.
     */
    public JeuModel() {
        reset();
    }

    /**
     * Retourne le score du joueur 1.
     * @return le score du joueur 1
     */
    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    /**
     * Retourne le score du joueur 2.
     * @return le score du joueur 2
     */
    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    /**
     * Vérifie si c'est le tour du joueur 1.
     * @return `true` si c'est le tour du joueur 1, `false` sinon
     */

    public boolean isTourJoueur1() {
        return tourJoueur1;
    }


    /**
     * Valide si un joueur peut placer un pion.
     * @return `true` si le placement est valide
     * @throws IllegalStateException si un joueur tente de placer plus de 3 pions
     */
    public void isValidePlacer(int buttonId, boolean joueur1) {
		if (joueur1 && pionJoueur1 >= 3) {
			changerBoutonClique(buttonId, joueur1);
		} else if (!joueur1 && pionJoueur2 >= 3) {
			changerBoutonClique(buttonId, !joueur1);
		}

    }

    /**
     * Modifie le score en fonction du bouton cliqué et du joueur actuel.
     * @param buttonId l'identifiant du bouton cliqué
     * @param ajouter  `true` pour ajouter au score, `false` pour le soustraire
     */
    private void modifierScore(int buttonId, boolean ajouter) {
        int valeur = switch (buttonId) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> 4;
            case 5 -> 5;
            case 6 -> 6;
            case 7 -> 7;
            case 8 -> 8;
            case 9 -> 9;
            default -> throw new IllegalArgumentException("Bouton invalide !");
        };

        if (ajouter) {
            if (boutonsCliquer && valeurPrecedente != 0) {
                unsetBouttonCliqueJoueur(valeurPrecedente, true);
                scoreJoueur1 -= switch (valeurPrecedente) {
                    case 1 -> 1;
                    case 2 -> 2;
                    case 3 -> 3;
                    case 4 -> 4;
                    case 5 -> 5;
                    case 6 -> 6;
                    case 7 -> 7;
                    case 8 -> 8;
                    case 9 -> 9;
                    default -> 0;
                };
            }
            setBouttonCliqueJoueur(buttonId, true);
            scoreJoueur1 += valeur;
            valeurPrecedente = buttonId;
        } else {
            if (boutonsCliquer && valeurPrecedente != 0) {
                unsetBouttonCliqueJoueur(valeurPrecedente, false);
                scoreJoueur2 -= switch (valeurPrecedente) {
                    case 1 -> 1;
                    case 2 -> 2;
                    case 3 -> 3;
                    case 4 -> 4;
                    case 5 -> 5;
                    case 6 -> 6;
                    case 7 -> 7;
                    case 8 -> 8;
                    case 9 -> 9;
                    default -> 0;
                };
            }
			if (pionJoueur2 <= 3) {
				setBouttonCliqueJoueur(buttonId, false);
	            scoreJoueur2 += valeur;
	            valeurPrecedente = buttonId;
			} else {
				throw new IllegalStateException("Le joueur 2 ne peut pas placer plus de 3 pions !");
			}
			
			if (pionJoueur1 <= 3) {
				setBouttonCliqueJoueur(buttonId, true);
				scoreJoueur1 += valeur;
				valeurPrecedente = buttonId;
			} else {
				throw new IllegalStateException("Le joueur 1 ne peut pas placer plus de 3 pions !");
			}

        }
        boutonsCliquer = true;
    }

    /**
     * Ajoute le score pour l'identifiant de bouton spécifié.
     * @param buttonId l'identifiant du bouton cliqué
     */
    public void ajouterScore(int buttonId) {
        if (tourJoueur1) {
            verificationBouttonClique(buttonId, true);
            modifierScore(buttonId, true);
        } else {
            verificationBouttonClique(buttonId, false);
            modifierScore(buttonId, false);
        }
    }

    /**
     * Calcule le score et vérifie les conditions de victoire.
     */
    public void calculScore() {
        if (scoreJoueur1 == 15) {
            // Logique pour la victoire du joueur 1
        }

        if (scoreJoueur2 == 15) {
            // Logique pour la victoire du joueur 2
        }
    }

    /**
     * Réinitialise l'état du jeu à ses valeurs initiales.
     */
    public void reset() {
        scoreJoueur1 = 0;
        scoreJoueur2 = 0;
        tourJoueur1 = true;
        pionJoueur1 = 0;
        pionJoueur2 = 0;
        scoreJoueur1Check = -1;
        scoreJoueur2Check = -1;
        
        for (int i = 0; i < boutonsCliquesJoueur1.length; i++) {
            boutonsCliquesJoueur1[i] = false;
            boutonsCliquesJoueur2[i] = false;
        }
    }


	/**
	 * Valide le tour actuel et passe au joueur suivant.
	 */
	public void valider() {
	    if (tourJoueur1 && scoreJoueur1 != scoreJoueur1Check) {
	        tourJoueur1 = false;
	        pionJoueur1++;
	        scoreJoueur1Check = scoreJoueur1;
	    } else if (!tourJoueur1 && scoreJoueur2 != scoreJoueur2Check) {
	        tourJoueur1 = true;
	        pionJoueur2++;
	        scoreJoueur2Check = scoreJoueur2;
	    }
	    valeurPrecedente = 0;
	}


    /**
     * Vérifie si un bouton a été cliqué par un joueur spécifique.
     * @param buttonId l'identifiant du bouton
     * @param joueur1  `true` pour le joueur 1, `false` pour le joueur 2
     * @return `true` si le bouton a été cliqué, `false` sinon
     */
    public boolean isBouttonCliquer(int buttonId, boolean joueur1) {
        return switch (buttonId) {
            case 1 -> boutonsCliquesJoueur1[0];
            case 2 -> boutonsCliquesJoueur1[1];
            case 3 -> boutonsCliquesJoueur1[2];
            case 4 -> boutonsCliquesJoueur1[3];
            case 5 -> boutonsCliquesJoueur1[4];
            case 6 -> boutonsCliquesJoueur1[5];
            case 7 -> boutonsCliquesJoueur1[6];
            case 8 -> boutonsCliquesJoueur1[7];
            case 9 -> boutonsCliquesJoueur1[8];
            default -> throw new IllegalArgumentException("Bouton invalide !");
        };
    }

    /**
     * Vérifie si un bouton peut être cliqué par un joueur spécifique.
     * @param buttonId l'identifiant du bouton
     * @param joueur1  `true` pour le joueur 1, `false` pour le joueur 2
     * @throws IllegalStateException si le bouton a déjà été cliqué
     */
    public void verificationBouttonClique(int buttonId, boolean joueur1) {
        if (joueur1) {
            if (boutonsCliquesJoueur1[buttonId - 1]) {
                throw new IllegalStateException("Ce bouton a déjà été cliqué par le joueur 1 !");
            }
            if (boutonsCliquesJoueur2[buttonId - 1]) {
                throw new IllegalStateException("Ce bouton a déjà été cliqué par le joueur 2 !");
            }
        } else {
            if (boutonsCliquesJoueur2[buttonId - 1]) {
                throw new IllegalStateException("Ce bouton a déjà été cliqué par le joueur 2 !");
            }
            if (boutonsCliquesJoueur1[buttonId - 1]) {
                throw new IllegalStateException("Ce bouton a déjà été cliqué par le joueur 1 !");
            }
        }
    }

    /**
     * Définit l'état d'un bouton comme cliqué pour un joueur spécifique.
     * @param buttonId l'identifiant du bouton
     * @param joueur1  `true` pour le joueur 1, `false` pour le joueur 2
     */
    public void setBouttonCliqueJoueur(int buttonId, boolean joueur1) {
        verificationBouttonClique(buttonId, joueur1);
        
        if (pionJoueur1 <= 3 || pionJoueur2 <= 3) {
            if (joueur1) {
            	boutonsCliquesJoueur1[buttonId - 1] = true;
	        } else {
	        	boutonsCliquesJoueur2[buttonId - 1] = true;
	        }
        }
        
    }

    /**
     * Réinitialise l'état d'un bouton comme non cliqué pour un joueur spécifique.
     * @param buttonId l'identifiant du bouton
     * @param joueur1  `true` pour le joueur 1, `false` pour le joueur 2
     */
    public void unsetBouttonCliqueJoueur(int buttonId, boolean joueur1) {
        if (joueur1) {
            boutonsCliquesJoueur1[buttonId - 1] = false;
        } else {
            boutonsCliquesJoueur2[buttonId - 1] = false;
        }
    }
    
	public void changerBoutonClique(int buttonId, boolean joueur1) {
		if (joueur1) {
			unsetBouttonCliqueJoueur(buttonId, joueur1);
		} else {
			unsetBouttonCliqueJoueur(buttonId, !joueur1);
		}
	}
	
}
