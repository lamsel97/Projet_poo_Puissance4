package business;

/**
 * Classe de configuration pour le jeu Puissance 4.
 * @Sellam Aiman
 */
public class Config {
    
    /**
     * Nombre de lignes de la grille de jeu.
     */
    public final static int NB_LIGNES = 6;
    
    /**
     * Nombre de colonnes de la grille de jeu.
     */
    public final static int NB_COLONNES = 7;
    
    /**
     * Tableau représentant le plateau de jeu.
     */
    int[][] plateau = new int[Config.NB_LIGNES][Config.NB_COLONNES];

}