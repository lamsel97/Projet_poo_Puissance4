import business.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrilleTest {

    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
    }

    // =========================
    // insererJeton
    // =========================

    @Test
    void insererJeton_dansColonneVide_retourneDerniereLigne() throws Puissance4Exception {
        Jeton jeton = new Jeton(Couleur.JAUNE);
        int ligne = grille.insererJeton(jeton, 3);

        assertEquals(Grille.NB_LIGNES - 1, ligne);
        assertEquals(jeton, grille.getJetons(new Position(ligne, 3)));
    }

    @Test
    void insererJeton_empileLesJetons() throws Puissance4Exception {
        Jeton j1 = new Jeton(Couleur.JAUNE);
        Jeton j2 = new Jeton(Couleur.ROUGE);

        int l1 = grille.insererJeton(j1, 0);
        int l2 = grille.insererJeton(j2, 0);

        assertEquals(Grille.NB_LIGNES - 1, l1);
        assertEquals(Grille.NB_LIGNES - 2, l2);
    }

    @Test
    void insererJeton_colonnePleine_declencheException() throws Puissance4Exception {
        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            grille.insererJeton(new Jeton(Couleur.JAUNE), 1);
        }

        assertThrows(Puissance4Exception.class, () ->
                grille.insererJeton(new Jeton(Couleur.ROUGE), 1)
        );
    }

    @Test
    void insererJeton_colonneInvalide_declencheException() {
        assertThrows(IllegalArgumentException.class, () ->
                grille.insererJeton(new Jeton(Couleur.JAUNE), -1)
        );
    }

    // =========================
    // isFullColonne
    // =========================

    @Test
    void isFullColonne_retourneVrai_siColonnePleine() throws Puissance4Exception {
        for (int i = 0; i < Grille.NB_LIGNES; i++) {
            grille.insererJeton(new Jeton(Couleur.JAUNE), 2);
        }

        assertTrue(grille.isFullColonne(2));
    }

    @Test
    void isFullColonne_retourneFaux_siColonneNonPleine() {
        assertFalse(grille.isFullColonne(0));
    }

    // =========================
    // isFullGrille
    // =========================

    @Test
    void isFullGrille_retourneFaux_siGrilleVide() {
        assertFalse(grille.isFullGrille());
    }

    @Test
    void isFullGrille_retourneVrai_siGrillePleine() throws Puissance4Exception {
        for (int col = 0; col < Grille.NB_COLONNES; col++) {
            for (int lig = 0; lig < Grille.NB_LIGNES; lig++) {
                grille.insererJeton(new Jeton(Couleur.JAUNE), col);
            }
        }

        assertTrue(grille.isFullGrille());
    }

    // =========================
    // alignementRealise
    // =========================

    @Test
    void alignementVertical_detecteVictoire() throws Puissance4Exception {
        for (int i = 0; i < 4; i++) {
            grille.insererJeton(new Jeton(Couleur.JAUNE), 0);
        }

        assertTrue(grille.alignementRealise(
                new Position(Grille.NB_LIGNES - 4, 0))
        );
    }

    @Test
    void alignementHorizontal_detecteVictoire() throws Puissance4Exception {
        for (int col = 0; col < 4; col++) {
            grille.insererJeton(new Jeton(Couleur.ROUGE), col);
        }

        assertTrue(grille.alignementRealise(
                new Position(Grille.NB_LIGNES - 1, 1))
        );
    }

    @Test
    void alignementDiagonal1_detecteVictoire() throws Puissance4Exception {
        // Construction manuelle d'une diagonale /
        grille.insererJeton(new Jeton(Couleur.JAUNE), 0);

        grille.insererJeton(new Jeton(Couleur.ROUGE), 1);
        grille.insererJeton(new Jeton(Couleur.JAUNE), 1);

        grille.insererJeton(new Jeton(Couleur.ROUGE), 2);
        grille.insererJeton(new Jeton(Couleur.ROUGE), 2);
        grille.insererJeton(new Jeton(Couleur.JAUNE), 2);

        grille.insererJeton(new Jeton(Couleur.ROUGE), 3);
        grille.insererJeton(new Jeton(Couleur.ROUGE), 3);
        grille.insererJeton(new Jeton(Couleur.ROUGE), 3);
        grille.insererJeton(new Jeton(Couleur.JAUNE), 3);

        assertTrue(grille.alignementRealise(
                new Position(Grille.NB_LIGNES - 4, 3))
        );
    }

    // =========================
    // getJetons
    // =========================

    @Test
    void getJetons_positionInvalide_declencheException() {
        assertThrows(IllegalArgumentException.class, () ->
                grille.getJetons(new Position(-1, 0))
        );
    }
}
