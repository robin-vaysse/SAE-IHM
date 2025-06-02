package application.model;

import application.controller.JeuController;

public class JeuModel {
	private int scoreJoueur1;
	private int scoreJoueur2;
	private boolean tourJoueur1;
	private int pionJoueur1 = 0;
	private int pionJoueur2 = 0;
	private int verifierScore = 0;
	
	public JeuModel() {

	    scoreJoueur1 = 0;
	    scoreJoueur2 = 0;
	    tourJoueur1 = true;
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
	
	public boolean isValidePlacer() {

		if(pionJoueur1 >= 3 || pionJoueur2 >=3 ) {
			return false;
		}
		return true;
	}
	public void ajouterScore(int buttonId) {

	    if (tourJoueur1) {
	    	if(verifierScore == 0) {
		        switch (buttonId) {
		            case 1 -> {
		            	scoreJoueur1 += 1;
		            	verifierScore = 1;
		            }
		            case 2 -> {
		                scoreJoueur1 += 2;
		                verifierScore = 2;
		            }
		            case 3 -> {
		                scoreJoueur1 += 3;
		                verifierScore = 3;
		            }
		            case 4 -> {
		                scoreJoueur1 += 4;
		                verifierScore = 4;
		            }
		            case 5 -> {
		                scoreJoueur1 += 5;
		                verifierScore = 5;
		            }
		            case 6 -> {
		                scoreJoueur1 += 6;
		                verifierScore = 6;
		            }
		            case 7 -> {
		                scoreJoueur1 += 7;
		                verifierScore = 7;
		            }
		            case 8 -> {
		                scoreJoueur1 += 8;
		                verifierScore = 8;
		            }
		            case 9 -> {
		                scoreJoueur1 += 9;
		                verifierScore = 9;
		            }
		            default -> throw new IllegalArgumentException("Bouton invalide !");		      
		        }
		        
		    }else {
		        switch (buttonId) {
	            case 1 -> {
	            	scoreJoueur1 -= 1;
	            	verifierScore -= 1;
	            }
	            case 2 -> {
	                scoreJoueur1 -= 2;
	                verifierScore -= 2;
	            }
	            case 3 -> {
	                scoreJoueur1 -= 3;
	                verifierScore -= 3;
	            }
	            case 4 -> {
	                scoreJoueur1 -= 4;
	                verifierScore -= 4;
	            }
	            case 5 -> {
	                scoreJoueur1 -= 5;
	                verifierScore -= 5;
	            }
	            case 6 -> {
	                scoreJoueur1 -= 6;
	                verifierScore -= 6;
	            }
	            case 7 -> {
	                scoreJoueur1 -= 7;
	                verifierScore -= 7;
	            }
	            case 8 -> {
	                scoreJoueur1 -= 8;
	                verifierScore -= 8;
	            }
	            case 9 -> {
	                scoreJoueur1 -= 9;
	                verifierScore -= 9;
	            }
	            default -> throw new IllegalArgumentException("Bouton invalide !");		      
	        }
            	
            }
	    } else {
	        switch (buttonId) {
	            case 1 -> scoreJoueur2 += 1;
	            case 2 -> scoreJoueur2 += 2;
	            case 3 -> scoreJoueur2 += 3;
	            case 4 -> scoreJoueur2 += 4;
	            case 5 -> scoreJoueur2 += 5;
	            case 6 -> scoreJoueur2 += 6;
	            case 7 -> scoreJoueur2 += 7;
	            case 8 -> scoreJoueur2 += 8;
	            case 9 -> scoreJoueur2 += 9;
	            default -> throw new IllegalArgumentException("Bouton invalide !");
	        }
	    }
	}
	public void calculScore() {
		
	}
	
	public void reset() {
		scoreJoueur1 = 0;
		scoreJoueur2 = 0;
		tourJoueur1 = true;
		pionJoueur1 = 0;
		pionJoueur2 = 0;
	}
	
	public void valider() {
		if (tourJoueur1) {
			tourJoueur1 = false;
		} else {
			tourJoueur1 = true;
		}
		
	}
}
