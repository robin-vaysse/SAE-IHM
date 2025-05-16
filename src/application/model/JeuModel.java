package application.model;

public class JeuModel {
	private int [][] grille;
	private int scoreJoueur1;
	private int scoreJoueur2;
	private boolean tourJoueur1;
	
	public JeuModel() {
		// TODO initialisation de la grille et des scores
	}
	
	public void choisirCase(int x, int y) {
		// TODO choisir une case
	}
	
	public void changerTour() {
		tourJoueur1 = !tourJoueur1;
	}
	public int getScoreJoueur1() {
		return scoreJoueur1;
	}
	public void setScoreJoueur1(int scoreJoueur1) {
		this.scoreJoueur1 = scoreJoueur1;
	}
	public int getScoreJoueur2() {
		return scoreJoueur2;
	}
	public void setScoreJoueur2(int scoreJoueur2) {
		this.scoreJoueur2 = scoreJoueur2;
	}
	public boolean isTourJoueur1() {
		return tourJoueur1;
	}
	public void setTourJoueur1(boolean tourJoueur1) {
		this.tourJoueur1 = tourJoueur1;
	}
	public boolean isGrillePleine() {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (grille[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	public int[][] getGrille() {
		return grille;
	}
	public void setGrille(int[][] grille) {
		this.grille = grille;
	}
	// voir getter et setters
	public int getCase(int x, int y) {
		return grille[x][y];
	}
	public void setCase(int x, int y, int valeur) {
		grille[x][y] = valeur;
	}
}
