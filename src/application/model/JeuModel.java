package application.model;

import application.controller.JeuController;
import java.util.Random;

// Classe qui gère la logique du jeu
public class JeuModel {

    private int scoreJoueur1;
    private int scoreJoueur2;
    private int scoreJoueur1Check;
    private int scoreJoueur2Check;
    private boolean tourJoueur1;
    private int pionJoueur1 = 0;
    private int compteurJoueur1 = 0;
    private int compteurJoueur2 = 0;
    private boolean jeuTermine = false;
    private int pionJoueur2 = 0;
    private boolean[] boutonsCliquesJoueur1 = new boolean[9];
    private boolean[] boutonsCliquesJoueur2 = new boolean[9];
    boolean boutonsCliquer = false;
    private int valeurPrecedente = 0;
    private boolean modeDeplacement = false;
    private int pionADeplacer = 0;

    public JeuModel() {
        reset();
    }

    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    public boolean isTourJoueur1() {
        return tourJoueur1;
    }

    public boolean isModeDeplacement() {
        return modeDeplacement;
    }

    public int getPionADeplacer() {
        return pionADeplacer;
    }

    public boolean lesDeuxJoueursOnt3Pions() {
        return pionJoueur1 >= 3 && pionJoueur2 >= 3;
    }

    public void isValidePlacer(int buttonId, boolean joueur1) {
        // méthode pour validation
    }

    // Modifie le score selon le bouton cliqué
    private void modifierScore(int buttonId, boolean joueur1) {
        int valeur = buttonId; // simple, l'id du bouton = sa valeur

        if (joueur1) {
            if (boutonsCliquer && valeurPrecedente != 0) {
                unsetBouttonCliqueJoueur(valeurPrecedente, true);
                scoreJoueur1 -= valeurPrecedente;
            }
            scoreJoueur1 += valeur;
            valeurPrecedente = buttonId;
        } else {
            if (boutonsCliquer && valeurPrecedente != 0) {
                unsetBouttonCliqueJoueur(valeurPrecedente, false);
                scoreJoueur2 -= valeurPrecedente;
            }
            scoreJoueur2 += valeur;
            valeurPrecedente = buttonId;
        }
        boutonsCliquer = true;
    }

    // Gère le déplacement des pions
    private void gererDeplacementPion(int buttonId) {
        // Vérifie si le joueur peut encore déplacer
        if ((tourJoueur1 && compteurJoueur1 >= 10) || (!tourJoueur1 && compteurJoueur2 >= 10)) {
            throw new IllegalStateException("Plus de déplacements possibles !");
        }
        
        if (pionADeplacer == 0) {
            // Sélectionner un pion
            if (isBouttonCliquer(buttonId, tourJoueur1)) {
                pionADeplacer = buttonId;
            } else {
                throw new IllegalStateException("Sélectionnez un de vos pions !");
            }
        } else {
            // Déplacer vers une case libre
            if (boutonsCliquesJoueur1[buttonId - 1] || boutonsCliquesJoueur2[buttonId - 1]) {
                throw new IllegalStateException("Case déjà occupée !");
            }
            
            // Faire le déplacement
            unsetBouttonCliqueJoueur(pionADeplacer, tourJoueur1);
            setBouttonCliqueJoueur(buttonId, tourJoueur1);
            
            // Mettre à jour le score
            int ancienneValeur = pionADeplacer;
            int nouvelleValeur = buttonId;
            
            if (tourJoueur1) {
                scoreJoueur1 = scoreJoueur1 - ancienneValeur + nouvelleValeur;
                compteurJoueur1++;
                if (scoreJoueur1 == 15) {
                    jeuTermine = true;
                    return;
                }
            } else {
                scoreJoueur2 = scoreJoueur2 - ancienneValeur + nouvelleValeur;
                compteurJoueur2++; 
                if (scoreJoueur2 == 15) {
                    jeuTermine = true;
                    return; 
                }
            }
            
            pionADeplacer = 0;
            tourJoueur1 = !tourJoueur1;
        }
    }
    
    public void ajouterScore(int buttonId) {
        if (lesDeuxJoueursOnt3Pions()) {
            modeDeplacement = true;
            gererDeplacementPion(buttonId);
        } else {
            verificationBouttonClique(buttonId, tourJoueur1);
            modifierScore(buttonId, tourJoueur1);
        }
    }

