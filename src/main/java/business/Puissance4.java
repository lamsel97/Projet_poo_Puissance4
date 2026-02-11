package business;

/**
 * Classe principale du jeu Puissance 4.
 * 
 * @Sellam Aiman
 */
public class Puissance4 {

    /** La partie en cours */
    private Partie puissance4;

    /**
     * Crée une nouvelle partie de Puissance 4.
     */
    public Puissance4() {
        this.puissance4 = new Partie();
    }

    /**
     * Crée une instance de Puissance4 en utilisant une partie existante.
     *
     * @param puissance4 la partie à gérer
     */
    public Puissance4(Partie puissance4) {
        this.puissance4 = puissance4;
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return true si la partie est finie, sinon false
     */
    public boolean gameIsOver() {
        return puissance4.isPartieFinie();
    }

    /**
     * Permet au joueur courant de jouer un jeton dans la colonne spécifiée.
     *
     * @param numColonne le numéro de la colonne (0 à NB_COLONNES - 1)
     * @throws Puissance4Exception si insertion impossible (colonne pleine)
     */
    public void jouer(int numColonne) throws Puissance4Exception {
        if (puissance4.isPartieFinie()) {
            return;
        }

        Grille grille = puissance4.getGrille();
        Joueur joueurCourant = puissance4.getJoueurCourant();

        // Création du jeton du joueur courant
        Jeton jeton = new Jeton(joueurCourant.getNom());

        // Insertion du jeton dans la grille
        int ligne = grille.insererJeton(jeton, numColonne);
        Position position = new Position(ligne, numColonne);

        // Vérification de victoire
        if (grille.alignementRealise(position)) {
            puissance4.setGagnant(joueurCourant);
            puissance4.setPartieFinie(true);
            return;
        }

        // Vérification de match nul (grille pleine)
        if (grille.isFullGrille()) {
            puissance4.setGagnant(joueurCourant);
            puissance4.setPartieFinie(true);
            return;
        }

        // Changement du joueur courant
        puissance4.changerJoueurCourant();
    }

    /**
     * Permet au joueur courant d’abandonner la partie.
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
     * Retourne l’objet Partie.
     *
     * @return la partie en cours
     */
    public Partie getPartie() {
        return puissance4;
    }
}
