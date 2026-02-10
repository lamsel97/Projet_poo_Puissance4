package business;

public final class Joueur {
    private final Couleur nom;

    public Joueur(Couleur nom) {
        if (nom == null) {
            throw new IllegalArgumentException("La couleur du joueur ne peut pas être nulle");
        }
        this.nom = nom;
    }

    public Couleur getNom() {
        return nom;
    }
}