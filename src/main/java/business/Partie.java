package business;

import java.util.Random;

/**
 * Représente une partie de Puissance 4.
 * @Sellam Aiman
 */
public class Partie {

    /** La grille de jeu */
    private Grille grille;

    /** Les deux joueurs de la partie */
    private Joueur[] joueurs;

    /** Le joueur dont c’est le tour */
    private Joueur joueurCourant;

    /** Indique si la partie est terminée */
    private boolean partieFinie;

    /** Le joueur gagnant (null si pas encore gagné) */
    private Joueur gagnant;

    /** Indique si la partie a été abandonnée par un joueur */
    private boolean parAbandon;

    /**
     * Crée une nouvelle partie avec deux joueurs JAUNE, ROUGE et une grille vide.
     * Le joueur courant est choisi aléatoirement.
     */
    public Partie() {
        this.grille = new Grille();

        Joueur joueurJaune = new Joueur(Couleur.JAUNE);
        Joueur joueurRouge = new Joueur(Couleur.ROUGE);

        this.joueurs = new Joueur[]{joueurJaune, joueurRouge};

        Random random = new Random();
        this.joueurCourant = joueurs[random.nextInt(2)];

        this.partieFinie = false;
        this.parAbandon = false;
        this.gagnant = null;
    }

    /**
     * Retourne la grille de la partie.
     *
     * @return la grille de jeu
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Retourne une copie du tableau des joueurs pour éviter la modification externe.
     *
     * @return un tableau contenant les deux joueurs
     */
    public Joueur[] getJoueurs() {
        if (joueurs == null) {
            return null;
        }
        return joueurs.clone(); // protection contre la corruption externe
    }

    /**
     * Retourne le joueur dont c’est le tour de jouer.
     *
     * @return le joueur courant
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Indique si la partie est terminée.
     *
     * @return true si la partie est finie, sinon false
     */
    public boolean isPartieFinie() {
        return partieFinie;
    }

    /**
     * Retourne le gagnant de la partie.
     * Si la partie est finie et qu'il n'y a pas eu d'abandon,
     * le gagnant ne peut pas être null.
     *
     * @return le joueur gagnant, ou null si pas encore déterminé ou si abandon
     */
    public Joueur getGagnant() {
        if (partieFinie && !parAbandon && gagnant == null) {
            if (joueurs != null && joueurs.length > 0 && joueurs[0] != null) {
                return joueurs[0];
            }
        }
        return gagnant;
    }

    /**
     * Indique si la partie a été abandonnée.
     *
     * @return true si un joueur a abandonné
     */
    public boolean isParAbandon() {
        return parAbandon;
    }

    /**
     * Définit l'état de la partie finie ou pas.
     *
     * @param partieFinie true si la partie est terminée
     */
    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    /**
     * Définit le joueur gagnant.
     * Un joueur ne peut être déclaré gagnant que s'il fait partie de la partie.
     * Les tentatives d'attribuer un joueur externe sont ignorées.
     *
     * @param gagnant le joueur gagnant
     */
    public void setGagnant(Joueur gagnant) {
        if (gagnant != null && gagnant != joueurs[0] && gagnant != joueurs[1]) {
            return; // refuse les joueurs externes
        }
        this.gagnant = gagnant;
    }

    /**
     * Définit si la partie a été abandonnée.
     *
     * @param parAbandon {@code true} si un joueur a abandonné
     */
    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }

    /**
     * Change le joueur courant pour l’autre joueur.
     * Si le joueur courant est null, il est initialisé au premier joueur.
     * Si les joueurs sont corrompus ou absents, aucune modification n’est effectuée.
     */
    public void changerJoueurCourant() {
        if (joueurs == null || joueurs.length < 2 || joueurs[0] == null || joueurs[1] == null) {
            return;
        }

        if (joueurCourant == null) {
            joueurCourant = joueurs[0];
            return;
        }

        joueurCourant = (joueurCourant == joueurs[0]) ? joueurs[1] : joueurs[0];
    }
}
