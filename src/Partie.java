public class Partie {
    private Grille grille;
    private Joueur[] joueurs;
    private Joueur joueurCourant;
    private boolean partieFinie;
    private Joueur gagnant;
    private boolean parAbandon;

    public Partie(){
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
    }

    public Joueur getGagnant() {
        return gagnant;
    }
}
