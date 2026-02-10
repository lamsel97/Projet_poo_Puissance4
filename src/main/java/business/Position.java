package business;

public class Position {
    private int ligne;
    private int colonne;

    public Position(int ligne, int colonne) {
        if (ligne < 0 || colonne < 0) {
            throw new IllegalArgumentException("Les lignes ou colonnes ne peuvent pas être en dessous de 0");
        }
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {

        return ligne;
    }

    public int getColonne() {

        return colonne;
    }

    @Override
    public String toString() {
        return "Position{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return ligne == position.ligne && colonne == position.colonne;
    }


}
