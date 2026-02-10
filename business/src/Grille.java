public class Grille {
    public static final int NB_LIGNES = Config.NB_LIGNES;
    public static final int NB_COLONNES = Config.NB_COLONNES;
    private Jeton[][] plateauJetons;

    public Grille() {
    }

    public Grille(Jeton[][] plateauJetons) {
        this.plateauJetons = plateauJetons;
    }

    public Jeton getJetons(Position position) {
        if (plateauJetons[position.getLigne()][position.getColonne()] == null) {
            return null;
        } else if (position.getLigne() < 0 || position.getLigne() > NB_LIGNES
                || position.getColonne() < 0 || position.getColonne() > NB_COLONNES) {
            throw new IllegalArgumentException("La position donné est invalide");
        }
        return plateauJetons[position.getLigne()][position.getColonne()];
    }


    public int insererJeton(Jeton jeton, int numColonne) throws Puissance4Exception {
        int ligne = 0;
        if (numColonne < 0 || numColonne > NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide");
        } else if (plateauJetons[6][numColonne] != null) {
            Puissance4Exception erreur = new Puissance4Exception("La colonne est remplie");
        }
        while (plateauJetons[ligne][numColonne] != null) {
            ligne++;
        }
        plateauJetons[ligne][numColonne] = jeton;
        return ligne;
    }

    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne > NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide");
        } else if (plateauJetons[6][numColonne] != null) {
            return true;
        } else {
            return false;
        }
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
        if (position == null) {
            throw new IllegalArgumentException("La position est invalide");
        }
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
        int ligne = position.ligne;
        int colonne = position.colonne;
        int nbAlignes = 1;

        // vers la gauche
        int c = colonne - 1;
        while (c >= 0 && grille[ligne][c] == couleur) {
            nbAlignes++;
            c--;
        }

        // vers la droite
        c = colonne + 1;
        while (c < NB_COLONNES && grille[ligne][c] == couleur) {
            nbAlignes++;
            c++;
        }

        return nbAlignes;
    }

    private int alignementVertical(Position position, Couleur couleur) {
        int ligne = position.ligne;
        int colonne = position.colonne;
        int nbAlignes = 1;

        // vers le bas
        int l = ligne - 1;
        while (l >= 0 && grille[l][colonne] == couleur) {
            nbAlignes++;
            l--;
        }

        // vers le haut
        l = ligne + 1;
        while (l < NB_LIGNES && grille[l][colonne] == couleur) {
            nbAlignes++;
            l++;
        }

        return nbAlignes;
    }

    private int alignementDiagonal1(Position position, Couleur couleur) {
        int ligne = position.ligne;
        int colonne = position.colonne;
        int nbAlignes = 1;

        // vers le bas-gauche
        int l = ligne - 1;
        int c = colonne - 1;
        while (l >= 0 && c >= 0 && grille[l][c] == couleur) {
            nbAlignes++;
            l--;
            c--;
        }

        // vers le haut-droite
        l = ligne + 1;
        c = colonne + 1;
        while (l < NB_LIGNES && c < NB_COLONNES && grille[l][c] == couleur) {
            nbAlignes++;
            l++;
            c++;
        }

        return nbAlignes;
    }

    private int alignementDiagonal2(Position position, Couleur couleur) {
        int ligne = position.ligne;
        int colonne = position.colonne;
        int nbAlignes = 1;

        // vers le haut-gauche
        int l = ligne + 1;
        int c = colonne - 1;
        while (l < NB_LIGNES && c >= 0 && grille[l][c] == couleur) {
            nbAlignes++;
            l++;
            c--;
        }

        // vers le bas-droite
        l = ligne - 1;
        c = colonne + 1;
        while (l >= 0 && c < NB_COLONNES && grille[l][c] == couleur) {
            nbAlignes++;
            l--;
            c++;
        }

        return nbAlignes;
    }


}