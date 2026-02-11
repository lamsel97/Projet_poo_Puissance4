package business;

/**
 * Représente un joueur du jeu Puissance 4.
 * @Sellam Aiman
 */
public final class Joueur {

    /**
     * La couleur du joueur, utilisée comme identifiant.
     */
    private final Couleur nom;

    /**
     * Crée un joueur avec la couleur spécifiée.
     *
     * @param nom La couleur du joueur, ne peut pas être {@code null}.
     * @throws IllegalArgumentException si la couleur est {@code null}.
     */
    public Joueur(Couleur nom) {
        if (nom == null) {
            throw new IllegalArgumentException("La couleur du joueur ne peut pas être nulle");
        }
        this.nom = nom;
    }

    /**
     * Retourne la couleur de ce joueur.
     *
     * @return La couleur du joueur.
     */
    public Couleur getNom() {
        return nom;
    }
}
