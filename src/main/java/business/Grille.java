package business;


public class Grille {

    public static final int NB_LIGNES = Config.NB_LIGNES;
    public static final int NB_COLONNES = Config.NB_COLONNES;

    private Jeton[][] plateauJetons;

    public Grille() {
        plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    public Grille(Jeton[][] plateauJetons) {
        if (plateauJetons == null) {
            throw new IllegalArgumentException("Le plateau ne peut pas être nul");
        }
        this.plateauJetons = plateauJetons;
    }

    public Jeton getJetons(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La position est invalide");
        }

        int ligne = position.getLigne();
        int colonne = position.getColonne();

        if (ligne < 0 || ligne >= NB_LIGNES || colonne < 0 || colonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La position donnée est invalide");
        }

        return plateauJetons[ligne][colonne];
    }

    public int insererJeton(Jeton jeton, int numColonne) throws Puissance4Exception {
        if (jeton == null) {
            throw new IllegalArgumentException("Le jeton ne peut pas être nul");
        }

        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide");
        }

        if (plateauJetons[NB_LIGNES - 1][numColonne] != null) {
            throw new Puissance4Exception("La colonne est remplie");
        }

        int ligne = 0;
        while (ligne < NB_LIGNES && plateauJetons[ligne][numColonne] != null) {
            ligne++;
        }

        plateauJetons[ligne][numColonne] = jeton;
        return ligne;
    }

    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide");
        }
        return plateauJetons[NB_LIGNES - 1][numColonne] != null;
    }

    public boolean isFullGrille() {
        for (int i = 0; i < NB_LIGNES; i++) {
            for (int j = 0; j < NB_COLONNES; j++) {
                if (plateauJetons[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean alignementRealise(Position position) {
        Jeton jeton = getJetons(position);
        if (jeton == null) {
            return false;
        }

        Couleur couleur = jeton.getCouleur();

        return alignementHorizontal(position, couleur) >= 4
                || alignementVertical(position, couleur) >= 4
                || alignementDiagonal1(position, couleur) >= 4
                || alignementDiagonal2(position, couleur) >= 4;
    }

    private int alignementHorizontal(Position position, Couleur couleur) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        int c = colonne - 1;
        while (c >= 0 && plateauJetons[ligne][c] != null
                && plateauJetons[ligne][c].getCouleur() == couleur) {
            nbAlignes++;
            c--;
        }

        c = colonne + 1;
        while (c < NB_COLONNES && plateauJetons[ligne][c] != null
                && plateauJetons[ligne][c].getCouleur() == couleur) {
            nbAlignes++;
            c++;
        }

        return nbAlignes;
    }

    private int alignementVertical(Position position, Couleur couleur) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        int l = ligne - 1;
        while (l >= 0 && plateauJetons[l][colonne] != null
                && plateauJetons[l][colonne].getCouleur() == couleur) {
            nbAlignes++;
            l--;
        }

        l = ligne + 1;
        while (l < NB_LIGNES && plateauJetons[l][colonne] != null
                && plateauJetons[l][colonne].getCouleur() == couleur) {
            nbAlignes++;
            l++;
        }

        return nbAlignes;
    }

    private int alignementDiagonal1(Position position, Couleur couleur) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        int l = ligne - 1;
        int c = colonne - 1;
        while (l >= 0 && c >= 0 && plateauJetons[l][c] != null
                && plateauJetons[l][c].getCouleur() == couleur) {
            nbAlignes++;
            l--;
            c--;
        }

        l = ligne + 1;
        c = colonne + 1;
        while (l < NB_LIGNES && c < NB_COLONNES && plateauJetons[l][c] != null
                && plateauJetons[l][c].getCouleur() == couleur) {
            nbAlignes++;
            l++;
            c++;
        }

        return nbAlignes;
    }

    private int alignementDiagonal2(Position position, Couleur couleur) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        int nbAlignes = 1;

        int l = ligne + 1;
        int c = colonne - 1;
        while (l < NB_LIGNES && c >= 0 && plateauJetons[l][c] != null
                && plateauJetons[l][c].getCouleur() == couleur) {
            nbAlignes++;
            l++;
            c--;
        }

        l = ligne - 1;
        c = colonne + 1;
        while (l >= 0 && c < NB_COLONNES && plateauJetons[l][c] != null
                && plateauJetons[l][c].getCouleur() == couleur) {
            nbAlignes++;
            l--;
            c++;
        }

        return nbAlignes;
    }
}
