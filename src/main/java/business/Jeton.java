package business;

/**
 * Représente un jeton du jeu Puissance 4.
 * @Sellam Aiman
 */
public class Jeton {

    /**
     * La couleur du jeton, correspondant au joueur.
     */
    public Couleur couleur;

    /**
     * Crée un jeton avec la couleur spécifiée.
     *
     * @param couleur La couleur du jeton, qui peut pas etre null.
     * @throws IllegalArgumentException si la couleur est null.
     */
    public Jeton(Couleur couleur) {
        if (couleur == null) {
            throw new IllegalArgumentException("La couleur du jeton ne peut pas être null");
        }
        this.couleur = couleur;
    }

    /**
     * Retourne la couleur de ce jeton.
     *
     * @return La couleur du jeton.
     */
    public Couleur getCouleur() {
        return couleur;
    }
}
