package business;

import java.util.Random;

public class Partie {

    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private Joueur gagnant;
    private boolean parAbandon;

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

    public Grille getGrille() {
        return grille;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }

    public Joueur getGagnant() {
        return gagnant;
    }

    public boolean isParAbandon() {
        return parAbandon;
    }


    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }


    public void changerJoueurCourant() {
        if (joueurCourant == joueurs[0]) {
            joueurCourant = joueurs[1];
        } else {
            joueurCourant = joueurs[0];
        }
    }
}
