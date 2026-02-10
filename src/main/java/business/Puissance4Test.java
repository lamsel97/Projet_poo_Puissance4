package business;

import Business.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class Puissance4Test {
    @Test
    public void testInsererJetonNull() {
        Grille grille = new Grille();

        assertThrows(IllegalArgumentException.class, () -> {
            grille.insererJeton(null, 3);
        });
    }

    @Test
    public void testInsererJeton() throws Puissance4Exception {
        Grille grille = new Grille();
        Jeton jeton = new Jeton(Couleur.JAUNE);
        int ligne = grille.insererJeton(jeton, 3);
        assertEquals(5, ligne, "Le jeton devrait être inséré à la dernière ligne disponible.");
    }

    @Test
    public void testIsFullColonne() throws Puissance4Exception {
        Grille grille = new Grille();
        for (int i = 0; i < Config.NB_LIGNES; i++) {
            grille.insererJeton(new Jeton(Couleur.ROUGE), 0);
        }
        assertTrue(grille.isFullColonne(0), "La colonne 0 devrait être pleine.");
    }

    @Test
    public void testIsFullGrille() throws Puissance4Exception {
        Grille grille = new Grille();
        for (int col = 0; col < Config.NB_COLONNES; col++) {
            for (int row = 0; row < Config.NB_LIGNES; row++) {
                grille.insererJeton(new Jeton(Couleur.JAUNE), col);
            }
        }
        assertTrue(grille.isFullGrille(), "La grille devrait être pleine.");
    }

    @Test
    public void testGameIsOver() {
        Partie partie = new Partie();
        Puissance4 jeu = new Puissance4(partie);
        assertFalse(jeu.gameIsOver(), "La partie ne devrait pas être terminée au début.");
    }

    @Test
    public void testJouerJeton() throws Puissance4Exception {
        Partie partie = new Partie();
        Puissance4 jeu = new Puissance4(partie);
        jeu.jouer(2);
        assertFalse(jeu.gameIsOver(), "La partie ne devrait pas être terminée après un coup.");
    }

    @Test
    public void testAbandonner() {
        Partie partie = new Partie();
        Puissance4 jeu = new Puissance4(partie);
        jeu.abandonner();
        assertTrue(jeu.gameIsOver(), "La partie devrait être terminée après un abandon.");
        assertNotNull(partie.getGagnant(), "Un gagnant devrait être défini après un abandon.");
    }

    @Test
    public void testPositionEquals() {
        Position pos1 = new Position(2, 3);
        Position pos2 = new Position(2, 3);
        Position pos3 = new Position(1, 3);
        assertTrue(pos1.equals(pos2), "Les positions devraient être égales.");
        assertFalse(pos1.equals(pos3), "Les positions ne devraient pas être égales.");
    }

    @Test
    public void testAlignementRealise() throws Puissance4Exception {
        Grille grille = new Grille();
        Jeton jeton = new Jeton(Couleur.JAUNE);
        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);
        grille.insererJeton(jeton, 0);
        Position position = new Position(2, 0);
        assertTrue(grille.alignementRealise(position), "Un alignement devrait être détecté.");
    }
    @Test
    void insererJeton_colonnePleine_doitLeverException() throws Puissance4Exception {
        Grille grille = new Grille();
        Jeton j = new Jeton(Couleur.JAUNE);

        for (int i = 0; i < 6; i++) {
            grille.insererJeton(new Jeton(Couleur.JAUNE), 3);
        }
        assertThrows(Exception.class, () -> grille.insererJeton(j, 3));
    }
    @Test
    void insererJeton_grillePleine_doitLeverException() throws Puissance4Exception {
        Grille grille = new Grille();

        // Remplissage 6x7
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                Couleur c = ((row + col) % 2 == 0) ? Couleur.JAUNE : Couleur.ROUGE;
                grille.insererJeton(new Jeton(c), col);
            }
        }

        assertThrows(Exception.class, () -> grille.insererJeton(new Jeton(Couleur.JAUNE), 0));
    }
    @Test
    void insererJeton_indexation_colonne0_ou_1_aClarifier() {
        Grille grille = new Grille();
        Jeton jeton = new Jeton(Couleur.JAUNE);
        try {
            grille.insererJeton(jeton, 0);
        } catch (Exception e) {
        }
    }

    @Test
    void insererJeton_fuzz_neDoitJamaisPlanterAutrementQueParException() {
        Grille grille = new Grille();

        for (int i = 0; i < 200; i++) {
            int col = (int) (Math.random() * 30) - 10; // [-10..19]
            Jeton jeton = (Math.random() < 0.2) ? null : new Jeton(Couleur.JAUNE);

            try {
                grille.insererJeton(jeton, col);
            } catch (RuntimeException | Puissance4Exception e) {
            }
        }
    }
    @Test
    void invariant_gravite_pasDeJetonFlottant() throws Puissance4Exception {
        Grille g = new Grille();

        g.insererJeton(new Jeton(Couleur.JAUNE), 2);
        g.insererJeton(new Jeton(Couleur.ROUGE), 2);

        for (int ligne = 0; ligne < 5; ligne++) {
            if (g.getJeton(new Position(ligne, 2)) != null) {
                assertNotNull(g.getJeton(new Position(ligne + 1, 2)),
                        "Jeton flottant détecté : il y a un trou sous un jeton");
            }
        }
    }

    @Test
    void deuxInsertionsDoiventCreerDeuxJetonsDifferents() throws Puissance4Exception {
        Grille g = new Grille();

        int l1 = g.insererJeton(new Jeton(Couleur.JAUNE), 0);
        int l2 = g.insererJeton(new Jeton(Couleur.JAUNE), 1);

        assertNotSame(
                g.getJeton(new Position(l1, 0)),
                g.getJeton(new Position(l2, 1)),
                "Deux insertions doivent produire deux jetons distincts"
        );
    }



    @Test
    void constructeur_joueurCourant_estAleatoire() {
        boolean vuJaune = false;
        boolean vuRouge = false;

        for (int i = 0; i < 200; i++) {
            Partie p = new Partie();
            Couleur c = p.getJoueurCourant().getNom();
            if (c == Couleur.JAUNE) vuJaune = true;
            if (c == Couleur.ROUGE) vuRouge = true;
            if (vuJaune && vuRouge) break;
        }

        assertTrue(vuJaune && vuRouge,
                "Le joueur courant n'est pas aléatoire (ou test trop faible)");
    }


    @Test
    void insererJeton_doitRetournerLaBonneLigne() throws Puissance4Exception {
        Grille g = new Grille();

        int l1 = g.insererJeton(new Jeton(Couleur.JAUNE), 4);
        int l2 = g.insererJeton(new Jeton(Couleur.ROUGE), 4);

        assertEquals(l1 - 1, l2,
                "La ligne retournée ne correspond pas à la position réelle");
    }

    @Test
    void deuxGrillesDoiventEtreIndependantes() throws Puissance4Exception {
        Grille g1 = new Grille();
        Grille g2 = new Grille();

        g1.insererJeton(new Jeton(Couleur.JAUNE), 0);

        assertNull(g2.getJeton(new Position(5, 0)),
                "État partagé entre instances de Grille");
    }

    @Test
    void getJoueurs_exposeLeTableauInterne_et_on_peut_le_corrompre() {
        Partie p = new Partie();

        Joueur[] ref = p.getJoueurs();
        ref[0] = null;

        assertNotNull(p.getJoueurs()[0],
                "Le tableau interne est modifiable depuis l'extérieur (faille d'encapsulation)");
    }
    @Test
    void changerJoueur_peut_planter_si_joueurs_est_corrompu() {
        Partie p = new Partie();

        p.getJoueurs()[0] = null;
        p.changerJoueur();
        assertNotNull(p.getJoueurCourant(),
                "Après corruption, joueurCourant peut devenir null");
    }
    @Test
    void partieFinie_sansAbandon_doitAvoirUnGagnant() {
        Partie p = new Partie();

        p.setPartieFinie(true);
        p.setParAbandon(false);
        p.setGagnant(null);

        assertNotNull(p.getGagnant(),
                "Règle métier violée : partie finie sans abandon => il faut un gagnant");
    }

    @Test
    void setGagnant_accepte_un_joueur_etranger_a_la_partie() {
        Partie p = new Partie();
        Joueur intrus = new Joueur(Couleur.JAUNE);

        p.setGagnant(intrus);

        assertNotSame(intrus, p.getGagnant(),
                "On peut déclarer gagnant un joueur qui ne participe pas (faille)");
    }

}