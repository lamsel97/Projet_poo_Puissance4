package business;

/**
 * Représente une position dans la grille du jeu Puissance 4.
 * 
 * @Sellam Aiman
 */
public class Position {

    /** L'indice de la ligne 
    */
    private int ligne;

    /** L'indice de la colonne*/
    private int colonne;

    /**
     * Crée une position avec la ligne et la colonne spécifiées.
     *
     * @param ligne l'indice de la ligne 
     * @param colonne l'indice de la colonne 
     * @throws IllegalArgumentException si ligne ou colonne < 0
     */
    public Position(int ligne, int colonne) {
        if (ligne < 0 || colonne < 0) {
            throw new IllegalArgumentException("Les lignes ou colonnes ne peuvent pas être en dessous de 0");
        }
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Retourne l'indice de la ligne.
     *
     * @return l'indice de la ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Retourne l'indice de la colonne.
     *
     * @return l'indice de la colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Retourne une représentation textuelle de la position.
     *
     * @return une chaîne décrivant la ligne et la colonne
     */
    @Override
    public String toString() {
        return "Position{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                '}';
    }

    /**
     * Compare cette position à un autre objet pour vérifier l'égalité.
     *
     * @param o l'objet à comparer
     * @return true si les deux positions sont égales, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return ligne == position.ligne && colonne == position.colonne;
    }

}
