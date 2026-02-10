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
        if (joueurs == null) {
            return null;
        }
        return joueurs.clone();
    }


    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }

    public Joueur getGagnant() {
        if (partieFinie && !parAbandon && gagnant == null) {
            if (joueurs != null && joueurs.length > 0 && joueurs[0] != null) {
                return joueurs[0];
            }
        }
        return gagnant;
    }

    public boolean isParAbandon() {
        return parAbandon;
    }


    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    public void setGagnant(Joueur gagnant) {
        if (gagnant != null && gagnant != joueurs[0] && gagnant != joueurs[1]) {
            return;
        }

        this.gagnant = gagnant;
    }

    public void setParAbandon(boolean parAbandon) {
        this.parAbandon = parAbandon;
    }


    public void changerJoueurCourant() {

        if (joueurs == null || joueurs.length < 2 || joueurs[0] == null || joueurs[1] == null) {
            return; // on ne touche pas au joueurCourant
        }

        if (joueurCourant == null) {
            joueurCourant = joueurs[0];
            return;
        }

        joueurCourant = (joueurCourant == joueurs[0]) ? joueurs[1] : joueurs[0];
    }

}
