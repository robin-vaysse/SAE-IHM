
package application.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestJeuModel {

    private JeuModel jeuModel;

    @BeforeEach
    void setUp() {
        jeuModel = new JeuModel();
    }

    @Test
    void testInitialisation() {
        assertEquals(0, jeuModel.getScoreJoueur1());
        assertEquals(0, jeuModel.getScoreJoueur2());
        assertTrue(jeuModel.isTourJoueur1());
    }

    @Test
    void testAjoutScoreJoueur1() {
        jeuModel.ajouterScore(1);
        assertEquals(1, jeuModel.getScoreJoueur1());
        assertFalse(jeuModel.isBouttonCliquer(1, true));
    }

    @Test
    void testAjoutScoreJoueur2() {
        jeuModel.valider();
        jeuModel.ajouterScore(2);
        assertEquals(2, jeuModel.getScoreJoueur2());
        System.out.println(jeuModel.isBouttonCliquer(2, false));
        assertFalse(jeuModel.isBouttonCliquer(2, false));
    }

    @Test
    void testPlacerPlusDeTroisPions() {
        jeuModel.ajouterScore(1);
        jeuModel.valider();
        jeuModel.ajouterScore(2);
        jeuModel.valider();
        jeuModel.ajouterScore(3);
        jeuModel.valider();
        assertEquals(4, jeuModel.getScoreJoueur1());
    }

    @Test
    void testModeDeplacement() {
        jeuModel.ajouterScore(1);
        jeuModel.valider();
        jeuModel.ajouterScore(2);
        jeuModel.valider();
        jeuModel.ajouterScore(3);
        jeuModel.valider();

        jeuModel.valider();
        jeuModel.ajouterScore(4);
        jeuModel.valider();
        jeuModel.ajouterScore(5);
        jeuModel.valider();
        jeuModel.ajouterScore(6);
        jeuModel.valider();

        assertTrue(jeuModel.lesDeuxJoueursOnt3Pions());

        assertThrows(IllegalStateException.class, () -> jeuModel.ajouterScore(1));
    }

    @Test
    void testReset() {
        jeuModel.ajouterScore(1);
        jeuModel.valider();
        jeuModel.reset();

        assertEquals(0, jeuModel.getScoreJoueur1());
        assertEquals(0, jeuModel.getScoreJoueur2());
        assertTrue(jeuModel.isTourJoueur1());
        assertFalse(jeuModel.isModeDeplacement());
    }

}