    public void calculScore() {
        // Vérifier si quelqu'un a gagné
        if (scoreJoueur1 == 15 || scoreJoueur2 == 15) {
            jeuTermine = true;
        }
        
        // Vérifier si plus de déplacements possibles
        if (modeDeplacement && compteurJoueur1 >= 10 && compteurJoueur2 >= 10) {
            jeuTermine = true;
        }
    }

    // Remet tout à zéro
    public void reset() {
        scoreJoueur1 = 0;
        scoreJoueur2 = 0;
        Random random = new Random();
        tourJoueur1 = random.nextBoolean();
        pionJoueur1 = 0;
        pionJoueur2 = 0;
        scoreJoueur1Check = -1;
        scoreJoueur2Check = -1;
        modeDeplacement = false;
        pionADeplacer = 0;
        valeurPrecedente = 0;
        boutonsCliquer = false;
        compteurJoueur1 = 0;
        compteurJoueur2 = 0;
        jeuTermine = false;
        
        for (int i = 0; i < boutonsCliquesJoueur1.length; i++) {
            boutonsCliquesJoueur1[i] = false;
            boutonsCliquesJoueur2[i] = false;
        }
    }
    
    public boolean isBouttonCliquer(int buttonId, boolean joueur1) {
        if (joueur1) {
            return boutonsCliquesJoueur1[buttonId - 1];
        } else {
            return boutonsCliquesJoueur2[buttonId - 1];
        }
    }

    public void verificationBouttonClique(int buttonId, boolean joueur1) {
        if (boutonsCliquesJoueur1[buttonId - 1] || boutonsCliquesJoueur2[buttonId - 1]) {
            throw new IllegalStateException("Bouton déjà utilisé !");
        }
    }

    public void setBouttonCliqueJoueur(int buttonId, boolean joueur1) {
        if (joueur1) {
            boutonsCliquesJoueur1[buttonId - 1] = true;
        } else {
            boutonsCliquesJoueur2[buttonId - 1] = true;
        }
    }

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
    
    public int getBoutonSelectionne() {
        return valeurPrecedente;
    }
    
    public int getCompteurJoueur1() {
        return compteurJoueur1;
    }

    public int getCompteurJoueur2() {
        return compteurJoueur2;
    }

    public boolean isCompteurEpuise() {
        return (10 - compteurJoueur1) <= 0 && (10 - compteurJoueur2) <= 0;
    }
     
    public String determinerResultatFinal(String nomJoueur1, String nomJoueur2) {
        int distanceJoueur1 = Math.abs(15 - scoreJoueur1);
        int distanceJoueur2 = Math.abs(15 - scoreJoueur2);
        
        if (distanceJoueur1 < distanceJoueur2) {
            return nomJoueur1 + " gagne ! (Plus proche de 15)";
        } else if (distanceJoueur2 < distanceJoueur1) {
            return nomJoueur2 + " gagne ! (Plus proche de 15)";
        } else {
            return "Match nul !";
        }
    }
    
    public boolean isJeuTermine() {
        return jeuTermine;
    }

    public void annulerSelectionDeplacement() {
        if (pionADeplacer != 0) {
            pionADeplacer = 0;
        }
    }

    public void valider() {
        if (modeDeplacement) {
            if (pionADeplacer != 0) {
                throw new IllegalStateException("Terminez le déplacement en cours !");
            }
        } else {
            // Mode placement
            if (tourJoueur1 && scoreJoueur1 != scoreJoueur1Check) {
                if (pionJoueur1 < 3) {
                    tourJoueur1 = false;
                    pionJoueur1++;
                    scoreJoueur1Check = scoreJoueur1;
                    
                    if (scoreJoueur1 == 15) {
                        jeuTermine = true;
                    }
                } else {
                    throw new IllegalStateException("Joueur 1 ne peut plus placer de pions !");
                }
            } else if (!tourJoueur1 && scoreJoueur2 != scoreJoueur2Check) {
                if (pionJoueur2 < 3) {
                	tourJoueur1 = true;
                    pionJoueur2++;
                    scoreJoueur2Check = scoreJoueur2;
                    
                    if (scoreJoueur2 == 15) {
                        jeuTermine = true;
                    }
                } else {
                    throw new IllegalStateException("Joueur 2 ne peut plus placer de pions !");
                }
            }
        }
        
        if (modeDeplacement && compteurJoueur1 >= 10 && compteurJoueur2 >= 10) {
            jeuTermine = true;
        }
        
        valeurPrecedente = 0;
        boutonsCliquer = false;
    }
}