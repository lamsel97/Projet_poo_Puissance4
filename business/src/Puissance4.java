public class Puissance4 {

    private Partie puissance4;

    public Puissance4() {
        this.puissance4 = new Partie();
    }

    public Puissance4(Partie puissance4) {
        this.puissance4 = puissance4;
    }

    /**
     * Retourne vrai si la partie est terminée
     */
    public boolean gameIsOver() {
        return puissance4.isPartieFinie();
    }

    /**
     * Permet de jouer un jeton dans une colonne
     */
    public void jouer(int numColonne) throws Puissance4Exception {
        if (puissance4.isPartieFinie()) {
            return;
        }

        Grille grille = puissance4.getGrille();
        Joueur joueurCourant = puissance4.getJoueurCourant();

        // Création du jeton du joueur courant
        Jeton jeton = new Jeton(joueurCourant.getNom());

        // Insertion du jeton
        int ligne = grille.insererJeton(jeton, numColonne);

        // Position du dernier jeton joué
        Position position = new Position(ligne, numColonne);

        // Vérification victoire
        if (grille.alignementRealise(position)) {
            puissance4.setGagnant(joueurCourant);
            puissance4.setPartieFinie(true);
            return;
        }

        // Vérification match nul
        if (grille.isFullGrille()) {
            puissance4.setPartieFinie(true);
            return;
        }

        // Changement de joueur
        puissance4.changerJoueurCourant();
    }

    /**
     * Abandon de la partie par le joueur courant
     */
    public void abandonner() {
        if (puissance4.isPartieFinie()) {
            return;
        }

        Joueur joueurCourant = puissance4.getJoueurCourant();
        Joueur[] joueurs = puissance4.getJoueurs();

        Joueur gagnant = (joueurs[0] == joueurCourant)
                ? joueurs[1]
                : joueurs[0];

        puissance4.setParAbandon(true);
        puissance4.setGagnant(gagnant);
        puissance4.setPartieFinie(true);
    }

    /**
     * Getter utile pour les tests
     */
    public Partie getPartie() {
        return puissance4;
    }
}
