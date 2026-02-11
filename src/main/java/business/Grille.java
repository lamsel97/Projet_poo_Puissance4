package business;

/**
 * Représente la grille de jeu pour le jeu de Puissance 4.
 * @Sellam Aiman
 */
public class Grille {

    /**
     * Nombre de lignes de la grille.
     */
    public static final int NB_LIGNES = Config.NB_LIGNES;
    
    /**
     * Nombre de colonnes de la grille.
     */
    public static final int NB_COLONNES = Config.NB_COLONNES;

    /**
     * Tableau représentant le plateau de jeu.
     */
    private Jeton[][] plateauJetons;

    /**
     * Constructeur qui initialise une grille vide.
     */
    public Grille() {
        plateauJetons = new Jeton[NB_LIGNES][NB_COLONNES];
    }

    /**
     * Constructeur qui initialise la grille avec un plateau de jetons existant.
     * 
     * @param plateauJetons le tableau de jetons à utiliser pour initialiser la grille
     * @throws IllegalArgumentException si le plateau fourni est null
     */
    public Grille(Jeton[][] plateauJetons) {
        if (plateauJetons == null) {
            throw new IllegalArgumentException("Le plateau ne peut pas être nul");
        }
        this.plateauJetons = plateauJetons;
    }

    /**
     * Récupère le jeton à une position donnée dans la grille.
     * 
     * @param position la position du jeton à récupérer
     * @return le jeton à la position spécifiée, ou null si la case est vide
     * @throws IllegalArgumentException si la position est null ou invalide
     */
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

    /**
     * Insère un jeton dans une colonne spécifiée.
     * 
     * @param jeton le jeton à insérer
     * @param numColonne le numéro de la colonne où insérer le jeton
     * @return la ligne où le jeton a été inséré
     * @throws IllegalArgumentException si le jeton est null ou si la colonne est invalide
     * @throws Puissance4Exception si la colonne est déjà pleine
     */
    public int insererJeton(Jeton jeton, int numColonne) throws Puissance4Exception {
        if (jeton == null) {
            throw new IllegalArgumentException("Le jeton ne peut pas être nul");
        }

        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide");
        }

        int ligne = NB_LIGNES - 1;
        while (ligne >= 0 && plateauJetons[ligne][numColonne] != null) {
            ligne--;
        }

        if (ligne < 0) {
            throw new Puissance4Exception("La colonne est remplie");
        }

        plateauJetons[ligne][numColonne] = jeton;
        return ligne;
    }

    /**
     * Vérifie si une colonne spécifique est pleine.
     * 
     * @param numColonne le numéro de la colonne à vérifier
     * @return true si la colonne est pleine, false sinon
     * @throws IllegalArgumentException si le numéro de colonne est invalide
     */
    public boolean isFullColonne(int numColonne) {
        if (numColonne < 0 || numColonne >= NB_COLONNES) {
            throw new IllegalArgumentException("La colonne est invalide");
        }
        return plateauJetons[NB_LIGNES - 1][numColonne] != null;
    }

    /**
     * Vérifie si la grille est complètement remplie.
     * 
     * @return true si toutes les cases de la grille sont occupées, false sinon
     */
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

    /**
     * Vérifie si un alignement d'au moins 4 jetons de même couleur est réalisé
     * à partir d'une position donnée.
     * 
     * @param position la position à partir de laquelle vérifier les alignements
     * @return true si un alignement d'au moins 4 jetons est trouvé, false sinon
     */
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

    /**
     * Calcule le nombre de jetons alignés horizontalement de même couleur.
     * 
     * @param position la position de départ
     * @param couleur la couleur des jetons à vérifier
     * @return le nombre total de jetons alignés horizontalement
     */
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

    /**
     * Calcule le nombre de jetons alignés verticalement de même couleur.
     * 
     * @param position la position de départ
     * @param couleur la couleur des jetons à vérifier
     * @return le nombre total de jetons alignés verticalement
     */
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

    /**
     * Calcule le nombre de jetons alignés en diagonale (du haut gauche vers le bas droite).
     * 
     * @param position la position de départ
     * @param couleur la couleur des jetons à vérifier
     * @return le nombre total de jetons alignés en diagonale
     */
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

    /**
     * Calcule le nombre de jetons alignés en diagonale (du bas gauche vers le haut droite).
     * 
     * @param position la position de départ
     * @param couleur la couleur des jetons à vérifier
     * @return le nombre total de jetons alignés en diagonale
     */
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