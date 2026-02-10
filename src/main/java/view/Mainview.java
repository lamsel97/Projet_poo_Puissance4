package view;

import business.*;
import java.util.Scanner;

public class Mainview {

    private Puissance4 puissance4;
    private Scanner scanner;

    public Mainview() {
        this.puissance4 = new Puissance4();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Mainview view = new Mainview();
        view.run();
    }

    public void run() {
        while (!puissance4.gameIsOver()) {
            display();
            demanderAction();
        }

        afficherResultatFinal();
    }

    /**
     * Affiche la grille et les informations de la partie
     */
    public void display() {
        Grille grille = puissance4.getPartie().getGrille();

        System.out.println("\nGrille de jeu :");

        for (int ligne = Config.NB_LIGNES - 1; ligne >= 0; ligne--) {
            System.out.print("| ");
            for (int col = 0; col < Config.NB_COLONNES; col++) {
                Jeton jeton = grille.getJetons(new Position(ligne, col));
                if (jeton == null) {
                    System.out.print(". ");
                } else if (jeton.getCouleur() == Couleur.JAUNE) {
                    System.out.print("J ");
                } else {
                    System.out.print("R ");
                }
            }
            System.out.println("|");
        }

        System.out.print("  ");
        for (int i = 0; i < Config.NB_COLONNES; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Joueur courant : "
                + puissance4.getPartie().getJoueurCourant().getNom());
        System.out.println("Choisissez une colonne (0-6) ou -1 pour abandonner");
    }

    private void demanderAction() {
        try {
            int choix = scanner.nextInt();

            if (choix == -1) {
                puissance4.abandonner();
            } else {
                puissance4.jouer(choix);
            }
        } catch (Puissance4Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Entrée invalide.");
            scanner.nextLine();
        }
    }

    private void afficherResultatFinal() {
        display();

        if (puissance4.getPartie().isParAbandon()) {
            System.out.println("La partie a été abandonnée.");
        }

        Joueur gagnant = puissance4.getPartie().getGagnant();
        if (gagnant != null) {
            System.out.println("Le gagnant est : " + gagnant.getNom());
        } else {
            System.out.println("Match nul !");
        }
    }
}
